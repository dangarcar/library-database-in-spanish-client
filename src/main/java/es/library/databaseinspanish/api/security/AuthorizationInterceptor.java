package es.library.databaseinspanish.api.security;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import es.library.databaseinspanish.api.security.token.RefreshTokenApi;
import es.library.databaseinspanish.api.security.token.TokenManager;
import es.library.databaseinspanish.exceptions.ApiError;
import es.library.databaseinspanish.exceptions.security.RefreshTokenExpiredException;
import es.library.databaseinspanish.exceptions.security.RefreshTokenNotFoundException;
import es.library.databaseinspanish.exceptions.security.UnathorizedException;

@Component
public class AuthorizationInterceptor implements ClientHttpRequestInterceptor {

	private Logger logger = LogManager.getLogger();
	
	@Autowired
	private RefreshTokenApi refreshTokenApi;
	
	@Autowired
	private ObjectMapper mapper;
	
	private AuthorizationInterceptor() {}
	
	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution clientHttpRequestExecution)
			throws IOException {
		logger.info("Ejecutando "+getClass().getName()+" interceptor...");
		try {
			if(!request.getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
					request.getHeaders().setBearerAuth(TokenManager.getInstance().getUserTokens().getAccessToken());
					logger.info("Se ha añadido el access token a la request");
			}
		} catch (IOException e) {
			logger.debug(e);
		}	
		catch (Exception e) {
			logger.error("Error en la autenticación",e);
			throw new UnathorizedException("Error mientras se intentaba autenticar",e);
		}
		
		ClientHttpResponse response = clientHttpRequestExecution.execute(request, body);
		
		if(response.getStatusCode().value() == HttpStatus.UNAUTHORIZED.value()) {
			ApiError error = mapper.readValue(response.getBody(), ApiError.class);
			logger.info("La request no ha sido autorizada, por lo que se procederá a crear un nuevo token");
			try {
				refreshTokenApi.refreshToken(TokenManager.getInstance().getUserTokens().getRefreshToken());
				request.getHeaders().setBearerAuth(TokenManager.getInstance().getUserTokens().getAccessToken());
			} 
			catch (RefreshTokenExpiredException|RefreshTokenNotFoundException e) {
				throw e;
			}
			catch (Exception e) {
				throw new UnathorizedException(error.getMessage(),e);
			}
			logger.info("Se ha mandado la request de nuevo");
			
			return clientHttpRequestExecution.execute(request, body);
		}
		
		return response;
	}
}
