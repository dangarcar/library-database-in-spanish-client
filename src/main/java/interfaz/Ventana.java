package interfaz;

import javax.swing.*;

import SQL.*;
import contenido.*;
import database.DatabaseWritable;
import perfiles.*;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.border.LineBorder;


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
		getContentPane().setLayout(windowSwitcher);
		
		getContentPane().add(pantallaInicio());
		//getContentPane().add(resultadoTXT(new ArrayList<Contenido>()),"Resultado");
		
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
	
		pantallaInicio.add(txtFieldPerfiles);
		
		JLabel contenidosLogoLabel = new JLabel("Contenidos");
		contenidosLogoLabel.setLocation(705, 352);
		contenidosLogoLabel.setSize(269, 121);
		contenidosLogoLabel.setVerticalTextPosition(SwingConstants.BOTTOM);
		contenidosLogoLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		contenidosLogoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contenidosLogoLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		contenidosLogoLabel.setIcon(new ImageIcon(contenidosLogoEscalado));
		pantallaInicio.add(contenidosLogoLabel);
		
		
		pantallaInicio.add(txtFieldContenidos);
		
		JLabel logo = new JLabel();
		logo.setHorizontalAlignment(SwingConstants.CENTER);
		logo.setSize(1000, 400);
		logo.setLocation(0, 0);
		logo.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		logo.setBackground(Color.RED);
		logo.setIcon(new ImageIcon("D:\\Programaci\u00F3n\\Java\\library-database\\files\\images\\logo.png"));
		pantallaInicio.add(logo);
		
		pantallaInicio.add(new BotonBuscarContenidos());
		pantallaInicio.add(new BotonBuscarPerfiles());
		
		return pantallaInicio;
	}
	
	private JPanel resultadoTXT(List<? extends DatabaseWritable> resultado) {		
		JPanel panelResultado = new JPanel();
		panelResultado.setBackground(Color.LIGHT_GRAY);
		panelResultado.setBounds(0, 0, 984, 661);
		panelResultado.setLayout(new BorderLayout(0, 0));
		
		JLabel tituloResultadoBusqueda = new JLabel("Resultado de b\u00FAsqueda");
		tituloResultadoBusqueda.setBackground(Color.WHITE);
		tituloResultadoBusqueda.setSize(new Dimension(0, 100));
		tituloResultadoBusqueda.setHorizontalAlignment(SwingConstants.CENTER);
		tituloResultadoBusqueda.setFont(new Font("Segoe UI", Font.BOLD, 24));
		panelResultado.add(tituloResultadoBusqueda, BorderLayout.NORTH);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(new LineBorder(new Color(130, 135, 144), 8));
		panelResultado.add(scrollPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(0, 1, 10, 0));
		panel.setBackground(Color.WHITE);
		if(resultado != null) {
			List<Long> listaIDS = new ArrayList<Long>();
			for (DatabaseWritable o:resultado){
				if(!(listaIDS.contains(o.getSpecificID()))) {
					if(o != null) {
						panel.add(o.getGUIRepresentation(),Component.CENTER_ALIGNMENT);
						listaIDS.add(o.getSpecificID());
					}
				}
			}
			if (resultado.isEmpty()){
				new Thread(new Runnable() { @Override public void run() {JOptionPane.showMessageDialog(panel,"No existe ningún objeto que coincida con la búsqueda");} }).start();;
			}
		}
		scrollPane.setViewportView(panel);
		
		JButton botonVolverAtras = new JButton("Volver");
		panelResultado.add(botonVolverAtras, BorderLayout.SOUTH);
		botonVolverAtras.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				windowSwitcher.first(((Container)e.getSource()).getParent().getParent());
				txtFieldContenidos.setText("");
				txtFieldPerfiles.setText("");
			}
			
		});
		
		return panelResultado;
	}
	
	private class BotonBuscarContenidos extends JButton implements ActionListener{
		private static final long serialVersionUID = -3472324132940058042L;
		private List<Contenido> contenidos;
		
		public BotonBuscarContenidos() {
			super("Buscar contenido");
			setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			setFont(new Font("Segoe UI", Font.BOLD, 16));
			setBorderPainted(false);
			setBackground(new Color(14, 209, 69));
			setBounds(750, 535, 200, 34);
			addActionListener(this);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println(txtFieldContenidos.getText()+"\n");
			
			try {
				contenidos = Buscador.buscarContenido((txtFieldContenidos.getText().equals("")? null:txtFieldContenidos.getText()));
				getContentPane().add(resultadoTXT(contenidos),"Resultado");
				windowSwitcher.show(getContentPane(), "Resultado");
				List<Long> listaID = new ArrayList<Long>();
				for (Contenido c:contenidos) {
					if(!(listaID.contains(c.getSpecificID()))) {
						System.out.println(c.getTitulo()+"\n");
						System.out.println(c.getAutor()+"\n");
						System.out.println(((Integer)c.getAno()).toString()+"\n");
						System.out.println(c.getClass().getName()+"\n\n");
						listaID.add(c.getSpecificID());
					}
				}
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(this, e1.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE, new ImageIcon("files\\images\\error.png"));
			}
		}
		
		@SuppressWarnings("unused")
		public List<Contenido> getContenido(){
			return contenidos;
		}
	}

	private class BotonBuscarPerfiles extends JButton implements ActionListener{
		private static final long serialVersionUID = -3472324189940058042L;
		private List<Perfil> perfiles;
		
		public BotonBuscarPerfiles() {
			super("Buscar usuario");
			setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			setBorderPainted(false);
			setBackground(new Color(14,209,69));
			setFont(new Font("Segoe UI", Font.BOLD, 16));
			setBounds(45, 535, 200, 34);
			addActionListener(this);
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println(txtFieldPerfiles.getText());
			
			perfiles = Buscador.buscarPerfiles((txtFieldPerfiles.getText().equals("")? null:txtFieldPerfiles.getText()));
			getContentPane().add(resultadoTXT(perfiles),"Resultado");
			windowSwitcher.show(getContentPane(), "Resultado");
			/*List<Integer> listaDNI = new ArrayList<Integer>();
			for (Perfil c:perfiles) {
				if(!(listaDNI.contains(c.getDNI()))) {
					txtResultado.append(((Integer)c.getDNI()).toString()+c.getLetraDNI()+"\n");
					txtResultado.append(c.getNombre()+" "+c.getApellido()+"\n");
					txtResultado.append((c.getFechaNacimiento().toString())+"\n");
					txtResultado.append(c.getClass().getName()+"\n\n");
					listaDNI.add(c.getDNI());
				}
			}
			*/
		}
		
		@SuppressWarnings("unused") 
		public List<Perfil> getPerfiles(){
			return perfiles;
		}
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