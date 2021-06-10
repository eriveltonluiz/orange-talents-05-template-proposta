package br.com.zupacademy.erivelton.proposta.dto.interno.requisicao;

@SuppressWarnings("unused")
public class PropostaSecundariaDTO {
	
	private Long propostaId;
	
	private String documento;
	
	private String nome;

	public PropostaSecundariaDTO(Long propostaId, String documento, String nome) {
		this.propostaId = propostaId;
		this.documento = documento;
		this.nome = nome;
	}

	public String getDocumento() {
		return documento;
	}
	
}
