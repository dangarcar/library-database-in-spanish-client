package es.library.databaseinspanish.exceptions.security;

/**
 * Una excepción para cuando no es válida la contraseña
 * 
 * @author Daniel García
 *
 */
public class NotValidPasswordException extends RuntimeException{

	public NotValidPasswordException(String message, Throwable cause) {
		super(message, cause);
	}

	public NotValidPasswordException(String message) {
		super(message);
	}
	
}