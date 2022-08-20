package es.library.databaseinspanish.contenido.api.implementations;

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
import es.library.databaseinspanish.contenido.exceptions.IllegalContenidoException;
import es.library.databaseinspanish.contenido.exceptions.UnexpectedContenidoException;

@Component
public class ContenidoApiSpringErrorHandler implements ResponseErrorHandler{

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
		case NOT_FOUND:
			throw new ContenidoNotFoundException(error.getMessage());
		case BAD_REQUEST:
			throw new IllegalContenidoException(error.getMessage());
		default:
			throw new UnexpectedContenidoException(error.getMessage());
		}
	}

}
