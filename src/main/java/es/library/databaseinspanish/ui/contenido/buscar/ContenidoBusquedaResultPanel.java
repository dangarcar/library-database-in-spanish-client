package es.library.databaseinspanish.ui.contenido.buscar;

import java.util.List;

import javax.swing.JPanel;

import es.library.databaseinspanish.model.contenido.modeltypes.ContenidoModel;
import es.library.databaseinspanish.ui.SwingApp;
import es.library.databaseinspanish.ui.utils.BotonRetroceso;
import es.library.databaseinspanish.ui.utils.ProjectConstants;
import net.miginfocom.swing.MigLayout;
import javax.swing.BoxLayout;
import java.awt.Component;

public class ContenidoBusquedaResultPanel extends JPanel {
	
	public ContenidoBusquedaResultPanel(SwingApp parent, List<ContenidoModel> models) {
		setBackground(ProjectConstants.BACKGROUND_COLOR);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		BotonRetroceso botonRetroceso = new BotonRetroceso(parent);
		add(botonRetroceso);
		
		ContenidoBusquedaResultScroll contenidoBusquedaResultScroll = new ContenidoBusquedaResultScroll(parent, models);
		contenidoBusquedaResultScroll.setAlignmentX(Component.LEFT_ALIGNMENT);
		add(contenidoBusquedaResultScroll);
	}

}
