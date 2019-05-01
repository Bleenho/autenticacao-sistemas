package br.com.facilita.autenticacao.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.facilita.autenticacao.api.request.CadastroUsuarioRequest;
import br.com.facilita.autenticacao.api.request.LogarRequest;
import br.com.facilita.autenticacao.api.response.DefaultResponse;
import br.com.facilita.autenticacao.exception.LoginException;
import br.com.facilita.autenticacao.service.UsuarioService;
import io.swagger.annotations.Api;

@RestController
@RequestMapping("/usuarios")
@Api(tags = "Usuário",
value = "API's de usuários")
public class UsuarioController {

	private UsuarioService service ;
	
	@Autowired
	public UsuarioController(UsuarioService service) {
		this.service = service;
	}
	
	@PostMapping
	public ResponseEntity<?> save(@RequestBody CadastroUsuarioRequest usuario) {
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.eTag(String.valueOf(service.cadastrarUsuario(usuario))).build();
	}

	@PostMapping( value ="/logar")
	@ResponseStatus(HttpStatus.OK)
	@CrossOrigin
	public ResponseEntity<DefaultResponse> logar(@RequestBody LogarRequest logar) throws LoginException {
		return new ResponseEntity<>(DefaultResponse.builder().dado(service.logar(logar)).sucesso(true).build(), HttpStatus.OK);
	}
}
