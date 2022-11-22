package es.library.databaseinspanish.exceptions.security;

/**
 * Excepción para cuando el refresh token esta caducado
 * 
 * @author Daniel García
 *
 */
public class RefreshTokenExpiredException extends RuntimeException {

	public RefreshTokenExpiredException(String message, Throwable cause) {
		super(message, cause);
	}

	public RefreshTokenExpiredException(String message) {
		super(message);
	}

}