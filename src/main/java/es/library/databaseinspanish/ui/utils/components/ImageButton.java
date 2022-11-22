package es.library.databaseinspanish.ui.utils.components;

import java.awt.Cursor;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class ImageButton extends JButton {

	public ImageButton(ImageIcon image) {
		super();
		this.setCursor(new Cursor(Cursor.HAND_CURSOR));
		this.setIcon(image);
		this.setVerticalAlignment(CENTER);
		this.setHorizontalAlignment(CENTER);
	}
	
	public ImageButton(ImageIcon image, int width, int height) {
		super();
		
		ImageIcon imageScaled = null;
		double ratio = ((double)height)/image.getIconHeight();
		
		if(image.getIconWidth()*ratio > width) {
			ratio = ((double)width)/image.getIconWidth();
		}
		
		imageScaled = new ImageIcon(image.getImage().getScaledInstance((int) (image.getIconWidth()*ratio), (int) (image.getIconHeight()*ratio), Image.SCALE_SMOOTH));
		
		this.setCursor(new Cursor(Cursor.HAND_CURSOR));
		this.setIcon(imageScaled);
		this.setVerticalAlignment(CENTER);
		this.setHorizontalAlignment(CENTER);
	}
	
}
