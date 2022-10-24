package es.library.databaseinspanish.ui.pantallainicio;

import javax.swing.JPanel;

import es.library.databaseinspanish.exceptions.perfil.RoleNotFoundException;
import es.library.databaseinspanish.model.perfil.Roles;
import es.library.databaseinspanish.ui.SwingApp;
import es.library.databaseinspanish.ui.utils.ProjectConstants;

public class PantallaInicio extends JPanel {

	protected SwingApp parent;
	
	protected PantallaInicio(SwingApp parent) {
		super();
		this.parent = parent;
		setBackground(ProjectConstants.BACKGROUND_COLOR);
		setName("Home");
	}
	
	public static PantallaInicio getInstance(SwingApp parent, String mode) {
		return switch (mode) {
		case "guest" -> new PantallaInicioGuest(parent);
		case "user" -> new PantallaInicioUser(parent);
		case "staff" -> new PantallaInicioStaff(parent);
		case "admin" -> new PantallaInicioAdmin(parent);
		default -> throw new RoleNotFoundException("No existe el rol "+mode+" en la biblioteca");
		};
	}
	
	public static PantallaInicio getInstance(SwingApp parent, Roles mode) {
		return switch (mode) {
		case ROLE_GUEST -> new PantallaInicioGuest(parent);
		case ROLE_USER -> new PantallaInicioUser(parent);
		case ROLE_STAFF -> new PantallaInicioStaff(parent);
		case ROLE_ADMIN -> new PantallaInicioAdmin(parent);
		};
	}
	
}
