package br.com.zupacademy.erivelton.proposta.validacao.validador;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.com.zupacademy.erivelton.proposta.config.excecao.DuplicidadeException;
import br.com.zupacademy.erivelton.proposta.validacao.anotacao.UniqueValue;

public class ValorUnicoValidacao implements ConstraintValidator<UniqueValue, Object>{

	@PersistenceContext
	private EntityManager em;
	
	private String atributo;
	
	private Class<?> classe;
	
	@Override
	public void initialize(UniqueValue constraintAnnotation) {
		atributo = constraintAnnotation.atributo();
		classe = constraintAnnotation.classe();
	}
	
	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		Query query = em.createQuery("SELECT 1 FROM " + classe.getName() + " o where o." + atributo + " = :valor");
		query.setParameter("valor", value);
		
		if(!query.getResultList().isEmpty())
			throw new DuplicidadeException(context.getDefaultConstraintMessageTemplate());
			
		return true;
	}
	
}
