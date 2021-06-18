package br.com.zupacademy.erivelton.proposta.dto.interno.requisicao;

import java.math.BigDecimal;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import br.com.zupacademy.erivelton.proposta.config.utils.Criptografia;
import br.com.zupacademy.erivelton.proposta.entidade.Proposta;
import br.com.zupacademy.erivelton.proposta.validacao.anotacao.CPFOuCNPJ;
import br.com.zupacademy.erivelton.proposta.validacao.anotacao.UniqueValue;

public class NovaPropostaRequisicao {
	
	@CPFOuCNPJ
	@NotBlank
	@UniqueValue(classe = Proposta.class, atributo = "documento", message = "Documento já cadastrado!!")
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

	public NovaPropostaRequisicao(@NotBlank String documento, @Email @NotBlank String email, @NotBlank String nome,
			@NotBlank String endereco, @Positive @NotNull BigDecimal salario) {
		this.documento = documento;
		this.email = email;
		this.nome = nome;
		this.endereco = endereco;
		this.salario = salario;
	}
	
	public String getDocumento() {
		return documento;
	}

	public Proposta paraEntidade() {
		return new Proposta(Criptografia.criptografar(documento), email, nome, endereco, salario);
	}
}
