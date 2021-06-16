package br.com.zupacademy.erivelton.proposta.dto.interno.requisicao;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.zupacademy.erivelton.proposta.dto.externo.resposta.AssociacaoCarteiraCartaoDTO;
import br.com.zupacademy.erivelton.proposta.entidade.Cartao;
import br.com.zupacademy.erivelton.proposta.entidade.CarteiraDigital;
import br.com.zupacademy.erivelton.proposta.enums.EstadoCarteiraDigital;
import br.com.zupacademy.erivelton.proposta.enums.TipoCarteiraDigital;

public class NovaCarteiraDigitalRequisicao {

	@NotBlank
	private String email;

	@NotNull
	private TipoCarteiraDigital carteira;

	public NovaCarteiraDigitalRequisicao(@NotBlank String email, @NotNull TipoCarteiraDigital carteira) {
		this.email = email;
		this.carteira = carteira;
	}

	public String getEmail() {
		return email;
	}

	public TipoCarteiraDigital getCarteira() {
		return carteira;
	}
	
	public String carteiraToString() {
		return carteira.toString();
	}

	public CarteiraDigital paraEntidadeCarteira(AssociacaoCarteiraCartaoDTO respostaAPIExterna, Cartao cartao) {
		EstadoCarteiraDigital estado = Enum.valueOf(EstadoCarteiraDigital.class, respostaAPIExterna.getResultado());

		return new CarteiraDigital(respostaAPIExterna.getId(), email, carteira, estado, cartao);
	}
}
