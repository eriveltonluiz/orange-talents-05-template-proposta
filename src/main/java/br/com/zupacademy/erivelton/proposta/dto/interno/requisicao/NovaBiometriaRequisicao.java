package br.com.zupacademy.erivelton.proposta.dto.interno.requisicao;

import javax.validation.constraints.NotBlank;

import br.com.zupacademy.erivelton.proposta.validacao.anotacao.Base64;

public class NovaBiometriaRequisicao {

	@NotBlank
	@Base64
	private String fingerprint;

	public String getFingerprint() {
		return fingerprint;
	}

}
