package br.com.facilita.autenticacao.converter;

import br.com.facilita.autenticacao.api.request.SistemRegisterRequest;
import br.com.facilita.autenticacao.model.Sistema;

public class SistemConverter {

	public static Sistema sistemRegisterRequestToSistema(SistemRegisterRequest sistemRegisterRequest) {
		return Sistema.builder()
				.dsSistema(sistemRegisterRequest.getDsSistem())
				.build();
	}
}
