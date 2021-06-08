package br.com.zupacademy.erivelton.proposta.controle;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zupacademy.erivelton.proposta.dto.reposta.DetalhesPropostaDTO;
import br.com.zupacademy.erivelton.proposta.dto.requisicao.NovaPropostaRequisicao;
import br.com.zupacademy.erivelton.proposta.entidade.Proposta;
import br.com.zupacademy.erivelton.proposta.repositorio.PropostaRepositorio;
import br.com.zupacademy.erivelton.proposta.validacao.validador.ProibeDuplicidade;

@RestController
@RequestMapping("/propostas")
public class PropostaControle {
	
	@Autowired
	private PropostaRepositorio propostaRepositorio;
	
	@Autowired
	private ProibeDuplicidade proibeDuplicidade;

	@InitBinder
	public void init(WebDataBinder binder) {
		binder.addValidators(proibeDuplicidade);
	}
	
	@GetMapping("/{id}")
	public DetalhesPropostaDTO buscarPropostaPorId(@PathVariable Long id) {
		Proposta proposta = propostaRepositorio.findById(id).get();
		
		return new DetalhesPropostaDTO(proposta);
	}
	
	@PostMapping
	public ResponseEntity<Void> salvar(@Valid @RequestBody NovaPropostaRequisicao requisicao, UriComponentsBuilder uriBuilder){
		Proposta proposta = requisicao.paraEntidade();
		propostaRepositorio.save(proposta);
		
		URI uri = uriBuilder.path("/propostas/{id}").buildAndExpand(proposta.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
}
