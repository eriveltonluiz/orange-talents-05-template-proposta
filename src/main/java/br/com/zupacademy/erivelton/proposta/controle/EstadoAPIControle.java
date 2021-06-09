package br.com.zupacademy.erivelton.proposta.controle;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
public class EstadoAPIControle {

	@Value("${actuator.host}")
	private String recursoActuator;

	@Autowired
	private RestTemplate restTemplate;

	@GetMapping(value = "/api/estados")
	public ResponseEntity<Object> retornarEstadoAPI(UriComponentsBuilder builder)
			throws RestClientException, URISyntaxException {
		ResponseEntity<Object> estadoAPI = restTemplate
				.getForEntity(new URI(builder.path(recursoActuator).toUriString()), Object.class);

		return estadoAPI;
	}
}
