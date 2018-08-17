package br.com.facilita.autenticacao.api.response;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(Include.NON_NULL)
public class DefaultResponse implements Serializable {

	/**
     * 
     */
    private static final long serialVersionUID = -433145155497001532L;

    private boolean sucesso ;
	
	private Object dado;
	
	private String msgRetorno;
}
