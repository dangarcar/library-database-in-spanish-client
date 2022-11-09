package es.library.databaseinspanish.ui.pantallainicio;

import es.library.databaseinspanish.ui.SwingApp;
import es.library.databaseinspanish.ui.menu.ItemMenu;
import es.library.databaseinspanish.ui.menu.dialog.admin.AnadirPerfilDialog;
import es.library.databaseinspanish.ui.menu.dialog.admin.CambiarRolDialog;
import es.library.databaseinspanish.ui.menu.dialog.admin.EliminarPerfilDialog;

public class PantallaInicioAdmin extends PantallaInicioStaff {

	public PantallaInicioAdmin(SwingApp parent) {
		super(parent);
		init();
	}

	private void init() {
		botonMenu.getMenu().addItem(new ItemMenu("Añadir perfil", 			(e) -> new AnadirPerfilDialog()));
		botonMenu.getMenu().addItem(new ItemMenu("Eliminar perfil", 		(e) -> new EliminarPerfilDialog()));		
		botonMenu.getMenu().addItem(new ItemMenu("Cambiar rol de perfil", 	(e) -> new CambiarRolDialog()));
	}
	
}