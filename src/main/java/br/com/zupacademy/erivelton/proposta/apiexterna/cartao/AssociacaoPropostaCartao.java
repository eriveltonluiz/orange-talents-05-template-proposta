package br.com.zupacademy.erivelton.proposta.apiexterna.cartao;

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

import br.com.zupacademy.erivelton.proposta.dto.externo.resposta.CartaoGeradorDTO;
import br.com.zupacademy.erivelton.proposta.entidade.Cartao;
import br.com.zupacademy.erivelton.proposta.entidade.Proposta;
import br.com.zupacademy.erivelton.proposta.enums.StatusFinanceiro;
import br.com.zupacademy.erivelton.proposta.repositorio.CartaoRepositorio;
import br.com.zupacademy.erivelton.proposta.repositorio.PropostaRepositorio;

@Component
public class AssociacaoPropostaCartao {

	private final Logger logger = LoggerFactory.getLogger(AssociacaoPropostaCartao.class);

	@Value("${cartoes.proposta.host}")
	private String urlAPIExternaCartoesDaProposta;

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private CartaoRepositorio cartaoRepositorio;

	@Autowired
	private PropostaRepositorio propostaRepositorio;

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
