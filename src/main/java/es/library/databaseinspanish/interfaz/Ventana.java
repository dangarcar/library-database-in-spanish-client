package es.library.databaseinspanish.interfaz;

import es.library.databaseinspanish.contenido.Contenido;
import es.library.databaseinspanish.contenido.excepciones.ExcepcionContenido;
import es.library.databaseinspanish.database.DatabaseWritable;
import es.library.databaseinspanish.perfil.Perfil;
import es.library.databaseinspanish.perfil.excepciones.ExcepcionPerfil;

import static es.library.databaseinspanish.database.Buscador.*;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.awt.Container;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

/**
 * Frame principal del programa
 * @author Daniel Garc�a
 *
 */
public class Ventana extends JFrame{
	private static final long serialVersionUID = -3030099236595355215L;
	private CardLayout windowSwitcher = new CardLayout();
	private PantallaInicio lobby = new PantallaInicio(this);
	
	public Ventana() {
		setResizable(false);
		setFocusable(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBackground(SystemColor.controlDkShadow);
		setTitle("Library Database In Spanish");
		setIconImage(Toolkit.getDefaultToolkit().getImage("src/main/resources/files/images/icon.png"));
		setBounds(100,100,1000,700);
		getContentPane().setLayout(getWindowSwitcher());
		
		getContentPane().add(lobby);
		
		setVisible(true);
	}

	public CardLayout getWindowSwitcher() {return windowSwitcher;}
	
	public PantallaInicio getLobby() {return lobby;}
	
}

/**
 * Clase encargada de mostrar en pantalla el resultado de cierta busqueda
 * @author Daniel Garc�a
 *
 */
class ResultadoTXT extends JPanel {
	private static final long serialVersionUID = -810498298324250318L;
	private List<? extends DatabaseWritable> resultado = new ArrayList<DatabaseWritable>();
	private JLabel tituloResultadoBusqueda;
	private JSplitPane splitPane;
	private JPanel panelLateral;
	private JPanel panelBotones;
	private JScrollPane scrollPane;
	private JButton botonVolverAtras;
	private JScrollPane scrollPanelLateral;
	
	public ResultadoTXT(List<? extends DatabaseWritable> resultado,Ventana parent) {
		setBackground(Color.LIGHT_GRAY);
		setBounds(0, 0, 984, 661);
		setLayout(new BorderLayout(0, 0));
		
		this.resultado = resultado;
		
		tituloResultadoBusqueda = new JLabel("Resultado de busqueda");
		tituloResultadoBusqueda.setBackground(Color.WHITE);
		tituloResultadoBusqueda.setSize(new Dimension(0, 100));
		tituloResultadoBusqueda.setHorizontalAlignment(SwingConstants.CENTER);
		tituloResultadoBusqueda.setFont(new Font("Segoe UI", Font.BOLD, 24));
		add(tituloResultadoBusqueda, BorderLayout.NORTH);		

		splitPane = new JSplitPane();
		splitPane.setBackground(Color.BLACK);
		splitPane.setBorder(UIManager.getBorder("SplitPane.border"));
		add(splitPane, BorderLayout.CENTER);
		
		panelLateral = new JPanel();
		panelLateral.setBackground(Color.GRAY);
		panelLateral.setPreferredSize(new Dimension(450, 0));
		panelLateral.setLayout(new BorderLayout(0, 0));
		scrollPanelLateral = new JScrollPane();
		scrollPanelLateral.setViewportView(panelLateral);
		splitPane.setLeftComponent(scrollPanelLateral);
		
		panelBotones = new JPanel();
		panelBotones.setLayout(new GridLayout(0, 1, 10, 0));
		panelBotones.setBackground(Color.WHITE);
		
		scrollPane = new JScrollPane();
		scrollPane.setMinimumSize(new Dimension(200, 0));
		splitPane.setRightComponent(scrollPane);
		
		//Para cada objeto no repetido, creo un boton
		for(DatabaseWritable o:searchObjects()) {
			JButton boton = null;
			try {
				boton = o.getGUIRepresentation(resultado);
				boton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						splitPane.updateUI();
						panelLateral.removeAll();
						panelLateral.add(o.getExtendedGUIRepresentation(resultado));
					}
				});;
			} catch (ExcepcionContenido e1) {
				e1.printStackTrace();
			} catch (ExcepcionPerfil e1) {
				e1.printStackTrace();
			}
			panelBotones.add(boton,Component.CENTER_ALIGNMENT);
		}
		scrollPane.setViewportView(panelBotones);
		
		//El boton de volver a la pantalla principal
		botonVolverAtras = new JButton("Volver");
		botonVolverAtras.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				parent.getWindowSwitcher().first(((Container)e.getSource()).getParent().getParent());
				parent.getLobby().getTxtFieldContenidos().setText("");
				parent.getLobby().getTxtFieldPerfiles().setText("");
			}
		});
		add(botonVolverAtras, BorderLayout.SOUTH);
	}
	
	/**
	 * Devuelve los objetos de la lista delresultado de busqueda no repetidos
	 * @return ArrayList de DatabaseWritable
	 */
	public List<? extends DatabaseWritable> searchObjects(){
		List<DatabaseWritable> resultados = new ArrayList<DatabaseWritable>();
		
		if(this.resultado != null) {
			List<Long> listaIDS = new ArrayList<Long>();
			for (DatabaseWritable o:resultado){
				if(!(listaIDS.contains(o.getSpecificID()))) {
					if(o != null) {
						resultados.add(o);
						listaIDS.add(o.getSpecificID());
					}
				}
			}
			if (resultado.isEmpty()){
				new Thread(new Runnable() { @Override public void run() {JOptionPane.showMessageDialog(panelBotones,"No existe ningun objeto que coincida con la busqueda");} }).start();;
			}
		}
		return resultados;
	}
	
}

