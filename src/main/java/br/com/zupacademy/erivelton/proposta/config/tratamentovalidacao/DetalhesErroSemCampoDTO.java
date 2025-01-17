package br.com.zupacademy.erivelton.proposta.config.tratamentovalidacao;

import java.time.OffsetDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public class DetalhesErroSemCampoDTO {
	
	private String mensagem;
	
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm.ss")
	private OffsetDateTime momento;

	public DetalhesErroSemCampoDTO(String mensagem, OffsetDateTime momento) {
		this.mensagem = mensagem;
		this.momento = momento;
	}
	
	public String getMensagem() {
		return mensagem;
	}
	
	public OffsetDateTime getMomento() {
		return momento;
	}
}
