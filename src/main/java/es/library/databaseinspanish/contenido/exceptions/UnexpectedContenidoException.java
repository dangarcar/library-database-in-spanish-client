package es.library.databaseinspanish.contenido.exceptions;

/**
 * Una excepcion para cuando ha sucedido un error inseperado del lado del servidor
 * @author Daniel Garcia
 *
 */
public class UnexpectedContenidoException extends RuntimeException{

	public UnexpectedContenidoException(String message, Throwable cause) {
		super(message, cause);
	}

	public UnexpectedContenidoException(String message) {
		super(message);
	}
	
}
