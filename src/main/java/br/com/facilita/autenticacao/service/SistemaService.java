package br.com.facilita.autenticacao.service;

import java.util.List;

import br.com.facilita.autenticacao.api.request.SistemRegisterRequest;
import br.com.facilita.autenticacao.model.Sistema;

public interface SistemaService {

	Long createSistem(SistemRegisterRequest sistemRegisterRequest);

	List<Sistema> allSistem();
	
	void deleteSistem(Long idSistem);

}
