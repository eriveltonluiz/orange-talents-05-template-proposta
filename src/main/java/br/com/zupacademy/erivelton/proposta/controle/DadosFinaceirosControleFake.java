package br.com.zupacademy.erivelton.proposta.controle;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.erivelton.proposta.dto.requisicao.PropostaSecundariaDTO;
import br.com.zupacademy.erivelton.proposta.enums.StatusFinanceiro;

@RestController
public class DadosFinaceirosControleFake {
	
	@PostMapping(value = "/api/dados/financeiros")
	public StatusFinanceiro retornarSituacaoSolicitante(@RequestBody PropostaSecundariaDTO novaProposta) {
		String c = novaProposta.getDocumento().substring(0,1);

		if(c.equals("3")) {
			return StatusFinanceiro.NAO_ELEGIVEL;
		} else {
			return StatusFinanceiro.ELEGIVEL;
		}
	}
}
