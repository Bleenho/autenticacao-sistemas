package br.com.facilita.autenticacao.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.facilita.autenticacao.api.request.SistemRegisterRequest;
import br.com.facilita.autenticacao.converter.SistemConverter;
import br.com.facilita.autenticacao.model.Sistema;
import br.com.facilita.autenticacao.repository.SistemRepository;
import br.com.facilita.autenticacao.service.SistemaService;

@Service
public class SistemaServiceImpl implements SistemaService{

	private SistemRepository sistemRepository;
	
	@Autowired
	public SistemaServiceImpl(SistemRepository sistemRepository) {
		this.sistemRepository = sistemRepository;
	}

	@Override
	public Long createSistem(SistemRegisterRequest sistemRegisterRequest) {
		return sistemRepository.save(SistemConverter.sistemRegisterRequestToSistema(sistemRegisterRequest)).getIdSistema();
	}

	@Override
	public List<Sistema> allSistem() {
		return sistemRepository.findAll();
	}

	@Override
	public void deleteSistem(Long idSistem) {
		sistemRepository.delete(idSistem);
	}

}
