package es.library.databaseinspanish.perfil;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import es.library.databaseinspanish.contenido.Contenido;
import es.library.databaseinspanish.contenido.excepciones.ExcepcionContenido;
import es.library.databaseinspanish.database.DatabaseWritable;
import es.library.databaseinspanish.interfaz.GUIObjetosBiblioteca;
import es.library.databaseinspanish.perfil.excepciones.ExcepcionDNIPerfil;
import es.library.databaseinspanish.perfil.excepciones.ExcepcionPerfil;

/**
 * Clase encargada de ser padre del resto de es.library.databaseinspanish.perfil, adem�s de poder ser instancia para crear el objeto del perfil base, que ser�an los te�ricos usuarios normales de la biblioteca
 * @author Daniel Garc�a
 *
 */
public class Perfil implements DatabaseWritable{
	private static final long serialVersionUID = 8775700157993623247L;
	private final char[] letrasDNI = {'T','R','W','A','G','M','Y','F','P','D','X','B','N','J','Z','S','Q','V','H','L','C','K','E'};
	private String nombre;
	private String apellido;
	private LocalDate fechaNacimiento = null;
	private String direccionDeCasa;
	private String correoElectronico;
	private int DNI;
	private ArrayList<Integer> contenidosEnPrestamo = new ArrayList<Integer>();
	
	/**
	 * Constructor del perfil base
	 * @param nombre
	 * @param apellido
	 * @param fecha
	 * @param dni
	 * @param direccionCasa
	 * @param correoElectronico
	 * @throws ExcepcionNoID 
	 * @throws IOException 
	 * @throws Excepcionperfil
	 */
	public Perfil(String nombre, String apellido, LocalDate fecha, int dni, String direccionCasa, String correoElectronico) throws ExcepcionPerfil, ExcepcionDNIPerfil{
		setNombre(nombre);
		setApellido(apellido);
		setDNI(dni);
		try{
			setFechaNacimiento(fecha);
		} catch(ExcepcionPerfil e) {
			fechaNacimiento = null;
		}
		setDireccionDeCasa(direccionCasa);
		setCorreoElectronico(correoElectronico);
	}

	/* Setters: Aqu� compruebo si son correctos los datos */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	/**
	 * Es un setter que comprueba que la edad de la persona es menor de 120 a�os y mayor de 6
	 * @param fechaNacimiento
	 * @throws ExcepcionEdadPersona 
	 */
	public void setFechaNacimiento(LocalDate fechaDeNacimiento) throws ExcepcionPerfil {
		if (getEdad(fechaDeNacimiento) < 6) {
			throw new ExcepcionPerfil("La fecha introducida es demasiado reciente, debe ser mayor de 6 a�os",this);
		}
		if (getEdad(fechaDeNacimiento) > 120) {
			throw new ExcepcionPerfil("La fecha introducida es demasiado antigua, debe ser menor de 120 a�os",this);
		}
		this.fechaNacimiento = fechaDeNacimiento;
	}

	public void setDireccionDeCasa(String direccionDeCasa) {
		this.direccionDeCasa = direccionDeCasa;
	}

	public void setCorreoElectronico(String correoElectronico) throws ExcepcionPerfil {
		if(correoElectronico != null) {	
			if (!(correoElectronico.contains("@"))) {
				throw new ExcepcionPerfil("La direccion de correo electronico no contiene arroba, no es valida",this);
			}
		}
		this.correoElectronico = correoElectronico;
	}

	public void setDNI(int dni) throws ExcepcionDNIPerfil {
		if (dni > 99999999 || dni < 0) {
			throw new ExcepcionDNIPerfil("El numero de DNI no es correcto",this);
		}
		DNI = dni;
	}

	
	/* Getters */
	public String getNombre() { return nombre; }
	public String getApellido() { return apellido; }
	public LocalDate getFechaNacimiento() { return fechaNacimiento; }
	public String getDireccionDeCasa() { return direccionDeCasa; }
	public String getCorreoElectronico() { return correoElectronico; }
	public int getDNI() { return DNI; }
	public char getLetraDNI() { return this.letrasDNI[DNI % 23]; }
	public int getID() { return DNI; }
	public ArrayList<Integer> getEnPrestamo() { return contenidosEnPrestamo; }
	
	public void setEnPrestamo(ArrayList<Integer> a) { contenidosEnPrestamo = a; }
	
	/**
	 * Devuelve la edad en a�os del perfil respecto a la actual
	 * @return
	 */
	public int getEdad() {
		int edad = LocalDate.now().getYear() - fechaNacimiento.getYear();
		return edad;
	}
	
	/**
	 * Devuelve los a�os que ha pasado desde la fecha que se pasa por parametro
	 * @param fecha
	 * @return
	 */
	public int getEdad(LocalDate fecha) {
		int edad = (fecha == null)? 0:LocalDate.now().getYear() - fecha.getYear();
		return edad;
	}
	
	@Override
	/* Metodos sobreescritos de la clase Object, toString y equals*/
	public String toString() {
		return "Nombre: "+nombre+"\nApellidos: "+apellido+"\nFecha de nacimiento: "+((fechaNacimiento != null)? fechaNacimiento.toString():"No existe")+"\nDNI: "+DNI+getLetraDNI();
	}
	
	/**
	 * Devuelve true si el DNI pasado por parametro es igual al de la persona
	 * @param dni
	 * @return
	 */
	public boolean equals(int dni) {
		if (this.DNI == dni) {
			return true;
		}
		return false;
	}
	
	/**
	 * Devuelve true si el DNI de la persona pasada por parametro es igual al this.DNI
	 * @param persona
	 * @return
	 */
	public boolean equals(Perfil user) {
		if(user.getDNI() == this.DNI) {
			return true;
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		int hash = 15;
		hash = hash + 13 * DNI;
		hash = hash - 2 * nombre.hashCode();
		return hash;
	}

	/**
	 * Este m�todo a�ade el identificador del es.library.databaseinspanish.contenido pasado por par�metro<br>
	 * a la lista de contenidos en pr�stamo del perfil
	 * @param c El es.library.databaseinspanish.contenido a ser prestado
	 */
	public void prestarPerfil(Contenido c) {
		contenidosEnPrestamo.add(c.getID());
	}
	
	/**
	 * Este m�todo elimina el identificador del es.library.databaseinspanish.contenido pasado por par�metro<br>
	 * a la lista de contenidos en pr�stamo del perfil
	 * @param c El es.library.databaseinspanish.contenido a ser prestado
	 */
	public void devolverPerfil(Contenido c) {
		contenidosEnPrestamo.remove((Object)c.getID());
	}

	@Override
	public JButton getGUIRepresentation(List<? extends DatabaseWritable> listaD) throws ExcepcionContenido {
		GUIObjetosBiblioteca gui = new GUIObjetosBiblioteca(this,listaD);
		return gui.createGUIPerfil();
	}

	@Override
	public long getSpecificID() {
		return DNI;
	}

	@Override
	public JPanel getExtendedGUIRepresentation(List<? extends DatabaseWritable> listaD) {
		GUIObjetosBiblioteca gui = new GUIObjetosBiblioteca(this,listaD);
		return gui.createExtendedGUIPerfil();
	}
	
	public ImageIcon getIcon() {
		return new ImageIcon("src/main/resources/files/images/perfilFino.png");
	}
}
