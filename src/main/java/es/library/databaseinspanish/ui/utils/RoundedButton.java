package es.library.databaseinspanish.ui.utils;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class RoundedButton extends JPanel {

	private JButton btnAnadir;
	
	public RoundedButton(String text) {
		btnAnadir = new JButton(text);
		btnAnadir.setForeground(Color.WHITE);
		btnAnadir.setBorder(null);
		btnAnadir.setFont(ProjectConstants.font12P.deriveFont(Font.BOLD,14f));
		
		super.setBackground(ProjectConstants.BACKGROUND_COLOR);
		this.setLayout(new BorderLayout(0, 0));
		this.add(btnAnadir);
	}
	
	public void setBackgroundBorder(Color bg) {
		btnAnadir.setBackground(bg);
		setBorder(new RoundedFilledBorder(10,bg));
	}
	
	public void addActionListener(ActionListener l) {
		btnAnadir.addActionListener(l);
	}
	
	public JButton getBtnAnadir() {
		return btnAnadir;
	}
}
