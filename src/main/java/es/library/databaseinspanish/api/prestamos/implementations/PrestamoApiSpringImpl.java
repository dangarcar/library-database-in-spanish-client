package es.library.databaseinspanish.api.prestamos.implementations;

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

import es.library.databaseinspanish.api.prestamos.PrestamoApi;
import es.library.databaseinspanish.api.prestamos.dto.PrestamoParamsDto;
import es.library.databaseinspanish.api.utils.ApiUtils;
import es.library.databaseinspanish.exceptions.contenido.ContenidoNotFoundException;
import es.library.databaseinspanish.exceptions.perfil.PerfilNotFoundException;
import es.library.databaseinspanish.exceptions.perfil.PrestamoNotFoundException;
import es.library.databaseinspanish.exceptions.prestamo.IllegalPrestamoException;
import es.library.databaseinspanish.exceptions.prestamo.PrestamoNotAllowedException;
import es.library.databaseinspanish.exceptions.prestamo.UnexpectedPrestamoException;
import es.library.databaseinspanish.model.prestamo.Prestamo;

@Component
public class PrestamoApiSpringImpl implements PrestamoApi {

	@Autowired
	@Qualifier("PrestamoRestTemplate")
	private RestTemplate restTemplate;

	@Autowired
	private ApiUtils apiUtils;

	@Override
	public Prestamo prestar(long contenidoId, long perfilId) throws ContenidoNotFoundException, PerfilNotFoundException,
	PrestamoNotAllowedException, UnexpectedPrestamoException {
		URI uri = apiUtils.uriBuilder()
				.path("/prestar")
				.queryParam("contenido", contenidoId)
				.queryParam("perfil", perfilId)
				.build()
				.toUri();

		return restTemplate.postForObject(uri, null, Prestamo.class);
	}

	@Override
	public Prestamo devolver(long contenidoId, long perfilId) throws ContenidoNotFoundException,
	PerfilNotFoundException, PrestamoNotAllowedException, UnexpectedPrestamoException {
		URI uri = apiUtils.uriBuilder()
				.path("/devolver")
				.queryParam("contenido", contenidoId)
				.queryParam("perfil", perfilId)
				.build()
				.toUri();

		return restTemplate.postForObject(uri, null, Prestamo.class);
	}

	@Override
	public Prestamo addPrestamo(Prestamo prestamo) throws IllegalPrestamoException, UnexpectedPrestamoException {
		URI uri = apiUtils.uriBuilder()
				.path("prestamos")
				.build()
				.toUri();
		
		return restTemplate.postForObject(uri, prestamo, Prestamo.class);
	}

	@Override
	public Prestamo updatePrestamoById(long id, Prestamo prestamo)
			throws PrestamoNotFoundException, IllegalPrestamoException, UnexpectedPrestamoException {
		URI uri = apiUtils.uriBuilder()
				.path("prestamos/"+id)
				.build()
				.toUri();
		
		return restTemplate.exchange(
				uri, 
				HttpMethod.PUT, 
				new HttpEntity<Prestamo>(prestamo), 
				Prestamo.class)
			.getBody();
	}

	@Override
	public void deletePrestamoById(long id) throws PrestamoNotFoundException, UnexpectedPrestamoException {
		URI uri = apiUtils.uriBuilder()
				.path("prestamos/"+id)
				.build()
				.toUri();
		
		restTemplate.delete(uri);
	}
	
	@Override
	public Prestamo getPrestamoById(long id) throws PrestamoNotFoundException, UnexpectedPrestamoException {
		URI uri = apiUtils.uriBuilder()
				.path("prestamos/search/id/"+id)
				.build()
				.toUri();

		return restTemplate.getForObject(uri, Prestamo.class);
	}

	@Override
	public List<Prestamo> getPrestamosByParams(PrestamoParamsDto dto) throws UnexpectedPrestamoException {
		UriComponentsBuilder builder = apiUtils.uriBuilder()
				.path("/prestamos/search");

		if(dto.getIdContenido() != null) 	builder.queryParam("contenido", dto.getIdContenido());
		if(dto.getIdPerfil() != null) 		builder.queryParam("perfil", dto.getIdPerfil());
		if(dto.getMinDias() != null) 		builder.queryParam("minDias", dto.getMinDias());
		if(dto.getMaxDias() != null) 		builder.queryParam("maxDias", dto.getMaxDias());
		if(dto.getFromPrestamo() != null) 	builder.queryParam("fromPrestamo", dto.getFromPrestamo());
		if(dto.getToPrestamo() != null) 	builder.queryParam("toPrestamo", dto.getToPrestamo());
		if(dto.getFromDevolucion() != null)	builder.queryParam("fromDevolucion", dto.getFromDevolucion());
		if(dto.getToDevolucion() != null) 	builder.queryParam("toDevolucion", dto.getToDevolucion());
		if(dto.getDevuelto() != null) 		builder.queryParam("d", dto.getDevuelto());

		URI uri = builder
				.build()
				.toUri();

		return restTemplate.exchange(
				uri,
				HttpMethod.GET, 
				null, 
				new ParameterizedTypeReference<List<Prestamo>>() {})
			.getBody();
	}
}
