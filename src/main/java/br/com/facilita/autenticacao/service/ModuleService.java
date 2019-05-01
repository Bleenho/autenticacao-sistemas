package br.com.facilita.autenticacao.service;

import java.util.List;

import br.com.facilita.autenticacao.api.request.ModuleRegisterRequest;
import br.com.facilita.autenticacao.model.Modulo;
import javassist.NotFoundException;

public interface ModuleService {

	Long createModule(ModuleRegisterRequest moduleRegisterRequest) throws NotFoundException;

	List<Modulo> allModule();

	void deleteModule(Long idModule);

}
