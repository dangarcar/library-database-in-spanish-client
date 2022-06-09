package interfaz;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import contenido.Contenido;
import contenido.excepciones.ExcepcionContenido;
import database.DatabaseWritable;
import perfiles.Perfil;
import perfiles.excepciones.ExcepcionPerfil;

/**
 * Solo se pueden usar los metodos que especifiquen que son compatibles con la clase 
 * de Database Writable pasado por parï¿½metro en el constructor
 * @author danie
 *
 */
public class GUIObjetosBiblioteca {
	private DatabaseWritable c;
	private List<DatabaseWritable> listaC = new ArrayList<DatabaseWritable>();
	
	private List<Contenido> listaCCon = new ArrayList<Contenido>();
	private List<Perfil> listaCPer = new ArrayList<Perfil>();
	
	public GUIObjetosBiblioteca(DatabaseWritable d,List<? extends DatabaseWritable> listaD) {
		if(listaD != null) {
			for (DatabaseWritable o: listaD) {
				if(o.getSpecificID() == d.getSpecificID()) {
					listaC.add(o);
					if(o instanceof Contenido) {
						listaCCon.add((Contenido)o);
					}
					if (o instanceof Perfil) {
						listaCPer.add((Perfil)o);
					}
					//System.out.println(((Contenido)o).getTitulo());
				}
			}
		}
		this.c = d;
	}
	
	public JButton createGUIContenido() throws ExcepcionPerfil {
		JButton boton = new JButton();
		
		if(c instanceof Contenido) {
			boton.setFont(new Font("Segoe UI",Font.CENTER_BASELINE,16));
			boton.setText("<html><body><p>"+((Contenido) c).getTitulo()+"<br> "+((Contenido) c).getAutor()+"<br> "+((Contenido) c).getAno()+"</p></body></html>");
			boton.setPreferredSize(new Dimension(0,300));
			boton.setBackground(Color.WHITE);
			boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			boton.setIcon(new ImageIcon(((Contenido) c).getSoporte().getIcon().getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
		} else throw new ExcepcionPerfil("Este método no es aplicable para la clase Perfil",(Perfil) c);
		
		return boton;
	}
	
	public JButton createGUIPerfil() throws ExcepcionContenido {
		JButton boton = new JButton();
		
		if(c instanceof Perfil) {
			boton.setFont(new Font("Segoe UI",Font.CENTER_BASELINE,16));
			boton.setText("<html><body>"+((Perfil) c).getNombre()+"<br> "+((Perfil) c).getApellido()+"<br> "+((Perfil) c).getDNI()+"</body></html>");
			boton.setPreferredSize(new Dimension(0,300));
			boton.setBackground(Color.WHITE);
			boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			boton.setIcon(new ImageIcon(((Perfil) c).getIcon().getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH)));
		} else throw new ExcepcionContenido("Estï¿½ mï¿½todo no es aplicable para la clase Perfil",(Contenido) c);
		
		return boton;
	}
	
	public JPanel  createExtendedGUIContenido() {
		return new ExtendedGUIContenido((Contenido)c,listaCCon); 
	}
	
	public JPanel createExtendedGUIPerfil() {
		return new ExtendedGUIPerfil((Perfil)c,listaCPer);
	}
	
}