package es.library.databaseinspanish.exceptions.perfil;

/**
 * Excepcion para cuando un perfil tiene algun parametro no permitido
 * @author Daniel Garcia
 *
 */
public class IllegalPerfilException extends RuntimeException{

	public IllegalPerfilException(String message, Throwable cause) {
		super(message, cause);
	}

	public IllegalPerfilException(String message) {
		super(message);
	}

}
