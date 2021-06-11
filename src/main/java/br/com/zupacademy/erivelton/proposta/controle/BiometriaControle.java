package br.com.zupacademy.erivelton.proposta.controle;

import java.net.URI;
import java.util.Base64;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zupacademy.erivelton.proposta.config.excecao.DadoNaoEncontradoException;
import br.com.zupacademy.erivelton.proposta.dto.interno.requisicao.NovaBiometriaRequisicao;
import br.com.zupacademy.erivelton.proposta.entidade.Biometria;
import br.com.zupacademy.erivelton.proposta.entidade.Cartao;

@RestController
public class BiometriaControle {

	@PersistenceContext
	private EntityManager em;

	@PostMapping(value = "/biometrias")
	@Transactional
	public ResponseEntity<Void> salvar(@Valid @RequestBody NovaBiometriaRequisicao requisicao,
			UriComponentsBuilder uriBuilder) {
		Cartao cartao = em.find(Cartao.class, requisicao.getIdentificadorCartao());

		if (cartao == null) {
			throw new DadoNaoEncontradoException("Identificador do cartão não foi encontrado!!");
		}

		String fingerprint = requisicao.getFingerprint();
		String fingerprintCodificado = Base64.getEncoder().encodeToString(fingerprint.getBytes());

		Biometria biometria = new Biometria(fingerprintCodificado, cartao);
		em.persist(biometria);

		URI uri = uriBuilder.path("/biometrias/{id}").buildAndExpand(biometria.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
}
