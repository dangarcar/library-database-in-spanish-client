package es.library.databaseinspanish.exceptions.perfil;

/**
 * Excepción para cuando el rol que se está indicando no existe en la biblioteca
 * @author Daniel García
 *
 */
public class RoleNotFoundException extends RuntimeException{

	public RoleNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public RoleNotFoundException(String message) {
		super(message);
	}
}
