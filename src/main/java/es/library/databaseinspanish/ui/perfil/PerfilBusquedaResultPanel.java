package es.library.databaseinspanish.ui.perfil;

import java.awt.Component;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import es.library.databaseinspanish.model.perfil.Perfil;
import es.library.databaseinspanish.ui.SwingApp;
import es.library.databaseinspanish.ui.utils.ProjectConstants;
import es.library.databaseinspanish.ui.utils.components.BotonRetroceso;

public class PerfilBusquedaResultPanel extends JPanel {

	public PerfilBusquedaResultPanel(SwingApp parent, List<Perfil> perfiles) {
		setBackground(ProjectConstants.BACKGROUND_COLOR);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		BotonRetroceso botonRetroceso = new BotonRetroceso(parent);
		add(botonRetroceso);
		
		PerfilBusquedaResultScroll resultPanel = new PerfilBusquedaResultScroll(parent, perfiles);
		resultPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		add(resultPanel);
	}
	
}
