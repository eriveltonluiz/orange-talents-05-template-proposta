package br.com.zupacademy.erivelton.proposta.controle;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.erivelton.proposta.apiexterna.cartao.APICartaoAssociacaoCarteira;
import br.com.zupacademy.erivelton.proposta.config.excecao.ErroRespostaAPIExternaException;
import br.com.zupacademy.erivelton.proposta.config.excecao.RecursoNaoEncontradoException;
import br.com.zupacademy.erivelton.proposta.dto.externo.resposta.AssociacaoCarteiraCartaoDTO;
import br.com.zupacademy.erivelton.proposta.dto.interno.requisicao.NovaCarteiraDigitalRequisicao;
import br.com.zupacademy.erivelton.proposta.entidade.Cartao;
import br.com.zupacademy.erivelton.proposta.entidade.CarteiraDigital;
import br.com.zupacademy.erivelton.proposta.validacao.anotacao.UniqueValue;

@RestController
@Validated
public class CarteiraDigitalControle {

	@PersistenceContext
	private EntityManager em;

	@Autowired
	private APICartaoAssociacaoCarteira apiCartaoAssociacaoCarteira;

	@PostMapping(value = "/carteiras/{idCartao}")
	@Transactional
	public void salvarCarteira(
			@PathVariable @UniqueValue(classe = CarteiraDigital.class, atributo = "cartao.id", message = "Carteira Paypal já está associada!!") String idCartao,
			@Valid @RequestBody NovaCarteiraDigitalRequisicao requisicao) {
		Cartao cartao = em.find(Cartao.class, idCartao);

		if (cartao == null)
			throw new RecursoNaoEncontradoException();

		try {
			AssociacaoCarteiraCartaoDTO respostaAPIExterna = apiCartaoAssociacaoCarteira
					.associarCartaoComCarteira(idCartao, requisicao);

			CarteiraDigital carteiraDigital = requisicao.paraEntidadeCarteira(respostaAPIExterna, cartao);
			em.persist(carteiraDigital);
		} catch (Exception e) {
			throw new ErroRespostaAPIExternaException(
					"Erro ao associar a carteira " + requisicao.getCarteira() + " no sistema bancário!!");
		}
	}

}
