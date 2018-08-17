package br.com.facilita.autenticacao.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.facilita.autenticacao.api.request.CadastroUsuarioRequest;
import br.com.facilita.autenticacao.api.request.LogarRequest;
import br.com.facilita.autenticacao.api.response.LogarResponse;
import br.com.facilita.autenticacao.api.response.PerfilResponse;
import br.com.facilita.autenticacao.api.response.ResponseEnum;
import br.com.facilita.autenticacao.converter.UsuarioConverter;
import br.com.facilita.autenticacao.exception.LoginException;
import br.com.facilita.autenticacao.model.Sistema;
import br.com.facilita.autenticacao.model.SolicitacaoAcesso;
import br.com.facilita.autenticacao.model.Usuario;
import br.com.facilita.autenticacao.model.UsuarioPerfil;
import br.com.facilita.autenticacao.repository.SolicitacaoAcessoRepository;
import br.com.facilita.autenticacao.repository.UsuarioPerfilRepository;
import br.com.facilita.autenticacao.repository.UsuarioRepository;
import br.com.facilita.autenticacao.service.UsuarioService;
import br.com.facilita.autenticacao.util.md5;

@Service
public class UsuarioServiceImpl implements UsuarioService{
	
	private UsuarioRepository usuarioRepository;
	
	private SolicitacaoAcessoRepository solicitacaoAcessoRepository ;
	
	private UsuarioPerfilRepository usuarioPerfilRepository;
	
	@Autowired
	public UsuarioServiceImpl (UsuarioRepository usuarioRepository
			,SolicitacaoAcessoRepository solicitacaoAcessoRepository
			,UsuarioPerfilRepository usuarioPerfilRepository) {
		this.usuarioRepository = usuarioRepository;
		this.solicitacaoAcessoRepository = solicitacaoAcessoRepository ;
		this.usuarioPerfilRepository = usuarioPerfilRepository;
	}

	@Override
	public LogarResponse cadastrarUsuario(CadastroUsuarioRequest cadastro) {
		Usuario usuario = UsuarioConverter.cadastroUsuarioRequestToUsuario(cadastro);

		usuario.setDsSenha(md5.cript(usuario.getDsSenha()));
		usuario.setFlStatus("A");
		usuario.setDtAlteracao(new Date());
		
		return UsuarioConverter.usuarioToLogarResponse(usuarioRepository.save(usuario));
	}

	@Override
	public LogarResponse logar(LogarRequest logando) throws LoginException {
		
		Usuario user = validaUsers(usuarioRepository
				.findByDsSenhaAndCdUsuario(md5
						.cript(logando.getPassword()),logando.getUserName()));
		
		SolicitacaoAcesso solicitacaoAcesso = solicitacaoAcessoRepository
				.save(SolicitacaoAcesso.builder()
						.dsToken(md5.cript(String.valueOf(new Date().getTime())))
						.dtToken(new Date())
						.sistema(Sistema.builder().idSistema(logando.getCdSistema()).build())
						.usuario(user)
						.build());
		
		List<PerfilResponse> perfis = montaPerfil(user);
		
		return LogarResponse.builder()
				.dsToken(solicitacaoAcesso.getDsToken())
				.userName(user.getCdUsuario())
				.perfis(perfis)
				.build();
	}

	private List<PerfilResponse> montaPerfil(Usuario user) {
		List<UsuarioPerfil> usuarioPerfis = usuarioPerfilRepository.findByUsuario(user);
		List<PerfilResponse> perfisResponse = new ArrayList<>();
		for(UsuarioPerfil userPerfil : usuarioPerfis ) {
			perfisResponse.add(PerfilResponse.builder()
					.dsPerfil(userPerfil.getPerfil().getDsPerfil())
					.idPerfil(userPerfil.getPerfil().getIdPerfil())
					.build());
		}
		return perfisResponse;
	}

	private Usuario validaUsers(List<Usuario> users) throws LoginException {
		if(users.isEmpty()) {
			throw new LoginException(ResponseEnum.SENHA_INCORRETA);
		}
		if(users.get(0).getFlStatus() == null || users.get(0).getFlStatus().equals("I")) {
			throw new LoginException(ResponseEnum.USUARIO_BLOQUEADO);
		}
		return users.get(0);
	}

}
