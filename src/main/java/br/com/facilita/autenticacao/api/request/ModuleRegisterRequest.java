package br.com.facilita.autenticacao.api.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ModuleRegisterRequest {

	private String describeModule;
	
	private Long idSistema;
}
