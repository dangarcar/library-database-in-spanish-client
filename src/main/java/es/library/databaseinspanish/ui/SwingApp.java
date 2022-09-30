package es.library.databaseinspanish.ui;

import java.awt.SystemColor;
import java.awt.Toolkit;

import javax.swing.JFrame;

import es.library.databaseinspanish.model.perfil.Perfil;
import es.library.databaseinspanish.ui.pantallainicio.PantallaInicio;
import es.library.databaseinspanish.ui.utils.ProjectConstants;

/**
 * Frame principal del programa
 * @author Daniel Garc�a
 *
 */
public class SwingApp extends JFrame {
	private PantallaInicio lobby = PantallaInicio.getInstance(this,"staff");
	
	private Perfil userLoggenIn;
	
	public SwingApp() {
		setResizable(true);
		setFocusable(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBackground(SystemColor.controlDkShadow);
		setTitle("Library Database In Spanish");
		setBounds(100,100,ProjectConstants.SCREEN_WIDTH,ProjectConstants.SCREEN_HEIGHT);
		setIconImage(Toolkit.getDefaultToolkit().getImage(SwingApp.class.getResource("/es/library/databaseinspanish/ui/images/icon.png")));
		
		getContentPane().add(lobby);
		
		setVisible(true);
	}
	
	public Perfil getUserLoggenIn() {return userLoggenIn;}
	public void setUserLoggenIn(Perfil userLoggenIn) {this.userLoggenIn = userLoggenIn;}
	
}

/*class BotonBuscarContenidos extends JButton implements ActionListener{
	private static final long serialVersionUID = -3472324132940058042L;
	private List<Contenido> contenidos;
	private SwingApp parent;
	
	public BotonBuscarContenidos(SwingApp parent) {
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
	private SwingApp parent;
	
	public BotonBuscarPerfiles(SwingApp parent) {
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
	
}*/