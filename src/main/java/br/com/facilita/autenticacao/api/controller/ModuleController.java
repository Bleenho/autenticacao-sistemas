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

import br.com.facilita.autenticacao.api.request.ModuleRegisterRequest;
import br.com.facilita.autenticacao.api.response.DefaultResponse;
import br.com.facilita.autenticacao.exception.LoginException;
import br.com.facilita.autenticacao.service.ModuleService;
import io.swagger.annotations.Api;
import javassist.NotFoundException;

@RestController
@RequestMapping("/modules")
@Api(tags = "Modulo", value = "API's de modulos")
public class ModuleController {

	private ModuleService service;

	@Autowired
	public ModuleController(ModuleService service) {
		this.service = service;
	}

	@PostMapping
	public ResponseEntity<?> save(@RequestBody ModuleRegisterRequest moduleRegisterRequest) throws NotFoundException {
		return ResponseEntity.status(HttpStatus.CREATED)
				.eTag(String.valueOf(service.createModule(moduleRegisterRequest))).build();
	}

	@GetMapping
	public ResponseEntity<DefaultResponse> list() throws LoginException {
		return ResponseEntity.status(HttpStatus.OK)
				.body(DefaultResponse.builder().dado(service.allModule()).sucesso(true).build());
	}

	@DeleteMapping(value = "/{idModule}")
	public ResponseEntity<?> delete(@PathVariable(name = "idModule") Long idModule) throws LoginException {
		service.deleteModule(idModule);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
}
