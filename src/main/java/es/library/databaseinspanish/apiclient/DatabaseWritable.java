package es.library.databaseinspanish.database;

import java.io.Serializable;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

import es.library.databaseinspanish.contenido.excepciones.ExcepcionContenido;
import es.library.databaseinspanish.perfil.excepciones.ExcepcionPerfil;

/**
 * Interfaz que hereda de Serializable y sirve para unir los objetos guardables en la BBDD,<br>
 * es decir, Contenido, pErfil y sus hijos
 * @author Daniel Garc�a
 *
 */
public interface DatabaseWritable extends Serializable{
	public JButton getGUIRepresentation(List<? extends DatabaseWritable> listaD) throws ExcepcionContenido, ExcepcionPerfil;
	public JPanel getExtendedGUIRepresentation(List<? extends DatabaseWritable> listaD);
	public long getSpecificID();
}
