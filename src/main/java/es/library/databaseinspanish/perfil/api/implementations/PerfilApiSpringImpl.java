package es.library.databaseinspanish.perfil.api.implementations;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import es.library.databaseinspanish.perfil.Perfil;
import es.library.databaseinspanish.perfil.api.PerfilApi;
import es.library.databaseinspanish.perfil.exceptions.PerfilNotFoundException;
import es.library.databaseinspanish.perfil.exceptions.UnexpectedPerfilException;

@Component
public class PerfilApiSpringImpl implements PerfilApi{
	
	@Autowired
	@Qualifier("PerfilRestTemplate")
	private RestTemplate restTemplate;
	
	@Autowired
	@Qualifier("PerfilUriBuilder")
	private UriComponentsBuilder uriBuilder;
	
	@Override
	public List<Perfil> getPerfilByQuery(String query) {
		URI uri = uriBuilder
				.path("perfiles/search")
				.queryParam("q", query)
				.build()
				.toUri();
		
		return restTemplate.exchange(
				uri, 
				HttpMethod.GET,
				null,
				new ParameterizedTypeReference<List<Perfil>>() {})
			.getBody();
	}

	@Override
	public Perfil getPerfilById(long id) throws PerfilNotFoundException, UnexpectedPerfilException {
		URI uri = uriBuilder
				.path("perfiles/search/id/"+id)
				.build()
				.toUri();
		
		return restTemplate.getForObject(uri, Perfil.class);
	}

	@Override
	public Perfil addPerfil(Perfil perfil){
		URI uri = uriBuilder
				.path("perfiles")
				.build()
				.toUri();

		return restTemplate.postForObject(uri, perfil, Perfil.class);
	}

	@Override
	public void makePerfilAdmin(long id){
		URI uri = uriBuilder
				.path("perfiles/admin/"+id)
				.build()
				.toUri();
		
		restTemplate.put(uri, null);
	}

	@Override
	public void makeAdminPerfil(long id){
		URI uri = uriBuilder
				.path("perfiles/perfil/"+id)
				.build()
				.toUri();
		
		restTemplate.put(uri, null);
	}

	@Override
	public void deletePerfil(long id){
		URI uri = uriBuilder
				.path("perfiles/"+id)
				.build()
				.toUri();
		
		restTemplate.delete(uri);
	}
	
	@Override
	public List<Perfil> getPerfilByParams(String nombre, String email, LocalDate fromNacimiento, LocalDate toNacimiento, Boolean admin) {
		UriComponentsBuilder uri = uriBuilder.path("perfiles/search");
		
		if(nombre != null) uri.queryParam("nombre", nombre);
		if(email != null) uri.queryParam("email", email);
		if(fromNacimiento != null) uri.queryParam("fromNacimiento", fromNacimiento);
		if(toNacimiento != null) uri.queryParam("toNacimiento", toNacimiento);
		if(admin != null) uri.queryParam("admin", admin);
		
		return restTemplate.exchange(
				uri.build().toUri(), 
				HttpMethod.GET, 
				null, 
				new ParameterizedTypeReference<List<Perfil>>() {})
			.getBody();
	}
	
}
