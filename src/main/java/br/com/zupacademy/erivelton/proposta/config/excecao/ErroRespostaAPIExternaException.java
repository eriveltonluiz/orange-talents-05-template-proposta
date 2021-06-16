package br.com.zupacademy.erivelton.proposta.config.excecao;

public class ErroRespostaAPIExternaException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public ErroRespostaAPIExternaException(String msg) {
		super(msg);
	}
}
