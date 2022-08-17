package es.library.databaseinspanish.perfil.api.implementations;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;

import com.fasterxml.jackson.databind.ObjectMapper;

import es.library.databaseinspanish.apiclient.ApiError;
import es.library.databaseinspanish.perfil.exceptions.EmailAlreadyExistPerfilException;
import es.library.databaseinspanish.perfil.exceptions.IllegalPerfilException;
import es.library.databaseinspanish.perfil.exceptions.PerfilNotFoundException;
import es.library.databaseinspanish.perfil.exceptions.UnexpectedPerfilException;

@Component
public class PerfilApiSpringErrorHandler implements ResponseErrorHandler{

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
		
		switch(HttpStatus.valueOf(error.getStatusCode())) {
		
		case CONFLICT: 
			throw new EmailAlreadyExistPerfilException(error.getMessage());
		
		case NOT_FOUND:
			throw new PerfilNotFoundException(error.getMessage());
		
		case BAD_REQUEST:
			throw new IllegalPerfilException(error.getMessage());
		
		default:
			throw new UnexpectedPerfilException(error.getMessage());
		
		}
	}

}
