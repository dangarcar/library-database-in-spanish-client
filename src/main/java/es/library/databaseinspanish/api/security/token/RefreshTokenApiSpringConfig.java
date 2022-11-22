package es.library.databaseinspanish.api.security.token;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RefreshTokenApiSpringConfig {

	@Autowired
	private AnnotationConfigApplicationContext appContext;
	
	@Bean("RefreshTokenRestTemplate")
	public RestTemplate refreshTokenRestTemplate(RestTemplateBuilder restTemplateBuilder) {
		return restTemplateBuilder
				.errorHandler(appContext.getBean(RefreshTokenApiSpringErrorHandler.class))
				.build();
	}
	
}
