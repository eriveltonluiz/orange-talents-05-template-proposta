package br.com.zupacademy.erivelton.proposta.dto.externo.requisicao;

public class RequisicaoBloqueio {
	
	private String sistemaResponsavel;
	
	public RequisicaoBloqueio(String sistemaResponsavel) {
		this.sistemaResponsavel = sistemaResponsavel;
	}

	public String getSistemaResponsavel() {
		return sistemaResponsavel;
	}
	
}
