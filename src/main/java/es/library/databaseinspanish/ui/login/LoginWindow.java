package es.library.databaseinspanish.ui.login;

import java.awt.Toolkit;

import javax.swing.JDialog;
import javax.swing.JFrame;

import es.library.databaseinspanish.ui.SwingApp;

public class LoginWindow extends JDialog {
	
	private LoginController loginController;
	
	public LoginWindow() {
		this.loginController = new LoginController(new LoginPanel(), this);
		
		initUI();
	}
	
	public LoginWindow(SwingApp parent) {
		this.loginController = new LoginController(parent, new LoginPanel(), this);
		
		initUI();
	}
	
	private void initUI() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(SwingApp.class.getResource("/es/library/databaseinspanish/ui/images/icon.png")));
		
		loginController.init();
		
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	public void destroy() {
		this.setVisible(false);
		this.dispose();
	}
	
}
