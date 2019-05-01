package br.com.facilita.autenticacao.service;

import br.com.facilita.autenticacao.api.request.CadastroUsuarioRequest;
import br.com.facilita.autenticacao.api.request.LogarRequest;
import br.com.facilita.autenticacao.api.response.LogarResponse;
import br.com.facilita.autenticacao.exception.LoginException;

public interface UsuarioService {

	Long cadastrarUsuario(CadastroUsuarioRequest cadastro);
	
	LogarResponse logar(LogarRequest logando) throws LoginException;
}
