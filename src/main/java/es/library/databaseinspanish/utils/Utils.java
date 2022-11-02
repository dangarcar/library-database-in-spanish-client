package es.library.databaseinspanish.utils;

import es.library.databaseinspanish.model.perfil.Roles;

public class Utils {

	public static String getHtmlText(String text) {
		return "<html>"+text+"</html>";
	}

	public static String getHtmlText(String title, Object text) {
		return "<html><b>"+title+"</b>: "+text.toString()+"</html>";
	}
	
	public static String getHtmlTextWithBr(String title, Object text) {
		return "<html><b>"+title+"</b><br>"+text.toString()+"</html>";
	}
	
	public static String getFormattedStringFromRole(Roles role) {
		return switch (role) {
		case ROLE_GUEST -> 	"Invitado";
		case ROLE_USER -> 	"Usuario";
		case ROLE_STAFF -> 	"Trabajador";
		case ROLE_ADMIN -> 	"Administrador";
		};
	}
	
}
