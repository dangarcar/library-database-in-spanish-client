package es.library.databaseinspanish.ui.utils.components;

import javax.swing.ImageIcon;

import es.library.databaseinspanish.ui.SwingApp;
import es.library.databaseinspanish.ui.contenido.ContenidoRenderer;
import es.library.databaseinspanish.ui.utils.ImageUtils;
import es.library.databaseinspanish.ui.utils.ProjectConstants;

public class BotonRetroceso extends ImageButton {
	
	public BotonRetroceso(SwingApp parent) {
		super(ImageUtils.getScaledIcon(new ImageIcon(ContenidoRenderer.class.getResource("/es/library/databaseinspanish/ui/images/flechaAtras.png")),32,32));
		
		this.setBackground(ProjectConstants.BACKGROUND_COLOR);
		this.setBorder(null);
		this.addActionListener((e) -> parent.returnHome());
	}
	
}
