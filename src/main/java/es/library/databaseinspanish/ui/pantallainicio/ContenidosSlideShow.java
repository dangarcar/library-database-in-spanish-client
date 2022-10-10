package es.library.databaseinspanish.ui.pantallainicio;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;

import es.library.databaseinspanish.api.contenido.ContenidoApi;
import es.library.databaseinspanish.model.contenido.Contenido;
import es.library.databaseinspanish.ui.SwingApp;
import es.library.databaseinspanish.ui.contenido.ContenidoPicture;
import es.library.databaseinspanish.ui.utils.ProjectConstants;

public class ContenidosSlideShow extends JScrollPane {
	
	private ContenidoApi contenidoApi;
	
	private List<Contenido> contenidos;
	
	private GridBagLayout layout = new GridBagLayout();
	
	private GridBagConstraints constraints = new GridBagConstraints();
	
	private JPanel viewport = new JPanel();
	
	private SwingApp app;
	
	public ContenidosSlideShow(ContenidoApi api, SwingApp app) {
		this.app = app;
		this.contenidoApi = api;
		
		setBorder(new LineBorder(Color.BLACK, 5, true));
		viewport.setBackground(ProjectConstants.BACKGROUND_COLOR);
		viewport.setLayout(layout);
		this.getViewport().add(viewport);
		
		constraints.fill = GridBagConstraints.VERTICAL;
		constraints.insets = new Insets(0, 5, 0, 5);
		constraints.gridy = 0;
		
		setContenidos();

		for(var c:contenidos) {
			addRenderer(c);
		}
	}
	
	private void addRenderer(Contenido c) {
		viewport.add(new ContenidoPicture(c, 200, 200, app), constraints);
	}
	
	private void setContenidos() {
		contenidos = contenidoApi.getContenidosMasPrestados();
	}
}
