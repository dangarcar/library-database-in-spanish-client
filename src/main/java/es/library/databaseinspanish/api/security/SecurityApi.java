package es.library.databaseinspanish.api.security;

import es.library.databaseinspanish.api.security.implementations.LoginCredentials;
import es.library.databaseinspanish.model.perfil.Perfil;
import es.library.databaseinspanish.model.security.TokenPair;

public interface SecurityApi {
	
	public TokenPair signUp(Perfil perfil);
	
	public TokenPair login(LoginCredentials login);
	
	public void deletePerfil(String username);
	
	public void logout(String username);
	
}
