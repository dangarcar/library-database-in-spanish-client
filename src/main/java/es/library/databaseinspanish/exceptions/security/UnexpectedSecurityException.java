package es.library.databaseinspanish.exceptions.security;

/**
 * 
 * Excepción para cuando ocurren un error inesperado con la autenticación y autorización
 * 
 * @author Daniel García
 *
 */
public class UnexpectedSecurityException extends RuntimeException {

	public UnexpectedSecurityException(String message, Throwable cause) {
		super(message, cause);
	}

	public UnexpectedSecurityException(String message) {
		super(message);
	}
	
}
