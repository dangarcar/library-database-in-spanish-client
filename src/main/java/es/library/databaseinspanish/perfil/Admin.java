package es.library.databaseinspanish.perfil;

import java.time.LocalDate;

import javax.swing.ImageIcon;

import es.library.databaseinspanish.perfil.excepciones.ExcepcionDNIPerfil;
import es.library.databaseinspanish.perfil.excepciones.ExcepcionPerfil;

/**
 * Clase hija de Perfil que sirve para representar a los administradores, que en esta biblioteca serían los teóricos trabajadores de la misma
 * @author Daniel García
 *
 */
public class Admin extends Perfil{
	private static final long serialVersionUID = -1129094060044587791L;

	/**
	 * Constructor igual que el de su clase padre, Perfil<br>
	 * Está hecho para crear administradores igual que se crean es.library.databaseinspanish.perfil normales
	 * @param nombre
	 * @param apellido
	 * @param fecha
	 * @param dni
	 * @param direccionCasa
	 * @param correoElectronico
	 * @throws ExcepcionDNIPerfil
	 * @throws ExcepcionPerfil
	 */
	public Admin(String nombre, String apellido, LocalDate fecha, int dni, String direccionCasa,String correoElectronico) throws ExcepcionDNIPerfil, ExcepcionPerfil{
		super(nombre, apellido, fecha, dni, direccionCasa, correoElectronico);
	}
	

	/**
	 * Este constructor está hecho para crear administrasdores sin tenr que tenr un perfil completo
	 * @param nombre
	 * @param dni
	 * @throws ExcepcionDNIPerfil
	 * @throws ExcepcionPerfil
	 */
	public Admin(String nombre,int dni) throws ExcepcionDNIPerfil, ExcepcionPerfil{
		super(nombre, "administrator", null, dni, null, null);
	}
	
	public String toString() {
		return super.toString()+"\nSoy Admin";
	}
	
	public ImageIcon getIcon() {
		return new ImageIcon("src/main/resources/files/images/admin.png");
	}
	
}
