package es.library.databaseinspanish.api.contenido.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import es.library.databaseinspanish.api.security.AuthorizationInterceptor;

@Configuration
public class ContenidoApiSpringConfig {

	@Autowired
	private AnnotationConfigApplicationContext appContext;
	
	@Autowired
	private AuthorizationInterceptor authorizationInterceptor;
	
	@Bean("ContenidoRestTemplate")
	public RestTemplate contenidoRestTemplate(RestTemplateBuilder restTemplateBuilder) {
		return restTemplateBuilder
				.additionalInterceptors(authorizationInterceptor)
				.errorHandler(appContext.getBean(ContenidoApiSpringErrorHandler.class))
				.build();
	}
	
	@Bean("FreeContenidoRestTemplate")
	public RestTemplate freeContenidoRestTemplate(RestTemplateBuilder restTemplateBuilder) {
		return restTemplateBuilder
				.errorHandler(appContext.getBean(ContenidoApiSpringErrorHandler.class))
				.build();
	}
	
}