class BotonBuscarContenidos extends JButton implements ActionListener{
	private static final long serialVersionUID = -3472324132940058042L;
	private List<Contenido> contenidos;
	private Ventana parent;
	
	public BotonBuscarContenidos(Ventana parent) {
		super("Buscar Contenido");
		this.parent = parent;
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		setFont(new Font("Segoe UI", Font.BOLD, 16));
		setBorderPainted(false);
		setBackground(new Color(14, 209, 69));
		setBounds(750, 535, 200, 34);
		addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			contenidos = BuscarContenido((parent.getLobby().getTxtFieldContenidos().getText().equals("")? null:parent.getLobby().getTxtFieldContenidos().getText()));
			parent.getContentPane().add(new ResultadoTXT(contenidos,parent),"Resultado");
			parent.getWindowSwitcher().show(parent.getContentPane(), "Resultado");
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(this, e1.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE, new ImageIcon("src/main/resources/files/images/error.png"));
		}
	}
}

class BotonBuscarPerfiles extends JButton implements ActionListener{
	private static final long serialVersionUID = -3472324189940058042L;
	private List<Perfil> perfiles;
	private Ventana parent;
	
	public BotonBuscarPerfiles(Ventana parent) {
		super("Buscar usuario");
		this.parent = parent;
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		setBorderPainted(false);
		setBackground(new Color(14,209,69));
		setFont(new Font("Segoe UI", Font.BOLD, 16));
		setBounds(45, 535, 200, 34);
		addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		perfiles = BuscarPerfiles((parent.getLobby().getTxtFieldPerfiles().getText().equals("")? null:parent.getLobby().getTxtFieldPerfiles().getText()));
		parent.getContentPane().add(new ResultadoTXT(perfiles,parent),"Resultado");
		parent.getWindowSwitcher().show(parent.getContentPane(), "Resultado");
	}
	
}

/**
 * Clase encargada de la pantalla que aparece al abrir el programa
 * @author Daniel Garcia
 *
 */
class PantallaInicio extends JPanel implements ActionListener{
	private static final long serialVersionUID = 5806149385417939112L;
	private JTextField txtFieldContenidos;
	private JTextField txtFieldPerfiles;
	private JPopupMenu popupMenu;
	
