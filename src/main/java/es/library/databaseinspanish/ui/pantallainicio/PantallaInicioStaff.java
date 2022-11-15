package es.library.databaseinspanish.ui.pantallainicio;

import javax.swing.JMenu;

import es.library.databaseinspanish.ui.SwingApp;
import es.library.databaseinspanish.ui.menu.ItemMenu;
import es.library.databaseinspanish.ui.menu.dialog.staff.AnadirContenidoDialog;
import es.library.databaseinspanish.ui.menu.dialog.staff.BuscarPerfilDialog;
import es.library.databaseinspanish.ui.menu.dialog.staff.EliminarContenidoDialog;
import es.library.databaseinspanish.ui.menu.dialog.staff.HacerDevolucionDialog;
import es.library.databaseinspanish.ui.menu.dialog.staff.HacerPrestamoDialog;
import es.library.databaseinspanish.ui.utils.ProjectConstants;

public class PantallaInicioStaff extends PantallaInicioUser {
	
	public PantallaInicioStaff(SwingApp parent) {
		super(parent);
		init();
	}
	
	private void init() {
		botonMenu.getMenu().addItem(new ItemMenu("Añadir contenido", (e) -> new AnadirContenidoDialog()));
		botonMenu.getMenu().addItem(new ItemMenu("Eliminar contenido", (e) -> new EliminarContenidoDialog()));
		
		var buscar = new JMenu("Buscar perfil...");
		buscar.setFont(ProjectConstants.font12P);
		buscar.add(new ItemMenu("Por ID", (e) -> new BuscarPerfilDialog.IDBusqueda(parent)));
		buscar.add(new ItemMenu("Por username", (e) -> new BuscarPerfilDialog.UsernameBusqueda(parent)));
		buscar.add(new ItemMenu("Por palabras clave", (e) -> new BuscarPerfilDialog.PalabrasClaveBusqueda(parent)));
		botonMenu.getMenu().addItem(buscar);
		
		botonMenu.getMenu().addItem(new ItemMenu("Hacer préstamo", (e) -> new HacerPrestamoDialog()));
		botonMenu.getMenu().addItem(new ItemMenu("Hacer devolución", (e) -> new HacerDevolucionDialog()));
	}
	
}