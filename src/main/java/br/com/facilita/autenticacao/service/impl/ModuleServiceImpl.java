package br.com.facilita.autenticacao.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import br.com.facilita.autenticacao.api.request.ModuleRegisterRequest;
import br.com.facilita.autenticacao.exception.BadRequestException;
import br.com.facilita.autenticacao.model.Modulo;
import br.com.facilita.autenticacao.model.Sistema;
import br.com.facilita.autenticacao.repository.ModuleRepository;
import br.com.facilita.autenticacao.repository.SistemRepository;
import br.com.facilita.autenticacao.service.ModuleService;
import javassist.NotFoundException;

@Service
public class ModuleServiceImpl implements ModuleService {

	private ModuleRepository moduleRepository;
	private SistemRepository sistemaRepository;

	@Autowired
	public ModuleServiceImpl(ModuleRepository moduleRepository, SistemRepository sistemaRepository) {
		this.moduleRepository = moduleRepository;
		this.sistemaRepository = sistemaRepository;
	}

	@Override
	public Long createModule(ModuleRegisterRequest moduleRegisterRequest) throws NotFoundException {
		Sistema sistema = sistemaRepository.findOne(moduleRegisterRequest.getIdSistema());
		if (ObjectUtils.isEmpty(sistema)) {
			throw new BadRequestException("idSistema");
		}
		return moduleRepository
				.save(Modulo.builder().cdSistema(sistema).dsModulo(moduleRegisterRequest.getDescribeModule()).build())
				.getIdModulo();
	}

	@Override
	public List<Modulo> allModule() {
		return moduleRepository.findAll();
	}

	@Override
	public void deleteModule(Long idModule) {
		moduleRepository.delete(idModule);
	}

}
