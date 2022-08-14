package es.library.databaseinspanish.database;

import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import es.library.databaseinspanish.contenido.Contenido;
import es.library.databaseinspanish.perfil.Admin;
import es.library.databaseinspanish.perfil.Perfil;
import es.library.databaseinspanish.perfil.excepciones.ExcepcionDNIPerfil;
import es.library.databaseinspanish.perfil.excepciones.ExcepcionPerfil;

/**
 * Clase encargada de modificar los datos de los es.library.databaseinspanish.perfil en la BBDD<p>
 * Los m�todos devolver y prestar de esta clase solo modifican el perfil, no cogen ning�n es.library.databaseinspanish.contenido en pr�stamo
 * @author Daniel Garc�a
 *
 */
public class PerfilSQL {
	/**
	 * Crea un objeto Perfil a partir de una clave, en este caso el DNI<br>
	 * Es v�lida para los admins
	 * @param dni El DNI de la persona
	 * @return El objeto del tipo Perfil
	 * @throws ExcepcionPerfil 
	 * @throws ExcepcionDNIPerfil 
	 */
	public static Perfil GetPerfil(int dni) throws ExcepcionDNIPerfil, ExcepcionPerfil {
		Perfil perfil = null;
		ResultSet resultado = null;
		ConectorSQL conector = null;
		
		String nombre;
		String apellidos;
		LocalDate fechaDeNacimiento = null;
		int DNI;
		String direccionCasa;
		String correoElectronico;
		boolean admin;
		
		String peticionSQL = "SELECT Nombre,Apellidos,FechaDeNacimiento,DNI,DireccionCasa,CorreoElectronico,Admin,ContenidoEnPrestamo FROM Perfiles WHERE DNI = "+dni+";";
		
		try {
			//Creo un objeto con el que conectarme a la base de datos y me conecto a la base de datos
			conector = new ConectorSQL();
			conector.conectar();
			
			//Hago al peticion SQL a la base de datos y almaceno el ResultSet
			resultado = conector.seleccionar(peticionSQL);
			
			//Saco las variables a partir de el ResultSet SQL
			nombre = resultado.getString("Nombre");
			apellidos = resultado.getString("Apellidos");
			try{
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				fechaDeNacimiento = LocalDate.parse(resultado.getString("FechaDeNacimiento"),formatter);
			} catch(DateTimeParseException e) {
				System.out.println("�Eres admin?");
			}
			DNI = resultado.getInt("DNI");
			direccionCasa = resultado.getString("DireccionCasa");
			correoElectronico = resultado.getString("CorreoElectronico");
			admin = resultado.getBoolean("Admin");
			
			if(admin == true) {
				perfil = new Admin(nombre,apellidos,fechaDeNacimiento,DNI,direccionCasa,correoElectronico);
				perfil.setEnPrestamo((inputEnPrestamo(resultado.getString("ContenidoEnPrestamo")) != null)? inputEnPrestamo(resultado.getString("ContenidoEnPrestamo")):new ArrayList<Integer>());
			}
			else {
				perfil = new Perfil(nombre,apellidos,fechaDeNacimiento,DNI,direccionCasa,correoElectronico);
				perfil.setEnPrestamo((inputEnPrestamo(resultado.getString("ContenidoEnPrestamo")) != null)? inputEnPrestamo(resultado.getString("ContenidoEnPrestamo")):new ArrayList<Integer>());
			}
			
		} catch (SQLException e) {
			throw new ExcepcionPerfil("La base de datos ha tenido un problema", null);
		} finally {
			//Si la conexion a la base de datos existe, la cierro
			if(conector != null) {
				conector.cerrar();
			}
		}
		
		return perfil;
	}
	
	/**
	 * Esta funci�n escribe en la base de datos el perfil si es correcto<br>
	 * No es v�lida para los admins
	 * @param perfil El objeto Perfil a ser pasado
	 * @throws ExcepcionPerfil 
	 */
	public static void WritePerfil(Perfil perfil) throws ExcepcionPerfil {
		ConectorSQL conector = null;
		PreparedStatement st;
		
		if (perfil != null) {
			try{
				//Me conecto a la base de datos
				conector = new ConectorSQL();
				Connection connect = conector.conectar();
				
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				String peticionSQL = "INSERT INTO Perfiles(Nombre,Apellidos,FechaDeNacimiento,DNI,DireccionCasa,CorreoElectronico,ContenidoEnPrestamo) VALUES(?,?,?,?,?,?,?);";
			
				st = connect.prepareStatement(peticionSQL);
				st.setString(1, perfil.getNombre());
				st.setString(2, perfil.getApellido());
				st.setString(3, (perfil.getFechaNacimiento() != null)? perfil.getFechaNacimiento().format(formatter):null);
				st.setInt(4, perfil.getDNI());
				st.setString(5, perfil.getDireccionDeCasa());
				st.setString(6, perfil.getCorreoElectronico());
				st.setString(7, outputEnPrestamo(perfil.getEnPrestamo()));
				st.executeUpdate();
				st.clearParameters();
				
				//System.out.println("Se ha guardado el perfil en la base de datos");
			
			} catch (SQLException e) {
				throw new ExcepcionPerfil("La base de datos ha tenido un problema", null);
			} finally {
				//Si la conexi�n a la base de datos existe, la cierro
				if(conector != null) {
					conector.cerrar();
				}
			}
		} else {
			throw new ExcepcionPerfil("El perfil seleccionado no es v�lido",perfil);
		}
	}
	
