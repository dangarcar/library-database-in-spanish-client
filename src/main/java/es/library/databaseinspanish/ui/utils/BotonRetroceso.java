package es.library.databaseinspanish.ui.utils;

import javax.swing.ImageIcon;

import es.library.databaseinspanish.ui.SwingApp;
import es.library.databaseinspanish.ui.contenido.ContenidoRenderer;
import es.library.databaseinspanish.utils.Utils;

public class BotonRetroceso extends ImageButton {
	
	private SwingApp parent;
	
	public BotonRetroceso(SwingApp parent) {
		super(Utils.getScaledIcon(new ImageIcon(ContenidoRenderer.class.getResource("/es/library/databaseinspanish/ui/images/flechaAtras.png")),32,32));
		this.parent = parent;
		
		this.setBackground(ProjectConstants.BACKGROUND_COLOR);
		this.setBorder(null);
		this.addActionListener((e) -> parent.returnHome());
	}
	
}
