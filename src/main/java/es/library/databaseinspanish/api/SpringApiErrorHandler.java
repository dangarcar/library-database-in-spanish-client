package es.library.databaseinspanish.api;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

import com.fasterxml.jackson.databind.ObjectMapper;

import es.library.databaseinspanish.exceptions.security.ForbiddenException;
import es.library.databaseinspanish.exceptions.security.UnathorizedException;

public class SpringApiErrorHandler implements ResponseErrorHandler {
	
	protected static final String PERFIL_PREFIX = "PerfilException: ";
	protected static final String CONTENIDO_PREFIX = "ContenidoException: ";
	
	@Autowired
	protected ObjectMapper mapper;
	
	@Override
	public boolean hasError(ClientHttpResponse response) throws IOException {
		return response.getStatusCode().isError();
	}

	@SuppressWarnings("incomplete-switch")
	@Override
	public void handleError(ClientHttpResponse response) throws IOException {		
		switch (response.getStatusCode()) {
		case UNAUTHORIZED:
			throw new UnathorizedException("No tiene autorización para hacer esto");
		case FORBIDDEN:
			throw new ForbiddenException("No tiene permiso para hacer esto.");
		}
	}
	
}
