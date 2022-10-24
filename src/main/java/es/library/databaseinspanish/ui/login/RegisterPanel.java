package es.library.databaseinspanish.ui.login;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.time.Month;
import java.time.Year;
import java.util.stream.IntStream;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import es.library.databaseinspanish.ui.utils.ImageLabel;
import es.library.databaseinspanish.ui.utils.ProjectConstants;
import es.library.databaseinspanish.ui.utils.RoundedBorder;
import net.miginfocom.swing.MigLayout;
import java.awt.Dimension;

public class RegisterPanel extends JPanel {
	
	private JTextField nameTextField;
	private JTextField emailTextField;
	private JPasswordField passwordField;
	private JButton registerButton;
	private JComboBox<Integer> dayComboBox;
	private JComboBox<String> monthComboBox;
	private JComboBox<Integer> yearComboBox;

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
		panel.setBorder(new RoundedBorder(20, new Color(240,240,240)));
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
		
		dayComboBox = new JComboBox<Integer>();
		dayComboBox.setMinimumSize(new Dimension(64, 22));
		dayComboBox.setFont(ProjectConstants.font12P);
		panel.add(dayComboBox, "cell 1 1,growx");

		String[] months = ProjectConstants.MONTHS.keySet().stream().sorted((s1,s2) -> ProjectConstants.MONTHS.get(s1).compareTo(ProjectConstants.MONTHS.get(s2))).toArray(String[]::new);
		monthComboBox = new JComboBox<String>(months);
		monthComboBox.setMinimumSize(new Dimension(64, 22));
		monthComboBox.setFont(ProjectConstants.font12P);
		monthComboBox.addActionListener((e)->{
			dayComboBox.setModel(new DefaultComboBoxModel<>(getDaysFromMonth(ProjectConstants.MONTHS.get(monthComboBox.getSelectedItem()))));
		});
		panel.add(monthComboBox, "cell 2 1,growx");
		
		Integer[] years = IntStream.rangeClosed(1, Year.now().getValue()).boxed().sorted((f1,f2) -> Integer.compare(f2, f1)).toArray(Integer[]::new);
		yearComboBox = new JComboBox<Integer>(years);
		yearComboBox.setMinimumSize(new Dimension(64, 22));
		yearComboBox.setFont(ProjectConstants.font12P);
		panel.add(yearComboBox, "cell 3 1,growx");
		
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
		
		JPanel newPanel = new JPanel();
		newPanel.setBorder(new RoundedBorder(20, new Color(11, 84, 30)));
		GridBagConstraints gbc_newPanel = new GridBagConstraints();
		gbc_newPanel.insets = new Insets(10, 10, 10, 10);
		gbc_newPanel.fill = GridBagConstraints.BOTH;
		gbc_newPanel.gridx = 0;
		gbc_newPanel.gridy = 3;
		add(newPanel, gbc_newPanel);
		
		registerButton = new JButton("Registrar");
		registerButton.setBackground(new Color(11, 84, 30));
		registerButton.setForeground(Color.white);
		registerButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
		registerButton.setBorder(null);
		newPanel.add(registerButton);	
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

	public JComboBox<Integer> getDayComboBox() {
		return dayComboBox;
	}

	public void setDayComboBox(JComboBox<Integer> dayComboBox) {
		this.dayComboBox = dayComboBox;
	}

	public JComboBox<String> getMonthComboBox() {
		return monthComboBox;
	}

	public void setMonthComboBox(JComboBox<String> monthComboBox) {
		this.monthComboBox = monthComboBox;
	}

	public JComboBox<Integer> getYearComboBox() {
		return yearComboBox;
	}

	public void setYearComboBox(JComboBox<Integer> yearComboBox) {
		this.yearComboBox = yearComboBox;
	}
	
	public Integer[] getDaysFromMonth(Month m) {
		return IntStream.rangeClosed(1, m.maxLength()).boxed().toArray(Integer[]::new);
	}
	
}