package es.library.databaseinspanish.ui.utils.components;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

import es.library.databaseinspanish.ui.utils.ProjectConstants;

public class NoContentLabel extends JLabel {

	public NoContentLabel(String text) {
		super(text);
		this.setFont(ProjectConstants.font12P.deriveFont(Font.BOLD,24f));
		this.setForeground(Color.GRAY);
	}
	
}
