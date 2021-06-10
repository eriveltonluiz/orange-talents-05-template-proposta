package br.com.zupacademy.erivelton.proposta.entidade;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "vencimento_cartao")
public class VencimentoCartao {
	
	@Id
	@NotBlank
	private String id;

	@NotNull
	private Integer dia;
	
	@NotNull
	@Column(name = "data_criacao")
	private LocalDateTime dataCriacao;
	
	@Deprecated
	public VencimentoCartao() {
	}
	
	public VencimentoCartao(String id, Integer dia, LocalDateTime dataCriacao) {
		this.id = id;
		this.dia = dia;
		this.dataCriacao = dataCriacao;
	}

}
