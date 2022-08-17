package es.library.databaseinspanish;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import es.library.databaseinspanish.perfil.Perfil;
import es.library.databaseinspanish.perfil.api.PerfilApi;
import es.library.databaseinspanish.perfil.api.implementations.PerfilApiSpringConfig;
import es.library.databaseinspanish.perfil.exceptions.PerfilNotFoundException;
import es.library.databaseinspanish.perfil.exceptions.UnexpectedPerfilException;


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
		var app = new SpringApplicationBuilder(DatabaseInSpanishApplication.class).headless(false).run(args);
		
		AnnotationConfigApplicationContext annotationsContext = new AnnotationConfigApplicationContext();
		annotationsContext.register(PerfilApiSpringConfig.class);
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
	@Bean("default")
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}
	@Bean("jsonMapper")
	public ObjectMapper jsonMapper() {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.findAndRegisterModules();
		return objectMapper;
	}
}
