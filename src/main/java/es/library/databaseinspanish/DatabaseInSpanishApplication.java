package es.library.databaseinspanish;

import java.awt.EventQueue;
import java.time.LocalDate;

import org.springframework.boot.Banner.Mode;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;

import com.fasterxml.jackson.databind.ObjectMapper;

import es.library.databaseinspanish.model.perfil.Perfil;
import es.library.databaseinspanish.model.perfil.Roles;
import es.library.databaseinspanish.ui.SwingApp;

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
			new SwingApp(new Perfil(null, "Hola", LocalDate.now(), "hola@email.xyz", "hajf%876%/)$=(/)", Roles.ROLE_USER));
//			ImageIcon icon = Utils.getImageFromPerfil("Perico de los palotes");
//			var label = new ImageLabel(icon, 400, 400);
//			try {
////				Contenido c = new Libro(null, "TPrueba", "APrueba", "DPrueba", 1234, "eusera", Soporte.FISICO, true, 21, true, new URL("http://books.google.com/books/content?id=iTZsRwAACAAJ&printsec=frontcover&img=1&zoom=1&source=gbs_api")/*null*/, "67213-81698-92637", 657, "SM");
//				JFrame frame = new JFrame("test");
////				frame.setBounds(100, 100, 200, 200);
//				frame.add(label);
//				frame.pack();
//				frame.setVisible(true);
//			}
//			finally {
//				
//			}
		});
	}
	
	@Bean("jsonMapper")
	public ObjectMapper jsonMapper() {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.findAndRegisterModules();
		return objectMapper;
	}
}
