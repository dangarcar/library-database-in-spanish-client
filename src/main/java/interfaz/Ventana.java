package interfaz;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;


class PanelPrincipal extends JPanel{
	private static final long serialVersionUID = -7194113105930860575L;
	
	public PanelPrincipal() {
		setLayout(new BorderLayout());
		setBackground(Color.DARK_GRAY);
		
		add(new Menu(),BorderLayout.WEST);
	}
	
}


/**
 * Esta es la ventana que corresponde con el programa principal, aunque los cambios de la interfaz los hago en el PanelPrincipal
 * @author danie
 *
 */
public class Ventana extends JFrame{
	private static final long serialVersionUID = -3030099236595355215L;
	
	public Ventana() {
		//Creo la ventana
		setBounds(50,50,640,480);
		setResizable(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		try {
			setIconImage(ImageIO.read(new File("files/images/icon.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		setTitle("Controlador de biblioteca");
		
		getContentPane().add(new PanelPrincipal());
		
		//Y la enseño
		setVisible(true);
	}
	
}