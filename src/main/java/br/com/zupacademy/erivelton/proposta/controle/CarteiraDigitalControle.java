package br.com.zupacademy.erivelton.proposta.controle;

import java.net.URI;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zupacademy.erivelton.proposta.apiexterna.cartao.APICartaoAssociacaoCarteira;
import br.com.zupacademy.erivelton.proposta.config.excecao.DuplicidadeException;
import br.com.zupacademy.erivelton.proposta.config.excecao.ErroRespostaAPIExternaException;
import br.com.zupacademy.erivelton.proposta.config.excecao.RecursoNaoEncontradoException;
import br.com.zupacademy.erivelton.proposta.dto.externo.resposta.AssociacaoCarteiraCartaoDTO;
import br.com.zupacademy.erivelton.proposta.dto.interno.requisicao.NovaCarteiraDigitalRequisicao;
import br.com.zupacademy.erivelton.proposta.entidade.Cartao;
import br.com.zupacademy.erivelton.proposta.entidade.CarteiraDigital;
import br.com.zupacademy.erivelton.proposta.enums.TipoCarteiraDigital;
import br.com.zupacademy.erivelton.proposta.repositorio.CarteiraRepositorio;

@RestController
@Validated
public class CarteiraDigitalControle {

	@PersistenceContext
	private EntityManager em;

	@Autowired
	private CarteiraRepositorio repositorio;

	@Autowired
	private APICartaoAssociacaoCarteira apiCartaoAssociacaoCarteira;

	@PostMapping(value = "/carteiras/{idCartao}")
	@Transactional
	public ResponseEntity<Void> salvarCarteira(@PathVariable String idCartao,
			@Valid @RequestBody NovaCarteiraDigitalRequisicao requisicao, UriComponentsBuilder uriBuilder) {
		Cartao cartao = em.find(Cartao.class, idCartao);

		if (cartao == null)
			throw new RecursoNaoEncontradoException();

		if (requisicao.verificarEmissor()
				&& repositorio.existsByCarteiraAndCartaoId(TipoCarteiraDigital.PAYPAL, idCartao))
			throw new DuplicidadeException("Carteira digital Paypal já está associada a este cartão!!");

		try {
			AssociacaoCarteiraCartaoDTO respostaAPIExterna = apiCartaoAssociacaoCarteira
					.associarCartaoComCarteira(idCartao, requisicao);

			CarteiraDigital carteiraDigital = requisicao.paraEntidadeCarteira(respostaAPIExterna, cartao);
			em.persist(carteiraDigital);

			URI uri = uriBuilder.path("/carteiras/{id}").buildAndExpand(carteiraDigital.getId()).toUri();
			return ResponseEntity.created(uri).build();
		} catch (Exception e) {
			throw new ErroRespostaAPIExternaException(
					"Erro ao associar a carteira " + requisicao.getCarteira() + " no sistema bancário!!");
		}

	}

}
