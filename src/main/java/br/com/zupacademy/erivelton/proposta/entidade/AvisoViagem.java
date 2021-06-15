package br.com.zupacademy.erivelton.proposta.entidade;

import java.time.LocalDate;
import java.time.OffsetDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "aviso_viagem")
public class AvisoViagem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	private String destino;
	
	@NotNull
	@Future
	@Column(name = "instante_termino")
	private LocalDate dataTermino;

	@Column(name = "instante_aviso")
	private OffsetDateTime instanteAviso = OffsetDateTime.now();
	
	@NotBlank
	@Column(name = "ip_cliente")
	private String ipCliente;
	
	@NotBlank
	@Column(name = "user_agent")
	private String userAgent;
	
	@ManyToOne
	private Cartao cartao;

	public AvisoViagem(@NotBlank String destino, @NotNull @Future LocalDate dataTermino, @NotBlank String ipCliente,
			@NotBlank String userAgent, Cartao cartao) {
		this.destino = destino;
		this.dataTermino = dataTermino;
		this.ipCliente = ipCliente;
		this.userAgent = userAgent;
		this.cartao = cartao;
	}
	
}
