package es.library.databaseinspanish.exceptions.security;

/**
 * Excepción para cuando el perfil no está autorizado
 * 
 * @author Daniel García
 *
 */
public class UnathorizedException extends RuntimeException {

	public UnathorizedException(String message, Throwable cause) {
		super(message, cause);
	}

	public UnathorizedException(String message) {
		super(message);
	}
	
}
