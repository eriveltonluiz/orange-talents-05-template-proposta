package br.com.zupacademy.erivelton.proposta.dto.interno.resposta;

import br.com.zupacademy.erivelton.proposta.enums.StatusFinanceiro;

public class DetalhesPropostaDTO {

	private StatusFinanceiro estadoProposta;
	
	public DetalhesPropostaDTO(StatusFinanceiro estadoProposta) {
		this.estadoProposta = estadoProposta;
	}

	public StatusFinanceiro getEstadoProposta() {
		return estadoProposta;
	}
}
