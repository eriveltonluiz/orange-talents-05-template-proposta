package br.com.zupacademy.erivelton.proposta.entidade;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import br.com.zupacademy.erivelton.proposta.enums.EstadoCartao;

@Entity
public class Cartao {

	@Id
	private String id;

	@NotNull
	private LocalDateTime dataEmissao;

	@NotNull
	private BigDecimal limite;

	@NotNull
	@Enumerated(EnumType.STRING)
	private EstadoCartao estado = EstadoCartao.ATIVO;

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

	public String getId() {
		return id;
	}

	public void setEstado(EstadoCartao estado) {
		this.estado = estado;
	}

}
