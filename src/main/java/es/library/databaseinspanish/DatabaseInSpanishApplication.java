package es.library.databaseinspanish;

import java.awt.EventQueue;

import org.springframework.boot.Banner.Mode;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;

import com.fasterxml.jackson.databind.ObjectMapper;

import es.library.databaseinspanish.api.utils.StaticApis;
import es.library.databaseinspanish.ui.SwingApp;
import es.library.databaseinspanish.ui.login.LoginWindow;

/**
 * Clase principal del programa.
 * @author Daniel García
 *
 */
@SpringBootApplication
public class DatabaseInSpanishApplication {
	
	public static SpringApplicationBuilder springApplicationBuilder() {
		return new SpringApplicationBuilder(DatabaseInSpanishApplication.class)
				.headless(false)
				.bannerMode(Mode.LOG)
				.logStartupInfo(false);
	}
	
	public static void main(String [] args){
		springApplicationBuilder().run(args);
		
		EventQueue.invokeLater(() -> {			
			try {
				new SwingApp(StaticApis.userApi().getMyInfo());
			} catch(Exception e) {
				new LoginWindow();
			}
		});
	}
	
	@Bean("jsonMapper")
	public ObjectMapper jsonMapper() {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.findAndRegisterModules();
		return objectMapper;
	}
}
