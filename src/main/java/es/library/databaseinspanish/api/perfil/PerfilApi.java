package es.library.databaseinspanish.api.perfil;

import java.util.List;

import es.library.databaseinspanish.api.perfil.dto.PerfilParamsDto;
import es.library.databaseinspanish.exceptions.perfil.IllegalPerfilException;
import es.library.databaseinspanish.exceptions.perfil.PerfilNotFoundException;
import es.library.databaseinspanish.exceptions.perfil.UnexpectedPerfilException;
import es.library.databaseinspanish.model.perfil.Perfil;
import es.library.databaseinspanish.model.perfil.Roles;

public interface PerfilApi {
	
	public Perfil addPerfil(Perfil perfil) throws IllegalPerfilException, UnexpectedPerfilException;
	
	public Perfil updatePerfil(long id, Perfil perfil) throws IllegalPerfilException, PerfilNotFoundException, UnexpectedPerfilException;
	
	public void deletePerfil(long id) throws PerfilNotFoundException, UnexpectedPerfilException;

	public void setRole(long id, Roles roles) throws PerfilNotFoundException, UnexpectedPerfilException;
	
	//BÚSQUEDA
	
	public Perfil getPerfilById(long id) throws PerfilNotFoundException, UnexpectedPerfilException;
	
	public Perfil getPerfilByUsername(String username) throws PerfilNotFoundException, UnexpectedPerfilException;
	
	public List<Perfil> getPerfilByParams(PerfilParamsDto dto);
	
}
