package es.library.databaseinspanish.ui.menu.dialog.admin;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import es.library.databaseinspanish.api.utils.StaticApis;
import es.library.databaseinspanish.exceptions.perfil.EmailAlreadyExistPerfilException;
import es.library.databaseinspanish.exceptions.perfil.IllegalPerfilException;
import es.library.databaseinspanish.exceptions.perfil.UnexpectedPerfilException;
import es.library.databaseinspanish.model.perfil.Perfil;
import es.library.databaseinspanish.ui.SwingApp;
import es.library.databaseinspanish.ui.login.RegisterController;
import es.library.databaseinspanish.ui.perfil.PerfilDescr;
import es.library.databaseinspanish.ui.utils.OptionPanes;
import es.library.databaseinspanish.ui.utils.ProjectConstants;
import es.library.databaseinspanish.ui.utils.Utils;
import es.library.databaseinspanish.ui.utils.components.LocalDateSelector;
import es.library.databaseinspanish.ui.utils.components.RolesComboBox;
import es.library.databaseinspanish.ui.utils.components.RoundedButton;
import net.miginfocom.swing.MigLayout;

public class AnadirPerfilDialog extends JDialog {
	
	private Logger logger = LogManager.getLogger();
	
	private JTextField textFieldNombre;
	private JPasswordField textFieldPassword;
	private JTextField textFieldCorreo;
	private LocalDateSelector dateSelector;
	private RolesComboBox roleComboBox;
	
	public AnadirPerfilDialog() {
		setResizable(false);
		setBounds(200,200,400,450);
		setTitle("Añadir perfil");
		setIconImage(Toolkit.getDefaultToolkit().getImage(SwingApp.class.getResource("/es/library/databaseinspanish/ui/images/usuario.png")));
		getContentPane().setLayout(new MigLayout("", "[][][grow]", "[grow][grow][grow][grow][grow][grow][grow]"));
		getContentPane().setBackground(ProjectConstants.BACKGROUND_COLOR);
		
		JLabel lblEnunciado = new JLabel("Añadir perfil");
		lblEnunciado.setFont(new Font("Segoe UI", Font.BOLD, 18));
		getContentPane().add(lblEnunciado, "cell 0 0 3 1,alignx center,aligny center");
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setFont(ProjectConstants.font12P);
		getContentPane().add(lblNombre, "cell 0 1,alignx right,aligny center");
		
		textFieldNombre = new JTextField();
		textFieldNombre.setFont(ProjectConstants.font12P);
		getContentPane().add(textFieldNombre, "cell 2 1,growx,aligny center");
		textFieldNombre.setColumns(10);
		
		JLabel lblPassword = new JLabel("Contraseña:");
		lblPassword.setFont(ProjectConstants.font12P);
		getContentPane().add(lblPassword, "cell 0 5,alignx right,aligny center");
		
		textFieldPassword = new JPasswordField();
		textFieldPassword.setFont(ProjectConstants.font12P);
		textFieldPassword.setColumns(10);
		getContentPane().add(textFieldPassword, "cell 2 5,growx,aligny center");
		
		JLabel lblFechaDeNacimiento = new JLabel("Fecha de nacimiento:");
		lblFechaDeNacimiento.setFont(ProjectConstants.font12P);
		getContentPane().add(lblFechaDeNacimiento, "cell 0 3,alignx right,aligny center");
		
		dateSelector = new LocalDateSelector();
		dateSelector.setFont(ProjectConstants.font12P);
		dateSelector.setBackground(ProjectConstants.BACKGROUND_COLOR);
		getContentPane().add(dateSelector, "cell 2 3, growx,aligny center");
		
		JLabel lblCorreoElectronico = new JLabel("Correo electrónico:");
		lblCorreoElectronico.setFont(ProjectConstants.font12P);
		getContentPane().add(lblCorreoElectronico, "cell 0 4,alignx right,aligny center");
		
		textFieldCorreo = new JTextField();
		textFieldCorreo.setFont(ProjectConstants.font12P);
		textFieldCorreo.setColumns(10);
		getContentPane().add(textFieldCorreo, "cell 2 4,growx,aligny center");
		
		JLabel lblRole = new JLabel("Rol:");
		lblRole.setFont(ProjectConstants.font12P);
		getContentPane().add(lblRole, "cell 0 2,alignx right");
		
		roleComboBox = new RolesComboBox();
		getContentPane().add(roleComboBox, "cell 2 2,growx");

		RoundedButton button = new RoundedButton("Añadir");
		button.addActionListener(anadirPerfil);
		button.setBackgroundBorder(ProjectConstants.GREEN_COLOR);
		getContentPane().add(button, "cell 0 6 3 1,grow");
		
		setLocationRelativeTo(null);
		setVisible(true);
	}

