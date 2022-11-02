package es.library.databaseinspanish.ui.utils;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;

import javax.swing.border.Border;

public class RoundedBorder implements Border {

	private int radius;
	private Color color;
	
	public RoundedBorder(int radius, Color color) {
		this.color = color;
		this.radius = radius;
	}
	
	@Override
	public Insets getBorderInsets(Component c) {
		return new Insets(2, 2, 2, 2);
	}

	@Override
	public boolean isBorderOpaque() {
		return true;
	}

	@Override
	public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
		g.setColor(color);
		g.drawRoundRect(x,y,width-1,height-1,radius,radius);
	}

}
