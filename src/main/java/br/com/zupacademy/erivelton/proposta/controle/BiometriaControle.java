package br.com.zupacademy.erivelton.proposta.controle;

import java.net.URI;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zupacademy.erivelton.proposta.config.excecao.RecursoNaoEncontradoException;
import br.com.zupacademy.erivelton.proposta.dto.interno.requisicao.NovaBiometriaRequisicao;
import br.com.zupacademy.erivelton.proposta.entidade.Biometria;
import br.com.zupacademy.erivelton.proposta.entidade.Cartao;

@RestController
public class BiometriaControle {
	
	@PersistenceContext
	private EntityManager em;

	@PostMapping(value = "/biometrias/{idCartao}")
	@Transactional
	public ResponseEntity<Void> salvar(@Valid @RequestBody NovaBiometriaRequisicao requisicao,
			@PathVariable String idCartao, UriComponentsBuilder uriBuilder) {
		Cartao cartao = em.find(Cartao.class, idCartao);

		if (cartao == null) throw new RecursoNaoEncontradoException();

		Biometria biometria = new Biometria(requisicao.getFingerprint(), cartao);
		em.persist(biometria);

		URI uri = uriBuilder.path("/biometrias/{id}").buildAndExpand(biometria.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

}
