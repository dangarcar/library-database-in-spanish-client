package es.library.databaseinspanish.ui.pantallainicio;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import es.library.databaseinspanish.ui.SwingApp;
import es.library.databaseinspanish.ui.utils.ImageLabel;
import net.miginfocom.swing.MigLayout;

public class PantallaInicioUser extends PantallaInicio {

	private JTextField perfilTextField;
	private JButton botonPerfil;
	private JTextField contenidoTextField;
	private JButton btnBuscarContenido;

	public PantallaInicioUser(SwingApp parent) {
		super(parent);
		init();
	}

	private void init() {
		ImageIcon perfilLogo = new ImageIcon(PantallaInicioStaff.class.getResource("/es/library/databaseinspanish/ui/images/perfilBusqueda.png"));
		ImageIcon contenidosLogo = new ImageIcon(PantallaInicioStaff.class.getResource("/es/library/databaseinspanish/ui/images/contenidos.png"));
		
		setLayout(new MigLayout("alignx center", "[25%][50%][25%,grow]", "[44.98%][][][][][grow]"));
		
		add(new ImageLabel(new ImageIcon(PantallaInicioStaff.class.getResource("/es/library/databaseinspanish/ui/images/logo.png")), 300, 300), "cell 1 0,alignx center");
		
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
