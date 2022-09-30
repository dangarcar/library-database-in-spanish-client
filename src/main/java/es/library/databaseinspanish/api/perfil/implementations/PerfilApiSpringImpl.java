package es.library.databaseinspanish.api.perfil.implementations;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import es.library.databaseinspanish.api.perfil.PerfilApi;
import es.library.databaseinspanish.api.perfil.dto.PerfilParamsDto;
import es.library.databaseinspanish.api.utils.ApiUtils;
import es.library.databaseinspanish.exceptions.perfil.IllegalPerfilException;
import es.library.databaseinspanish.exceptions.perfil.PerfilNotFoundException;
import es.library.databaseinspanish.exceptions.perfil.UnexpectedPerfilException;
import es.library.databaseinspanish.model.perfil.Perfil;
import es.library.databaseinspanish.model.perfil.Roles;

@Component
public class PerfilApiSpringImpl implements PerfilApi{
	
	@Autowired
	@Qualifier("PerfilRestTemplate")
	private RestTemplate restTemplate;
	
	@Autowired
	private ApiUtils apiUtils;

	@Override
	public Perfil addPerfil(Perfil perfil){
		URI uri = apiUtils.uriBuilder()
				.path("perfiles")
				.build()
				.toUri();

		return restTemplate.postForObject(uri, perfil, Perfil.class);
	}

	@Override
	public void deletePerfil(long id){
		URI uri = apiUtils.uriBuilder()
				.path("perfiles/"+id)
				.build()
				.toUri();
		
		restTemplate.delete(uri);
	}

	@Override
	public Perfil updatePerfil(long id, Perfil perfil)
			throws IllegalPerfilException, PerfilNotFoundException, UnexpectedPerfilException {
		URI uri = apiUtils.uriBuilder()
				.path("perfiles/"+id)
				.build()
				.toUri();
		
		return restTemplate.exchange(
				uri, 
				HttpMethod.PUT, 
				new HttpEntity<Perfil>(perfil), 
				Perfil.class)
			.getBody();
	}

	@Override
	public void setRole(long id, Roles roles) throws PerfilNotFoundException, UnexpectedPerfilException {
		URI uri = apiUtils.uriBuilder()
				.path("perfiles/roles/"+id+"/"+roles.toString().replace("ROLE_", ""))
				.build()
				.toUri();
		
		restTemplate.put(uri, null);
	}
	
	//BÚSQUEDA
	
	@Override
	public Perfil getPerfilById(long id) throws PerfilNotFoundException, UnexpectedPerfilException {
		URI uri = apiUtils.uriBuilder()
				.path("perfiles/search/id/"+id)
				.build()
				.toUri();
		
		return restTemplate.getForObject(uri, Perfil.class);
	}

	@Override
	public Perfil getPerfilByUsername(String username) throws PerfilNotFoundException, UnexpectedPerfilException {
		URI uri = apiUtils.uriBuilder()
				.path("perfiles/search/username/"+username)
				.build()
				.toUri();
		
		return restTemplate.getForObject(uri, Perfil.class);
	}
	
	@Override
	public List<Perfil> getPerfilByParams(PerfilParamsDto dto) {
		UriComponentsBuilder builder = apiUtils.uriBuilder()
				.path("perfiles/search");
		
		if(dto.getQuery() != null)			builder.queryParam("q", dto.getQuery());
		if(dto.getNombre() != null) 		builder.queryParam("nombre", dto.getNombre());
		if(dto.getEmail() != null) 			builder.queryParam("email", dto.getEmail());
		if(dto.getFromNacimiento() != null) builder.queryParam("fromNacimiento", dto.getFromNacimiento());
		if(dto.getToNacimiento() != null) 	builder.queryParam("toNacimiento", dto.getToNacimiento());
		if(dto.getRoles() != null) 			builder.queryParam("role", dto.getRoles());
		
		URI uri = builder
				.build()
				.toUri();
		
		return restTemplate.exchange(
				uri, 
				HttpMethod.GET, 
				null, 
				new ParameterizedTypeReference<List<Perfil>>() {})
			.getBody();
	}
	
}
