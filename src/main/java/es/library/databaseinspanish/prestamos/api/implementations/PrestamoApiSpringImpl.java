package es.library.databaseinspanish.prestamos.api.implementations;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import es.library.databaseinspanish.contenido.Contenido;
import es.library.databaseinspanish.contenido.exceptions.ContenidoNotFoundException;
import es.library.databaseinspanish.perfil.exceptions.PerfilNotFoundException;
import es.library.databaseinspanish.prestamos.Prestamo;
import es.library.databaseinspanish.prestamos.api.PrestamoApi;
import es.library.databaseinspanish.prestamos.exceptions.PrestamoNotAllowedException;
import es.library.databaseinspanish.prestamos.exceptions.PrestamoNotFoundException;
import es.library.databaseinspanish.prestamos.exceptions.UnexpectedPrestamoException;

@Component
public class PrestamoApiSpringImpl implements PrestamoApi{

	@Autowired
	@Qualifier("PrestamoRestTemplate")
	private RestTemplate restTemplate;
	
	@Autowired
	@Qualifier("PrestamoUriBuilder")
	private UriComponentsBuilder uriBuilder;
	
	@Override
	public Prestamo getPrestamoById(long id) throws PrestamoNotFoundException, UnexpectedPrestamoException {
		URI uri = uriBuilder
				.path("prestamos/search/id/"+id)
				.build()
				.toUri();
		
		return restTemplate.getForObject(uri, Prestamo.class);
	}

	@Override
	public List<Prestamo> getPrestamosByIdContenido(long idContenido, Boolean disponible)
			throws ContenidoNotFoundException, UnexpectedPrestamoException {
		var uri = uriBuilder
				.path("/prestamos/search/contenido/"+idContenido);
		
		if(disponible != null) uri.queryParam("d", disponible);
		
		return restTemplate.exchange(
				uri.build().toUri(), 
				HttpMethod.GET, 
				null, 
				new ParameterizedTypeReference<List<Prestamo>>() {})
			.getBody();
	}

	@Override
	public List<Prestamo> getPrestamosByIdPerfil(long idPerfil, Boolean disponible)
			throws PerfilNotFoundException, UnexpectedPrestamoException {
		var uri = uriBuilder
				.path("/prestamos/search/perfil/"+idPerfil);
		
		if(disponible != null) uri.queryParam("d", disponible);
		
		return restTemplate.exchange(
				uri.build().toUri(), 
				HttpMethod.GET, 
				null, 
				new ParameterizedTypeReference<List<Prestamo>>() {})
			.getBody();
	}

	@Override
	public List<Contenido> getContenidosPrestadosByIdPerfil(long idPerfil, Boolean disponible)
			throws ContenidoNotFoundException, PerfilNotFoundException, UnexpectedPrestamoException {
		var uri = uriBuilder
				.path("/prestamos/search/perfilCont/"+idPerfil);
		
		if(disponible != null) uri.queryParam("d", disponible);
		
		return restTemplate.exchange(
				uri.build().toUri(), 
				HttpMethod.GET, 
				null, 
				new ParameterizedTypeReference<List<Contenido>>() {})
			.getBody();
	}

	@Override
	public List<Prestamo> getPrestamosByParams(Long idContenido, Long idPerfil, Integer minDias, Integer maxDias,
			LocalDateTime fromPrestamo, LocalDateTime toPrestamo, LocalDateTime fromDevolucion,
			LocalDateTime toDevolucion, Boolean disponible) {
		var uri = uriBuilder
				.path("/prestamos/search");
		
		if(idContenido != null) 	uri.queryParam("contenido", idContenido);
		if(idPerfil != null) 		uri.queryParam("perfil", idPerfil);
		if(minDias != null) 		uri.queryParam("minDias", minDias);
		if(maxDias != null) 		uri.queryParam("maxDias", maxDias);
		if(fromPrestamo != null) 	uri.queryParam("fromPrestamo", fromPrestamo);
		if(toPrestamo != null) 		uri.queryParam("toPrestamo", toPrestamo);
		if(fromDevolucion != null)	uri.queryParam("fromDevolucion", fromDevolucion);
		if(toDevolucion != null) 	uri.queryParam("toDevolucion", toDevolucion);
		if(disponible != null) 		uri.queryParam("d", disponible);
				
		return restTemplate.exchange(
				uri.build().toUri(), 
				HttpMethod.GET, 
				null, 
				new ParameterizedTypeReference<List<Prestamo>>() {})
			.getBody();
	}

	@Override
	public Prestamo prestar(long contenidoId, long perfilId) throws ContenidoNotFoundException, PerfilNotFoundException,
			PrestamoNotAllowedException, UnexpectedPrestamoException {
		URI uri = uriBuilder
				.path("/prestar")
				.queryParam("cont", contenidoId)
				.queryParam("perf", perfilId)
				.build()
				.toUri();
		
		return restTemplate.postForObject(uri, null, Prestamo.class);
	}

	@Override
	public Prestamo devolver(long contenidoId, long perfilId) throws ContenidoNotFoundException,
			PerfilNotFoundException, PrestamoNotAllowedException, UnexpectedPrestamoException {
		URI uri = uriBuilder
				.path("/devolver")
				.queryParam("cont", contenidoId)
				.queryParam("perf", perfilId)
				.build()
				.toUri();
		
		return restTemplate.postForObject(uri, null, Prestamo.class);
	}

}
