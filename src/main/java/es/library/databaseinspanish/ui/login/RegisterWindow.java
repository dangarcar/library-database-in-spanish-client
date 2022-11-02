package es.library.databaseinspanish.ui.login;

import java.awt.Toolkit;

import javax.swing.JFrame;

import es.library.databaseinspanish.ui.SwingApp;

public class RegisterWindow extends JFrame {
	
	private RegisterController controller;
	
	public RegisterWindow() {
		this.controller = new RegisterController(new RegisterPanel(), this);
		
		initUI();
	}
	
	public RegisterWindow(SwingApp parent) {
		this.controller = new RegisterController(parent, new RegisterPanel(), this);
		
		initUI();
	}
	
	private void initUI() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(SwingApp.class.getResource("/es/library/databaseinspanish/ui/images/icon.png")));
		
		controller.init();
		
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	public void destroy() {
		this.setVisible(false);
		this.dispose();
	}
	
}
