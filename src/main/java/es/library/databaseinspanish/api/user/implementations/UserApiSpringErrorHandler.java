package es.library.databaseinspanish.api.user.implementations;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import es.library.databaseinspanish.api.SpringApiErrorHandler;
import es.library.databaseinspanish.exceptions.ApiError;
import es.library.databaseinspanish.exceptions.perfil.EmailAlreadyExistPerfilException;
import es.library.databaseinspanish.exceptions.perfil.IllegalPerfilException;
import es.library.databaseinspanish.exceptions.perfil.UnexpectedPerfilException;

@Component
public class UserApiSpringErrorHandler extends SpringApiErrorHandler {

	@Override
	public void handleError(ClientHttpResponse response) throws IOException {
		super.handleError(response);
		
		ApiError error = mapper.readValue(response.getBody(), ApiError.class);
		
		switch (HttpStatus.valueOf(error.getStatusCode())) {
		
		case BAD_REQUEST:
			throw new IllegalPerfilException(error.getMessage());
			
		case CONFLICT:
			throw new EmailAlreadyExistPerfilException(error.getMessage());
		
		default:
			throw new UnexpectedPerfilException(error.getMessage());
		}
	}

}