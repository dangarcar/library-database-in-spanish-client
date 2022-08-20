package es.library.databaseinspanish.perfil.api;

import java.time.LocalDate;
import java.util.List;

import es.library.databaseinspanish.perfil.Perfil;
import es.library.databaseinspanish.perfil.exceptions.IllegalPerfilException;
import es.library.databaseinspanish.perfil.exceptions.PerfilNotFoundException;
import es.library.databaseinspanish.perfil.exceptions.UnexpectedPerfilException;

/**
 * Interfaz encargada de modificar los datos de los Perfiles en la BBDD
 * @author Daniel García
 *
 */
public interface PerfilApi {
	
	public List<Perfil> getPerfilByQuery(String query);
	
	public Perfil getPerfilById(long id) throws PerfilNotFoundException, UnexpectedPerfilException;
	
	public Perfil addPerfil(Perfil perfil) throws IllegalPerfilException, UnexpectedPerfilException;
	
	public void makePerfilAdmin(long id) throws PerfilNotFoundException, UnexpectedPerfilException;
	
	public void makeAdminPerfil(long id) throws PerfilNotFoundException, UnexpectedPerfilException;
	
	public void deletePerfil(long id) throws PerfilNotFoundException, UnexpectedPerfilException;

	public List<Perfil> getPerfilByParams(String nombre, String email, LocalDate fromNacimiento, LocalDate toNacimiento, Boolean admin);
	
}
