package es.library.databaseinspanish.api.security.token;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import es.library.databaseinspanish.api.utils.ApiUtils;
import es.library.databaseinspanish.model.security.TokenPair;

@Component
public class RefreshTokenApi {
	
	@Autowired
	@Qualifier("RefreshTokenRestTemplate")
	private RestTemplate refreshTokenRestTemplate;
	
	@Autowired
	private ApiUtils apiUtils;

	public TokenPair refreshToken(String token) {
		URI uri = apiUtils.uriBuilder()
				.path("auth/token/refresh/"+token)
				.build()
				.toUri();
		
		TokenPair tokenPair = refreshTokenRestTemplate.getForObject(uri, TokenPair.class);
		
		try {
			TokenManager.getInstance().setUserTokens(tokenPair);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		return tokenPair;
	}

}
