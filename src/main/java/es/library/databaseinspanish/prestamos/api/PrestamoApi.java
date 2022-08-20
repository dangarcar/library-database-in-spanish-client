package es.library.databaseinspanish.prestamos.api;

import java.time.LocalDateTime;
import java.util.List;

import es.library.databaseinspanish.contenido.Contenido;
import es.library.databaseinspanish.contenido.exceptions.ContenidoNotFoundException;
import es.library.databaseinspanish.perfil.exceptions.PerfilNotFoundException;
import es.library.databaseinspanish.prestamos.Prestamo;
import es.library.databaseinspanish.prestamos.exceptions.PrestamoNotAllowedException;
import es.library.databaseinspanish.prestamos.exceptions.PrestamoNotFoundException;
import es.library.databaseinspanish.prestamos.exceptions.UnexpectedPrestamoException;

public interface PrestamoApi {

	public Prestamo getPrestamoById(long id) throws PrestamoNotFoundException, UnexpectedPrestamoException;

	public List<Prestamo> getPrestamosByIdContenido(long idContenido, Boolean disponible)
			throws ContenidoNotFoundException, UnexpectedPrestamoException;

	public List<Prestamo> getPrestamosByIdPerfil(long idPerfil, Boolean disponible)
			throws PerfilNotFoundException, UnexpectedPrestamoException;

	public List<Contenido> getContenidosPrestadosByIdPerfil(long idPerfil, Boolean disponible)
			throws ContenidoNotFoundException, PerfilNotFoundException, UnexpectedPrestamoException;

	public List<Prestamo> getPrestamosByParams(Long idContenido, Long idPerfil, Integer minDias, Integer maxDias, LocalDateTime fromPrestamo, LocalDateTime toPrestamo,
			LocalDateTime fromDevolucion, LocalDateTime toDevolucion, Boolean disponible);

	public Prestamo prestar(long contenidoId, long perfilId) throws ContenidoNotFoundException, PerfilNotFoundException,
			PrestamoNotAllowedException, UnexpectedPrestamoException;

	public Prestamo devolver(long contenidoId, long perfilId) throws ContenidoNotFoundException,
			PerfilNotFoundException, PrestamoNotAllowedException, UnexpectedPrestamoException;

}
