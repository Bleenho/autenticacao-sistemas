package br.com.facilita.autenticacao.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.facilita.autenticacao.api.request.ModuleIntoPerfilRequest;
import br.com.facilita.autenticacao.api.request.PerfilRegisterRequest;
import br.com.facilita.autenticacao.model.Perfil;
import br.com.facilita.autenticacao.service.PerfilService;
import io.swagger.annotations.Api;
import javassist.NotFoundException;

@RestController
@RequestMapping("/perfis")
@Api(tags = "Perfil", value = "Um perfil possue acesso aos modulos de um sistema ou de varios sistemas")
public class PerfilController {

	private PerfilService service;

	@Autowired
	public PerfilController(PerfilService service) {
		this.service = service;
	}

	@PostMapping
	public ResponseEntity<?> save(@RequestBody PerfilRegisterRequest perfilRegisterRequest) throws NotFoundException {
		return ResponseEntity.status(HttpStatus.CREATED)
				.eTag(String.valueOf(service.createPerfil(perfilRegisterRequest))).build();
	}
	
	@PutMapping(value= "/modules")
	public ResponseEntity<?> alterModules(@RequestBody ModuleIntoPerfilRequest monduleIntoPerfilRequest) {
		service.alterModulesIntoPerfil(monduleIntoPerfilRequest);
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	
	@GetMapping
	public ResponseEntity<List<Perfil>> findPerfis(){
		return ResponseEntity.status(HttpStatus.OK).body(service.allPerfis());
	}

}
