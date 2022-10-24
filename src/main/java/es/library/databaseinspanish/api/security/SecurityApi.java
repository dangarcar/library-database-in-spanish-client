package es.library.databaseinspanish.api.security;

import es.library.databaseinspanish.api.security.implementations.LoginCredentials;
import es.library.databaseinspanish.model.perfil.Perfil;

public interface SecurityApi {
	
	public void signUp(Perfil perfil);
	
	public void login(LoginCredentials login);
	
	public void deletePerfil(String username);
	
	public void logout(String username);
	
}