	public PantallaInicio(Ventana parent) {
		setBackground(new Color(148,238,229));
		setLayout(null);
		
		ImageIcon perfilLogo = new ImageIcon("src/main/resources/files/images/perfil.png");
		Image perfilLogoEscalado = perfilLogo.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		ImageIcon contenidosLogo = new ImageIcon("src/main/resources/files/images/contenidos.png");
		Image contenidosLogoEscalado = contenidosLogo.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		
		JLabel perfilLogoLabel = new JLabel("Perfiles de usuario");
		perfilLogoLabel.setLocation(10, 352);
		perfilLogoLabel.setSize(269, 121);
		perfilLogoLabel.setVerticalTextPosition(SwingConstants.BOTTOM);
		perfilLogoLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		perfilLogoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		perfilLogoLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		perfilLogoLabel.setIcon(new ImageIcon(perfilLogoEscalado));
		add(perfilLogoLabel);
		
		
		txtFieldContenidos = new JTextField();
		txtFieldContenidos.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		txtFieldContenidos.setBounds(705, 484, 269, 40);
		txtFieldContenidos.setColumns(10);
		txtFieldContenidos.setPreferredSize(new Dimension(40, 200));
		txtFieldContenidos.setHorizontalAlignment(SwingConstants.CENTER);
		txtFieldContenidos.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {}

			@Override
			public void keyPressed(KeyEvent e) {
				int code = e.getKeyCode();
				
				switch(code) {
				case KeyEvent.VK_ENTER:
					try {
						List<Contenido> contenidos = BuscarContenido((txtFieldContenidos.getText().equals("")? null:txtFieldContenidos.getText()));
						parent.getContentPane().add(new ResultadoTXT(contenidos,parent),"Resultado");
						parent.getWindowSwitcher().show(parent.getContentPane(), "Resultado");
					} catch (ExcepcionContenido e1) {
						e1.printStackTrace();
					}
					break;
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {}
			
		});
		add(txtFieldContenidos);
		
		JLabel contenidosLogoLabel = new JLabel("Contenidos");
		contenidosLogoLabel.setLocation(705, 352);
		contenidosLogoLabel.setSize(269, 121);
		contenidosLogoLabel.setVerticalTextPosition(SwingConstants.BOTTOM);
		contenidosLogoLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		contenidosLogoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contenidosLogoLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		contenidosLogoLabel.setIcon(new ImageIcon(contenidosLogoEscalado));
		add(contenidosLogoLabel);
		
		
		txtFieldPerfiles = new JTextField();
		txtFieldPerfiles.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		txtFieldPerfiles.setBounds(10, 484, 269, 40);
		txtFieldPerfiles.setColumns(10);
		txtFieldPerfiles.setPreferredSize(new Dimension(40, 200));
		txtFieldPerfiles.setHorizontalAlignment(SwingConstants.CENTER);
		txtFieldPerfiles.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {}

			@Override
			public void keyPressed(KeyEvent e) {
				int code = e.getKeyCode();
				
				switch(code) {
				case KeyEvent.VK_ENTER:
					List<Perfil> perfiles = BuscarPerfiles((txtFieldPerfiles.getText().equals("")? null:txtFieldPerfiles.getText()));
					parent.getContentPane().add(new ResultadoTXT(perfiles,parent),"Resultado");
					parent.getWindowSwitcher().show(parent.getContentPane(), "Resultado");
					break;
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {}
			
		});
		add(txtFieldPerfiles);
		
		JLabel logo = new JLabel();
		logo.setHorizontalAlignment(SwingConstants.CENTER);
		logo.setSize(1000, 400);
		logo.setLocation(0, 0);
		logo.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		logo.setBackground(Color.RED);
		logo.setIcon(new ImageIcon("src/main/resources/files/images/logo.png"));
		add(logo);
		
		add(new BotonBuscarContenidos(parent));
		add(new BotonBuscarPerfiles(parent));
		
		
		popupMenu = new JPopupMenu();
		popupMenu.setFont(new Font("Segoe UI",16,Font.BOLD));
		popupMenu.setSize(200, 300);
		popupMenu.setBackground(new Color(240,240,240));
		
		JMenuItem perfilesMenu = new JMenuItem("A�adir perfil...");
		perfilesMenu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new AnadirPerfilGUI();
			}
		});
		popupMenu.add(perfilesMenu);
		
		JMenuItem contenidosMenu = new JMenuItem("A�adir es.library.databaseinspanish.contenido...");
		contenidosMenu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new AnadirContenidoGUI();
			}
		});
		popupMenu.add(contenidosMenu);
		
		JMenuItem informacionMenu = new JMenuItem("M�s informaci�n");
		informacionMenu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
				    try {
						Desktop.getDesktop().browse(new URI("https://github.com/dangarcar/library-es.library.databaseinspanish.database-in-spanish"));
					} catch (IOException | URISyntaxException e1) {
						JOptionPane.showMessageDialog(null, "No se ha podido acceder a la informaci�n extra", "Error", JOptionPane.ERROR_MESSAGE);
						e1.printStackTrace();
					}
				}
			}
		});
		popupMenu.add(informacionMenu);
		/*
		TODO Estaria bien hacer un menu de ayuda
		JMenuItem ayudaMenu = new JMenuItem("Ayuda");
		popupMenu.add(ayudaMenu);
		JMenuItem informacionMenu = new JMenuItem("Más información");
		popupMenu.add(informacionMenu);
		*/
		
		
		JButton botonMenu = new JButton();
		botonMenu.setBackground(Color.RED);
		botonMenu.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		botonMenu.setBorderPainted(false);
		botonMenu.setIcon(new ImageIcon((new ImageIcon("src/main/resources/files/images/menu.png")).getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH)));
		botonMenu.setToolTipText("Ver m�s opciones");
		botonMenu.setContentAreaFilled(false);
		botonMenu.setBounds(950, 0, 32, 32);
		botonMenu.addActionListener(this);
		add(botonMenu);
	}
	
	public JTextField getTxtFieldContenidos() {return txtFieldContenidos;}

	public JTextField getTxtFieldPerfiles() {return txtFieldPerfiles;}

	@Override
	public void actionPerformed(ActionEvent e) {
		popupMenu.show(this,this.getWidth()-popupMenu.getWidth(), 0);
	}
}
