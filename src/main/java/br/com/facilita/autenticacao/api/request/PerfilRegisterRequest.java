package br.com.facilita.autenticacao.api.request;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PerfilRegisterRequest {

	private String describePerfil;
	
	private List<Long> idsModulo;
	
}
