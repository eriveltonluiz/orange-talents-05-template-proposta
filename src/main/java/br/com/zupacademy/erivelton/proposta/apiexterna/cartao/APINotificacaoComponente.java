package br.com.zupacademy.erivelton.proposta.apiexterna.cartao;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import br.com.zupacademy.erivelton.proposta.dto.externo.requisicao.DadosAvisoViagemRequisicao;
import br.com.zupacademy.erivelton.proposta.dto.externo.requisicao.RequisicaoBloqueio;
import br.com.zupacademy.erivelton.proposta.dto.externo.resposta.RespostaBloqueioDTO;
import br.com.zupacademy.erivelton.proposta.dto.interno.requisicao.NovoAvisoViagemRequisicao;
import br.com.zupacademy.erivelton.proposta.enums.EstadoCartao;

@Component
public class APINotificacaoComponente {

	@Value("${cartoes.host}")
	private String urlAPIExternaCartoes;

	@Autowired
	private RestTemplate restTemplate;
	
	public EstadoCartao notificarBanco(String idCartao) {
		String uriExternaBloqueioCartao = urlAPIExternaCartoes + "/" + idCartao + "/bloqueios";
		RespostaBloqueioDTO estadoCartao = restTemplate.postForObject(URI.create(uriExternaBloqueioCartao),
				new RequisicaoBloqueio("API proposta"), RespostaBloqueioDTO.class);

		return Enum.valueOf(EstadoCartao.class, estadoCartao.getResultado());
	}

	public void notificarAvisoAoBanco(String idCartao, NovoAvisoViagemRequisicao requisicao) {
		String uriExternaAvisoViagem = urlAPIExternaCartoes + "/" + idCartao + "/avisos";
		DadosAvisoViagemRequisicao dadosAviso = new DadosAvisoViagemRequisicao(requisicao.getDestino(), requisicao.converterLocalDateEmString());
		
		restTemplate.postForObject(URI.create(uriExternaAvisoViagem), dadosAviso , String.class);
	}
}
