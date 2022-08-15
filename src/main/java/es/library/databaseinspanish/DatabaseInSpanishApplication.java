package es.library.databaseinspanish;

import java.awt.EventQueue;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;

import es.library.databaseinspanish.interfaz.Ventana;

/**
 * Clase principal del programa.<p>
 * Este es un programa para gestionar una biblioteca, en este caso la biblioteca es ficticia.<p>
 * Se puede gestionar préstamos, añadir nuevos contenidos y es.library.databaseinspanish.perfil y hacer búsquedas dentro de la BBDD.
 * @author Daniel García
 *
 */
@SpringBootApplication
public class DatabaseInSpanishApplication {	
	public static final String url = "src/main/java/es/library/databaseinspanish/database/database.db";
	
	public static void main(String [] args){
		var app = new SpringApplicationBuilder(DatabaseInSpanishApplication.class).headless(false).run(args);
		
		EventQueue.invokeLater(() -> {
			app.getBean(DatabaseInSpanishApplication.class);
		});
	}
	
	@Bean
	public CommandLineRunner createVentana() {
		return args -> {
			new Ventana();
		};
	}
}
