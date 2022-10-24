package es.library.databaseinspanish.api.utils;

import es.library.databaseinspanish.api.contenido.ContenidoApi;
import es.library.databaseinspanish.api.perfil.PerfilApi;
import es.library.databaseinspanish.api.prestamos.PrestamoApi;
import es.library.databaseinspanish.api.security.SecurityApi;
import es.library.databaseinspanish.api.user.UserApi;

/**
 * Una clase para acceder a las partes del programa que se comunican con las apis de forma estática sin la necesidad de usar Spring
 * 
 * @author Daniel García
 *
 */
public class StaticApis {

	public static ContenidoApi contenidoApi() {
		return StaticApplicationContext.getContext().getBean(ContenidoApi.class);
	}
	
	public static PerfilApi perfilApi() {
		return StaticApplicationContext.getContext().getBean(PerfilApi.class);
	}
	
	public static PrestamoApi prestamoApi() {
		return StaticApplicationContext.getContext().getBean(PrestamoApi.class);
	}
	
	public static SecurityApi securityApi() {
		return StaticApplicationContext.getContext().getBean(SecurityApi.class);
	}
	
	public static UserApi userApi() {
		return StaticApplicationContext.getContext().getBean(UserApi.class);
	}
	
}
