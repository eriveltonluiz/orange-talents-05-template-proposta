package br.com.zupacademy.erivelton.proposta.controle;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.erivelton.proposta.apiexterna.APICartoes;
import br.com.zupacademy.erivelton.proposta.config.excecao.RecursoNaoEncontradoException;
import br.com.zupacademy.erivelton.proposta.entidade.BloqueioCartao;
import br.com.zupacademy.erivelton.proposta.entidade.Cartao;
import br.com.zupacademy.erivelton.proposta.enums.EstadoCartao;
import br.com.zupacademy.erivelton.proposta.validacao.anotacao.UniqueValue;

@RestController
@Validated
public class BloqueioCartaoControle {

	@PersistenceContext
	private EntityManager em;

	@Autowired
	private APICartoes apiCartoes;

	@PostMapping(value = "/bloqueios/{idCartao}")
	@Transactional
	public void salvar(
			@PathVariable @UniqueValue(classe = BloqueioCartao.class, atributo = "cartao.id", message = "Cartão já está bloqueado") String idCartao,
			HttpServletRequest request) {
		Cartao cartao = em.find(Cartao.class, idCartao);

		if (cartao == null)
			throw new RecursoNaoEncontradoException();

		String ipCliente = request.getRemoteAddr();
		String userAgent = request.getHeader("user-agent");

		BloqueioCartao bloqueioCartao = new BloqueioCartao(ipCliente, userAgent, cartao);
		em.persist(bloqueioCartao);

		EstadoCartao estadoCartao = apiCartoes.notificarBanco(cartao.getId());
		cartao.setEstado(estadoCartao);
	}
}
