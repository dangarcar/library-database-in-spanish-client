package es.library.databaseinspanish.ui.contenido;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import es.library.databaseinspanish.model.contenido.modeltypes.ContenidoModel;
import es.library.databaseinspanish.ui.SwingApp;
import es.library.databaseinspanish.ui.utils.ImageLabel;
import es.library.databaseinspanish.utils.Utils;

public class ContenidoPicture extends JButton {

	private ContenidoModel contenido;
	
	public ContenidoPicture(ContenidoModel c, int width, int height, SwingApp app) {
		super(c.getTitulo());
		setBorder(new LineBorder(Color.ORANGE, 5, true));
		setBorderPainted(false);
		contenido = c;
		setBackground(Color.WHITE);
		setHorizontalTextPosition(SwingConstants.CENTER);
		setVerticalTextPosition(SwingConstants.BOTTOM);
		setFont(new Font("Segoe UI", Font.PLAIN,12));
		this.setCursor(new Cursor(Cursor.HAND_CURSOR));
		this.setIcon(new ImageLabel(Utils.getContenidoImageIcon(contenido.toContenido()),width,height - getFontMetrics(getFont()).getHeight()).getIcon());
		this.setHorizontalAlignment(CENTER);
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				setBorderPainted(true);
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				setBorderPainted(false);
			}
		});
	}

}
