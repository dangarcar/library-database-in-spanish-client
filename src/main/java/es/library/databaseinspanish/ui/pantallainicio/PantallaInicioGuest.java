package es.library.databaseinspanish.ui.pantallainicio;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import es.library.databaseinspanish.ui.SwingApp;
import es.library.databaseinspanish.ui.contenido.buscar.BuscarContenidoPanel;
import es.library.databaseinspanish.ui.menu.BotonMenu;
import es.library.databaseinspanish.ui.user.GuestButton;
import es.library.databaseinspanish.ui.utils.ImageLabel;
import net.miginfocom.swing.MigLayout;

public class PantallaInicioGuest extends PantallaInicio {
	
	private ContenidosSlideShow slideShow;
	private BuscarContenidoPanel contenidoPanel;
	protected BotonMenu botonMenu;

	public PantallaInicioGuest(SwingApp parent) {
		super(parent);
		init();
	}

	private void init() {
		setLayout(new MigLayout("alignx center", "[25%,grow][50%,grow][25%,grow][grow]", "[][][][grow]"));

		ImageLabel logo = new ImageLabel(new ImageIcon(PantallaInicioUser.class.getResource("/es/library/databaseinspanish/ui/images/logo.png")), 300, 300);
		add(logo, "cell 1 0 1 2,alignx center");

		botonMenu = new BotonMenu(parent);
		add(botonMenu, "cell 3 0, aligny top, alignx right");
		
		contenidoPanel = new BuscarContenidoPanel(parent);
		add(contenidoPanel, "cell 2 0 2 2,alignx center,growy");

		JLabel titulo = new JLabel("Contenidos más prestados de la biblioteca");
		titulo.setPreferredSize(new Dimension(0, 15));
		titulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
		add(titulo, "cell 0 2 3 1,alignx center,aligny bottom");

		slideShow = new ContenidosSlideShow(parent);
		add(slideShow, "cell 0 3 4 1,grow");
		
		if(parent.isGuest()) 
			add(new GuestButton(parent), "cell 0 0");
	}

}
