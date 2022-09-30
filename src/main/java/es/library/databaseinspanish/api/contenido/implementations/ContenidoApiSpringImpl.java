package es.library.databaseinspanish.api.contenido.implementations;

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

import es.library.databaseinspanish.api.contenido.ContenidoApi;
import es.library.databaseinspanish.api.contenido.dto.ContenidoParamsDto;
import es.library.databaseinspanish.api.utils.ApiUtils;
import es.library.databaseinspanish.exceptions.contenido.ContenidoNotFoundException;
import es.library.databaseinspanish.exceptions.contenido.IllegalContenidoException;
import es.library.databaseinspanish.exceptions.contenido.UnexpectedContenidoException;
import es.library.databaseinspanish.model.contenido.Contenido;
import es.library.databaseinspanish.model.contenido.ContenidoModel;

@Component
public class ContenidoApiSpringImpl implements ContenidoApi{

	@Autowired
	@Qualifier("ContenidoRestTemplate")
	private RestTemplate contenidosRestTemplate;
	
	@Autowired
	@Qualifier("FreeContenidoRestTemplate")
	private RestTemplate freeContenidosRestTemplate;
	
	@Autowired
	private ApiUtils apiUtils;

	@Override
	public Contenido addContenido(Contenido contenido) throws IllegalContenidoException, UnexpectedContenidoException {
		URI uri = apiUtils.uriBuilder()
				.path("contenidos")
				.build()
				.toUri();

		return contenidosRestTemplate.postForObject(uri, contenido, Contenido.class);
	}

	@Override
	public void deleteContenido(long id) throws ContenidoNotFoundException, UnexpectedContenidoException {
		URI uri = apiUtils.uriBuilder()
				.path("contenidos/"+id)
				.build()
				.toUri();
		
		contenidosRestTemplate.delete(uri);
	}

	@Override
	public Contenido updateContenido(long id, Contenido contenido)
			throws ContenidoNotFoundException, IllegalContenidoException, UnexpectedContenidoException {
		URI uri = apiUtils.uriBuilder()
				.path("contenidos/"+id)
				.build()
				.toUri();
		
		return contenidosRestTemplate.exchange(
				uri, 
				HttpMethod.PUT, 
				new HttpEntity<Contenido>(contenido), 
				Contenido.class)
			.getBody();
	}

	
	//BUSCAR CONTENIDOS (PÚBLICO)
	
	@Override
	public Contenido getContenidoById(long id) throws ContenidoNotFoundException, UnexpectedContenidoException {
		URI uri = apiUtils.uriBuilder()
				.path("contenidos/search/id/"+id)
				.build()
				.toUri();
		
		return freeContenidosRestTemplate.getForObject(uri, Contenido.class);
	}
	
	@Override
	public List<Contenido> getContenidoByParams(ContenidoParamsDto dto, Boolean disponible, Boolean prestable) {
		UriComponentsBuilder builder = apiUtils.uriBuilder()
				.path("contenidos/search");
		
		if(disponible != null) builder.queryParam("d", disponible);
		if(prestable != null) builder.queryParam("p", prestable);
		
		URI uri = addNonNullFieldsToUri(dto, builder);
		
		return freeContenidosRestTemplate.exchange(
				uri, 
				HttpMethod.GET, 
				null, 
				new ParameterizedTypeReference<List<Contenido>>() {})
			.getBody();
	}
	
	@Override
	public List<ContenidoModel> getContenidoByParams(ContenidoParamsDto dto) {
		UriComponentsBuilder builder = apiUtils.uriBuilder()
				.path("contenidos/search/unique");
		
		URI uri = addNonNullFieldsToUri(dto, builder);
		
		return freeContenidosRestTemplate.exchange(
				uri, 
				HttpMethod.GET, 
				null, 
				new ParameterizedTypeReference<List<ContenidoModel>>() {})
			.getBody();
	}

	@Override
	public List<Contenido> getContenidosMasPrestados() {
		URI uri = apiUtils.uriBuilder()
				.path("contenidos/search/topprestamos")
				.build()
				.toUri();
		
		return freeContenidosRestTemplate.exchange(
				uri, 
				HttpMethod.GET, 
				null, 
				new ParameterizedTypeReference<List<Contenido>>() {})
			.getBody();
	}
	
	@Override
	public List<Contenido> getNContenidosMasPrestados(int n) {
		URI uri = apiUtils.uriBuilder()
				.path("contenidos/search/topprestamos")
				.queryParam("limit", n)
				.build()
				.toUri();
		
		return freeContenidosRestTemplate.exchange(
				uri, 
				HttpMethod.GET, 
				null, 
				new ParameterizedTypeReference<List<Contenido>>() {})
			.getBody();
	}
	
	private URI addNonNullFieldsToUri(ContenidoParamsDto dto, UriComponentsBuilder builder) {
		
		if(dto.getTitulo() != null) 	builder.queryParam("titulo", dto.getTitulo());
		if(dto.getAutor() != null) 		builder.queryParam("autor", dto.getAutor());
		if(dto.getMinAno() != null) 	builder.queryParam("minAno", dto.getMinAno());
		if(dto.getMaxAno() != null) 	builder.queryParam("maxAno", dto.getMaxAno());
		if(dto.getIdioma() != null) 	builder.queryParam("idioma", dto.getIdioma());
		if(dto.getSoporte() != null) 	builder.queryParam("soporte", dto.getSoporte());
		if(dto.getMinPaginas() != null) builder.queryParam("minPaginas", dto.getMinPaginas());
		if(dto.getMaxPaginas() != null) builder.queryParam("maxPaginas", dto.getMaxPaginas());
		if(dto.getEditorial() != null) 	builder.queryParam("editorial", dto.getEditorial());
		if(dto.getIsbn() != null) 		builder.queryParam("isbn", dto.getIsbn());
		if(dto.getMinEdad() != null) 	builder.queryParam("minEdad", dto.getMinEdad());
		if(dto.getMaxEdad() != null)	builder.queryParam("maxEdad", dto.getMaxEdad());
		if(dto.getMinDuracion() != null)builder.queryParam("minDuracion", dto.getMinDuracion());
		if(dto.getMaxDuracion() != null)builder.queryParam("maxDuracion", dto.getMaxDuracion());
		if(dto.getMinCalidad() != null) builder.queryParam("minCalidad", dto.getMinCalidad());
		if(dto.getMaxCalidad() != null) builder.queryParam("maxCalidad", dto.getMaxCalidad());
		if(dto.getType() != null) 		builder.queryParam("type", dto.getType());
		
		return builder
				.build()
				.toUri();
	}
	
}