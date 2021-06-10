package br.com.zupacademy.erivelton.proposta.dto.externo.resposta;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.zupacademy.erivelton.proposta.entidade.VencimentoCartao;

public class VencimentoCartaoDTO {
	
	@NotBlank
	private String id;

	@NotNull
	private Integer dia;
	
	@NotNull
	private String dataDeCriacao;
	
	public VencimentoCartao paraEntidadeVencimentoCartao() {
		return new VencimentoCartao(id, dia, LocalDateTime.parse(dataDeCriacao));
	}
	
	public String getId() {
		return id;
	}
	
	public Integer getDia() {
		return dia;
	}
	
	public String getDataDeCriacao() {
		return dataDeCriacao;
	}

}
