package es.library.databaseinspanish.ui.user;

import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import es.library.databaseinspanish.ui.SwingApp;
import es.library.databaseinspanish.ui.utils.BotonRetroceso;
import es.library.databaseinspanish.ui.utils.ImageButton;
import es.library.databaseinspanish.ui.utils.ProjectConstants;
import net.miginfocom.swing.MigLayout;
import javax.swing.JSeparator;
import java.awt.Color;

public class UserPanel extends JPanel {

	private SwingApp parent;
	
	public UserPanel(SwingApp parent) {
		this.parent = parent;
		
		this.setBackground(ProjectConstants.BACKGROUND_COLOR);
		setLayout(new MigLayout("", "[][grow][][]", "[][][][][grow]"));
		
		BotonRetroceso botonRetroceso = new BotonRetroceso(parent);
		add(botonRetroceso, "cell 0 0,aligny top");
		
		JSeparator separatorV1 = new JSeparator();
		separatorV1.setForeground(Color.WHITE);
		separatorV1.setOrientation(SwingConstants.VERTICAL);
		add(separatorV1, "cell 2 0 1 5,grow");
		
		JButton cerrarSesionButton = new ImageButton(new ImageIcon(UserPanel.class.getResource("/es/library/databaseinspanish/ui/images/cerrarSesion.png")),64,64);
		cerrarSesionButton.setFont(ProjectConstants.font12P.deriveFont(Font.BOLD, 14f));
		cerrarSesionButton.setHorizontalTextPosition(SwingConstants.CENTER);
		cerrarSesionButton.setVerticalTextPosition(SwingConstants.BOTTOM);
		cerrarSesionButton.setText("Cerrar sesión");
		cerrarSesionButton.setBorder(null);
		cerrarSesionButton.setBackground(ProjectConstants.BACKGROUND_COLOR);
		add(cerrarSesionButton, "cell 3 0 1 2");
		
		JSeparator separatorH1 = new JSeparator();
		separatorH1.setForeground(Color.WHITE);
		separatorH1.setBorder(null);
		add(separatorH1, "cell 3 2,growx");
		
		JButton deleteCuentaButton = new ImageButton(new ImageIcon(UserPanel.class.getResource("/es/library/databaseinspanish/ui/images/papelera.png")), 64, 64);
		deleteCuentaButton.setFont(ProjectConstants.font12P.deriveFont(Font.BOLD, 14f));
		deleteCuentaButton.setHorizontalTextPosition(SwingConstants.CENTER);
		deleteCuentaButton.setVerticalTextPosition(SwingConstants.BOTTOM);
		deleteCuentaButton.setText("Borrar cuenta");
		deleteCuentaButton.setBorder(null);
		deleteCuentaButton.setBackground(ProjectConstants.BACKGROUND_COLOR);
		add(deleteCuentaButton, "cell 3 3");
		
		JPanel perfilDescripPanel = new JPanel();
		add(perfilDescripPanel, "cell 1 4,grow");
	}
	
}
