package es.library.databaseinspanish.ui.menu.dialog.admin;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import es.library.databaseinspanish.api.utils.StaticApis;
import es.library.databaseinspanish.exceptions.perfil.PerfilNotFoundException;
import es.library.databaseinspanish.model.perfil.Perfil;
import es.library.databaseinspanish.ui.SwingApp;
import es.library.databaseinspanish.ui.login.AuthenticationManager;
import es.library.databaseinspanish.ui.utils.OptionPanes;
import es.library.databaseinspanish.ui.utils.ProjectConstants;
import es.library.databaseinspanish.ui.utils.RolesComboBox;
import es.library.databaseinspanish.ui.utils.RoundedButton;
import net.miginfocom.swing.MigLayout;

public class CambiarRolDialog extends JDialog {
	
	private Logger logger = LogManager.getLogger();
	
	private RolesComboBox rolesComboBox;
	private JTextField usernameField;
	
	public CambiarRolDialog() {
		setResizable(false);
		setBounds(200,200,392,256);
		setTitle("Cambir rol de perfil");
		setIconImage(Toolkit.getDefaultToolkit().getImage(SwingApp.class.getResource("/es/library/databaseinspanish/ui/images/usuario.png")));
		getContentPane().setBackground(ProjectConstants.BACKGROUND_COLOR);
		getContentPane().setLayout(new MigLayout("", "[grow][grow]", "[][grow][grow][]"));
		
		JLabel titulo = new JLabel("Cambiar rol de perfil");
		titulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
		getContentPane().add(titulo, "cell 0 0 2 1,alignx center");
		
		JLabel email = new JLabel("Correo electrónico: ");
		email.setFont(ProjectConstants.font12P);
		getContentPane().add(email, "cell 0 1,alignx right");
		
		usernameField = new JTextField();
		usernameField.setFont(ProjectConstants.font12P);
		usernameField.setColumns(10);
		getContentPane().add(usernameField, "cell 1 1");
		
		JLabel roles = new JLabel("Rol:");
		roles.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		getContentPane().add(roles, "cell 0 2,alignx trailing");
		
		rolesComboBox = new RolesComboBox();
		getContentPane().add(rolesComboBox, "cell 1 2");
		
		
		RoundedButton eliminarButton = new RoundedButton("Cambiar rol");
		eliminarButton.setBackgroundBorder(ProjectConstants.GREEN_COLOR);
		eliminarButton.addActionListener(listener);
		getContentPane().add(eliminarButton, "cell 0 3 2 1,grow");
		
		setLocationRelativeTo(null);
		setVisible(true);
	}

	private ActionListener listener = (ActionEvent e) -> {
		Perfil perfil = null;
		String email = usernameField.getText();
		
		if(email.isBlank()) {
			OptionPanes.warn("El correo electrónico no debe estar en blanco");
			AuthenticationManager.setNotValidJTextField(usernameField);
			return;
		}
		if(!email.matches("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")) {
			OptionPanes.warn("El correo electrónico debe ser válido");
			AuthenticationManager.setNotValidJTextField(usernameField);
			return;
		}
		AuthenticationManager.setValidJTextField(usernameField);
		
		try {
			perfil = StaticApis.perfilApi().getPerfilByUsername(email);
		} catch (PerfilNotFoundException e1) {
			OptionPanes.errorBlocking("No existe ningún perfil con el email "+email);
			logger.warn("No existe perfil con usuario "+email, e1);
			AuthenticationManager.setNotValidJTextField(usernameField);
			return;
		} catch (Exception e1) {
			OptionPanes.error(e1.getMessage());
			logger.warn("Error en el cambio de rol de perfil",e1);
			AuthenticationManager.setNotValidJTextField(usernameField);
			return;
		}
		
		try {
			StaticApis.perfilApi().setRole(perfil.getID(), rolesComboBox.getRole());
			logger.info("Ahora el perfil {} es {}",perfil.getCorreoElectronico(),perfil.getRole());
			
			OptionPanes.info("Ahora el perfil " + perfil.getCorreoElectronico() + " es " + perfil.getRole());
			
			this.setVisible(false);
			this.dispose();
		} catch (Exception e1) {
			OptionPanes.error(e1.getMessage());
			logger.warn("Error en el cambio de rol de perfil",e1);
		}	
	};	
}
