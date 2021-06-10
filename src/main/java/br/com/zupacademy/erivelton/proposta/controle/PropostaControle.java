package br.com.zupacademy.erivelton.proposta.controle;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zupacademy.erivelton.proposta.apiexterna.APICartoesComponente;
import br.com.zupacademy.erivelton.proposta.apiexterna.APIAnaliseFinanceiraProposta;
import br.com.zupacademy.erivelton.proposta.dto.externo.requisicao.DadosPropostaRequisicao;
import br.com.zupacademy.erivelton.proposta.dto.interno.requisicao.NovaPropostaRequisicao;
import br.com.zupacademy.erivelton.proposta.dto.interno.resposta.DetalhesPropostaDTO;
import br.com.zupacademy.erivelton.proposta.entidade.Proposta;
import br.com.zupacademy.erivelton.proposta.enums.ResultadoSolicitacao;
import br.com.zupacademy.erivelton.proposta.repositorio.PropostaRepositorio;
import br.com.zupacademy.erivelton.proposta.validacao.validador.ProibeDuplicidade;

@RestController
@RequestMapping("/propostas")
public class PropostaControle {

	@Autowired
	private PropostaRepositorio propostaRepositorio;

	@Autowired
	private ProibeDuplicidade proibeDuplicidade;

	@Autowired
	private APIAnaliseFinanceiraProposta apiExterna;
	
	@Autowired
	private APICartoesComponente apiCartoesComponente;

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
	@Transactional
	public ResponseEntity<String> salvar(@Valid @RequestBody NovaPropostaRequisicao requisicao,
			UriComponentsBuilder uriBuilder) {
		Proposta proposta = requisicao.paraEntidade();
		propostaRepositorio.save(proposta);

		DadosPropostaRequisicao dadosProposta = new DadosPropostaRequisicao(proposta);
		ResultadoSolicitacao resultado = apiExterna.postSolicitacao(dadosProposta);
		proposta.setEstado(resultado.converterEnum());
		
		if(resultado.equals(ResultadoSolicitacao.COM_RESTRICAO)) {
			return ResponseEntity.unprocessableEntity().body(proposta.situacaoProposta());
		}
		
		apiCartoesComponente.postCartoes(dadosProposta);
		
		URI uri = uriBuilder.path("/propostas/{id}").buildAndExpand(proposta.getId()).toUri();
		return ResponseEntity.created(uri).body(proposta.situacaoProposta());
	}

}
