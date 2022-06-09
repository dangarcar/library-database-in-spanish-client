package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import interfaz.Ventana;

/**
 * Clase principal del programa.<p>
 * Este es un programa para gestionar una biblioteca, en este caso la biblioteca es ficticia.<p>
 * Se puede gestionar préstamos, añadir nuevos contenidos y perfiles y hacer búsquedas dentro de la BBDD.
 * @author Daniel García
 *
 */
public class App {
	/*public static final String url = getDatabase();
	
	public static String getDatabase() {
		String result = null;
		String options = "src/main/resources/options.txt";
		BufferedReader lector = null;
		try {
			lector = new BufferedReader(new FileReader(new File(options)));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			result = lector.readLine();
			System.out.println(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}*/
	
	public static final String url = "src/main/resources/database.db";
	
	public static void main(String [] args){
		new Ventana();
	}
}
