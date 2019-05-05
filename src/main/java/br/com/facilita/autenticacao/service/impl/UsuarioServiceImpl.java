package br.com.facilita.autenticacao.service.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import br.com.facilita.autenticacao.api.request.CadastroUsuarioRequest;
import br.com.facilita.autenticacao.api.request.LogarRequest;
import br.com.facilita.autenticacao.api.response.LogarResponse;
import br.com.facilita.autenticacao.api.response.ModuloResponse;
import br.com.facilita.autenticacao.api.response.PerfilResponse;
import br.com.facilita.autenticacao.api.response.ResponseEnum;
import br.com.facilita.autenticacao.converter.UsuarioConverter;
import br.com.facilita.autenticacao.exception.BadRequestException;
import br.com.facilita.autenticacao.exception.LoginException;
import br.com.facilita.autenticacao.model.Perfil;
import br.com.facilita.autenticacao.model.Sistema;
import br.com.facilita.autenticacao.model.SolicitacaoAcesso;
import br.com.facilita.autenticacao.model.Usuario;
import br.com.facilita.autenticacao.repository.LoginRepository;
import br.com.facilita.autenticacao.repository.ModuloPerfilRepository;
import br.com.facilita.autenticacao.repository.SolicitacaoAcessoRepository;
import br.com.facilita.autenticacao.repository.UsuarioRepository;
import br.com.facilita.autenticacao.service.PerfilService;
import br.com.facilita.autenticacao.service.UsuarioService;
import br.com.facilita.autenticacao.util.md5;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	private UsuarioRepository usuarioRepository;
	private LoginRepository loginRepository;
	private SolicitacaoAcessoRepository solicitacaoAcessoRepository;
	private ModuloPerfilRepository moduloPerfilRepository;
	private PerfilService perfilService;

	@Autowired
	public UsuarioServiceImpl(UsuarioRepository usuarioRepository,
			SolicitacaoAcessoRepository solicitacaoAcessoRepository, LoginRepository loginRepository,
			ModuloPerfilRepository moduloPerfilRepository,
			PerfilService perfilService) {
		this.loginRepository = loginRepository;
		this.usuarioRepository = usuarioRepository;
		this.moduloPerfilRepository = moduloPerfilRepository;
		this.solicitacaoAcessoRepository = solicitacaoAcessoRepository;
		this.perfilService = perfilService;
	}

	@Override
	public Long cadastrarUsuario(CadastroUsuarioRequest cadastro) {
		return usuarioRepository.save(
					builderUserRegister(cadastro, registerUserValidationAndGetPerfil(cadastro)))
						.getIdUsuario();
	}

	@Override
	public LogarResponse logar(LogarRequest logando) throws LoginException {
		Usuario user = validaUsers(logando);
		SolicitacaoAcesso solicitacaoAcesso = solicitacaoAcessoRepository.save(
				SolicitacaoAcesso.builder()
						.dsToken(md5.cript(String.valueOf(new Date().getTime())))
						.dtToken(new Date())
						.sistema(Sistema.builder()
								.idSistema(logando.getCdSistema())
								.build())
						.usuario(user)
						.build());
		return LogarResponse.builder()
				.dsToken(solicitacaoAcesso.getDsToken())
				.userName(user.getCdUsuario())
				.perfil(montaPerfil(user.getPerfil()))
				.build();
	}
	
	private Usuario builderUserRegister(CadastroUsuarioRequest cadastro, Perfil perfil) {
		Usuario usuario = UsuarioConverter.cadastroUsuarioRequestToUsuario(cadastro);
		usuario.setDsSenha(md5.cript(usuario.getDsSenha()));
		usuario.setFlStatus("A");
		usuario.setDtAlteracao(new Date());
		usuario.setPerfil(perfil);
		return usuario;
	}

	private Perfil registerUserValidationAndGetPerfil(CadastroUsuarioRequest cadastro) {
		if (!ObjectUtils.isEmpty(((List<Usuario>) usuarioRepository.findByCdUsuario(cadastro.getUserName())))){
			throw new BadRequestException("usuario: " + cadastro.getUserName() + " já existe");
		}
		Perfil perfil = perfilService.findOne(cadastro.getIdPerfil());
		if(ObjectUtils.isEmpty(perfil)) {
			throw new BadRequestException("idPerfil: " + cadastro.getIdPerfil());
		}
		return perfil;
	}

	private PerfilResponse montaPerfil(Perfil perfilDb) {
		List<ModuloResponse> modulos = moduloPerfilRepository.findDistinctModuloPerfilByPerfil(perfilDb).stream()
				.map(moduloPerfil -> ModuloResponse.builder()
						.idModulo(moduloPerfil.getModulo().getIdModulo())
						.dsModulo(moduloPerfil.getModulo().getDsModulo())
						.build())
				.collect(Collectors.toList());
		return PerfilResponse.builder()
				.idPerfil(perfilDb.getIdPerfil())
				.dsPerfil(perfilDb.getDsPerfil())
				.modulos(modulos)
				.build();
	}

	private Usuario validaUsers(LogarRequest logando) throws LoginException {
		Usuario user = null;
		try {
			user = loginRepository.realizarLogin(logando.getUserName(), md5.cript(logando.getPassword()),
					logando.getCdSistema());
		} catch (EmptyResultDataAccessException e) {
			throw new LoginException(ResponseEnum.SENHA_INCORRETA);
		}
		if (!"A".equals(user.getFlStatus())) {
			throw new LoginException(ResponseEnum.USUARIO_BLOQUEADO);
		}
		return user;
	}
}
