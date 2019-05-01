package br.com.facilita.autenticacao.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.facilita.autenticacao.api.request.PerfilRegisterRequest;
import br.com.facilita.autenticacao.model.Perfil;
import br.com.facilita.autenticacao.repository.PerfilRepository;
import br.com.facilita.autenticacao.service.PerfilService;

@Service
public class PerfilServiceImpl implements PerfilService {

	private PerfilRepository perfilRepository;
	
	@Autowired
	public PerfilServiceImpl(PerfilRepository perfilRepository) {
		this.perfilRepository = perfilRepository;
	}

	@Override
	public Long createPerfil(PerfilRegisterRequest perfilRegisterRequest) {
		return perfilRepository.save(Perfil.builder().dsPerfil(perfilRegisterRequest.getDescribePerfil()).build()).getIdPerfil();
	}


}
