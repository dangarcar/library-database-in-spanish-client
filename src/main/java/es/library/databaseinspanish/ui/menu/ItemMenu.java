package es.library.databaseinspanish.ui.menu;

import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

import es.library.databaseinspanish.ui.utils.ProjectConstants;

public class ItemMenu extends JMenuItem {

	public ItemMenu(String text, ActionListener action) {
		this.setText(text);
		this.setFont(ProjectConstants.font12P);
		this.addActionListener(action);
	}
	
}
