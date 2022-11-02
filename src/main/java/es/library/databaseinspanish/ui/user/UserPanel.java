package es.library.databaseinspanish.ui.user;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import es.library.databaseinspanish.model.perfil.Roles;
import es.library.databaseinspanish.ui.SwingApp;
import es.library.databaseinspanish.ui.utils.BotonRetroceso;
import es.library.databaseinspanish.ui.utils.ImageButton;
import es.library.databaseinspanish.ui.utils.ImageLabel;
import es.library.databaseinspanish.ui.utils.ProjectConstants;
import es.library.databaseinspanish.utils.ImageUtils;
import es.library.databaseinspanish.utils.Utils;
import net.miginfocom.swing.MigLayout;

public class UserPanel extends JPanel {

	private SwingApp parent;
	
	private JButton deleteCuentaButton;
	private JButton cerrarSesionButton;
	
	public UserPanel(SwingApp parent) {
		this.parent = parent;
		
		this.setBackground(ProjectConstants.BACKGROUND_COLOR);
		setLayout(new MigLayout("", "[][][5%][grow][][]", "[][][][][][][][][grow]"));
		
		BotonRetroceso botonRetroceso = new BotonRetroceso(parent);
		add(botonRetroceso, "cell 0 0,aligny top");
		
		JSeparator separatorV1 = new JSeparator();
		separatorV1.setForeground(Color.WHITE);
		separatorV1.setOrientation(SwingConstants.VERTICAL);
		add(separatorV1, "cell 4 0 1 9,alignx right,growy");
		
		cerrarSesionButton = new ImageButton(new ImageIcon(UserPanel.class.getResource("/es/library/databaseinspanish/ui/images/cerrarSesion.png")),64,64);
		cerrarSesionButton.setFont(ProjectConstants.font12P.deriveFont(Font.BOLD, 14f));
		cerrarSesionButton.setHorizontalTextPosition(SwingConstants.CENTER);
		cerrarSesionButton.setVerticalTextPosition(SwingConstants.BOTTOM);
		cerrarSesionButton.setText("Cerrar sesión");
		cerrarSesionButton.setBorder(null);
		cerrarSesionButton.setBackground(ProjectConstants.BACKGROUND_COLOR);
		add(cerrarSesionButton, "cell 5 0 1 2");
		
		JLabel roleImagenLabel = new ImageLabel(ImageUtils.getImagenFromRole(parent.getUserLoggenIn().getRole()),64,64);
		add(roleImagenLabel, "cell 1 5,alignx right,aligny bottom");
		
		JLabel imagenPerfil = new ImageLabel(ImageUtils.getImagenFromPerfil(parent.getUserLoggenIn().getNombre()),162,162);
		add(imagenPerfil, "cell 1 1 1 5,gapx 10 10,aligny top");
		
		JLabel nombreLabel = new JLabel();
		nombreLabel.setText(parent.getUserLoggenIn().getNombre());
		nombreLabel.setFont(ProjectConstants.font12P.deriveFont(Font.BOLD,24f));
		add(nombreLabel, "cell 3 1");
		
		JLabel emailLabel = new JLabel();
		emailLabel.setText(Utils.getHtmlText("Email", parent.getUserLoggenIn().getCorreoElectronico()));
		emailLabel.setFont(ProjectConstants.font12P.deriveFont(Font.PLAIN,16f));
		add(emailLabel, "cell 3 3");
		
		JLabel fechaNacimientoLabel = new JLabel();
		fechaNacimientoLabel.setFont(ProjectConstants.font12P.deriveFont(Font.PLAIN,16f));
		fechaNacimientoLabel.setText(Utils.getHtmlText("Fecha de nacimiento",ProjectConstants.LOCAL_DATE_FORMATTER.format(parent.getUserLoggenIn().getFechaNacimiento())));
		add(fechaNacimientoLabel, "cell 3 4");
		
		JLabel roleLabel = new JLabel();
		roleLabel.setFont(ProjectConstants.font12P.deriveFont(Font.PLAIN,16f));
		roleLabel.setText(Utils.getHtmlText("Rol",Utils.getFormattedStringFromRole(parent.getUserLoggenIn().getRole())));
		if(parent.getUserLoggenIn().getRole() != Roles.ROLE_USER) add(roleLabel, "cell 3 5, aligny top");
		
		JSeparator separatorH1 = new JSeparator();
		separatorH1.setForeground(Color.WHITE);
		separatorH1.setBorder(null);
		add(separatorH1, "cell 5 2,growx");
		
		deleteCuentaButton = new ImageButton(new ImageIcon(UserPanel.class.getResource("/es/library/databaseinspanish/ui/images/papelera.png")), 64, 64);
		deleteCuentaButton.setFont(ProjectConstants.font12P.deriveFont(Font.BOLD, 14f));
		deleteCuentaButton.setHorizontalTextPosition(SwingConstants.CENTER);
		deleteCuentaButton.setVerticalTextPosition(SwingConstants.BOTTOM);
		deleteCuentaButton.setText("Borrar cuenta");
		deleteCuentaButton.setBorder(null);
		deleteCuentaButton.setBackground(ProjectConstants.BACKGROUND_COLOR);
		add(deleteCuentaButton, "cell 5 3 1 3,aligny top");
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.WHITE);
		add(separator, "cell 1 6 3 1,growx,gapy 15 15");
		
		JLabel contenidosPrestamoLabel = new JLabel("Contenidos actualmente en préstamo");
		contenidosPrestamoLabel.setFont(ProjectConstants.font12P.deriveFont(Font.BOLD, 18f));
		add(contenidosPrestamoLabel, "cell 1 7 3 1");
		
		PrestamoScroll scrollPane = new PrestamoScroll(parent);
		add(scrollPane, "cell 1 8 3 1,grow");
	}

	public JButton getDeleteCuentaButton() {
		return deleteCuentaButton;
	}

	public JButton getCerrarSesionButton() {
		return cerrarSesionButton;
	}
}
