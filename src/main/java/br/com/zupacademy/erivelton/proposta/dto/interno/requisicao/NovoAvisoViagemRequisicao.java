package br.com.zupacademy.erivelton.proposta.dto.interno.requisicao;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class NovoAvisoViagemRequisicao {

	@NotBlank
	private String destino;
	
	@NotNull
	@Future
	private LocalDate dataTermino;

	public NovoAvisoViagemRequisicao(String destino, LocalDate dataTermino) {
		this.destino = destino;
		this.dataTermino = dataTermino;
	}
	
	public String converterLocalDateEmString() {
		return dataTermino.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	}

	public String getDestino() {
		return destino;
	}
	
	public LocalDate getDataTermino() {
		return dataTermino;
	}
}
