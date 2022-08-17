package es.library.databaseinspanish.perfil.api.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Configuration
public class PerfilApiSpringConfig {
	
	@Autowired
	private AnnotationConfigApplicationContext appContext;
	
	@Value("${perfil.host}")
	private String host;
	@Value("${perfil.port}")
	private int port;
	@Value("${perfil.scheme}")
	private String scheme;
	
	@Bean("PerfilRestTemplate")
	public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
		return restTemplateBuilder
				.errorHandler(appContext.getBean(PerfilApiSpringErrorHandler.class))
				.build();
	}
	
	@Bean("PerfilUriBuilder")
	public UriComponentsBuilder uriBuilder() {
		return UriComponentsBuilder.newInstance()
				.scheme(scheme)
				.host(host)
				.port(port);
	}
	
}
