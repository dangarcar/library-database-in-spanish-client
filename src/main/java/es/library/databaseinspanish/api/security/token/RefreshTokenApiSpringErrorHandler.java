package es.library.databaseinspanish.api.security.token;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;

import com.fasterxml.jackson.databind.ObjectMapper;

import es.library.databaseinspanish.exceptions.ApiError;
import es.library.databaseinspanish.exceptions.security.RefreshTokenExpiredException;
import es.library.databaseinspanish.exceptions.security.RefreshTokenNotFoundException;
import es.library.databaseinspanish.exceptions.security.UnexpectedSecurityException;

@Component
public class RefreshTokenApiSpringErrorHandler implements ResponseErrorHandler {

	@Autowired
	private ObjectMapper mapper;
	
	@Override
	public boolean hasError(ClientHttpResponse response) throws IOException {
		return response.getStatusCode().isError();
	}
	
	@Override
	public void handleError(ClientHttpResponse response) throws IOException {		
		ApiError error = mapper.readValue(response.getBody(), ApiError.class);
		
		switch (HttpStatus.valueOf(error.getStatusCode())) {
		case GONE:
			throw new RefreshTokenExpiredException(error.getMessage());
		case NOT_FOUND:
			throw new RefreshTokenNotFoundException(error.getMessage());
		default:
			throw new UnexpectedSecurityException(error.getMessage());
		}
	}
	
}