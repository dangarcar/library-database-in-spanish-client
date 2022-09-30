package es.library.databaseinspanish.api.security.implementations;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import es.library.databaseinspanish.api.security.SecurityApi;
import es.library.databaseinspanish.api.security.token.TokenManager;
import es.library.databaseinspanish.api.utils.ApiUtils;
import es.library.databaseinspanish.model.perfil.Perfil;
import es.library.databaseinspanish.model.security.TokenPair;

@Component
public class SecurityApiSpringImpl implements SecurityApi {
	
	@Autowired
	@Qualifier("AuthenticationRestTemplate")
	private RestTemplate authenticationRestTemplate;
	
	@Autowired
	@Qualifier("FreeAuthenticationRestTemplate")
	private RestTemplate freeAuthenticationRestTemplate;
	
	@Autowired
	private ApiUtils apiUtils;

	@Override
	public TokenPair signUp(Perfil perfil) {
		URI uri = apiUtils.uriBuilder()
				.path("auth/signup")
				.build()
				.toUri();
	
		TokenPair tokenPair = freeAuthenticationRestTemplate.postForObject(uri, perfil, TokenPair.class);
		
		try {
			TokenManager.getInstance().setUserTokens(tokenPair);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		return tokenPair;
	}

	@Override
	public TokenPair login(LoginCredentials login) {
		URI uri = apiUtils.uriBuilder()
				.path("auth/login")
				.build()
				.toUri();
		
		TokenPair tokenPair = freeAuthenticationRestTemplate.postForObject(uri, login, TokenPair.class);
		
		try {
			TokenManager.getInstance().setUserTokens(tokenPair);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		return tokenPair;
	}

	@Override
	public void deletePerfil(String username) {
		URI uri = apiUtils.uriBuilder()
				.path("auth/delete/"+username)
				.build()
				.toUri();

		authenticationRestTemplate.delete(uri);

		try {
			TokenManager.getInstance().deleteUserTokens();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void logout(String username) {
		URI uri = apiUtils.uriBuilder()
				.path("auth/logout/"+username)
				.build()
				.toUri();

		authenticationRestTemplate.put(uri, null);

		try {
			TokenManager.getInstance().deleteUserTokens();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
