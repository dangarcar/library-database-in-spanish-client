package es.library.databaseinspanish.database;

import static es.library.databaseinspanish.database.ContenidoSQL.*;
import static es.library.databaseinspanish.database.PerfilSQL.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import es.library.databaseinspanish.contenido.Contenido;
import es.library.databaseinspanish.contenido.excepciones.ExcepcionContenido;
import es.library.databaseinspanish.perfil.Perfil;
import es.library.databaseinspanish.perfil.excepciones.ExcepcionPerfil;

/**
 * Esta clase busca en la BBDD un es.library.databaseinspanish.contenido o un perfil
 * @author Daniel García
 *
 */
public class Buscador {
	/**
	 * Hace una búsqueda de es.library.databaseinspanish.contenido basada en el String pasado por parámetro
	 * @param busqueda texto en el que se basa la búsqueda
	 * @return La lista de contenidos que coinciden con esa búsqueda
	 * @throws ExcepcionContenido
	 */
	public static List<Contenido> BuscarContenido(String busqueda) throws ExcepcionContenido {
		ConectorSQL conector = null;
		ResultSet resultado = null;
		Connection conexion = null;
		PreparedStatement st = null;
		//ArrayList<Integer> contenidosID = new ArrayList<Integer>();
		List<Contenido> contenidos = new ArrayList<Contenido>();
		
		String peticionSQL = "SELECT IDAudio,ISBN FROM BusquedaContenidos(?) GROUP BY ISBN, IDAudio ORDER BY rank;";	
		if(busqueda != null) {
		try {
			//Creo el objeto con el que conectarme a la BD
			conector = new ConectorSQL();
			conexion = conector.conectar();
			
			//Hago al peticion SQL a la base de datos y almaceno el ResultSet
			st = conexion.prepareStatement(peticionSQL);
			st.setString(1, busqueda);
			resultado = st.executeQuery();
			st.clearParameters();
			
			while(resultado.next()) {
				if(resultado.getInt("IDAudio") != 0) {
					contenidos.addAll(GetAudiovisual(resultado.getInt("IDAudio"),null));
				} else if(resultado.getLong("ISBN") != 0) {
					contenidos.addAll(GetLibro(resultado.getLong("ISBN"),null));
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			//throw new ExcepcionContenido("Hubo un error con la base de datos, es probable que el es.library.databaseinspanish.contenido que buscas no exista en esta biblioteca",contenidos.get(0));
		} finally {
			//Si la conexion a la base de datos existe, la cierro
			if(conector != null) {
				conector.cerrar();
			}
		}
		}
		
		return contenidos;
	}
	
	/**
	 * Hace una búsqueda de es.library.databaseinspanish.perfil basada en el String pasado por parámetro
	 * @param busqueda texto en el que se basa la búsqueda
	 * @return La lista de es.library.databaseinspanish.perfil que coinciden con esa búsqueda
	 */
	public static List<Perfil> BuscarPerfiles(String busqueda){
		ConectorSQL conector = null;
		ResultSet resultado = null;
		Connection conexion = null;
		PreparedStatement st = null;
		ArrayList<Perfil> perfiles = new ArrayList<Perfil>();
		
		String peticionSQL = "SELECT DNI,Nombre,Apellidos,CorreoElectronico FROM BusquedaPerfiles(?) ORDER BY rank;";	
		
		if(busqueda != null) {
		try {
			//Creo el objeto con el que conectarme a la BD
			conector = new ConectorSQL();
			conexion = conector.conectar();
			
			//Hago al peticion SQL a la base de datos y almaceno el ResultSet
			st = conexion.prepareStatement(peticionSQL);
			st.setString(1, busqueda);
			resultado = st.executeQuery();
			st.clearParameters();
			
			while(resultado.next()) {
				perfiles.add(GetPerfil(resultado.getInt("DNI")));
			}
			
		} catch (SQLException | ExcepcionPerfil e) {
			e.printStackTrace();
			//throw new ExcepcionContenido("Hubo un error con la base de datos, es probable que el es.library.databaseinspanish.contenido que buscas no exista en esta biblioteca",contenidos.get(0));
		} finally {
			//Si la conexion a la base de datos existe, la cierro
			if(conector != null) {
				conector.cerrar();
			}
		}
		}
		
		return perfiles;
	}
}
