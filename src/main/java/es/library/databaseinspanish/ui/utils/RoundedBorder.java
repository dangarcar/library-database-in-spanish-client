package es.library.databaseinspanish.ui.utils;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;

import javax.swing.border.Border;

public class RoundedBorder implements Border {
    
    private int radius;
    private Color bg;
    private Color fg = Color.black;
    
    public RoundedBorder(int radius, Color bg) {
        this.radius = radius;
        this.bg = bg;
    }
    
    public RoundedBorder(int radius, Color bg, Color fg) {
        this.radius = radius;
        this.bg = bg;
        this.fg = fg;
    }
    
    @Override
    public Insets getBorderInsets(Component c) {
        return new Insets(this.radius+1, this.radius+1, this.radius+2, this.radius);
    }

    @Override
    public boolean isBorderOpaque() {
        return true;
    }

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
    	g.setColor(bg);
        g.fillRoundRect(x,y,width-1,height-1,radius,radius);
        g.setColor(fg);
        g.drawRoundRect(x,y,width-1,height-1,radius,radius);
    }
}
