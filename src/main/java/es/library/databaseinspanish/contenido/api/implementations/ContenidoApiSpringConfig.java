package es.library.databaseinspanish.contenido.api.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Configuration
public class ContenidoApiSpringConfig {

	@Autowired
	private AnnotationConfigApplicationContext appContext;
	
	@Value("${contenido.host}")
	private String host;
	@Value("${contenido.port}")
	private int port;
	@Value("${contenido.scheme}")
	private String scheme;
	
	@Bean("ContenidoRestTemplate")
	public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
		return restTemplateBuilder
				.errorHandler(appContext.getBean(ContenidoApiSpringErrorHandler.class))
				.build();
	}
	
	@Bean("ContenidoUriBuilder")
	public UriComponentsBuilder uriBuilder() {
		return UriComponentsBuilder.newInstance()
				.scheme(scheme)
				.host(host)
				.port(port);
	}
	
}
