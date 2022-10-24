package es.library.databaseinspanish.ui.pantallainicio;

import es.library.databaseinspanish.ui.SwingApp;
import es.library.databaseinspanish.ui.user.UserButton;

public class PantallaInicioUser extends PantallaInicioGuest {

	public PantallaInicioUser(SwingApp parent) {
		super(parent);
		init();
	}

	private void init() {
		add(new UserButton(parent), "cell 0 1 1 4");
	}
	
}
