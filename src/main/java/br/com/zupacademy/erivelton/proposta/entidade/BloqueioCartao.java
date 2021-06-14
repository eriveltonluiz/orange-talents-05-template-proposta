package br.com.zupacademy.erivelton.proposta.entidade;

import java.time.OffsetDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;

@Entity
public class BloqueioCartao {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Column(name = "ip_cliente")
	private String ipCliente;
	
	@NotBlank
	@Column(name = "user_agent")
	private String userAgent;
	
	@Column(name = "instante_bloqueio")
	private OffsetDateTime instanteBloqueio = OffsetDateTime.now();
	
	@OneToOne
	private Cartao cartao;

	public BloqueioCartao(String ipCliente, String userAgent, Cartao cartao) {
		this.ipCliente = ipCliente;
		this.userAgent = userAgent;
		this.cartao = cartao;
	}
	
}
