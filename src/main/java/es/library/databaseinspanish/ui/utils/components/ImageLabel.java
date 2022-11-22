package es.library.databaseinspanish.ui.utils.components;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class ImageLabel extends JLabel{
	
	public ImageLabel(ImageIcon image) {
		super();
		this.setIcon(image);
		this.setVerticalAlignment(CENTER);
		this.setHorizontalAlignment(CENTER);
	}
	
	public ImageLabel(ImageIcon image, int width, int height) {
		super();
		
		ImageIcon imageScaled = null;
		double ratio = ((double)height)/image.getIconHeight();
		
		if(image.getIconWidth()*ratio > width) {
			ratio = ((double)width)/image.getIconWidth();
		}
		
		imageScaled = new ImageIcon(image.getImage().getScaledInstance((int) (image.getIconWidth()*ratio), (int) (image.getIconHeight()*ratio), Image.SCALE_SMOOTH));
		
		this.setIcon(imageScaled);
		this.setVerticalAlignment(CENTER);
		this.setHorizontalAlignment(CENTER);
	}
	
}
