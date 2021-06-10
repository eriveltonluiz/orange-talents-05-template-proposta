package br.com.zupacademy.erivelton.proposta.entidade;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Cartao {
	
	@Id
	private String id;

	@NotNull
	private LocalDateTime dataEmissao;
	
	@NotNull
	private BigDecimal limite;
	
	@OneToOne(cascade = CascadeType.ALL)
	private VencimentoCartao vencimento;
	
	@Deprecated
	public Cartao() {
	}
	
	public Cartao(String id, LocalDateTime dataEmissao, BigDecimal limite, VencimentoCartao vencimento) {
		this.id = id;
		this.dataEmissao = dataEmissao;
		this.limite = limite;
		this.vencimento = vencimento;
	}
	
}
