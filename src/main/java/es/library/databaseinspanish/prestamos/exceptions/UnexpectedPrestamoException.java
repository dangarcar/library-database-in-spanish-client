package es.library.databaseinspanish.prestamos.exceptions;

/**
 * Una excepcion para cuando ha sucedido un error inesperado del lado del servidor de los préstamos
 * @author Daniel Garcia
 *
 */
public class UnexpectedPrestamoException extends RuntimeException{

	public UnexpectedPrestamoException(String message, Throwable cause) {
		super(message, cause);
	}

	public UnexpectedPrestamoException(String message) {
		super(message);
	}
	
}
