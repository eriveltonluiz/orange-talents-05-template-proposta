package br.com.zupacademy.erivelton.proposta.config.tratamentovalidacao;

import java.time.OffsetDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public class DetalhesErroDTO {
	
	private String campo;
	private String mensagem;
	
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm.ss")
	private OffsetDateTime momento;

	public DetalhesErroDTO(String campo, String mensagem, OffsetDateTime momento) {
		this.campo = campo;
		this.mensagem = mensagem;
		this.momento = momento;
	}
	
	public String getCampo() {
		return campo;
	}
	
	public String getMensagem() {
		return mensagem;
	}
	
	public OffsetDateTime getMomento() {
		return momento;
	}
}
