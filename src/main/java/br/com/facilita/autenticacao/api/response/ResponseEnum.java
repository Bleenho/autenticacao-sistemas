package br.com.facilita.autenticacao.api.response;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public enum ResponseEnum {

	SENHA_INCORRETA(DefaultResponse.builder()
			.sucesso(false)
			.msgRetorno("Usuário ou senha incorreto")
			.build(), HttpStatus.UNAUTHORIZED),
	USUARIO_BLOQUEADO(DefaultResponse.builder()
			.sucesso(false)
			.msgRetorno("Usuario inválido")
			.build(), HttpStatus.METHOD_NOT_ALLOWED);
	
	
	private ResponseEnum(DefaultResponse response, HttpStatus httpStatus) {
		this.response = response;
		this.httpStatus = httpStatus;
	}
	private DefaultResponse response;
	
	private HttpStatus httpStatus;
}
