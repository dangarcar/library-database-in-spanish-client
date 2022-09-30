package es.library.databaseinspanish.ui.pantallainicio;

import javax.swing.JPanel;

import es.library.databaseinspanish.exceptions.perfil.RoleNotFoundException;
import es.library.databaseinspanish.ui.SwingApp;
import es.library.databaseinspanish.ui.utils.ProjectConstants;

public class PantallaInicio extends JPanel {

	protected PantallaInicio(SwingApp parent) {
		setBackground(ProjectConstants.BACKGROUND_COLOR);
	}
	
	public static PantallaInicio getInstance(SwingApp parent, String mode) {
		switch (mode) {
		case "guest":
			return new PantallaInicioGuest(parent);
		case "user":
			return new PantallaInicioUser(parent);
		case "staff":
			return new PantallaInicioStaff(parent);
		case "admin":
			return new PantallaInicioAdmin(parent);
		default:
			throw new RoleNotFoundException("No existe el rol "+mode+" en la biblioteca");
		}
	}
	
}
