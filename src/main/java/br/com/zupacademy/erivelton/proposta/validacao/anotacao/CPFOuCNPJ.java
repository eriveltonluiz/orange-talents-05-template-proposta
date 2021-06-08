package br.com.zupacademy.erivelton.proposta.validacao.anotacao;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import org.hibernate.validator.constraints.CompositionType;
import org.hibernate.validator.constraints.ConstraintComposition;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

@CPF
@CNPJ
@ConstraintComposition(CompositionType.OR)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {})
@Documented
public @interface CPFOuCNPJ {

	String message() default "Formato do campo deve ser cpf ou cnpj";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
