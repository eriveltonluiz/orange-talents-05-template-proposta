package br.com.zupacademy.erivelton.proposta.dto.externo.requisicao;

import br.com.zupacademy.erivelton.proposta.config.utils.Criptografia;
import br.com.zupacademy.erivelton.proposta.entidade.Proposta;

public class DadosPropostaRequisicao {

	private String idProposta;

	private String documento;

	private String nome;

	public DadosPropostaRequisicao(Proposta proposta) {
		this.idProposta = String.valueOf(proposta.getId());
		this.documento = Criptografia.descriptografar(proposta.getDocumento());
		this.nome = proposta.getNome();
	}
	
	public String getIdProposta() {
		return idProposta;
	}
	
	public String getDocumento() {
		return documento;
	}
	
	public String getNome() {
		return nome;
	}
}
