package es.library.databaseinspanish.contenido.api;

import java.util.List;

import es.library.databaseinspanish.contenido.Contenido;
import es.library.databaseinspanish.contenido.ContenidoModel;
import es.library.databaseinspanish.contenido.exceptions.ContenidoNotFoundException;
import es.library.databaseinspanish.contenido.exceptions.IllegalContenidoException;
import es.library.databaseinspanish.contenido.exceptions.UnexpectedContenidoException;
import es.library.databaseinspanish.contenido.Soporte;

public interface ContenidoApi {

	public List<ContenidoModel> getContenidoByQuery(String query);
	
	public Contenido getContenidoById(long id) throws ContenidoNotFoundException, UnexpectedContenidoException;
	
	public Contenido addContenido(Contenido contenido) throws IllegalContenidoException, UnexpectedContenidoException;
	
	public void deleteContenido(long id) throws ContenidoNotFoundException, UnexpectedContenidoException;
	
	public List<ContenidoModel> getContenidoByParams(String titulo, String autor, Integer minAno, Integer maxAno,
			String idioma, Soporte soporte, Integer minPaginas, Integer maxPaginas, String editorial, String isbn,
			Integer minEdad, Integer maxEdad, Double minDuracion, Double maxDuracion, Integer minCalidad,
			Integer maxCalidad, String type);
	
	public List<Contenido> getContenidoByParams(String titulo, String autor, Integer minAno, Integer maxAno,
			String idioma, Soporte soporte, Integer minPaginas, Integer maxPaginas, String editorial, String isbn,
			Integer minEdad, Integer maxEdad, Double minDuracion, Double maxDuracion, Integer minCalidad,
			Integer maxCalidad, String type, Boolean d, Boolean p);
	
	public List<Contenido> getNContenidosMasPrestados(int n);
	
}
