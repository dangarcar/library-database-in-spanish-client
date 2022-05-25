package interfaz;

import javax.swing.*;

import SQL.*;
import contenido.*;
import contenido.excepciones.ExcepcionContenido;
import database.DatabaseWritable;
import perfiles.*;
import perfiles.excepciones.ExcepcionPerfil;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;


public class Ventana extends JFrame{
	private static final long serialVersionUID = -3030099236595355215L;
	private CardLayout windowSwitcher = new CardLayout();
	private TxtFieldContenidos txtFieldContenidos = new TxtFieldContenidos();
	private TxtFieldPerfiles txtFieldPerfiles = new TxtFieldPerfiles();
	
	public Ventana() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBackground(SystemColor.controlDkShadow);
		setTitle("App Biblioteca");
		setIconImage(Toolkit.getDefaultToolkit().getImage("files\\images\\icon.png"));
		setBounds(100,100,1000,700);
		getContentPane().setLayout(getWindowSwitcher());
		
		getContentPane().add(pantallaInicio());

		setVisible(true);
	}
	
	private JPanel pantallaInicio() {
		JPanel pantallaInicio = new JPanel();
		pantallaInicio.setBackground(new Color(148,238,229));
		pantallaInicio.setLayout(null);
		
		ImageIcon perfilLogo = new ImageIcon("D:\\Programaci\u00F3n\\Java\\library-database\\files\\images\\perfil.png");
		Image perfilLogoEscalado = perfilLogo.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		ImageIcon contenidosLogo = new ImageIcon("D:\\Programaci\u00F3n\\Java\\library-database\\files\\images\\contenidos.png");
		Image contenidosLogoEscalado = contenidosLogo.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		
		JLabel perfilLogoLabel = new JLabel("Perfiles de usuario");
		perfilLogoLabel.setLocation(10, 352);
		perfilLogoLabel.setSize(269, 121);
		perfilLogoLabel.setVerticalTextPosition(SwingConstants.BOTTOM);
		perfilLogoLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		perfilLogoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		perfilLogoLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		perfilLogoLabel.setIcon(new ImageIcon(perfilLogoEscalado));
		pantallaInicio.add(perfilLogoLabel);
	
		pantallaInicio.add(getTxtFieldPerfiles());
		
		JLabel contenidosLogoLabel = new JLabel("Contenidos");
		contenidosLogoLabel.setLocation(705, 352);
		contenidosLogoLabel.setSize(269, 121);
		contenidosLogoLabel.setVerticalTextPosition(SwingConstants.BOTTOM);
		contenidosLogoLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		contenidosLogoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contenidosLogoLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		contenidosLogoLabel.setIcon(new ImageIcon(contenidosLogoEscalado));
		pantallaInicio.add(contenidosLogoLabel);
		
		
		pantallaInicio.add(getTxtFieldContenidos());
		
		JLabel logo = new JLabel();
		logo.setHorizontalAlignment(SwingConstants.CENTER);
		logo.setSize(1000, 400);
		logo.setLocation(0, 0);
		logo.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		logo.setBackground(Color.RED);
		logo.setIcon(new ImageIcon("D:\\Programaci\u00F3n\\Java\\library-database\\files\\images\\logo.png"));
		pantallaInicio.add(logo);
		
		pantallaInicio.add(new BotonBuscarContenidos(this));
		pantallaInicio.add(new BotonBuscarPerfiles(this));
		
		JPopupMenu popupMenu = new JPopupMenu();
		popupMenu.setFont(new Font("Segoe UI",16,Font.BOLD));
		popupMenu.setSize(200, 300);
		popupMenu.setBackground(new Color(240,240,240));
		JMenuItem perfilesMenu = new JMenuItem("Añadir perfil...");
		perfilesMenu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new AnadirPerfilGUI();
			}
			
		});
		popupMenu.add(perfilesMenu);
		JMenuItem contenidosMenu = new JMenuItem("Añadir contenido...");
		contenidosMenu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new AnadirContenidoGUI();
			}
			
		});
		popupMenu.add(contenidosMenu);
		JMenuItem ayudaMenu = new JMenuItem("Ayuda");
		popupMenu.add(ayudaMenu);
		JMenuItem informacionMenu = new JMenuItem("M\u00E1s informaci\u00F3n");
		popupMenu.add(informacionMenu);
		
		JButton botonMenu = new JButton();
		botonMenu.setBackground(Color.RED);
		botonMenu.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		botonMenu.setBorderPainted(false);
		botonMenu.setIcon(new ImageIcon((new ImageIcon("D:\\Programaci\u00F3n\\Java\\library-database\\files\\images\\menu.png")).getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH)));
		botonMenu.setToolTipText("Ver m\u00E1s opciones");
		botonMenu.setContentAreaFilled(false);
		botonMenu.setBounds(950, 0, 32, 32);
		botonMenu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				popupMenu.show(pantallaInicio, pantallaInicio.getWidth()-popupMenu.getWidth(), 0);
			}	
		});
		pantallaInicio.add(botonMenu);
		
		return pantallaInicio;
	}

	public CardLayout getWindowSwitcher() {return windowSwitcher;}

	public void setWindowSwitcher(CardLayout windowSwitcher) {this.windowSwitcher = windowSwitcher;}

	public TxtFieldContenidos getTxtFieldContenidos() {return txtFieldContenidos;}

	public void setTxtFieldContenidos(TxtFieldContenidos txtFieldContenidos) {this.txtFieldContenidos = txtFieldContenidos;}

	public TxtFieldPerfiles getTxtFieldPerfiles() {return txtFieldPerfiles;}

	public void setTxtFieldPerfiles(TxtFieldPerfiles txtFieldPerfiles) {this.txtFieldPerfiles = txtFieldPerfiles;}
	
	
}

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
		
		tituloResultadoBusqueda = new JLabel("Resultado de b\u00FAsqueda");
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
		
		//Para cada objeto no repetido, creo un botón
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
		
		//El botón de volver ala pantalla principal
		botonVolverAtras = new JButton("Volver");
		botonVolverAtras.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				parent.getWindowSwitcher().first(((Container)e.getSource()).getParent().getParent());
				parent.getTxtFieldContenidos().setText("");
				parent.getTxtFieldPerfiles().setText("");
			}
		});
		add(botonVolverAtras, BorderLayout.SOUTH);
	}
	
	/**
	 * Devuelve los objetos de la lista delresultado de búsqueda no repetidos
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
				new Thread(new Runnable() { @Override public void run() {JOptionPane.showMessageDialog(panelBotones,"No existe ningún objeto que coincida con la búsqueda");} }).start();;
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
		super("Buscar contenido");
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
		//System.out.println(parent.getTxtFieldContenidos().getText()+"\n");
		
		try {
			contenidos = Buscador.buscarContenido((parent.getTxtFieldContenidos().getText().equals("")? null:parent.getTxtFieldContenidos().getText()));
			parent.getContentPane().add(new ResultadoTXT(contenidos,parent),"Resultado");
			parent.getWindowSwitcher().show(parent.getContentPane(), "Resultado");
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(this, e1.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE, new ImageIcon("files\\images\\error.png"));
		}
	}
	
	public List<Contenido> getContenido(){
		return contenidos;
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
		//System.out.println(parent.getTxtFieldPerfiles().getText());
		
		perfiles = Buscador.buscarPerfiles((parent.getTxtFieldPerfiles().getText().equals("")? null:parent.getTxtFieldPerfiles().getText()));
		parent.getContentPane().add(new ResultadoTXT(perfiles,parent),"Resultado");
		parent.getWindowSwitcher().show(parent.getContentPane(), "Resultado");
	}
	
	public List<Perfil> getPerfiles(){
		return perfiles;
	}
}

class TxtFieldPerfiles extends JTextField {
	private static final long serialVersionUID = 4302638240358094946L;
	
	public TxtFieldPerfiles() {
		setHorizontalAlignment(SwingConstants.CENTER);
		setPreferredSize(new Dimension(40, 200));
		setDisabledTextColor(Color.BLACK);
		setFont(new Font("Segoe UI", Font.PLAIN, 20));
		setBounds(10, 484, 269, 40);
		setColumns(10);
	}
}

class TxtFieldContenidos extends JTextField {
	private static final long serialVersionUID = 4302638240358094946L;
	
	public TxtFieldContenidos() {
		setDisabledTextColor(Color.BLACK);
		setFont(new Font("Segoe UI", Font.PLAIN, 20));
		setHorizontalAlignment(SwingConstants.CENTER);
		setBounds(705, 484, 269, 40);
		setColumns(10);
	}
}