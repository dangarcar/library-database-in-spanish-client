package es.library.databaseinspanish.ui.pantallainicio;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import es.library.databaseinspanish.ui.SwingApp;
import es.library.databaseinspanish.ui.user.GuestButton;
import es.library.databaseinspanish.ui.utils.ImageLabel;
import es.library.databaseinspanish.ui.utils.ProjectConstants;
import net.miginfocom.swing.MigLayout;

public class PantallaInicioGuest extends PantallaInicio {
	
	private JTextField contenidoTextField;
	private JButton btnBuscarContenido;
	private ContenidosSlideShow slideShow;

	public PantallaInicioGuest(SwingApp parent) {
		super(parent);
		init();
	}

	private void init() {
		ImageIcon contenidosLogo = new ImageIcon(PantallaInicioUser.class.getResource("/es/library/databaseinspanish/ui/images/contenidos.png"));

		setLayout(new MigLayout("alignx center", "[25%][50%,grow][25%,grow]", "[][][][][][][][grow]"));

		ImageLabel logo = new ImageLabel(new ImageIcon(PantallaInicioUser.class.getResource("/es/library/databaseinspanish/ui/images/logo.png")), 300, 300);
		add(logo, "cell 1 0 1 6,alignx center");

		add(new ImageLabel(contenidosLogo,128,128),"cell 2 1,alignx center");

		JLabel contenidoLabel = new JLabel("Contenidos");
		contenidoLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
		add(contenidoLabel, "flowx,cell 2 2,alignx center");

		contenidoTextField = new JTextField();
		contenidoTextField.setPreferredSize(new Dimension(7, 25));
		contenidoTextField.setColumns(20);
		add(contenidoTextField, "cell 2 3,alignx center");

		btnBuscarContenido = new JButton("Buscar contenido");
		btnBuscarContenido.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnBuscarContenido.setForeground(Color.WHITE);
		btnBuscarContenido.setBorderPainted(false);
		btnBuscarContenido.setBackground(new Color(0, 128, 0));
		btnBuscarContenido.setFont(ProjectConstants.font12P);
		add(btnBuscarContenido, "cell 2 4,alignx center,aligny top");
		
		JLabel titulo = new JLabel("Contenidos más prestados de la biblioteca");
		titulo.setPreferredSize(new Dimension(0, 15));
		titulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
		add(titulo, "cell 0 6 3 1,alignx center,aligny bottom");

		slideShow = new ContenidosSlideShow(parent);
		add(slideShow, "cell 0 7 3 1,grow");
		
		if(parent.isGuest()) 
			add(new GuestButton(parent), "cell 0 1 1 4");
	}

}
