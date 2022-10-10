package es.library.databaseinspanish.ui.pantallainicio;

import es.library.databaseinspanish.ui.SwingApp;
import es.library.databaseinspanish.ui.utils.ImageLabel;
import es.library.databaseinspanish.utils.Utils;

public class PantallaInicioUser extends PantallaInicioGuest {

	public PantallaInicioUser(SwingApp parent) {
		super(parent);
		init();
	}

	private void init() {
		ImageLabel imageLabel = new ImageLabel(Utils.getImageFromPerfil(parent.getUserLoggenIn().getNombre()));
		add(imageLabel, "cell 0 1 1 4");
	}
	
}
