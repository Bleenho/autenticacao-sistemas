package br.com.facilita.autenticacao.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.facilita.autenticacao.api.request.SistemRegisterRequest;
import br.com.facilita.autenticacao.api.response.DefaultResponse;
import br.com.facilita.autenticacao.exception.LoginException;
import br.com.facilita.autenticacao.service.SistemaService;
import io.swagger.annotations.Api;

@RestController
@RequestMapping("/sistems")
@Api(tags = "Sistema", value = "API's de sistemas")
public class SistemaController {

	private SistemaService service;

	@Autowired
	public SistemaController(SistemaService service) {
		this.service = service;
	}

	@PostMapping
	public ResponseEntity<?> save(@RequestBody SistemRegisterRequest sistemRegisterRequest) {
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.eTag(String.valueOf(service.createSistem(sistemRegisterRequest)))
				.build();
	}

	@GetMapping
	public ResponseEntity<DefaultResponse> list() throws LoginException {
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(DefaultResponse.builder().dado(service.allSistem()).sucesso(true).build());
	}
	
	@DeleteMapping(value = "/{idSistem}")
	public ResponseEntity<?> delete(@PathVariable(name = "idSistem") Long idSistem) throws LoginException {
		service.deleteSistem(idSistem);
		return ResponseEntity
				.status(HttpStatus.NO_CONTENT)
				.build();
	}
}
