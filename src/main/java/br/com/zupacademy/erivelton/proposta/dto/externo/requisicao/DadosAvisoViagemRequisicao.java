package br.com.zupacademy.erivelton.proposta.dto.externo.requisicao;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class DadosAvisoViagemRequisicao {

	@NotBlank
	private String destino;
	
	@NotNull
	@Future
	private String validoAte;

	public DadosAvisoViagemRequisicao(String destino, String validoAte) {
		this.destino = destino;
		this.validoAte = validoAte;
	}

	public String getDestino() {
		return destino;
	}
	
	public String getValidoAte() {
		return validoAte;
	}
}
