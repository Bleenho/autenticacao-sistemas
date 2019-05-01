package br.com.facilita.autenticacao.exception;

public class BadRequestException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3272560221203947516L;
	private String campo;
	
	public BadRequestException(String campo) {
		super();
		this.campo = campo;
	}

	public String getCampo() {
		return campo;
	}
}
