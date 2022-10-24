package es.library.databaseinspanish.api.contenido;

import java.util.List;

import es.library.databaseinspanish.api.contenido.dto.ContenidoParamsDto;
import es.library.databaseinspanish.exceptions.contenido.ContenidoNotFoundException;
import es.library.databaseinspanish.exceptions.contenido.IllegalContenidoException;
import es.library.databaseinspanish.exceptions.contenido.UnexpectedContenidoException;
import es.library.databaseinspanish.model.contenido.Contenido;
import es.library.databaseinspanish.model.contenido.modeltypes.ContenidoModel;

public interface ContenidoApi {
	
	//PARA MODIFICAR
	
	public Contenido addContenido(Contenido contenido) throws IllegalContenidoException, UnexpectedContenidoException;
	
	public void deleteContenido(long id) throws ContenidoNotFoundException, UnexpectedContenidoException;
	
	public Contenido updateContenido(long id, Contenido contenido) throws ContenidoNotFoundException, IllegalContenidoException, UnexpectedContenidoException;
	
	//PARA BUSCAR
	
	public Contenido getContenidoById(long id) throws ContenidoNotFoundException, UnexpectedContenidoException;
	
	public List<ContenidoModel> getContenidoByParams(ContenidoParamsDto dto);
	
	public List<Contenido> getContenidoByParams(ContenidoParamsDto dto, Boolean disponible, Boolean prestable);
	
	public List<? extends ContenidoModel> getNContenidosMasPrestados(int n);
	
	public List<? extends ContenidoModel> getContenidosMasPrestados();
	
}
