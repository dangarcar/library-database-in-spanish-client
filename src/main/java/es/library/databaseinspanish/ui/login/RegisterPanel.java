package es.library.databaseinspanish.ui.login;

import java.awt.Color;
import java.awt.Component;
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

import es.library.databaseinspanish.ui.utils.ProjectConstants;
import es.library.databaseinspanish.ui.utils.components.ImageLabel;
import es.library.databaseinspanish.ui.utils.components.LocalDateSelector;
import es.library.databaseinspanish.ui.utils.components.RoundedButton;
import es.library.databaseinspanish.ui.utils.components.RoundedFilledBorder;
import net.miginfocom.swing.MigLayout;

public class RegisterPanel extends JPanel {
	
	private JTextField nameTextField;
	private JTextField emailTextField;
	private JPasswordField passwordField;
	private JButton registerButton;
	private LocalDateSelector dateSelector;

	public RegisterPanel() {
		setBackground(Color.WHITE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0};
		setLayout(gridBagLayout);
		
		JLabel logo = new ImageLabel(new ImageIcon(LoginWindow.class.getResource("/es/library/databaseinspanish/ui/images/icon.png")), 64, 64);
		GridBagConstraints gbc_logo = new GridBagConstraints();
		gbc_logo.gridx = 0;
		gbc_logo.gridy = 0;
		this.add(logo, gbc_logo);
		
		JLabel title = new JLabel("Registrarse");
		title.setAlignmentY(Component.TOP_ALIGNMENT);
		title.setForeground(Color.BLACK);
		title.setBackground(Color.BLACK);
		title.setAlignmentX(Component.CENTER_ALIGNMENT);
		title.setFont(new Font("Segoe UI", Font.BOLD, 24));
		GridBagConstraints gbc_title = new GridBagConstraints();
		gbc_title.insets = new Insets(0, 0, 5, 0);
		gbc_title.gridx = 0;
		gbc_title.gridy = 1;
		this.add(title, gbc_title);
		
		JPanel panel = new JPanel();
		panel.setBorder(new RoundedFilledBorder(20, new Color(240,240,240)));
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(10, 10, 5, 10);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 2;
		add(panel, gbc_panel);
		panel.setLayout(new MigLayout("", "[][grow][grow][grow]", "[grow][grow][grow][grow]"));
		
		JLabel name = new JLabel("Nombre");
		name.setFont(ProjectConstants.font12P);
		panel.add(name, "cell 0 0,alignx trailing,gapx 0 10");
		
		nameTextField = new JTextField();
		nameTextField.setFont(ProjectConstants.font12P);
		panel.add(nameTextField, "cell 1 0 3 1,growx");
		nameTextField.setColumns(10);
		
		JLabel fecha = new JLabel("Fecha de nacimiento");
		fecha.setFont(ProjectConstants.font12P);
		panel.add(fecha, "cell 0 1,alignx trailing,gapx 0 10");
		
		dateSelector = new LocalDateSelector();
		dateSelector.setFont(ProjectConstants.font12P);
		panel.add(dateSelector, "cell 1 1 3 1,growx");
		
		JLabel email = new JLabel("Correo electrónico");
		email.setFont(ProjectConstants.font12P);
		panel.add(email, "cell 0 2,alignx trailing,gapx 0 10");
		
		emailTextField = new JTextField();
		emailTextField.setFont(ProjectConstants.font12P);
		panel.add(emailTextField, "cell 1 2 3 1,growx");
		emailTextField.setColumns(10);
		
		JLabel password = new JLabel("Contraseña");
		password.setFont(ProjectConstants.font12P);
		panel.add(password, "cell 0 3,alignx trailing,gapx 0 10");
		
		passwordField = new JPasswordField();
		passwordField.setFont(ProjectConstants.font12P);
		panel.add(passwordField, "cell 1 3 3 1,growx");
		
		RoundedButton button = new RoundedButton("Registrar");
		registerButton = button.getBtnAnadir();
		button.setBackgroundBorder(ProjectConstants.GREEN_COLOR);
		button.setBackground(Color.WHITE);
		GridBagConstraints gbc_button = new GridBagConstraints();
		gbc_button.insets = new Insets(10, 10, 10, 10);
		gbc_button.fill = GridBagConstraints.BOTH;
		gbc_button.gridx = 0;
		gbc_button.gridy = 3;
		add(button, gbc_button);
		
	}

	public JTextField getNameTextField() {
		return nameTextField;
	}

	public void setNameTextField(JTextField nameTextField) {
		this.nameTextField = nameTextField;
	}

	public JTextField getEmailTextField() {
		return emailTextField;
	}

	public void setEmailTextField(JTextField emailTextField) {
		this.emailTextField = emailTextField;
	}

	public JPasswordField getPasswordField() {
		return passwordField;
	}

	public void setPasswordField(JPasswordField passwordField) {
		this.passwordField = passwordField;
	}

	public JButton getRegisterButton() {
		return registerButton;
	}

	public void setRegisterButton(JButton registerButton) {
		this.registerButton = registerButton;
	}
	
	public LocalDateSelector getDateSelector() {
		return dateSelector;
	}
	
	public void setDateSelector(LocalDateSelector dateSelector) {
		this.dateSelector = dateSelector;
	}
}