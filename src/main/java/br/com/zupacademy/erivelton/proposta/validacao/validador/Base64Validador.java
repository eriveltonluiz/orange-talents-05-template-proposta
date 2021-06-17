package br.com.zupacademy.erivelton.proposta.validacao.validador;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.com.zupacademy.erivelton.proposta.validacao.anotacao.Base64;

public class Base64Validador implements ConstraintValidator<Base64, String>{

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		try {
			java.util.Base64.getDecoder().decode(value.getBytes());
			return true;
		} catch (IllegalArgumentException e) {
			return false;
		}
	}
	
}
