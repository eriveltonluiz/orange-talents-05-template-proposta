package br.com.zupacademy.erivelton.proposta.validacao.anotacao;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import br.com.zupacademy.erivelton.proposta.validacao.validador.Base64Validador;

@Documented
@Target(ElementType.FIELD)
@Constraint(validatedBy = {Base64Validador.class})
@Retention(RetentionPolicy.RUNTIME)
public @interface Base64 {

	String message() default "Formato Base64 inválido";
	
	Class<?>[] groups() default {};
	
	Class<? extends Payload>[] payload() default {};
}
