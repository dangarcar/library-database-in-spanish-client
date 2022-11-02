package es.library.databaseinspanish.ui;

import java.awt.CardLayout;
import java.awt.SystemColor;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

import es.library.databaseinspanish.model.perfil.Perfil;
import es.library.databaseinspanish.model.perfil.Roles;
import es.library.databaseinspanish.ui.pantallainicio.PantallaInicio;
import es.library.databaseinspanish.ui.utils.ProjectConstants;

/**
 * Frame principal del programa
 * @author Daniel García
 *
 */
public class SwingApp extends JFrame {
	
	private PantallaInicio home;	
	private Perfil userLoggenIn;
	
	private CardLayout cardLayout = new CardLayout();	
	private JPanel main = new JPanel();
	
	private boolean guest;

	public SwingApp(Perfil user) {
		userLoggenIn = user;		
		home = PantallaInicio.getInstance(this,user.getRole());
		
		initUI();
	}
	
	private SwingApp() {
		this.guest = true;
		home = PantallaInicio.getInstance(this, Roles.ROLE_GUEST);
		initUI();
	}
	
	public static SwingApp createGuestApp() {
		return new SwingApp();
	}
	
	public SwingApp changeToGuestApp() {
		this.guest = true;
		this.userLoggenIn = null;
		home = PantallaInicio.getInstance(this, Roles.ROLE_GUEST);
		initUI();
		return this;
	}
	
	public SwingApp changeToUserApp(Perfil user) {
		this.guest = false;
		this.userLoggenIn = user;
		home = PantallaInicio.getInstance(this, user.getRole());
		initUI();
		return this;
	}
	
	private void initUI() {
		setResizable(true);
		setFocusable(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBackground(SystemColor.controlDkShadow);
		setTitle("Library Database In Spanish");
		setIconImage(Toolkit.getDefaultToolkit().getImage(SwingApp.class.getResource("/es/library/databaseinspanish/ui/images/icon.png")));
		main.setLayout(cardLayout);
		
		main.add(home, home.getName());
		cardLayout.show(main, home.getName());
		
		this.getContentPane().add(main);
		
		setSize(ProjectConstants.SCREEN_WIDTH,ProjectConstants.SCREEN_HEIGHT);
		this.setLocationRelativeTo(null);
		
		setVisible(true);
	}
	
	public void changePanel(JPanel panel) {
		main.add(panel, panel.getName());
		cardLayout.show(main, panel.getName());
	}
	
	public void returnHome() {
		cardLayout.show(main, home.getName());
	}
	
	public PantallaInicio getHome() {return this.home;}
	public boolean isGuest() {return guest;}
	
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