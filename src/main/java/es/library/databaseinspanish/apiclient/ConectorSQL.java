package es.library.databaseinspanish.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import es.library.databaseinspanish.DatabaseInSpanishApplication;

/**
 * Esta clase hace la conexi�n b�sica con la BBDD
 * @author Daniel Garc�a
 *
 */
public class ConectorSQL {
	private Connection connect = null;
	
	/**
	 * Esta funcion establece una conexion con la base de datos
	 */
	public Connection conectar(){
		try {
			Class.forName("org.sqlite.JDBC");
            connect = DriverManager.getConnection("jdbc:sqlite:"+DatabaseInSpanishApplication.url);
		} catch (SQLException | ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, "NO se ha podido establecer conexi�n con la base de datos");
			e.printStackTrace();
		}
		return connect;
	}
	
	/**
	 * Esta funcion cierra la comunicacion con la base de datos
	 */
	public void cerrar(){
		try {
			connect.close();
			//JOptionPane.showMessageDialog(null, "Se ha cerrado la conexi�n con la base de datos");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "No se ha podido cerrar la conexi�n con la base de datos");
			e.printStackTrace();
		}
	}
	
	/**
	 * Esta funcion sirve para hacer consultas a la base de datos.<br>
	 * Ejecuta la sentencia SQL pasada en el String pasada por parametro
	 * @param sentenciaSQL La sentencia SQL a ser ejecutada
	 * @return El ResultSet que devuelve la peticion SQL
	 */
	public ResultSet seleccionar(String sentenciaSQL) {
		ResultSet resultado = null;
		try {
			Statement st = connect.createStatement();
			resultado = st.executeQuery(sentenciaSQL);
		
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "No se ha podido realizar la peticion correctamente");
			e.printStackTrace();
		}
		return resultado;
	}
}