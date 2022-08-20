package es.library.databaseinspanish.prestamos.api.implementations;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;

import com.fasterxml.jackson.databind.ObjectMapper;

import es.library.databaseinspanish.ApiError;
import es.library.databaseinspanish.contenido.exceptions.ContenidoNotFoundException;
import es.library.databaseinspanish.perfil.exceptions.PerfilNotFoundException;
import es.library.databaseinspanish.prestamos.exceptions.IllegalPrestamoException;
import es.library.databaseinspanish.prestamos.exceptions.PrestamoNotAllowedException;
import es.library.databaseinspanish.prestamos.exceptions.PrestamoNotFoundException;
import es.library.databaseinspanish.prestamos.exceptions.UnexpectedPrestamoException;

@Component
public class PrestamoApiSpringErrorHandler implements ResponseErrorHandler{

	private static final String PERFIL_PREFIX = "PerfilException: ";
	private static final String CONTENIDO_PREFIX = "ContenidoException: ";
	
	@Autowired
	@Qualifier("jsonMapper")
	private ObjectMapper jsonMapper;
	
	@Override
	public boolean hasError(ClientHttpResponse response) throws IOException {
		return response.getStatusCode().isError();
	}

	@Override
	public void handleError(ClientHttpResponse response) throws IOException {
		ApiError error = jsonMapper.readValue(response.getBody(), ApiError.class);
		
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
