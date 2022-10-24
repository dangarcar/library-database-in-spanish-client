package es.library.databaseinspanish.ui.login;

import java.awt.Toolkit;

import javax.swing.JFrame;

import es.library.databaseinspanish.ui.SwingApp;

public class LoginWindow extends JFrame {
	
	private LoginController loginController;
	
	public LoginWindow() {
		this.loginController = new LoginController(new LoginPanel(), this);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(SwingApp.class.getResource("/es/library/databaseinspanish/ui/images/icon.png")));
		
		loginController.init();
		
		this.pack();
		this.setLocationRelativeTo(null);
//		this.setShape(new RoundRectangle2D.Double(10, 10, this.getWidth()-1, this.getHeight()-1, 50, 50));
		this.setVisible(true);
	}
	
	public void destroy() {
		this.setVisible(false);
		this.dispose();
	}
	
}
