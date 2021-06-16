package br.com.zupacademy.erivelton.proposta.entidade;

import java.time.OffsetDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.zupacademy.erivelton.proposta.enums.EstadoCarteiraDigital;
import br.com.zupacademy.erivelton.proposta.enums.TipoCarteiraDigital;

@Entity
public class CarteiraDigital {

	@Id
	@NotBlank
	private String id;

	@NotBlank
	private String email;
	
	@SuppressWarnings("unused")
	private OffsetDateTime associadaEm = OffsetDateTime.now();
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private TipoCarteiraDigital carteira;

	@NotNull
	@Enumerated(EnumType.STRING)
	private EstadoCarteiraDigital estado;
	
	@ManyToOne
	private Cartao cartao;

	public CarteiraDigital(@NotBlank String id, @NotBlank String email, @NotNull TipoCarteiraDigital carteira,
			@NotNull EstadoCarteiraDigital estado, Cartao cartao) {
		this.id = id;
		this.email = email;
		this.carteira = carteira;
		this.estado = estado;
		this.cartao = cartao;
	}
	
}
