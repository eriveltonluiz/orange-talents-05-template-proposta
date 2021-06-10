package br.com.zupacademy.erivelton.proposta.dto.interno.resposta;

import br.com.zupacademy.erivelton.proposta.entidade.Proposta;

public class DetalhesPropostaDTO {

	private String nome;

	public DetalhesPropostaDTO(Proposta proposta) {
		this.nome = proposta.getNome();
	}

	public String getNome() {
		return nome;
	}
}
