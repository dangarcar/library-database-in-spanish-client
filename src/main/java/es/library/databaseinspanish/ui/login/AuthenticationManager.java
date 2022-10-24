package es.library.databaseinspanish.ui.login;

import java.awt.Color;

import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

import es.library.databaseinspanish.api.utils.StaticApis;
import es.library.databaseinspanish.ui.SwingApp;

public class AuthenticationManager {

	public AuthenticationManager() {
		try {
			new SwingApp(StaticApis.userApi().getMyInfo());
		} catch(Exception e) {
			new LoginWindow();
		}
	}
	
	public static void setNotValidJTextField(JTextField textField) {
		textField.setBorder(new LineBorder(Color.RED,2));
		textField.setText(null);
	}
	
	public static void setValidJTextField(JTextField textField) {
		textField.setBorder(UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border"));
	}
	
}
