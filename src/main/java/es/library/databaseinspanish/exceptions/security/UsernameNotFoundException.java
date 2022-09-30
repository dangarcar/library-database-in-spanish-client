package es.library.databaseinspanish.exceptions.security;

/**
 * 
 * Excepción para cuando el usuario no existe en la base de datos
 * 
 * @author Daniel García
 *
 */
public class UsernameNotFoundException extends RuntimeException {

	public UsernameNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public UsernameNotFoundException(String message) {
		super(message);
	}

}
