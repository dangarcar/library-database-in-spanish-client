package SQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class ConectorSQL {
	private static final String url = "D:\\Programación\\Java\\library-database\\files\\database.db";
	private Connection connect = null;
	
	/**
	 * Esta funcion establece una conexion con la base de datos
	 */
	public Connection conectar(){
		try {
			connect = DriverManager.getConnection("jdbc:sqlite:"+url);
			/*if (connect != null) {
				JOptionPane.showMessageDialog(null, "Se ha conectado a la base de datos");
			}*/
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "NO se ha podido establecer conexión con la base de datos");
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
			//JOptionPane.showMessageDialog(null, "Se ha cerrado la conexión con la base de datos");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "No se ha podido cerrar la conexión con la base de datos");
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
			PreparedStatement st = connect.prepareStatement(sentenciaSQL);
			resultado = st.executeQuery();
		
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "No se ha podido realizar la peticion correctamente");
			e.printStackTrace();
		}
		return resultado;
	}
}