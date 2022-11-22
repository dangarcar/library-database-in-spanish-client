package es.library.databaseinspanish.ui.utils;

import java.awt.Color;

import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

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
	
	public static void checkEmail(String email, JTextField field) {
		if(email.isBlank()) {
			OptionPanes.warn("El correo electrónico no debe estar en blanco");
			setNotValidJTextField(field);
			return;
		}
		if(!email.matches("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")) {
			OptionPanes.warn("El correo electrónico debe ser válido");
			setNotValidJTextField(field);
			return;
		}
		setValidJTextField(field);
	}
	
	
	public static void setNotValidJTextField(JTextField textField) {
		textField.setBorder(new LineBorder(Color.RED,2));
		textField.setText(null);
	}
	
	public static void setValidJTextField(JTextField textField) {
		textField.setBorder(UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border"));
	}
}
