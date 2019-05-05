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
public class ModuleIntoPerfilRequest {

	private Long idPerfil;
	private List<Long> idsModulo;
	private AcaoModuloPerfil acao;
	
}
