package br.com.zupacademy.erivelton.proposta.apiexterna;

import java.net.URI;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import br.com.zupacademy.erivelton.proposta.dto.externo.requisicao.RequisicaoBloqueio;
import br.com.zupacademy.erivelton.proposta.dto.externo.resposta.CartaoGeradorDTO;
import br.com.zupacademy.erivelton.proposta.dto.externo.resposta.RespostaBloqueioDTO;
import br.com.zupacademy.erivelton.proposta.entidade.Cartao;
import br.com.zupacademy.erivelton.proposta.entidade.Proposta;
import br.com.zupacademy.erivelton.proposta.enums.EstadoCartao;
import br.com.zupacademy.erivelton.proposta.enums.StatusFinanceiro;
import br.com.zupacademy.erivelton.proposta.repositorio.CartaoRepositorio;
import br.com.zupacademy.erivelton.proposta.repositorio.PropostaRepositorio;

@Component
public class APICartoes {

	private final Logger logger = LoggerFactory.getLogger(APICartoes.class);

	@Value("${cartoes.proposta.host}")
	private String urlAPIExternaCartoesDaProposta;

	@Value("${cartoes.host}")
	private String urlAPIExternaCartoes;

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private CartaoRepositorio cartaoRepositorio;

	@Autowired
	private PropostaRepositorio propostaRepositorio;

	public EstadoCartao notificarBanco(String idCartao) {
		String uriExternaBloqueioCartao = urlAPIExternaCartoes + "/" + idCartao + "/bloqueios";
		RespostaBloqueioDTO estadoCartao = restTemplate.postForObject(URI.create(uriExternaBloqueioCartao),
				new RequisicaoBloqueio("API proposta"), RespostaBloqueioDTO.class);

		return Enum.valueOf(EstadoCartao.class, estadoCartao.getResultado());
	}

	@Scheduled(fixedDelayString = "${delay.execucao}")
	@Transactional
	public void associarCartaoComProposta() {
		List<Proposta> propostasAceitas = propostaRepositorio.findAllByEstadoAndCartaoIsNull(StatusFinanceiro.ELEGIVEL);

		propostasAceitas.forEach(proposta -> {
			try {
				CartaoGeradorDTO cartaoRetornado = restTemplate
						.getForObject(urlAPIExternaCartoesDaProposta + proposta.getId(), CartaoGeradorDTO.class);

				Cartao cartao = cartaoRetornado.paraEntidadeCartao();
				cartaoRepositorio.save(cartao);

				proposta.setCartao(cartao);
				propostaRepositorio.save(proposta);
			} catch (HttpStatusCodeException e) {
				logger.warn("API externa não localizou a proposta elegível");
			}
		});
	}
}
