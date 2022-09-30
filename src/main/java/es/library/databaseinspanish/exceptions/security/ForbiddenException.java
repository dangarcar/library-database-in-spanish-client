package es.library.databaseinspanish.exceptions.security;

/**
 * 
 * Excepción para cuando  la autorización de un usuario falla y no puede hacer lo que sea
 * 
 * @author Daniel García
 *
 */
public class ForbiddenException extends RuntimeException {

	public ForbiddenException(String message, Throwable cause) {
		super(message, cause);
	}

	public ForbiddenException(String message) {
		super(message);
	}

}
