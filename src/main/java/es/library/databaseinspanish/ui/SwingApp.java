package es.library.databaseinspanish.ui;

import java.awt.CardLayout;
import java.awt.Dimension;
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
		this.getContentPane().setLayout(cardLayout);
		
		this.getContentPane().add(home, home.getName());
		cardLayout.show(this.getContentPane(), home.getName());

		setSize(ProjectConstants.SCREEN_WIDTH,ProjectConstants.SCREEN_HEIGHT);
		setMinimumSize(new Dimension(256,256));
		setLocationRelativeTo(null);
		
		setVisible(true);
	}
	
	public void changePanel(JPanel panel) {
		this.getContentPane().add(panel, panel.getName());
		cardLayout.show(this.getContentPane(), panel.getName());
	}
	
	public void returnHome() {
		cardLayout.show(this.getContentPane(), home.getName());
	}
	
	public CardLayout getCardLayout() {
		return cardLayout;
	}
	
	public PantallaInicio getHome() {return this.home;}
	public boolean isGuest() {return guest;}
	
	public Perfil getUserLoggenIn() {return userLoggenIn;}
	public void setUserLoggenIn(Perfil userLoggenIn) {this.userLoggenIn = userLoggenIn;}
}