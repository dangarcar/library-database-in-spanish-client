package es.library.databaseinspanish.ui.login;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import es.library.databaseinspanish.api.security.implementations.LoginCredentials;
import es.library.databaseinspanish.api.utils.StaticApis;
import es.library.databaseinspanish.exceptions.perfil.EmailAlreadyExistPerfilException;
import es.library.databaseinspanish.exceptions.perfil.IllegalPerfilException;
import es.library.databaseinspanish.exceptions.security.NotValidPasswordException;
import es.library.databaseinspanish.exceptions.security.UnexpectedSecurityException;
import es.library.databaseinspanish.exceptions.security.UsernameNotFoundException;
import es.library.databaseinspanish.model.perfil.Perfil;
import es.library.databaseinspanish.ui.SwingApp;
import es.library.databaseinspanish.ui.utils.OptionPanes;

public class LoginController {

	private Logger logger = LogManager.getLogger(getClass());
	private LoginPanel loginPanel;
	private LoginWindow window;
	private boolean createNewApp;
	private SwingApp parent;
	
	public LoginController(LoginPanel panel, LoginWindow window) {
		this.loginPanel = panel;
		this.window = window;
		this.createNewApp = true;
	}
	
	public LoginController(SwingApp parent, LoginPanel panel, LoginWindow window) {
		this.loginPanel = panel;
		this.window = window;
		this.createNewApp = false;
		this.parent = parent;
	}
	
	public void init() {
		loginPanel.getRegisterBoton().addActionListener(registerButtonAction);
		loginPanel.getBotonLogin().addActionListener(loginButtonAction);
		loginPanel.getGuestBoton().addActionListener(guestBotonAction);
		
		window.add(loginPanel);
	}
	
	private LoginCredentials getLoginCredentials() throws IllegalPerfilException {
		var credentials = new LoginCredentials();
		
		String username = loginPanel.getUsername().getText();
		if(username.isBlank()) {
			OptionPanes.warn("El correo electrónico no debe estar en blanco");
			AuthenticationManager.setNotValidJTextField(loginPanel.getUsername());
			throw new IllegalPerfilException("El correo electrónico no debe estar en blanco");
		}	
		credentials.setUsername(username);
		AuthenticationManager.setValidJTextField(loginPanel.getUsername());
		
		String password = String.valueOf(loginPanel.getPassword().getPassword());
		if(password.isBlank()) {
			OptionPanes.warn("La contraseña no debe estar en blanco");
			AuthenticationManager.setNotValidJTextField(loginPanel.getPassword());
			throw new IllegalPerfilException("La contraseña no debe estar en blanco");
		}
		credentials.setPassword(password);
		AuthenticationManager.setValidJTextField(loginPanel.getPassword());
		
		return credentials;
	}
	
	private ActionListener loginButtonAction = (ActionEvent e) -> {
		LoginCredentials credentials;
		
		try {
			credentials = getLoginCredentials();
		} catch (IllegalPerfilException e2) {
			return;
		}
		
		try {
			StaticApis.securityApi().login(credentials);
			logger.info("El usuario {} ha iniciado sesión correctamente",credentials.getUsername());
		} catch (NotValidPasswordException|UsernameNotFoundException|EmailAlreadyExistPerfilException|UnexpectedSecurityException e1) {
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
		
	private ActionListener registerButtonAction = (ActionEvent e) -> {
		window.destroy();
		if(createNewApp) new RegisterWindow();
		else new RegisterWindow(parent);
	};
	
	private ActionListener guestBotonAction = (ActionEvent e) -> {
		window.destroy();
		if(createNewApp) SwingApp.createGuestApp();
		else parent.changeToGuestApp();
	};
}