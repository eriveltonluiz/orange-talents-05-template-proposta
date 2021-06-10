package br.com.zupacademy.erivelton.proposta.dto.externo.resposta;

import br.com.zupacademy.erivelton.proposta.enums.ResultadoSolicitacao;
import br.com.zupacademy.erivelton.proposta.enums.StatusFinanceiro;

public class ResultadoSolicitacaoDTO {

	private String documento;

	private String nome;
	
	private ResultadoSolicitacao resultadoSolicitacao;
	
	private String idProposta;
	
	public String getDocumento() {
		return documento;
	}
	
	public String getNome() {
		return nome;
	}
	
	public ResultadoSolicitacao getResultadoSolicitacao() {
		return resultadoSolicitacao;
	}
	
	public String getIdProposta() {
		return idProposta;
	}
	
	public StatusFinanceiro getEnumStatus() {
		return this.resultadoSolicitacao.converterEnum();
	}
	

	@Override
	public String toString() {
		return "ResultadoSolicitacaoDTO [documento=" + documento + ", nome=" + nome + ", resultadoSolicitacao="
				+ resultadoSolicitacao + ", idProposta=" + idProposta + "]";
	}
	
}
