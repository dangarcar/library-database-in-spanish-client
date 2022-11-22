package es.library.databaseinspanish.ui.menu;

import java.awt.Desktop;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import es.library.databaseinspanish.ui.SwingApp;
import es.library.databaseinspanish.ui.utils.OptionPanes;

public class Menu extends JPopupMenu {

	private List<JMenuItem> items = new ArrayList<>();
	
	public Menu(SwingApp parent) {
//		items.add(new ItemMenu("Ayuda", null));
		items.add(new ItemMenu("Más información", (e) -> {
			try {
				Desktop.getDesktop().browse(new URI("https://github.com/dangarcar/library-database-in-spanish-client"));
			} catch (Exception e1) {
				OptionPanes.error("No se ha podido conseguir más información");
			}
		}));
		
		for(var item: items) {
			this.add(item);
		}
	}
	
	public void addItem(JMenuItem item) {
		items.add(item);
		this.add(item);
	}
	
	public void removeItem(JMenuItem item) {
		items.remove(item);
		this.remove(item);
	}
	
	public void removeAll() {
		items.clear();
		this.removeAll();
	}
	
}
