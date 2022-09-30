package es.library.databaseinspanish.exceptions.perfil;

/**
 * 
 * Excepción para los errores del servidor de los perfiles
 * @author danie
 *
 */
public class UnexpectedPerfilException extends RuntimeException{

	public UnexpectedPerfilException(String message, Throwable cause) {
		super(message, cause);
	}

	public UnexpectedPerfilException(String message) {
		super(message);
	}

	public UnexpectedPerfilException(Throwable cause) {
		super(cause);
	}
	
}
