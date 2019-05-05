package br.com.facilita.autenticacao.service;

import java.util.List;

import br.com.facilita.autenticacao.api.request.ModuleIntoPerfilRequest;
import br.com.facilita.autenticacao.api.request.PerfilRegisterRequest;
import br.com.facilita.autenticacao.model.Perfil;

public interface PerfilService {

	Long createPerfil(PerfilRegisterRequest perfilRegisterRequest);

	void alterModulesIntoPerfil(ModuleIntoPerfilRequest moduleIntoPerfilRequest);

	List<Perfil> allPerfis();

	Perfil findOne(Long idPerfil);
}
