package br.com.zupacademy.erivelton.proposta.entidade;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import br.com.zupacademy.erivelton.proposta.enums.StatusFinanceiro;
import br.com.zupacademy.erivelton.proposta.validacao.anotacao.CPFOuCNPJ;

@Entity
public class Proposta {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@CPFOuCNPJ
	@NotBlank
	private String documento;
	
	@Email
	@NotBlank
	private String email;
	
	@NotBlank
	private String nome;
	
	@NotBlank
	private String endereco;

	@Positive
	@NotNull
	private BigDecimal salario;
	
	@Enumerated(EnumType.STRING)
	private StatusFinanceiro estado;

	@Deprecated
	public Proposta() {
	}
	
	public Proposta(@NotBlank String documento, @Email @NotBlank String email, @NotBlank String nome, @NotBlank String endereco,
			@Positive @NotNull BigDecimal salario) {
		this.documento = documento;
		this.email = email;
		this.nome = nome;
		this.endereco = endereco;
		this.salario = salario;
	}
	
	public Long getId() {
		return id;
	}
	
	public String getDocumento() {
		return documento;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getNome() {
		return nome;
	}
	
	public String getEndereco() {
		return endereco;
	}
	
	public BigDecimal getSalario() {
		return salario;
	}
	
	public void setEstado(StatusFinanceiro estado) {
		this.estado = estado;
	}
	
	public String situacaoProposta() {
		return this.estado.retornarMensagem();
	}
}
