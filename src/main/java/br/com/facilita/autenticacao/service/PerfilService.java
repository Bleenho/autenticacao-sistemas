package br.com.facilita.autenticacao.service;

import br.com.facilita.autenticacao.api.request.PerfilRegisterRequest;

public interface PerfilService {

	Long createPerfil(PerfilRegisterRequest perfilRegisterRequest);

}
