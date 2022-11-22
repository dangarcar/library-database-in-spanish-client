package es.library.databaseinspanish.ui.user;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import es.library.databaseinspanish.ui.SwingApp;
import es.library.databaseinspanish.ui.perfil.PerfilDescr;
import es.library.databaseinspanish.ui.utils.ProjectConstants;
import es.library.databaseinspanish.ui.utils.components.BotonRetroceso;
import es.library.databaseinspanish.ui.utils.components.ImageButton;
import net.miginfocom.swing.MigLayout;

public class UserPanel extends JPanel {
	
	private JButton deleteCuentaButton;
	private JButton cerrarSesionButton;
	
	public UserPanel(SwingApp parent) {		
		this.setBackground(ProjectConstants.BACKGROUND_COLOR);
		setLayout(new MigLayout("", "[][grow][][]", "[][][][][][][grow]"));
		
		BotonRetroceso botonRetroceso = new BotonRetroceso(parent);
		add(botonRetroceso, "cell 0 0,aligny top");
		
		JSeparator separatorV1 = new JSeparator();
		separatorV1.setForeground(Color.WHITE);
		separatorV1.setOrientation(SwingConstants.VERTICAL);
		add(separatorV1, "cell 2 0 1 7,alignx right,growy");
		
		cerrarSesionButton = new ImageButton(new ImageIcon(UserPanel.class.getResource("/es/library/databaseinspanish/ui/images/cerrarSesion.png")),64,64);
		cerrarSesionButton.setFont(ProjectConstants.font12P.deriveFont(Font.BOLD, 14f));
		cerrarSesionButton.setHorizontalTextPosition(SwingConstants.CENTER);
		cerrarSesionButton.setVerticalTextPosition(SwingConstants.BOTTOM);
		cerrarSesionButton.setText("Cerrar sesión");
		cerrarSesionButton.setBorder(null);
		cerrarSesionButton.setBackground(ProjectConstants.BACKGROUND_COLOR);
		add(cerrarSesionButton, "cell 3 0 1 2");		
		
		JPanel perfilDescr = new PerfilDescr(parent.getUserLoggenIn());
		perfilDescr.setBackground(ProjectConstants.BACKGROUND_COLOR);
		add(perfilDescr, "cell 1 1 1 3");
		
		JSeparator separatorH1 = new JSeparator();
		separatorH1.setForeground(Color.WHITE);
		separatorH1.setBorder(null);
		add(separatorH1, "cell 3 2,growx");
		
		deleteCuentaButton = new ImageButton(new ImageIcon(UserPanel.class.getResource("/es/library/databaseinspanish/ui/images/papelera.png")), 64, 64);
		deleteCuentaButton.setFont(ProjectConstants.font12P.deriveFont(Font.BOLD, 14f));
		deleteCuentaButton.setHorizontalTextPosition(SwingConstants.CENTER);
		deleteCuentaButton.setVerticalTextPosition(SwingConstants.BOTTOM);
		deleteCuentaButton.setText("Borrar cuenta");
		deleteCuentaButton.setBorder(null);
		deleteCuentaButton.setBackground(ProjectConstants.BACKGROUND_COLOR);
		add(deleteCuentaButton, "cell 3 3,aligny top");
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.WHITE);
		add(separator, "cell 1 4,growx,gapy 15 15");
		
		JLabel contenidosPrestamoLabel = new JLabel("Contenidos actualmente en préstamo");
		contenidosPrestamoLabel.setFont(ProjectConstants.font12P.deriveFont(Font.BOLD, 18f));
		add(contenidosPrestamoLabel, "cell 1 5");
		
		PrestamoScroll scrollPane = new PrestamoScroll(parent);
		add(scrollPane, "cell 1 6,grow");
	}

	public JButton getDeleteCuentaButton() {
		return deleteCuentaButton;
	}

	public JButton getCerrarSesionButton() {
		return cerrarSesionButton;
	}
}