	/**
	 * Este m�todo escribe en la base de datos el admin pasado por par�metros si es correcto<br>
	 * esta funci�n no es v�lida para los es.library.databaseinspanish.perfil de usuario normales
	 * @param perfil El objeto de tipo admin a ser pasado
	 * @throws ExcepcionPerfil 
	 */
	public static void WriteAdmin(Admin perfil) throws ExcepcionPerfil {
		ConectorSQL conector = null;
		PreparedStatement st;
		
		if (perfil != null) {
			try{
				//Me conecto a la base de datos
				conector = new ConectorSQL();
				Connection connect = conector.conectar();
				
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				String peticionSQL = "INSERT INTO Perfiles(Nombre,Apellidos,FechaDeNacimiento,DNI,DireccionCasa,CorreoElectronico,Admin,ContenidoEnPrestamo) VALUES(?,?,?,?,?,?,1,?);";
			
				st = connect.prepareStatement(peticionSQL);
				st.setString(1, perfil.getNombre());
				st.setString(2, perfil.getApellido());
				st.setString(3, (perfil.getFechaNacimiento() != null)? perfil.getFechaNacimiento().format(formatter):null);
				st.setInt(4, perfil.getDNI());
				st.setString(5, perfil.getDireccionDeCasa());
				st.setString(6, perfil.getCorreoElectronico());
				st.setString(7, outputEnPrestamo(perfil.getEnPrestamo()));
				st.executeUpdate();
				st.clearParameters();
				
				//System.out.println("Se ha guardado el administrador en la base de datos");
			
			} catch (SQLException e) {
				throw new ExcepcionPerfil("La base de datos ha tenido un problema", null);
			} finally {
				//Si la conexi�n a la base de datos existe, la cierro
				if(conector != null) {
					conector.cerrar();
				}
			}
		} else {
			throw new ExcepcionPerfil("El admin seleccionado no es v�lido",perfil);
		}
	}
	
	/**
	 * Esta funci�n da permisos de administrador a un perfil normal,<br>
	 * pone como administrador en la base datos a ese perfil
	 * @param perfil El perfil a ser convertido
	 * @return El perfil convertido en admin
	 * @throws ExcepcionPerfil 
	 */
	public static Admin PerfilToAdmin(Perfil perfil) throws ExcepcionPerfil {
		Admin admin = null;
		ConectorSQL conector = null;
		PreparedStatement st;
		
		if (perfil != null) {
			try{
				//Me conecto a la base de datos
				conector = new ConectorSQL();
				Connection connect = conector.conectar();
				
				String peticionSQL = "UPDATE Perfiles SET Admin = 1 WHERE DNI = (?);"; 
			
				st = connect.prepareStatement(peticionSQL);
				st.setInt(1, perfil.getDNI());
				st.executeUpdate();
				st.clearParameters();
				
				admin = new Admin(perfil.getNombre(),perfil.getApellido(),perfil.getFechaNacimiento(),perfil.getDNI(),perfil.getDireccionDeCasa(),perfil.getCorreoElectronico());
				
				//System.out.println("El perfil: "+perfil.getDNI()+" ha pasado a ser administrador");
			
			} catch (SQLException e) {
				throw new ExcepcionPerfil("La base de datos ha tenido un problema", null);
			} catch(ExcepcionPerfil e){
				throw new ExcepcionPerfil("El perfil no se ha podido convertir en admin",perfil);
			} finally {
				//Si la conexi�n a la base de datos existe, la cierro
				if(conector != null) {
					conector.cerrar();
				}
			}
		} else {
			throw new ExcepcionPerfil("El perfil seleccionado no es v�lido",perfil);
		}
		return admin;
	}
	
	/**
	 * Esta funci�n devuelve un perfil creado a partir del administrador que se pasa por par�metro,<br>
	 * quit�ndole todos los privilegios y modificando su estado en la base de datos
	 * @param admin El administrador a quitar privilegios
	 * @return Un objeto Perfil con los datos del administrador pasado por par�metro
	 * @throws ExcepcionPerfil
	 */
	public static Perfil AdminToPerfil(Admin admin) throws ExcepcionPerfil{
		Perfil perfil = null;
		ConectorSQL conector = null;
		PreparedStatement st;
		
		if (admin != null) {
			try{
				//Me conecto a la base de datos
				conector = new ConectorSQL();
				Connection connect = conector.conectar();
				
				String peticionSQL = "UPDATE Perfiles SET Admin = 0 WHERE DNI = (?);"; 
			
				st = connect.prepareStatement(peticionSQL);
				st.setInt(1, admin.getDNI());
				st.executeUpdate();
				st.clearParameters();
				
				perfil = new Perfil(admin.getNombre(),admin.getApellido(),admin.getFechaNacimiento(),admin.getDNI(),admin.getDireccionDeCasa(),admin.getCorreoElectronico());
				
				//System.out.println("El admin: "+admin.getDNI()+" ha pasado a ser un usuario normal");
			
			} catch (SQLException e) {
				throw new ExcepcionPerfil("La base de datos ha tenido un problema", null);
			} catch (ExcepcionPerfil e) {
				throw new ExcepcionPerfil("El admin no se ha podido convertir en perfil",null);
			} finally {
				//Si la conexi�n a la base de datos existe, la cierro
				if(conector != null) {
					conector.cerrar();
				}
			}
		} else {
			throw new ExcepcionPerfil("El perfil seleccionado no es v�lido",perfil);
		}
		return perfil;
	}
	
