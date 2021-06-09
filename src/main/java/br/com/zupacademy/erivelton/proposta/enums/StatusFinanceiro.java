package br.com.zupacademy.erivelton.proposta.enums;

public enum StatusFinanceiro {
	ELEGIVEL{
		@Override
		public String retornarMensagem() {
			return "Proposta elegível para compra";
		}
	}, NAO_ELEGIVEL{
		@Override
		public String retornarMensagem() {
			return "Proposta não elegível para compra";
		}
	};
	
	public abstract String retornarMensagem();
}
