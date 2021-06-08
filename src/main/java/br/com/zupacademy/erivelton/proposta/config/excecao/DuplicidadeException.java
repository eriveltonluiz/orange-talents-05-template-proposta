package br.com.zupacademy.erivelton.proposta.config.excecao;

public class DuplicidadeException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public DuplicidadeException(String campo) {
		super(campo);
	}
}
