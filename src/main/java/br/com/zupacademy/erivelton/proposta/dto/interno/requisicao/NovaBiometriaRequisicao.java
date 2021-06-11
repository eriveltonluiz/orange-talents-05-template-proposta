package br.com.zupacademy.erivelton.proposta.dto.interno.requisicao;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class NovaBiometriaRequisicao {

	@NotBlank
	private String fingerprint;

	@NotNull
	private String identificadorCartao;

	public String getFingerprint() {
		return fingerprint;
	}
	
	public String getIdentificadorCartao() {
		return identificadorCartao;
	}
}
