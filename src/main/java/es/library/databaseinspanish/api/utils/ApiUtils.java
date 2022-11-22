package es.library.databaseinspanish.api.utils;

import java.net.URI;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class ApiUtils {	
	@Value("${server.url}")
	private String url;
	
	private ApiUtils() {}
	
	public synchronized static ApiUtils getInstance() {
		return StaticApplicationContext.getContext().getBean(ApiUtils.class);
	}
	
	public URI getServerUri() {
		return URI.create(url);
	}
	
	public UriComponentsBuilder uriBuilder() {
		return UriComponentsBuilder.fromHttpUrl(url);
	}
	
}
