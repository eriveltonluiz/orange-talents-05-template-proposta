package br.com.zupacademy.erivelton.proposta.controle;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.erivelton.proposta.dto.interno.requisicao.NovoAvisoViagemRequisicao;
import br.com.zupacademy.erivelton.proposta.entidade.AvisoViagem;
import br.com.zupacademy.erivelton.proposta.entidade.Cartao;

@RestController
public class AvisoViagemControle {

	@PersistenceContext
	private EntityManager em;

	@PostMapping(value = "/avisos/viagens/{idCartao}")
	@Transactional
	public void avisarViagens(@Valid @RequestBody NovoAvisoViagemRequisicao requisicao, @PathVariable String idCartao,
			HttpServletRequest request) {
		Cartao cartao = em.find(Cartao.class, idCartao);

		String ip = request.getRemoteAddr();
		String userAgent = request.getHeader("user-agent");
		
		AvisoViagem avisoViagem = new AvisoViagem(requisicao.getDestino(), requisicao.getDataTermino(), ip, userAgent, cartao);
		em.persist(avisoViagem);
	}
}
