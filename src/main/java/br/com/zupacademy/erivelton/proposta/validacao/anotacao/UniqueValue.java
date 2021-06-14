package br.com.zupacademy.erivelton.proposta.validacao.anotacao;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import br.com.zupacademy.erivelton.proposta.validacao.validador.ValorUnicoValidacao;

@Documented
@Constraint(validatedBy = {ValorUnicoValidacao.class})
@Target(value = {ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueValue {

	String message() default "Campo solicitado já está cadastrado";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	String atributo();

	Class<?> classe();
}
