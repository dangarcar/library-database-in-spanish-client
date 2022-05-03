package interfaz;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import contenido.Contenido;
import perfiles.Perfil;

public class GUIObjetosBiblioteca {
	public static JButton createGUIContenido(Contenido c) {
		JButton boton = new JButton();
		
		boton.setText(c.getTitulo()+"\n "+c.getAutor()+"\n "+c.getAno()+"\n");
		
		boton.setPreferredSize(new Dimension(100,500));
		
		boton.setBackground(Color.WHITE);
		
		//boton.setBorder(null);
		
		return boton;
	}
	
	public static JButton createGUIPerfil(Perfil p) {
		JButton boton = new JButton();
		
		return boton;
	}
	
	public static JPanel createExtendedGUIContenido(Contenido p) {
		JPanel panel = new JPanel();
		
		return panel;
	}
	
	public static JPanel createExtendedGUIPerfil(Perfil p) {
		JPanel panel = new JPanel();
		
		return panel;
	}
}
