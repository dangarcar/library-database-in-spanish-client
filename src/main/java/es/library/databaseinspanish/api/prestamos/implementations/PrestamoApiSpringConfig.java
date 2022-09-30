package es.library.databaseinspanish.api.prestamos.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import es.library.databaseinspanish.api.security.AuthorizationInterceptor;

@Configuration
public class PrestamoApiSpringConfig {

	@Autowired
	private AnnotationConfigApplicationContext appContext;
	
	@Autowired
	private AuthorizationInterceptor authorizationInterceptor;
	
	@Bean("PrestamoRestTemplate")
	public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
		return restTemplateBuilder
				.additionalInterceptors(authorizationInterceptor)
				.errorHandler(appContext.getBean(PrestamoApiSpringErrorHandler.class))
				.build();
	}
	
}
