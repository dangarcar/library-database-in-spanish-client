package es.library.databaseinspanish.ui.pantallainicio;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.client.ResourceAccessException;

import es.library.databaseinspanish.api.utils.StaticApis;
import es.library.databaseinspanish.model.contenido.modeltypes.ContenidoModel;
import es.library.databaseinspanish.ui.SwingApp;
import es.library.databaseinspanish.ui.contenido.ContenidoPicture;
import es.library.databaseinspanish.ui.contenido.ContenidoRendererController;
import es.library.databaseinspanish.ui.utils.OptionPanes;
import es.library.databaseinspanish.ui.utils.ProjectConstants;

public class ContenidosSlideShow extends JScrollPane {
	
	private Logger logger = LogManager.getLogger(getClass());
	
	private List<? extends ContenidoModel> contenidos;
	
	private GridBagLayout layout = new GridBagLayout();
	
	private GridBagConstraints constraints = new GridBagConstraints();
	
	private JPanel viewport = new JPanel();
	
	private SwingApp app;
	
	public ContenidosSlideShow(SwingApp app) {
		this.app = app;
		
		setBorder(new LineBorder(Color.BLACK, 5, true));
		viewport.setBackground(ProjectConstants.BACKGROUND_COLOR);
		viewport.setLayout(layout);
		this.getViewport().add(viewport);
		
		constraints.fill = GridBagConstraints.VERTICAL;
		constraints.insets = new Insets(0, 5, 0, 5);
		constraints.gridy = 0;
		
		setContenidos();

		if(contenidos==null || contenidos.isEmpty()) {
			viewport.add(new JLabel("No hay contenidos disponibles"));
			return;
		}
		
		for(var c:contenidos) {
			addRenderer(c);
		}
	}
	
	private void addRenderer(ContenidoModel c) {
		var picture = new ContenidoPicture(c, 200, 200, app);
		picture.addActionListener((e) -> {
			new ContenidoRendererController(app, c);
		});
		viewport.add(picture, constraints);
	}
	
	private void setContenidos() {
		try{
			contenidos = StaticApis.contenidoApi().getContenidosMasPrestados();
		} catch(ResourceAccessException e) {
			logger.error(e);
			OptionPanes.error(e.getMessage());
		}
	}
}
