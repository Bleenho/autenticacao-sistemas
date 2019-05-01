package br.com.facilita.autenticacao.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.facilita.autenticacao.api.response.DefaultResponse;

@ControllerAdvice
public class RestExceptionHandlerAdvice extends ResponseEntityExceptionHandler {

	@ExceptionHandler({ LoginException.class })
	public ResponseEntity<DefaultResponse> handleFraudeException(LoginException ex, WebRequest request) {
		return new ResponseEntity<>(ex.getResponse().getResponse(), new HttpHeaders(),
				ex.getResponse().getHttpStatus());
	}

	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<DefaultResponse> handleFraudeException(BadRequestException ex, WebRequest request) {
		String msgRetorno = "VALOR INVÁLIDO PARA: " + ex.getCampo();
		return new ResponseEntity<>(DefaultResponse.builder().sucesso(false).msgRetorno(msgRetorno).build(),
				new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}

}
