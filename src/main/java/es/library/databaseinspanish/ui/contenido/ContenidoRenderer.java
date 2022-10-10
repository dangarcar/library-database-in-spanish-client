package es.library.databaseinspanish.ui.contenido;

import java.awt.Color;

import javax.swing.JPanel;

import es.library.databaseinspanish.ui.SwingApp;

public class ContenidoRenderer extends JPanel {

	private SwingApp app;
	
	public ContenidoRenderer(SwingApp app) {
		super();
		this.app = app;
		this.setBackground(Color.RED);
	}
	
}
