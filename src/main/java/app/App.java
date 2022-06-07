package app;

import interfaz.Ventana;

/**
 * Clase principal del programa.<p>
 * Este es un programa para gestionar una biblioteca, en este caso la biblioteca es ficticia.<p>
 * Se puede gestionar préstamos, añadir nuevos contenidos y perfiles y hacer búsquedas dentro de la BBDD.
 * @author Daniel García
 *
 */
public class App {
	//Donde está guardada la BBDD Sqlite, cámbiese para almacenar los datos en otro sitio
	public static final String url = "files/database.db";
	
	public static void main(String [] args){
		new Ventana();
	}
}
