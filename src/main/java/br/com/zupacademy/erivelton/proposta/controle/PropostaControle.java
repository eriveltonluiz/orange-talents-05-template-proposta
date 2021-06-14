package br.com.zupacademy.erivelton.proposta.controle;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zupacademy.erivelton.proposta.apiexterna.APIAnaliseFinanceiraProposta;
import br.com.zupacademy.erivelton.proposta.config.excecao.RecursoNaoEncontradoException;
import br.com.zupacademy.erivelton.proposta.dto.externo.requisicao.DadosPropostaRequisicao;
import br.com.zupacademy.erivelton.proposta.dto.interno.requisicao.NovaPropostaRequisicao;
import br.com.zupacademy.erivelton.proposta.dto.interno.resposta.DetalhesPropostaDTO;
import br.com.zupacademy.erivelton.proposta.entidade.Proposta;
import br.com.zupacademy.erivelton.proposta.enums.ResultadoSolicitacao;
import br.com.zupacademy.erivelton.proposta.repositorio.PropostaRepositorio;

@RestController
@RequestMapping("/propostas")
public class PropostaControle {

	@Autowired
	private PropostaRepositorio propostaRepositorio;

	@Autowired
	private APIAnaliseFinanceiraProposta apiAnaliseFinanceiraProposta;

	@GetMapping("/{id}")
	public ResponseEntity<DetalhesPropostaDTO> buscarPropostaPorId(@PathVariable Long id) {
		Proposta proposta = propostaRepositorio.findById(id).orElseThrow(() -> new RecursoNaoEncontradoException());
		
		return ResponseEntity.ok(new DetalhesPropostaDTO(proposta.getEstado()));
	}

	@PostMapping
	@Transactional
	public ResponseEntity<String> salvar(@Valid @RequestBody NovaPropostaRequisicao requisicao,
			UriComponentsBuilder uriBuilder) {
		Proposta proposta = requisicao.paraEntidade();
		propostaRepositorio.save(proposta);

		DadosPropostaRequisicao dadosProposta = new DadosPropostaRequisicao(proposta);
		ResultadoSolicitacao resultado = apiAnaliseFinanceiraProposta.postSolicitacao(dadosProposta);

		proposta.setEstado(resultado.converterEnum());

		if (resultado.equals(ResultadoSolicitacao.COM_RESTRICAO)) {
			return ResponseEntity.unprocessableEntity().body(proposta.situacaoProposta());
		}

		URI uri = uriBuilder.path("/propostas/{id}").buildAndExpand(proposta.getId()).toUri();
		return ResponseEntity.created(uri).body(proposta.situacaoProposta());
	}

}
