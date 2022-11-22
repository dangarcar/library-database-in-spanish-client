package es.library.databaseinspanish.exceptions.prestamo;

public class PrestamoNotAllowedException extends RuntimeException{

	public PrestamoNotAllowedException(String message, Throwable cause) {
		super(message, cause);
	}

	public PrestamoNotAllowedException(String message) {
		super(message);
	}

	
}