	/**
	 * Esta funci�n borra el perfil indicado de la base de adtos si el perfil es correcto y ya existe en ella<br>
	 * Es tambi�n v�lida para los admins
	 * @param perfil
	 * @throws ExcepcionPerfil 
	 */
	public static void DeletePerfil(Perfil perfil) throws ExcepcionPerfil {
		ConectorSQL conector = null;
		PreparedStatement st;
		
		if (perfil != null) {
			try{
				//Me conecto a la base de datos
				conector = new ConectorSQL();
				Connection connect = conector.conectar();
				
				String peticionSQL = "DELETE FROM Perfiles WHERE DNI = (?);"; 
			
				st = connect.prepareStatement(peticionSQL);
				st.setInt(1, perfil.getDNI());
				st.executeUpdate();
				st.clearParameters();
				
				//System.out.println("Se ha borrado el perfil de la base de datos");
			
			} catch (SQLException e) {
				throw new ExcepcionPerfil("La base de datos ha tenido un problema", null);
			} finally {
				//Si la conexi�n a la base de datos existe, la cierro
				if(conector != null) {
					conector.cerrar();
				}
			}
		} else {
			throw new ExcepcionPerfil("El perfil seleccionado no es v�lido",perfil);
		}
	}
	
	/**
	 * Modifica el perfil a�adiendo  el es.library.databaseinspanish.contenido c pasado por par�metro a la lista de contenidos en pr�stamo<br>
	 * Tambi�n modifica el perfi en la base de datos
	 * @param connect El objeto de clase Connection del SQL
	 * @param c El es.library.databaseinspanish.contenido a prestar
	 * @param p El perfil que toma prestado el es.library.databaseinspanish.contenido
	 * @throws ExcepcionPerfil 
	 */
	public static void PrestarPerfilBBDD(Connection connect,Contenido c,Perfil p) throws ExcepcionPerfil {
		PreparedStatement st;
		
		try {
			//Modifico la lista de contenidos en pr�stamo
			p.prestarPerfil(c);
			
			//Lo guardo en la BBDD
			st = connect.prepareStatement("UPDATE Perfiles SET ContenidoEnPrestamo = ? WHERE DNI = ?");
			st.setString(1, outputEnPrestamo(p.getEnPrestamo()));
			st.setInt(2, p.getDNI());
			st.executeUpdate();
			st.clearParameters();
			
		} catch (SQLException e) {
			throw new ExcepcionPerfil("La base de datos ha tenido un problema", null);
		}
	}
	
	/**
	 * Modifica el perfil y elimina de la lista de prestados el es.library.databaseinspanish.contenido c pasado por par�metro<br>
	 * Tambi�n modifica el perfil de la base de datos
	 * @param connect El objeto de clase Connection del SQL
	 * @param c El es.library.databaseinspanish.contenido a prestar
	 * @param p El perfil que toma prestado el es.library.databaseinspanish.contenido
	 * @throws ExcepcionPerfil 
	 */
	public static void DevolverPerfilBBDD(Connection connect,Contenido c,Perfil p) throws ExcepcionPerfil {
		PreparedStatement st;
		
		try {
			//Modifico la lista de contenidos en pr�stamo
			p.devolverPerfil(c);
			
			//Lo guardo en la BBDD
			st = connect.prepareStatement("UPDATE Perfiles SET ContenidoEnPrestamo = ? WHERE DNI = ?");
			st.setString(1, outputEnPrestamo(p.getEnPrestamo()));
			st.setInt(2, p.getDNI());
			st.executeUpdate();
			st.clearParameters();
			
		} catch(SQLException e) {
			throw new ExcepcionPerfil("La base de datos ha tenido un problema", null);
		}
	}
	
	/**
	 * Convierte de string de la BBDD a arrayList de contenidos en prestamo
	 * @param array
	 * @return
	 */
	public static ArrayList<Integer> inputEnPrestamo(String array){
		Gson gson = new Gson();
		Type type = new TypeToken<ArrayList<Integer>>() {}.getType();
		ArrayList<Integer> a = gson.fromJson(array, type);
		return a;
	}
	
	/**
	 * Convierte de arrayList de objetos en prestamo a String grabable en la BBDD
	 * @param p
	 * @return
	 */
	public static String outputEnPrestamo(ArrayList<Integer> p){
		Gson gson = new Gson();
		String outputString = gson.toJson(p);
		return outputString;
	}
}
