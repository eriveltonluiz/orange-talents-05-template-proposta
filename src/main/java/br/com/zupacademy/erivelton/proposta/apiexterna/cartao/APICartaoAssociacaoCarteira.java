package br.com.zupacademy.erivelton.proposta.apiexterna.cartao;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import br.com.zupacademy.erivelton.proposta.dto.externo.requisicao.DadosCarteiraDigitalRequisicao;
import br.com.zupacademy.erivelton.proposta.dto.externo.resposta.AssociacaoCarteiraCartaoDTO;
import br.com.zupacademy.erivelton.proposta.dto.interno.requisicao.NovaCarteiraDigitalRequisicao;

@Component
public class APICartaoAssociacaoCarteira {

	@Value("${cartoes.host}")
	private String uriExternaCartao;

	@Autowired
	private RestTemplate template;

	public AssociacaoCarteiraCartaoDTO associarCartaoComCarteira(String idCartao, NovaCarteiraDigitalRequisicao requisicao) {
		String uriCartaoComCarteira = uriExternaCartao + "/" + idCartao + "/carteiras";
		String carteira = requisicao.carteiraToString();

		return template.postForObject(URI.create(uriCartaoComCarteira),
				new DadosCarteiraDigitalRequisicao(requisicao.getEmail(), carteira), AssociacaoCarteiraCartaoDTO.class);
	}
}
