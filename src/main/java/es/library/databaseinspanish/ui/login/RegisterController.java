package es.library.databaseinspanish.ui.login;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import es.library.databaseinspanish.api.utils.StaticApis;
import es.library.databaseinspanish.exceptions.perfil.EmailAlreadyExistPerfilException;
import es.library.databaseinspanish.exceptions.perfil.IllegalPerfilException;
import es.library.databaseinspanish.exceptions.security.NotValidPasswordException;
import es.library.databaseinspanish.exceptions.security.UnexpectedSecurityException;
import es.library.databaseinspanish.exceptions.security.UsernameNotFoundException;
import es.library.databaseinspanish.model.perfil.Perfil;
import es.library.databaseinspanish.ui.SwingApp;
import es.library.databaseinspanish.ui.utils.OptionPanes;
import es.library.databaseinspanish.ui.utils.Utils;

public class RegisterController {

	private Logger logger = LogManager.getLogger(getClass());
	private RegisterPanel registerPanel;
	private RegisterWindow window;
	private boolean createNewApp;
	private SwingApp parent;
	
	public RegisterController(RegisterPanel registerPanel, RegisterWindow window) {
		this.registerPanel = registerPanel;
		this.window = window;
		this.createNewApp = true;
	}
	
	public RegisterController(SwingApp parent, RegisterPanel registerPanel, RegisterWindow window) {
		this.registerPanel = registerPanel;
		this.window = window;
		this.parent = parent;
		this.createNewApp = false;
	}
	
	public void init() {
		registerPanel.getRegisterButton().addActionListener(registerButtonAction);
		
		window.add(registerPanel);
	}
	
	private Perfil getPerfil() throws IllegalPerfilException {
		Perfil perfil = new Perfil();
		
		String name = registerPanel.getNameTextField().getText();
		if(name.isBlank()) {
			OptionPanes.warn("El nombre no debe estar en blanco");
			Utils.setNotValidJTextField(registerPanel.getNameTextField());
			throw new IllegalPerfilException("El nombre no debe estar en blanco");
		}	
		perfil.setNombre(name);
		Utils.setValidJTextField(registerPanel.getNameTextField());
		
		String email = registerPanel.getEmailTextField().getText();
		if(email.isBlank()) {
			OptionPanes.warn("El correo electrónico no debe estar en blanco");
			Utils.setNotValidJTextField(registerPanel.getEmailTextField());
			throw new IllegalPerfilException("El correo electrónico no debe estar en blanco");
		}
		if(!email.matches("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")) {
			OptionPanes.warn("El correo electrónico debe ser válido");
			Utils.setNotValidJTextField(registerPanel.getEmailTextField());
			throw new IllegalPerfilException("El correo electrónico debe ser válido");
		}
		perfil.setCorreoElectronico(email);
		Utils.setValidJTextField(registerPanel.getEmailTextField());
		
		String password = String.valueOf(registerPanel.getPasswordField().getPassword());
		if(password.isBlank()) {
			OptionPanes.warn("La contraseña no debe estar en blanco");
			Utils.setNotValidJTextField(registerPanel.getPasswordField());
			throw new IllegalPerfilException("El contraseña no debe estar en blanco");
		}
		if(!password.matches("^(?=.*[0-9])(?=.*[a-zA-Z])(?=\\S+$).{8,}$")) {
			OptionPanes.warn("La contraseña debe tener más de 8 caracteres y contener letras y números");
			Utils.setNotValidJTextField(registerPanel.getPasswordField());
			throw new IllegalPerfilException("La contraseña debe tener más de 8 caracteres y contener letras y números");
		}
		perfil.setContrasena(password);
		Utils.setValidJTextField(registerPanel.getPasswordField());
		
		perfil.setFechaNacimiento(registerPanel.getDateSelector().getLocalDate());
		
		JPanel panel = new JPanel();
		JPasswordField passwordField = new JPasswordField(32);
		panel.add(passwordField);
		confirmPassword(panel, passwordField);		
		while (!password.equals(String.valueOf(passwordField.getPassword()))) {
			OptionPanes.warnBlocking("La contraseña de la confirmación no se corresponde con la contraseña original");
			passwordField.setText("");
			confirmPassword(panel, passwordField);
		}
		
		return perfil;
	}
	
	public static void confirmPassword(JPanel panel, JPasswordField passwordField) throws IllegalPerfilException {
		String[] options = {"OK","Cancelar"};
		int opt = JOptionPane.showOptionDialog(null, panel, "Confirmar contraseña", JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[1]);
		if(opt != 0) {
			OptionPanes.warnBlocking("La contraseña no se ha confirmado");
			Utils.setNotValidJTextField(passwordField);
			throw new IllegalPerfilException("La contraseña no se ha confirmado");
		}
	}
	
	private ActionListener registerButtonAction = (ActionEvent e) -> {
		Perfil perfil;
		
		try {
			perfil = getPerfil();
		} catch (IllegalPerfilException e1) {
			return;
		}
		
		try {
			StaticApis.securityApi().signUp(perfil);
			logger.info("El usuario {} se ha registrado correctamente",perfil.getCorreoElectronico());
		} catch (NotValidPasswordException|UsernameNotFoundException|EmailAlreadyExistPerfilException|UnexpectedSecurityException|IllegalPerfilException e1) {
			OptionPanes.errorBlocking(e1.getMessage());
			return;
		}
		
		try {
			Perfil user = StaticApis.userApi().getMyInfo();
			OptionPanes.info("Bienvenido "+user.getNombre());
			window.destroy();
			if(createNewApp) new SwingApp(user);
			else parent.changeToUserApp(user);
		} catch (NotValidPasswordException|UsernameNotFoundException|EmailAlreadyExistPerfilException|UnexpectedSecurityException e1) {
			OptionPanes.errorBlocking(e1.getMessage());
		}
	};
	
}
