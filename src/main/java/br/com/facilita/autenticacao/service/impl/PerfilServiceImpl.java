package br.com.facilita.autenticacao.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import br.com.facilita.autenticacao.api.request.AcaoModuloPerfil;
import br.com.facilita.autenticacao.api.request.ModuleIntoPerfilRequest;
import br.com.facilita.autenticacao.api.request.PerfilRegisterRequest;
import br.com.facilita.autenticacao.exception.BadRequestException;
import br.com.facilita.autenticacao.model.Modulo;
import br.com.facilita.autenticacao.model.ModuloPerfil;
import br.com.facilita.autenticacao.model.Perfil;
import br.com.facilita.autenticacao.repository.ModuleRepository;
import br.com.facilita.autenticacao.repository.ModuloPerfilRepository;
import br.com.facilita.autenticacao.repository.PerfilRepository;
import br.com.facilita.autenticacao.service.PerfilService;

@Service
@Transactional
public class PerfilServiceImpl implements PerfilService {

	private PerfilRepository perfilRepository;
	private ModuloPerfilRepository moduloPerfilRepository;
	private ModuleRepository moduleRepository;
	
	@Autowired
	public PerfilServiceImpl(PerfilRepository perfilRepository, ModuloPerfilRepository moduloPerfilRepository, ModuleRepository moduleRepository) {
		this.perfilRepository = perfilRepository;
		this.moduloPerfilRepository = moduloPerfilRepository;
		this.moduleRepository = moduleRepository;
	}

	@Override
	public Long createPerfil(PerfilRegisterRequest perfilRegisterRequest) {
		Perfil perfil = perfilRepository.save(Perfil.builder().dsPerfil(perfilRegisterRequest.getDescribePerfil()).build());
		addModuloToPerfil(perfil, perfilRegisterRequest.getIdsModulo());
		return perfil.getIdPerfil();
	}
	
	@Override
	public void alterModulesIntoPerfil(ModuleIntoPerfilRequest moduleIntoPerfilRequest) {
		Perfil perfil = perfilRepository.findOne(moduleIntoPerfilRequest.getIdPerfil());
		if(ObjectUtils.isEmpty(perfil)) {
			throw new BadRequestException("Perfil de id: " + moduleIntoPerfilRequest.getIdPerfil());
		}
		if(AcaoModuloPerfil.ADD.equals(moduleIntoPerfilRequest.getAcao())) {
			addModuloToPerfil(perfil, moduleIntoPerfilRequest.getIdsModulo());
			return ;
		}
		removeModuloToPerfil(perfil, moduleIntoPerfilRequest.getIdsModulo());
	}
	
	@Override
	public List<Perfil> allPerfis() {
		return perfilRepository.findAll();
	}

	@Override
	public Perfil findOne(Long idPerfil) {
		return perfilRepository.findOne(idPerfil);
	}
	
	private void addModuloToPerfil(Perfil perfil, List<Long> idsModulo) {
		idsModulo.stream().forEach(idModulo -> {
			Modulo modulo = moduleRepository.findOne(idModulo);
			if(ObjectUtils.isEmpty(modulo)) {
				throw new BadRequestException("Modulo de id: " + idModulo);
			}
			moduloPerfilRepository.save(
				ModuloPerfil.builder()
					.perfil(perfil)
					.modulo(modulo)
					.build());
		});
	}

	private void removeModuloToPerfil(Perfil perfil, List<Long> idsModulo) {
		idsModulo.stream().forEach(idModulo -> {
			Modulo modulo = moduleRepository.findOne(idModulo);
			if(ObjectUtils.isEmpty(modulo)) {
				throw new BadRequestException("Modulo de id: " + idModulo);
			}
			moduloPerfilRepository.findByModuloIdModuloAndPerfilIdPerfil(idModulo, perfil.getIdPerfil())
				.stream().forEach(moduloPerfil -> {
					moduloPerfilRepository.delete(moduloPerfil.getIdModuloPerfil());
				});
		});
	}

}
