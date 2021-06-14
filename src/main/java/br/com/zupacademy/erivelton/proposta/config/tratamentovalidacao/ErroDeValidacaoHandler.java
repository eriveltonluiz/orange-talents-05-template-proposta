package br.com.zupacademy.erivelton.proposta.config.tratamentovalidacao;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.zupacademy.erivelton.proposta.config.excecao.DadoNaoEncontradoException;
import br.com.zupacademy.erivelton.proposta.config.excecao.DuplicidadeException;
import br.com.zupacademy.erivelton.proposta.config.excecao.RecursoNaoEncontradoException;

@RestControllerAdvice
public class ErroDeValidacaoHandler extends ResponseEntityExceptionHandler{

	@Autowired
	private MessageSource messageSource;
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		List<FieldError> fieldErrors  = ex.getBindingResult().getFieldErrors();
		List<DetalhesErroDTO> erros = new ArrayList<DetalhesErroDTO>();
		
		fieldErrors.forEach(field -> {
			String mensagem = messageSource.getMessage(field, LocaleContextHolder.getLocale());
			erros.add(new DetalhesErroDTO(field.getField(), mensagem, OffsetDateTime.now()));
		});
		
		return handleExceptionInternal(ex, erros, headers, status, request);
	}
	
	@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
	@ExceptionHandler(DuplicidadeException.class)
	public DetalhesErroSemCampoDTO handleDuplicidade(DuplicidadeException ex) {
		return new DetalhesErroSemCampoDTO(ex.getMessage(), OffsetDateTime.now());
	}
	
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	@ExceptionHandler(RecursoNaoEncontradoException.class)
	public DetalhesErroSemCampoDTO handleRecursoNaoEncontrado(RecursoNaoEncontradoException ex) {
		return new DetalhesErroSemCampoDTO(ex.getMessage(), OffsetDateTime.now());
	}
	
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	@ExceptionHandler(DadoNaoEncontradoException.class)
	public DetalhesErroSemCampoDTO handleDadoNaoEncontrado(DadoNaoEncontradoException ex) {
		return new DetalhesErroSemCampoDTO(ex.getMessage(), OffsetDateTime.now());
	}
}
