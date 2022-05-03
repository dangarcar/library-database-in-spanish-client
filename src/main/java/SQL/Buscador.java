package SQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import contenido.Contenido;
import contenido.excepciones.ExcepcionContenido;
import perfiles.Perfil;

public class Buscador {
	/**
	 * Hace una b�squeda de contenido basada en el String pasado por par�metro
	 * @param busqueda texto en el que se basa la b�squeda
	 * @return La lista de contenidos que coinciden conesa b�squeda
	 * @throws ExcepcionContenido
	 */
	public static List<Contenido> buscarContenido(String busqueda) throws ExcepcionContenido {
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
					contenidos.addAll(ContenidoSQL.getAudiovisual(resultado.getInt("IDAudio"),null));
				} else if(resultado.getLong("ISBN") != 0) {
					contenidos.addAll(ContenidoSQL.getLibro(resultado.getLong("ISBN"),null));
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			//throw new ExcepcionContenido("Hubo un error con la base de datos, es probable que el contenido que buscas no exista en esta biblioteca",contenidos.get(0));
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
	 * Hace una b�squeda de perfiles basada en el String pasado por par�metro
	 * @param busqueda texto en el que se basa la b�squeda
	 * @return La lista de perfiles que coinciden con esa b�squeda
	 */
	public static List<Perfil> buscarPerfiles(String busqueda){
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
				perfiles.add(PerfilSQL.getPerfil(resultado.getInt("DNI")));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			//throw new ExcepcionContenido("Hubo un error con la base de datos, es probable que el contenido que buscas no exista en esta biblioteca",contenidos.get(0));
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
