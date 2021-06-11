package br.com.zupacademy.erivelton.proposta.entidade;

import java.time.OffsetDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

@Entity
public class Biometria {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	private String fingerprint;
	
	@Column(name = "data_cadastro")
	private OffsetDateTime dataCadastro = OffsetDateTime.now();

	@ManyToOne
	private Cartao cartao;

	public Biometria(@NotBlank String fingerprint, Cartao cartao) {
		this.fingerprint = fingerprint;
		this.cartao = cartao;
	}
	
	public Long getId() {
		return id;
	}
	
}
