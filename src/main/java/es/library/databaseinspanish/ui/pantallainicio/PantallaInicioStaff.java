package es.library.databaseinspanish.ui.pantallainicio;

import javax.swing.JMenu;

import es.library.databaseinspanish.ui.SwingApp;
import es.library.databaseinspanish.ui.menu.ItemMenu;
import es.library.databaseinspanish.ui.menu.dialog.staff.EliminarContenidoDialog;
import es.library.databaseinspanish.ui.utils.ProjectConstants;

public class PantallaInicioStaff extends PantallaInicioUser {
	
	public PantallaInicioStaff(SwingApp parent) {
		super(parent);
		init();
	}
	
	//TODO: Búsqueda de perfiles, adición de contenidos y préstamos manuales
	
	private void init() {
		botonMenu.getMenu().addItem(new ItemMenu("Añadir contenido", null));
		botonMenu.getMenu().addItem(new ItemMenu("Eliminar contenido", (e) -> new EliminarContenidoDialog()));
		
		var buscar = new JMenu("Buscar perfil...");
		buscar.setFont(ProjectConstants.font12P);
		buscar.add(new ItemMenu("Por ID", null));
		buscar.add(new ItemMenu("Por username", null));
		buscar.add(new ItemMenu("Por palabras clave", null));
		botonMenu.getMenu().addItem(buscar);
		
		botonMenu.getMenu().addItem(new ItemMenu("Hacer préstamo",null));
		botonMenu.getMenu().addItem(new ItemMenu("Hacer devolución",null));
	}
	
}