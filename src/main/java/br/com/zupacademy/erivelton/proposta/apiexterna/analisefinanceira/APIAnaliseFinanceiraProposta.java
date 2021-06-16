package br.com.zupacademy.erivelton.proposta.apiexterna.analisefinanceira;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException.UnprocessableEntity;
import org.springframework.web.client.RestTemplate;

import br.com.zupacademy.erivelton.proposta.dto.externo.requisicao.DadosPropostaRequisicao;
import br.com.zupacademy.erivelton.proposta.dto.externo.resposta.ResultadoSolicitacaoDTO;
import br.com.zupacademy.erivelton.proposta.enums.ResultadoSolicitacao;

@Component
public class APIAnaliseFinanceiraProposta {

	@Autowired
	private RestTemplate restTemplate;

	@Value("${solicitacao.host}")
	private String urlAPIExternaSolicitacao;

	public ResultadoSolicitacao postSolicitacaoAnalise(DadosPropostaRequisicao dadosProposta) {
		try {
			ResultadoSolicitacaoDTO dto = restTemplate.postForObject(URI.create(urlAPIExternaSolicitacao),
					dadosProposta, ResultadoSolicitacaoDTO.class);
			
			return dto.getResultadoSolicitacao();
		} catch (UnprocessableEntity e) {
			return ResultadoSolicitacao.COM_RESTRICAO;
		}
	}
}
