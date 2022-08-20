package es.library.databaseinspanish.contenido.api.implementations;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import es.library.databaseinspanish.contenido.Contenido;
import es.library.databaseinspanish.contenido.ContenidoModel;
import es.library.databaseinspanish.contenido.Soporte;
import es.library.databaseinspanish.contenido.api.ContenidoApi;
import es.library.databaseinspanish.contenido.exceptions.ContenidoNotFoundException;
import es.library.databaseinspanish.contenido.exceptions.IllegalContenidoException;
import es.library.databaseinspanish.contenido.exceptions.UnexpectedContenidoException;

@Component
public class ContenidoApiSpringImpl implements ContenidoApi{

	@Autowired
	@Qualifier("ContenidoRestTemplate")
	private RestTemplate restTemplate;
	
	@Autowired
	@Qualifier("ContenidoUriBuilder")
	private UriComponentsBuilder uriBuilder;
	
	@Override
	public List<ContenidoModel> getContenidoByQuery(String query) {
		URI uri = uriBuilder
				.path("contenidos/search")
				.queryParam("q", query)
				.queryParam("unique", true)
				.build()
				.toUri();
		
		return restTemplate.exchange(
				uri, 
				HttpMethod.GET,
				null,
				new ParameterizedTypeReference<List<ContenidoModel>>() {})
			.getBody();
	}

	@Override
	public Contenido getContenidoById(long id) throws ContenidoNotFoundException, UnexpectedContenidoException {
		URI uri = uriBuilder
				.path("contenidos/search/id/"+id)
				.build()
				.toUri();
		
		return restTemplate.getForObject(uri, Contenido.class);
	}

	@Override
	public Contenido addContenido(Contenido contenido) throws IllegalContenidoException, UnexpectedContenidoException {
		URI uri = uriBuilder
				.path("contenidos")
				.build()
				.toUri();

		return restTemplate.postForObject(uri, contenido, Contenido.class);
	}

	@Override
	public void deleteContenido(long id) throws ContenidoNotFoundException, UnexpectedContenidoException {
		URI uri = uriBuilder
				.path("contenidos/"+id)
				.build()
				.toUri();
		
		restTemplate.delete(uri);
	}

	@Override
	public List<Contenido> getNContenidosMasPrestados(int n) {
		URI uri = uriBuilder
				.path("contenidos/search/topprestamos")
				.queryParam("limit", n)
				.build()
				.toUri();
		
		return restTemplate.exchange(
				uri, 
				HttpMethod.GET, 
				null, 
				new ParameterizedTypeReference<List<Contenido>>() {})
			.getBody();
	}

	@Override
	public List<ContenidoModel> getContenidoByParams(String titulo, String autor, Integer minAno, Integer maxAno,
			String idioma, Soporte soporte, Integer minPaginas, Integer maxPaginas, String editorial, String isbn,
			Integer minEdad, Integer maxEdad, Double minDuracion, Double maxDuracion, Integer minCalidad,
			Integer maxCalidad, String type) {

		UriComponentsBuilder uri = uriBuilder
				.path("contenidos/search/unique");
		
		if(titulo != null) 		uri.queryParam("titulo", titulo);
		if(autor != null) 		uri.queryParam("autor", autor);
		if(minAno != null) 		uri.queryParam("minAno", minAno);
		if(maxAno != null) 		uri.queryParam("maxAno", maxAno);
		if(idioma != null) 		uri.queryParam("idioma", idioma);
		if(soporte != null) 	uri.queryParam("soporte", soporte);
		if(minPaginas != null) 	uri.queryParam("minPaginas", minPaginas);
		if(maxPaginas != null) 	uri.queryParam("maxPaginas", maxPaginas);
		if(editorial != null) 	uri.queryParam("editorial", editorial);
		if(isbn != null) 		uri.queryParam("isbn", isbn);
		if(minEdad != null) 	uri.queryParam("minEdad", minEdad);
		if(maxEdad != null)	 	uri.queryParam("maxEdad", maxEdad);
		if(minDuracion != null) uri.queryParam("minDuracion", minDuracion);
		if(maxDuracion != null) uri.queryParam("maxDuracion", maxDuracion);
		if(minCalidad != null) 	uri.queryParam("minCalidad", minCalidad);
		if(maxCalidad != null) 	uri.queryParam("maxCalidad", maxCalidad);
		if(type != null) 		uri.queryParam("type", type);
		
		return restTemplate.exchange(
				uri.build().toUri(), 
				HttpMethod.GET, 
				null, 
				new ParameterizedTypeReference<List<ContenidoModel>>() {})
			.getBody();
	}

	@Override
	public List<Contenido> getContenidoByParams(String titulo, String autor, Integer minAno, Integer maxAno,
			String idioma, Soporte soporte, Integer minPaginas, Integer maxPaginas, String editorial, String isbn,
			Integer minEdad, Integer maxEdad, Double minDuracion, Double maxDuracion, Integer minCalidad,
			Integer maxCalidad, String type, Boolean d, Boolean p) {

		UriComponentsBuilder uri = uriBuilder
				.path("contenidos/search");
		
		if(titulo != null) 		uri.queryParam("titulo", titulo);
		if(autor != null) 		uri.queryParam("autor", autor);
		if(minAno != null) 		uri.queryParam("minAno", minAno);
		if(maxAno != null) 		uri.queryParam("maxAno", maxAno);
		if(idioma != null) 		uri.queryParam("idioma", idioma);
		if(soporte != null) 	uri.queryParam("soporte", soporte);
		if(minPaginas != null) 	uri.queryParam("minPaginas", minPaginas);
		if(maxPaginas != null) 	uri.queryParam("maxPaginas", maxPaginas);
		if(editorial != null) 	uri.queryParam("editorial", editorial);
		if(isbn != null) 		uri.queryParam("isbn", isbn);
		if(minEdad != null) 	uri.queryParam("minEdad", minEdad);
		if(maxEdad != null)	 	uri.queryParam("maxEdad", maxEdad);
		if(minDuracion != null) uri.queryParam("minDuracion", minDuracion);
		if(maxDuracion != null) uri.queryParam("maxDuracion", maxDuracion);
		if(minCalidad != null) 	uri.queryParam("minCalidad", minCalidad);
		if(maxCalidad != null) 	uri.queryParam("maxCalidad", maxCalidad);
		if(type != null) 		uri.queryParam("type", type);
		if(d != null) 			uri.queryParam("d", d);
		if(p != null) 			uri.queryParam("p", p);
		
		return restTemplate.exchange(
				uri.build().toUri(), 
				HttpMethod.GET, 
				null, 
				new ParameterizedTypeReference<List<Contenido>>() {})
			.getBody();
	}

}
