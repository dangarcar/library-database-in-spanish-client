package es.library.databaseinspanish.ui.contenido;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import es.library.databaseinspanish.api.utils.StaticApis;
import es.library.databaseinspanish.exceptions.prestamo.PrestamoNotAllowedException;
import es.library.databaseinspanish.model.contenido.Contenido;
import es.library.databaseinspanish.model.contenido.modeltypes.ContenidoModel;
import es.library.databaseinspanish.ui.SwingApp;
import es.library.databaseinspanish.ui.utils.OptionPanes;

public class ContenidoRendererController {

	private SwingApp app;	
	private ContenidoModel contenido;	
	private ContenidoRenderer renderer;
	private Logger logger = LogManager.getLogger(getClass());
	
	public ContenidoRendererController(SwingApp app, ContenidoModel c) {
		this.app = app;
		getContenidosInstantiated(c);
		
		renderContenido();
		
		if(!app.isGuest()) 
			renderer.getBotonPrestar().addActionListener(prestarFunction);
	}
	
	private void renderContenido() {
		this.renderer = new ContenidoRenderer(app, contenido);
		renderer.setName(contenido.getTitulo()+"_renderer");		
		app.changePanel(renderer);
	}
	
	private void getContenidosInstantiated(ContenidoModel c) {
		List<Contenido> contenidos = c.getIds().stream()
				.map(id -> StaticApis.contenidoApi().getContenidoById(id))
				.collect(Collectors.toList());
		
		c.setContenidos(contenidos);
		contenido = c;
	}
	
	ActionListener prestarFunction = (ActionEvent e) -> {
		var selectedValues = renderer.getSelectionList().getSelectedValuesList();
		
		if(selectedValues.isEmpty()) {
			OptionPanes.info("Usted no ha seleccionado ningún contenido para coger prestado.");
		}
		
		selectedValues.forEach(c -> {
			try {
				StaticApis.userApi().prestar(c.getID());
				logger.info("Usuario {} cogió prestado el contenido {}", app.getUserLoggenIn().getCorreoElectronico(), c.getID());
				OptionPanes.infoBlocking("Se ha cogido el contenido con id " + c.getID());
			} catch (PrestamoNotAllowedException e1) {
				logger.warn(e1);
				OptionPanes.errorBlocking("No puede coger prestado el contenido con id:" + c.getID());
			}
			contenido.getContenidos().set(contenido.getContenidos().indexOf(c), StaticApis.contenidoApi().getContenidoById(c.getID()));
		});
		
		renderer.getSelectionList().setModel(new ListaContenidoModel(contenido.getContenidos()));
	};
	
}
