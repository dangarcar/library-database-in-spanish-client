package es.library.databaseinspanish.ui.utils;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class ImageLabel extends JLabel{
	
	public ImageLabel(ImageIcon image) {
		super();
		this.setIcon(image);
	}
	
	public ImageLabel(ImageIcon image, int width, int height) {
		super();
		ImageIcon imageScaled = new ImageIcon(image.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));
		this.setIcon(imageScaled);
	}
	
}
