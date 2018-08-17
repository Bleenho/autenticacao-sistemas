package br.com.facilita.autenticacao.exception;

import br.com.facilita.autenticacao.api.response.ResponseEnum;
import lombok.Getter;

@Getter
public class LoginException extends Exception {

	private static final long serialVersionUID = 1L;
	
	private ResponseEnum response;
	

	public LoginException(ResponseEnum response) {
		super();
		this.response = response;
	}

	public LoginException() {
		super();
	}
}
