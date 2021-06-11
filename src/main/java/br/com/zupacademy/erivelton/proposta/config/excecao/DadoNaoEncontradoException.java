package br.com.zupacademy.erivelton.proposta.config.excecao;

public class DadoNaoEncontradoException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public DadoNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
}
