package es.library.databaseinspanish.ui.login;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import es.library.databaseinspanish.ui.utils.ImageLabel;
import es.library.databaseinspanish.ui.utils.ProjectConstants;
import es.library.databaseinspanish.ui.utils.RoundedBorder;
import net.miginfocom.swing.MigLayout;

public class LoginPanel extends JPanel {

	private JTextField username;
	private JPasswordField password;
	private JButton registerBoton;
	private JButton botonLogin;	
	
	public LoginPanel() {
		this.setBackground(Color.WHITE);
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0};
		this.setLayout(gridBagLayout);
		
		JLabel logo = new ImageLabel(new ImageIcon(LoginWindow.class.getResource("/es/library/databaseinspanish/ui/images/icon.png")), 64, 64);
		GridBagConstraints gbc_logo = new GridBagConstraints();
		gbc_logo.insets = new Insets(0, 0, 5, 5);
		gbc_logo.gridx = 0;
		gbc_logo.gridy = 0;
		this.add(logo, gbc_logo);
		
		JLabel title = new JLabel("Iniciar sesión");
		title.setAlignmentY(Component.TOP_ALIGNMENT);
		title.setForeground(Color.BLACK);
		title.setBackground(Color.BLACK);
		title.setAlignmentX(Component.CENTER_ALIGNMENT);
		title.setFont(new Font("Segoe UI", Font.BOLD, 24));
		GridBagConstraints gbc_title = new GridBagConstraints();
		gbc_title.gridwidth = 2;
		gbc_title.insets = new Insets(0, 0, 5, 5);
		gbc_title.gridx = 0;
		gbc_title.gridy = 1;
		this.add(title, gbc_title);
		
		JPanel loginPanel = new JPanel();
		GridBagConstraints gbc_loginPanel = new GridBagConstraints();
		gbc_loginPanel.gridwidth = 2;
		gbc_loginPanel.fill = GridBagConstraints.BOTH;
		gbc_loginPanel.insets = new Insets(0, 10, 10, 10);
		gbc_loginPanel.gridx = 0;
		gbc_loginPanel.gridy = 2;
		loginPanel.setLayout(new MigLayout("", "[grow]", "[][][][][]"));
		loginPanel.setBorder(new RoundedBorder(20,new Color(240,240,240)));
		this.add(loginPanel, gbc_loginPanel);
		
		JLabel usernameLabel = new JLabel("Correo electrónico");
		usernameLabel.setPreferredSize(new Dimension(88, 20));
		usernameLabel.setFont(ProjectConstants.font12P);
		loginPanel.add(usernameLabel, "cell 0 0,aligny bottom");
		
		username = new JTextField();
		username.setPreferredSize(new Dimension(7, 30));
		username.setFont(ProjectConstants.font12P);
		loginPanel.add(username, "cell 0 1,growx");
		username.setColumns(10);
		
		JLabel passwordLabel = new JLabel("Contraseña");
		passwordLabel.setPreferredSize(new Dimension(56, 20));
		passwordLabel.setFont(ProjectConstants.font12P);
		loginPanel.add(passwordLabel, "cell 0 2,aligny bottom");
		
		password = new JPasswordField();
		password.setPreferredSize(new Dimension(7, 30));
		password.setFont(ProjectConstants.font12P);
		loginPanel.add(password, "cell 0 3,growx");
		
		botonLogin = new JButton("Entrar");
		botonLogin.setBackground(new Color(11, 84, 30));
		botonLogin.setForeground(Color.WHITE);
		botonLogin.setFont(new Font("Segoe UI", Font.BOLD, 14));
		botonLogin.setBorder(null);
		JPanel auxPanel = new JPanel();
		auxPanel.setBorder(new RoundedBorder(5,new Color(11, 84, 30)));
		auxPanel.setBackground(new Color(240,240,240));
		auxPanel.add(botonLogin);
		loginPanel.add(auxPanel, "cell 0 4,aligny bottom,growx");
		
		JPanel newAccountPanel = new JPanel();
		newAccountPanel.setBorder(new RoundedBorder(20,new Color(240,240,240)));
		GridBagConstraints gbc_newAccountPanel = new GridBagConstraints();
		gbc_newAccountPanel.gridwidth = 2;
		gbc_newAccountPanel.insets = new Insets(10, 10, 10, 10);
		gbc_newAccountPanel.fill = GridBagConstraints.BOTH;
		gbc_newAccountPanel.gridx = 0;
		gbc_newAccountPanel.gridy = 3;
		this.add(newAccountPanel, gbc_newAccountPanel);
		
		registerBoton = new JButton("¿Nuevo en la biblioteca? Crear cuenta.");
		registerBoton.setBackground(new Color(240,240,240));
		registerBoton.setFont(ProjectConstants.font12P);
		registerBoton.setBorder(null);
		newAccountPanel.add(registerBoton);
	}

	JTextField getUsername() {
		return username;
	}

	void setUsername(JTextField username) {
		this.username = username;
	}

	JPasswordField getPassword() {
		return password;
	}

	void setPassword(JPasswordField password) {
		this.password = password;
	}

	JButton getRegisterBoton() {
		return registerBoton;
	}

	void setRegisterBoton(JButton registerBoton) {
		this.registerBoton = registerBoton;
	}

	JButton getBotonLogin() {
		return botonLogin;
	}

	void setBotonLogin(JButton botonLogin) {
		this.botonLogin = botonLogin;
	}
	
}