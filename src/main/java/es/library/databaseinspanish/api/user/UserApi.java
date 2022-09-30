package es.library.databaseinspanish.api.user;

import java.util.List;

import es.library.databaseinspanish.exceptions.contenido.ContenidoNotFoundException;
import es.library.databaseinspanish.exceptions.perfil.IllegalPerfilException;
import es.library.databaseinspanish.exceptions.prestamo.PrestamoNotAllowedException;
import es.library.databaseinspanish.model.contenido.Contenido;
import es.library.databaseinspanish.model.perfil.Perfil;
import es.library.databaseinspanish.model.prestamo.Prestamo;

public interface UserApi {

	public Perfil getMyInfo();
	
	public Perfil updateMyPerfil(Perfil perfil) throws IllegalPerfilException;
	
	public void logout();
	
	public void deleteMyPerfil();
	
	public void prestar(long idContenido) throws ContenidoNotFoundException, PrestamoNotAllowedException;
	
	public void devolver(long idContenido) throws ContenidoNotFoundException, PrestamoNotAllowedException;
	
	public List<Contenido> getContenidosEnPrestamo();
	
	public List<Contenido> getHistorialContenidosPrestados();
	
	public List<Prestamo> getPrestamos();
	
}