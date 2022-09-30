package es.library.databaseinspanish.ui.pantallainicio;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import es.library.databaseinspanish.ui.SwingApp;
import es.library.databaseinspanish.ui.utils.ImageLabel;
import net.miginfocom.swing.MigLayout;

/**
 * Clase encargada de la pantalla que aparece al abrir el programa
 * @author Daniel Garcia
 *
 */
public class PantallaInicioStaff extends PantallaInicio {
	
	private JTextField perfilTextField;
	private JTextField contenidoTextField;
	private JButton botonPerfil;
	private JButton btnBuscarContenido;
	
	public PantallaInicioStaff(SwingApp parent) {
		super(parent);
		init();
	}
	
	private void init() {
		ImageIcon perfilLogo = new ImageIcon(PantallaInicioStaff.class.getResource("/es/library/databaseinspanish/ui/images/perfilBusqueda.png"));
		ImageIcon contenidosLogo = new ImageIcon(PantallaInicioStaff.class.getResource("/es/library/databaseinspanish/ui/images/contenidos.png"));
		
		setLayout(new MigLayout("alignx center", "[25%][50%][25%,grow]", "[44.98%][][][][][grow]"));
		
		add(new ImageLabel(new ImageIcon(PantallaInicioStaff.class.getResource("/es/library/databaseinspanish/ui/images/logo.png")), 300, 300), "cell 1 0,alignx center");
		
		JLabel perfilLabel = new JLabel("Perfiles");
		perfilLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		perfilLabel.setVerticalAlignment(SwingConstants.TOP);
		perfilLabel.setVerticalTextPosition(SwingConstants.BOTTOM);
		perfilLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		perfilLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
		add(perfilLabel, "cell 0 2,alignx center,aligny center");
		
		perfilTextField = new JTextField();
		perfilTextField.setPreferredSize(new Dimension(7, 25));
		add(perfilTextField, "cell 0 3,alignx center");
		perfilTextField.setColumns(20);
		
		botonPerfil = new JButton("Buscar perfil");
		botonPerfil.setForeground(Color.WHITE);
		botonPerfil.setBackground(new Color(0, 128, 0));
		botonPerfil.setBorderPainted(false);
		botonPerfil.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		add(botonPerfil, "cell 0 4,alignx center");
		
		add(new ImageLabel(perfilLogo,128,128),"cell 0 1,alignx center");
		
		JLabel contenidoLabel = new JLabel("Contenidos");
		contenidoLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
		add(contenidoLabel, "cell 2 2,alignx center");
		
		contenidoTextField = new JTextField();
		contenidoTextField.setPreferredSize(new Dimension(7, 25));
		contenidoTextField.setColumns(20);
		add(contenidoTextField, "cell 2 3,alignx center");
		
		btnBuscarContenido = new JButton("Buscar contenido");
		btnBuscarContenido.setForeground(Color.WHITE);
		btnBuscarContenido.setBorderPainted(false);
		btnBuscarContenido.setBackground(new Color(0, 128, 0));
		btnBuscarContenido.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		add(btnBuscarContenido, "cell 2 4,alignx center");
		
		add(new ImageLabel(contenidosLogo,128,128),"cell 2 1,alignx center");
	}
	
}