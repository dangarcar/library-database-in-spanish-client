package es.library.databaseinspanish;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import com.fasterxml.jackson.databind.ObjectMapper;

import es.library.databaseinspanish.contenido.api.implementations.ContenidoApiSpringConfig;
import es.library.databaseinspanish.perfil.api.implementations.PerfilApiSpringConfig;
import es.library.databaseinspanish.prestamos.api.implementations.PrestamoApiSpringConfig;


/**
 * Clase principal del programa.<p>
 * Este es un programa para gestionar una biblioteca, en este caso la biblioteca es ficticia.<p>
 * Se puede gestionar préstamos, añadir nuevos contenidos y es.library.databaseinspanish.perfil y hacer búsquedas dentro de la BBDD.
 * @author Daniel García
 *
 */
@SpringBootApplication
public class DatabaseInSpanishApplication {
	
	public static void main(String [] args){
		var app = new SpringApplicationBuilder(DatabaseInSpanishApplication.class)
				.headless(false)
				.run(args);
		
		AnnotationConfigApplicationContext annotationsContext = new AnnotationConfigApplicationContext();
		annotationsContext.register(
				PerfilApiSpringConfig.class,
				ContenidoApiSpringConfig.class,
				PrestamoApiSpringConfig.class);
		annotationsContext.refresh();
		
		/*EventQueue.invokeLater(() -> {
			app.getBean(DatabaseInSpanishApplication.class);
		});*/
		
		annotationsContext.close();
	}
	
	/*@Bean
	public CommandLineRunner createVentana() {
		return args -> {
			new SwingApp();
		};
	}*/
	@Bean("jsonMapper")
	public ObjectMapper jsonMapper() {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.findAndRegisterModules();
		return objectMapper;
	}
}
