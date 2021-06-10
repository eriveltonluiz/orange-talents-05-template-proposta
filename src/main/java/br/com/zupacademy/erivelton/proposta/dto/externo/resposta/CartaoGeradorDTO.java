package br.com.zupacademy.erivelton.proposta.dto.externo.resposta;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import br.com.zupacademy.erivelton.proposta.entidade.Cartao;

public class CartaoGeradorDTO {
	
	private String id;
	private String emitidoEm;
	private String titular;
	private Integer limite;
	private VencimentoCartaoDTO vencimento;
	private String idProposta;
	
	public Cartao paraEntidadeCartao() {
		return new Cartao(id, LocalDateTime.parse(emitidoEm), new BigDecimal(limite), vencimento.paraEntidadeVencimentoCartao());
	}
	
	public String getId() {
		return id;
	}
	
	public String getEmitidoEm() {
		return emitidoEm;
	}
	
	public String getTitular() {
		return titular;
	}
	
	public String getIdProposta() {
		return idProposta;
	}
	
	public Integer getLimite() {
		return limite;
	}
	
	public VencimentoCartaoDTO getVencimento() {
		return vencimento;
	}

}
