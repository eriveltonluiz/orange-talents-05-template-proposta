package br.com.zupacademy.erivelton.proposta.enums;

public enum ResultadoSolicitacao {
	
	SEM_RESTRICAO{
		@Override
		public StatusFinanceiro converterEnum() {
			return StatusFinanceiro.ELEGIVEL;
		}
	}, COM_RESTRICAO{
		@Override
		public StatusFinanceiro converterEnum() {
			return StatusFinanceiro.NAO_ELEGIVEL;
		}
	};
	
	public abstract StatusFinanceiro converterEnum();
}
