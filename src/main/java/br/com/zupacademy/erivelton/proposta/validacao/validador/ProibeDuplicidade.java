package br.com.zupacademy.erivelton.proposta.validacao.validador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.com.zupacademy.erivelton.proposta.config.excecao.DuplicidadeException;
import br.com.zupacademy.erivelton.proposta.dto.requisicao.NovaPropostaRequisicao;
import br.com.zupacademy.erivelton.proposta.repositorio.PropostaRepositorio;

@Component
public class ProibeDuplicidade implements Validator {

	@Autowired
	private PropostaRepositorio repositorio;

	@Override
	public boolean supports(Class<?> clazz) {
		return NovaPropostaRequisicao.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		if (errors.hasErrors()) {
			return;
		}

		NovaPropostaRequisicao requisicao = (NovaPropostaRequisicao) target;
		if(repositorio.findByDocumento(requisicao.getDocumento()).isPresent()) {
			throw new DuplicidadeException("documento");
		}
	}
}