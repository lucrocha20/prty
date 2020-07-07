package br.fatec.prty.domain.exception;

public class AuthorizationException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public AuthorizationException(String message) {
		super(message);
	}
	
	public AuthorizationException(String message, Throwable cause) {
		super(message, cause);
	}
}
