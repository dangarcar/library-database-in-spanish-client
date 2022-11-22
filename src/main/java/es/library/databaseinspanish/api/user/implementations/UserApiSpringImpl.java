package es.library.databaseinspanish.api.user.implementations;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import es.library.databaseinspanish.api.security.token.TokenManager;
import es.library.databaseinspanish.api.user.UserApi;
import es.library.databaseinspanish.api.utils.ApiUtils;
import es.library.databaseinspanish.exceptions.contenido.ContenidoNotFoundException;
import es.library.databaseinspanish.exceptions.perfil.IllegalPerfilException;
import es.library.databaseinspanish.exceptions.prestamo.PrestamoNotAllowedException;
import es.library.databaseinspanish.model.contenido.Contenido;
import es.library.databaseinspanish.model.perfil.Perfil;
import es.library.databaseinspanish.model.prestamo.Prestamo;

@Component
public class UserApiSpringImpl implements UserApi {

	@Autowired
	@Qualifier("UserRestTemplate")
	private RestTemplate userRestTemplate;
	
	@Autowired
	@Qualifier("PrestamoRestTemplate")
	private RestTemplate prestamoRestTemplate;
	
	@Autowired
	private ApiUtils apiUtils;
	
	@Override
	public Perfil getMyInfo() {
		URI uri = apiUtils.uriBuilder()
				.path("user")
				.build()
				.toUri();
		
		return userRestTemplate.getForObject(uri, Perfil.class);
	}

	@Override
	public Perfil updateMyPerfil(Perfil perfil) throws IllegalPerfilException {
		URI uri = apiUtils.uriBuilder()
				.path("user")
				.build()
				.toUri();
		
		return userRestTemplate.exchange(
				uri, 
				HttpMethod.PUT, 
				new HttpEntity<Perfil>(perfil), 
				Perfil.class)
			.getBody();
	}

	@Override
	public void logout() {
		URI uri = apiUtils.uriBuilder()
				.path("user/logout")
				.build()
				.toUri();
		
		userRestTemplate.postForObject(uri, null, Void.class);
		
		try {
			TokenManager.getInstance().deleteUserTokens();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void deleteMyPerfil() {
		URI uri = apiUtils.uriBuilder()
				.path("user/delete")
				.build()
				.toUri();
		
		userRestTemplate.delete(uri);
		
		try {
			TokenManager.getInstance().deleteUserTokens();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void prestar(long idContenido) throws ContenidoNotFoundException, PrestamoNotAllowedException {
		URI uri = apiUtils.uriBuilder()
				.path("user/prestar/"+idContenido)
				.build()
				.toUri();
		
		prestamoRestTemplate.postForObject(uri, null, Prestamo.class);
	}

	@Override
	public void devolver(long idContenido) throws ContenidoNotFoundException, PrestamoNotAllowedException {
		URI uri = apiUtils.uriBuilder()
				.path("user/devolver/"+idContenido)
				.build()
				.toUri();
		
		prestamoRestTemplate.postForObject(uri, null, Prestamo.class);
	}

	@Override
	public List<Contenido> getContenidosEnPrestamo() {
		URI uri = apiUtils.uriBuilder()
				.path("user/prestamos")
				.build()
				.toUri();
		
		return prestamoRestTemplate.exchange(
				uri, 
				HttpMethod.GET, 
				null, 
				new ParameterizedTypeReference<List<Contenido>>() {})
			.getBody();
	}

	@Override
	public List<Contenido> getHistorialContenidosPrestados() {
		URI uri = apiUtils.uriBuilder()
				.path("user/prestamos/historial")
				.build()
				.toUri();
		
		return prestamoRestTemplate.exchange(
				uri, 
				HttpMethod.GET, 
				null, 
				new ParameterizedTypeReference<List<Contenido>>() {})
			.getBody();
	}

	@Override
	public List<Prestamo> getPrestamos() {
		URI uri = apiUtils.uriBuilder()
				.path("user/prestamos/todos")
				.build()
				.toUri();
		
		return prestamoRestTemplate.exchange(
				uri, 
				HttpMethod.GET, 
				null, 
				new ParameterizedTypeReference<List<Prestamo>>() {})
			.getBody();
	}

}