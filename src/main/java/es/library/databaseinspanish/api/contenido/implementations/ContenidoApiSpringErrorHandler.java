package es.library.databaseinspanish.api.contenido.implementations;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import es.library.databaseinspanish.api.SpringApiErrorHandler;
import es.library.databaseinspanish.exceptions.ApiError;
import es.library.databaseinspanish.exceptions.contenido.ContenidoNotFoundException;
import es.library.databaseinspanish.exceptions.contenido.IllegalContenidoException;
import es.library.databaseinspanish.exceptions.contenido.UnexpectedContenidoException;

@Component
public class ContenidoApiSpringErrorHandler extends SpringApiErrorHandler {

	@Override
	public void handleError(ClientHttpResponse response) throws IOException {
		super.handleError(response);
		
		ApiError error = mapper.readValue(response.getBody(), ApiError.class);
		
		switch (HttpStatus.valueOf(error.getStatusCode())) {
		case NOT_FOUND:
			throw new ContenidoNotFoundException(error.getMessage());
		case BAD_REQUEST:
			throw new IllegalContenidoException(error.getMessage());
		default:
			throw new UnexpectedContenidoException(error.getMessage());
		}
	}

}
