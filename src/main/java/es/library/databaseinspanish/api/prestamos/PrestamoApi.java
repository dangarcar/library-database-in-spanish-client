package es.library.databaseinspanish.api.prestamos;

import java.util.List;

import es.library.databaseinspanish.api.prestamos.dto.PrestamoParamsDto;
import es.library.databaseinspanish.exceptions.contenido.ContenidoNotFoundException;
import es.library.databaseinspanish.exceptions.perfil.PerfilNotFoundException;
import es.library.databaseinspanish.exceptions.perfil.PrestamoNotFoundException;
import es.library.databaseinspanish.exceptions.prestamo.IllegalPrestamoException;
import es.library.databaseinspanish.exceptions.prestamo.PrestamoNotAllowedException;
import es.library.databaseinspanish.exceptions.prestamo.UnexpectedPrestamoException;
import es.library.databaseinspanish.model.prestamo.Prestamo;

public interface PrestamoApi {

	public Prestamo addPrestamo(Prestamo prestamo) throws IllegalPrestamoException, UnexpectedPrestamoException;
	
	public Prestamo updatePrestamoById(long id, Prestamo prestamo) throws PrestamoNotFoundException, IllegalPrestamoException, UnexpectedPrestamoException;
	
	public void deletePrestamoById(long id) throws PrestamoNotFoundException, UnexpectedPrestamoException;
	
	//SIMPLIFICADO
	
	public Prestamo prestar(long contenidoId, long perfilId) throws ContenidoNotFoundException, PerfilNotFoundException,
			PrestamoNotAllowedException, UnexpectedPrestamoException;

	public Prestamo devolver(long contenidoId, long perfilId) throws ContenidoNotFoundException,
			PerfilNotFoundException, PrestamoNotAllowedException, UnexpectedPrestamoException;
	
	//BÚSQUEDA
	
	public Prestamo getPrestamoById(long id) throws PrestamoNotFoundException, UnexpectedPrestamoException;

	public List<Prestamo> getPrestamosByParams(PrestamoParamsDto dto) throws ContenidoNotFoundException, PerfilNotFoundException, UnexpectedPrestamoException;

}
