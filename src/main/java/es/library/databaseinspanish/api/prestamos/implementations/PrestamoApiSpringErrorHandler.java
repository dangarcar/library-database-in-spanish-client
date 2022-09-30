package es.library.databaseinspanish.api.prestamos.implementations;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import es.library.databaseinspanish.api.SpringApiErrorHandler;
import es.library.databaseinspanish.exceptions.ApiError;
import es.library.databaseinspanish.exceptions.contenido.ContenidoNotFoundException;
import es.library.databaseinspanish.exceptions.perfil.PerfilNotFoundException;
import es.library.databaseinspanish.exceptions.perfil.PrestamoNotFoundException;
import es.library.databaseinspanish.exceptions.prestamo.IllegalPrestamoException;
import es.library.databaseinspanish.exceptions.prestamo.PrestamoNotAllowedException;
import es.library.databaseinspanish.exceptions.prestamo.UnexpectedPrestamoException;

@Component
public class PrestamoApiSpringErrorHandler extends SpringApiErrorHandler {

	@Override
	public void handleError(ClientHttpResponse response) throws IOException {
		super.handleError(response);
		
		ApiError error = mapper.readValue(response.getBody(), ApiError.class);
		
		switch (HttpStatus.valueOf(error.getStatusCode())) {
		case BAD_REQUEST:
			throw new IllegalPrestamoException(error.getMessage());
		
		case NOT_FOUND:
			if(error.getMessage().startsWith(PERFIL_PREFIX)) 
				throw new PerfilNotFoundException(error.getMessage().replace(PERFIL_PREFIX, ""));
			
			if(error.getMessage().startsWith(CONTENIDO_PREFIX)) 
				throw new ContenidoNotFoundException(error.getMessage().replace(CONTENIDO_PREFIX, ""));
			
			throw new PrestamoNotFoundException(error.getMessage());
			
		case CONFLICT:
			throw new PrestamoNotAllowedException(error.getMessage());
		
		default:
			throw new UnexpectedPrestamoException(error.getMessage());
		}
	}

}
