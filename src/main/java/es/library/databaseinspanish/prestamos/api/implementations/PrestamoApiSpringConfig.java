package es.library.databaseinspanish.prestamos.api.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Configuration
public class PrestamoApiSpringConfig {

	@Autowired
	private AnnotationConfigApplicationContext appContext;
	
	@Value("${prestamo.host}")
	private String host;
	@Value("${prestamo.port}")
	private int port;
	@Value("${prestamo.scheme}")
	private String scheme;
	
	@Bean("PrestamoRestTemplate")
	public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
		return restTemplateBuilder
				.errorHandler(appContext.getBean(PrestamoApiSpringErrorHandler.class))
				.build();
	}
	
	@Bean("PrestamoUriBuilder")
	public UriComponentsBuilder uriBuilder() {
		return UriComponentsBuilder.newInstance()
				.scheme(scheme)
				.host(host)
				.port(port);
	}
	
}
