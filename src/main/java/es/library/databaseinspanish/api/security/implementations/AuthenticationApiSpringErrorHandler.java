package es.library.databaseinspanish.api.security.implementations;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import es.library.databaseinspanish.api.SpringApiErrorHandler;
import es.library.databaseinspanish.exceptions.ApiError;
import es.library.databaseinspanish.exceptions.perfil.EmailAlreadyExistPerfilException;
import es.library.databaseinspanish.exceptions.security.NotValidPasswordException;
import es.library.databaseinspanish.exceptions.security.UnexpectedSecurityException;
import es.library.databaseinspanish.exceptions.security.UsernameNotFoundException;

@Component
public class AuthenticationApiSpringErrorHandler extends SpringApiErrorHandler {

	@Override
	public void handleError(ClientHttpResponse response) throws IOException {
		super.handleError(response);
		
		ApiError error = mapper.readValue(response.getBody(), ApiError.class);
		
		switch (HttpStatus.valueOf(error.getStatusCode())) {
		case BAD_REQUEST:
			throw new NotValidPasswordException(error.getMessage());
		case NOT_FOUND:
			throw new UsernameNotFoundException(error.getMessage());
		case CONFLICT:
			throw new EmailAlreadyExistPerfilException(error.getMessage());
		default:
			throw new UnexpectedSecurityException(error.getMessage());
		}
	}
	
}