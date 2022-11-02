package es.library.databaseinspanish.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

import es.library.databaseinspanish.model.contenido.Contenido;
import es.library.databaseinspanish.model.contenido.Soporte;
import es.library.databaseinspanish.model.perfil.Roles;

public class ImageUtils {

	public static ImageIcon getImagenFromSoporte(Soporte soporte) {
		return switch (soporte) {
		case BLURAY ->	new ImageIcon(Utils.class.getResource("/es/library/databaseinspanish/ui/images/bluray.png"));
		case CASETE -> 	new ImageIcon(Utils.class.getResource("/es/library/databaseinspanish/ui/images/casete.png"));
		case CD -> 		new ImageIcon(Utils.class.getResource("/es/library/databaseinspanish/ui/images/cd.png"));
		case DVD -> 	new ImageIcon(Utils.class.getResource("/es/library/databaseinspanish/ui/images/dvd.png"));
		case E_BOOK -> 	new ImageIcon(Utils.class.getResource("/es/library/databaseinspanish/ui/images/ebook.png"));
		case FISICO -> 	new ImageIcon(Utils.class.getResource("/es/library/databaseinspanish/ui/images/libro.png"));
		case VINILO -> 	new ImageIcon(Utils.class.getResource("/es/library/databaseinspanish/ui/images/vinilo.png"));
		};
	}

	public static ImageIcon getImagenFromRole(Roles roles) {
		return switch (roles) {
		case ROLE_GUEST -> 	new ImageIcon(Utils.class.getResource("/es/library/databaseinspanish/ui/images/error.png"));
		case ROLE_USER -> 	new ImageIcon(Utils.class.getResource("/es/library/databaseinspanish/ui/images/usuario.png"));
		case ROLE_STAFF -> 	new ImageIcon(Utils.class.getResource("/es/library/databaseinspanish/ui/images/staff.png"));
		case ROLE_ADMIN -> 	new ImageIcon(Utils.class.getResource("/es/library/databaseinspanish/ui/images/admin.png"));
		};
	}

	public static ImageIcon getImagenFromPerfil(String nombre) {
		Image image = new BufferedImage(256, 256, BufferedImage.TYPE_INT_ARGB);
		int hash = nombre.hashCode();
		int r,g,b;
		r = ((byte) (hash)) + 128;
		g = ((byte) (hash >>> 8)) + 128;
		b = ((byte) (hash >>> 16)) + 128;
		
		Graphics graphics = image.getGraphics();
		
		String message = String.valueOf(nombre.charAt(0)).toUpperCase();
		
		graphics.setClip(new Ellipse2D.Float(0, 0, 256, 256));
		
		graphics.setColor(new Color(r, g, b));
		graphics.fillRect(0, 0, 256, 256);
		graphics.setFont(new Font("Segoe UI",Font.PLAIN, 192));
		graphics.setColor(Color.WHITE);
		
		FontMetrics metrics = graphics.getFontMetrics();
		
		graphics.drawString(message, (int) (256/2) - (metrics.stringWidth(message)/2), (int) (256/2) + metrics.getAscent() - (metrics.getHeight()/2) -12);
		
		graphics.dispose();
		
		return new ImageIcon(image);
	}

	public static ImageIcon getImagenFromContenido(Contenido c) {
		ImageIcon image;
		if(c.getImagen() == null) {
			image = getImagenFromSoporte(c.getSoporte());
		}
		else {
			image = new ImageIcon(c.getImagen());
		}
		return image;
	}

	public static ImageIcon getScaledIcon(ImageIcon icon, int width, int height) {
		return new ImageIcon(icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));
	}

}
