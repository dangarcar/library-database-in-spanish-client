package es.library.databaseinspanish.ui.perfil;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import es.library.databaseinspanish.model.perfil.Perfil;
import es.library.databaseinspanish.ui.SwingApp;
import es.library.databaseinspanish.ui.utils.ProjectConstants;
import es.library.databaseinspanish.ui.utils.components.NoContentLabel;

public class PerfilBusquedaResultScroll extends JScrollPane {
	
	private List<PerfilDescr> perfiles;
	private JPanel viewport = new JPanel();
	
	public PerfilBusquedaResultScroll(SwingApp parent, List<Perfil> perfiles) {
		this.perfiles = getPicturesFromModels(perfiles);
		
		setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		setBorder(null);
		this.setViewportView(viewport);
		viewport.setBackground(ProjectConstants.BACKGROUND_COLOR);
		viewport.setLayout(new BoxLayout(viewport, BoxLayout.Y_AXIS));
		
		setPictures();
	}
	
	private void setPictures() {
		if(perfiles.isEmpty()) {
			viewport.add(new NoContentLabel("Tu búsqueda no ha devuelto ningún resultado"));
			return;
		}
		
		for(PerfilDescr p : perfiles) {
			viewport.add(p);
		}
	}
	
	private List<PerfilDescr> getPicturesFromModels(List<Perfil> models){
		return models.stream()
				.map(p -> {
					PerfilDescr descr = new PerfilDescr(p);
					descr.setBackground(ProjectConstants.BACKGROUND_COLOR);
					return descr;
				}).collect(Collectors.toCollection(ArrayList::new));
	}
}
