package es.library.databaseinspanish.ui.pantallainicio;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import es.library.databaseinspanish.api.utils.StaticApis;
import es.library.databaseinspanish.exceptions.contenido.ContenidoNotFoundException;
import es.library.databaseinspanish.model.contenido.modeltypes.ContenidoModel;
import es.library.databaseinspanish.ui.SwingApp;
import es.library.databaseinspanish.ui.contenido.ContenidoPicture;
import es.library.databaseinspanish.ui.utils.OptionPanes;
import es.library.databaseinspanish.ui.utils.ProjectConstants;
import es.library.databaseinspanish.ui.utils.components.NoContentLabel;

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
		setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
		setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		viewport.setBackground(ProjectConstants.BACKGROUND_COLOR);
		viewport.setLayout(layout);
		this.setViewportView(viewport);
		
		constraints.fill = GridBagConstraints.VERTICAL;
		constraints.insets = new Insets(0, 5, 0, 5);
		constraints.gridy = 0;
		
		setContenidos();

		if(contenidos==null || contenidos.isEmpty()) {
			viewport.add(new NoContentLabel("No hay contenidos en recomendados"));
			return;
		}
		
		for(var c:contenidos) {
			addRenderer(c);
		}
	}
	
	private void addRenderer(ContenidoModel c) {
		var picture = new ContenidoPicture(c, 200, 200, app);
		viewport.add(picture, constraints);
	}
	
	private void setContenidos() {
		try{
			contenidos = StaticApis.contenidoApi().getContenidosMasPrestados();
		} catch(ContenidoNotFoundException e) {
			logger.warn(e);
		} catch(Exception e) {
			logger.error(e);
			OptionPanes.error(e.getMessage());
		}
	}
}
