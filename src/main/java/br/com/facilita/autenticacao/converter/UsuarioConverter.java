package br.com.facilita.autenticacao.converter;

import br.com.facilita.autenticacao.api.request.CadastroUsuarioRequest;
import br.com.facilita.autenticacao.api.response.LogarResponse;
import br.com.facilita.autenticacao.model.Perfil;
import br.com.facilita.autenticacao.model.Usuario;
import lombok.Builder;

@Builder
public class UsuarioConverter {
	
	private UsuarioConverter() {}

	public static Usuario cadastroUsuarioRequestToUsuario(CadastroUsuarioRequest cadastro) {
		return Usuario.builder()
				.nmUsuario(cadastro.getNmUsuario())
				.cdUsuario(cadastro.getUserName())
				.dsSenha(cadastro.getPassword())
				.dsEmail(cadastro.getDsEmail())
				.perfil(Perfil.builder().idPerfil(cadastro.getIdPerfil()).build())
				.build();
	}
	
	public static LogarResponse usuarioToLogarResponse(Usuario usuario) {
		return LogarResponse.builder()
				.userName(usuario.getCdUsuario())
				.build();
	}
}
