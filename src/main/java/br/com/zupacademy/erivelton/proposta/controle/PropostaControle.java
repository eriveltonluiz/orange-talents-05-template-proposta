package br.com.zupacademy.erivelton.proposta.controle;

import java.net.URI;
import java.net.URISyntaxException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
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
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zupacademy.erivelton.proposta.dto.reposta.DetalhesPropostaDTO;
import br.com.zupacademy.erivelton.proposta.dto.requisicao.NovaPropostaRequisicao;
import br.com.zupacademy.erivelton.proposta.entidade.Proposta;
import br.com.zupacademy.erivelton.proposta.enums.StatusFinanceiro;
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
	private RestTemplate restTemplate;

	@Value("${cartoes.host}")
	private String urlAPIExterna;

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

		URI uriAPIExterna = null;
		
		try {
			uriAPIExterna = new URI(urlAPIExterna);
		} catch (URISyntaxException e) {
			return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build();
		}

		StatusFinanceiro status = restTemplate.postForObject(uriAPIExterna, proposta, StatusFinanceiro.class);
		proposta.setEstado(status);

		URI uri = uriBuilder.path("/propostas/{id}").buildAndExpand(proposta.getId()).toUri();

		return ResponseEntity.created(uri).body(proposta.situacaoProposta());
	}

}
