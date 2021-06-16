package br.com.zupacademy.erivelton.proposta.dto.externo.requisicao;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.zupacademy.erivelton.proposta.entidade.CarteiraDigital;
import br.com.zupacademy.erivelton.proposta.validacao.anotacao.UniqueValue;

public class DadosCarteiraDigitalRequisicao {

	@NotBlank
	private String email;
	
	@UniqueValue(atributo = "carteira", classe = CarteiraDigital.class, message = "Carteira Paypal já está associada!!")
	@NotNull
	private String carteira;

	public DadosCarteiraDigitalRequisicao(@NotBlank String email, @NotNull String carteira) {
		this.email = email;
		this.carteira = carteira;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getCarteira() {
		return carteira;
	}
}