	private ActionListener anadirPerfil = (ActionEvent e) -> {
		Perfil perfil = null;
		try {
			perfil = getPerfil();
		} catch (Exception e1) {
			return;
		}
		
		try {
			int opt = JOptionPane.showConfirmDialog(null, new PerfilDescr(perfil), "¿Son correctos estos datos?", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);
			if(opt != 0) return;
			
			StaticApis.perfilApi().addPerfil(perfil);
			logger.info("Se añadió un perfil con username: {}",perfil.getCorreoElectronico());
			
			OptionPanes.info("Se ha añadido correctamente el perfil con email: "+perfil.getCorreoElectronico());
			
			this.setVisible(false);
			this.dispose();
		} catch(IllegalPerfilException e1) {
			OptionPanes.error("Los siguientes datos no son correctos:"+e1.getMessage().replace("\n", " "));
			logger.error("Error en la adición de perfiles a la biblioteca",e1);
		} catch(EmailAlreadyExistPerfilException e1) {
			OptionPanes.error(e1.getMessage());
			textFieldCorreo.setText("");
			Utils.setNotValidJTextField(textFieldCorreo);
			logger.error("Error en la adición de perfiles a la biblioteca",e1);
		} catch (UnexpectedPerfilException e1) {
			OptionPanes.error("No se ha añadido el perfil a la base de datos de la biblioteca");
			logger.error("Error en la adición de perfiles a la biblioteca",e1);
		}
	};
	
	private Perfil getPerfil() {
		Perfil perfil = new Perfil();
		
		String name = textFieldNombre.getText();
		if(name.isBlank()) {
			OptionPanes.warn("El nombre no debe estar en blanco");
			Utils.setNotValidJTextField(textFieldNombre);
			throw new IllegalPerfilException("El nombre no debe estar en blanco");
		}	
		perfil.setNombre(name);
		Utils.setValidJTextField(textFieldNombre);
		
		String email = textFieldCorreo.getText();
		if(email.isBlank()) {
			OptionPanes.warn("El correo electrónico no debe estar en blanco");
			Utils.setNotValidJTextField(textFieldCorreo);
			throw new IllegalPerfilException("El correo electrónico no debe estar en blanco");
		}
		if(!email.matches("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")) {
			OptionPanes.warn("El correo electrónico debe ser válido");
			Utils.setNotValidJTextField(textFieldCorreo);
			throw new IllegalPerfilException("El correo electrónico debe ser válido");
		}
		perfil.setCorreoElectronico(email);
		Utils.setValidJTextField(textFieldCorreo);
		
		String password = String.valueOf(textFieldPassword.getPassword());
		if(password.isBlank()) {
			OptionPanes.warn("La contraseña no debe estar en blanco");
			Utils.setNotValidJTextField(textFieldPassword);
			throw new IllegalPerfilException("El contraseña no debe estar en blanco");
		}
		if(!password.matches("^(?=.*[0-9])(?=.*[a-zA-Z])(?=\\S+$).{8,}$")) {
			OptionPanes.warn("La contraseña debe tener más de 8 caracteres y contener letras y números");
			Utils.setNotValidJTextField(textFieldPassword);
			throw new IllegalPerfilException("La contraseña debe tener más de 8 caracteres y contener letras y números");
		}
		perfil.setContrasena(password);
		Utils.setValidJTextField(textFieldPassword);
		
		LocalDate date = dateSelector.getLocalDate();
		if(date.isAfter(LocalDate.now().minusYears(ProjectConstants.EDAD_MINIMA))) {
			OptionPanes.warn("El perfil debe tener al mínimo "+ProjectConstants.EDAD_MINIMA+" años");
			throw new IllegalPerfilException("La contraseña debe tener más de 8 caracteres y contener letras y números");
		}
		perfil.setFechaNacimiento(date);
		
		perfil.setRole(roleComboBox.getRole());
		
		JPanel panel = new JPanel();
		JPasswordField passwordField = new JPasswordField(32);
		panel.add(passwordField);
		RegisterController.confirmPassword(panel, passwordField);		
		while (!password.equals(String.valueOf(passwordField.getPassword()))) {
			OptionPanes.warnBlocking("La contraseña de la confirmación no se corresponde con la contraseña original");
			passwordField.setText("");
			RegisterController.confirmPassword(panel, passwordField);
		}
		
		return perfil;
	}
}
