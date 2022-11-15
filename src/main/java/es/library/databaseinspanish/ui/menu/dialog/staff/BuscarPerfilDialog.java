package es.library.databaseinspanish.ui.menu.dialog.staff;

import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import es.library.databaseinspanish.api.perfil.dto.PerfilParamsDto;
import es.library.databaseinspanish.api.utils.StaticApis;
import es.library.databaseinspanish.exceptions.perfil.PerfilNotFoundException;
import es.library.databaseinspanish.exceptions.perfil.UnexpectedPerfilException;
import es.library.databaseinspanish.model.perfil.Perfil;
import es.library.databaseinspanish.ui.SwingApp;
import es.library.databaseinspanish.ui.perfil.PerfilBusquedaResultPanel;
import es.library.databaseinspanish.ui.utils.OptionPanes;

public class BuscarPerfilDialog {

	public static class IDBusqueda {
		private Logger logger = LogManager.getLogger();

		public IDBusqueda(SwingApp parent) {
			Perfil perfil = null;
			boolean correct = false;
			do {
				String text = JOptionPane.showInputDialog(null, "Escriba la id del perfil:");

				if(text == null || text.isBlank()) {
					OptionPanes.warn("La id no debe estar en blanco");
					break;
				}

				try {
					perfil = StaticApis.perfilApi().getPerfilById(Long.valueOf(text));
					correct = true;

					JPanel result = new PerfilBusquedaResultPanel(parent, List.of(perfil));
					result.setName(List.of(perfil).toString()+"_renderer");
					parent.changePanel(result);
				} catch (NumberFormatException e) {
					OptionPanes.warnBlocking(text + " no es una Id válida");
					logger.warn("Error en la búsqueda de List.of(perfil)", e);
				} catch (PerfilNotFoundException e) {
					OptionPanes.errorBlocking("No se ha encontrado el perfil " + text + " en la biblioteca");
					logger.warn("Error en la búsqueda de List.of(perfil)", e);
				} catch (UnexpectedPerfilException e) {
					OptionPanes.errorBlocking(e.getMessage());
					logger.warn("Error en la búsqueda de List.of(perfil)", e);
				} 
			} while (!correct);
		}
	}

	public static class UsernameBusqueda {		
		private Logger logger = LogManager.getLogger();

		public UsernameBusqueda(SwingApp parent) {
			Perfil perfil = null;
			boolean correct = false;
			do {
				String text = JOptionPane.showInputDialog(null, "Escriba el email del perfil:");

				if(text == null || text.isBlank()) {
					OptionPanes.warn("El email no debe estar en blanco");
					break;
				}

				try {
					perfil = StaticApis.perfilApi().getPerfilByUsername(text);
					correct = true;
					
					JPanel result = new PerfilBusquedaResultPanel(parent, List.of(perfil));
					result.setName(List.of(perfil).toString()+"_renderer");
					parent.changePanel(result);
				} catch (PerfilNotFoundException e) {
					OptionPanes.errorBlocking("No se ha encontrado el perfil " + text + " en la biblioteca");
					logger.warn("Error en la búsqueda de List.of(perfil)", e);
				} catch (UnexpectedPerfilException e) {
					OptionPanes.errorBlocking(e.getMessage());
					logger.warn("Error en la búsqueda de List.of(perfil)", e);
				} 
			} while (!correct);
		}
	}

	public static class PalabrasClaveBusqueda {
		private Logger logger = LogManager.getLogger();

		public PalabrasClaveBusqueda(SwingApp parent) {			
			List<Perfil> perfiles = null;
			boolean correct = false;
			do {
				String text = JOptionPane.showInputDialog(null, "Escriba las palabras claves para buscar el perfil:");

				if(text == null || text.isBlank()) {
					OptionPanes.warn("Las palabras claves no deben estar en blanco");
					break;
				}

				try {
					var params = new PerfilParamsDto();
					params.setQuery(text);
					perfiles = StaticApis.perfilApi().getPerfilByParams(params);
					correct = true;

					JPanel result = new PerfilBusquedaResultPanel(parent, perfiles);
					result.setName(perfiles.toString()+"_renderer");
					parent.changePanel(result);
				} catch (UnexpectedPerfilException e) {
					OptionPanes.errorBlocking(e.getMessage());
					logger.warn("Error en la búsqueda de List.of(perfil)", e);
				} 
			} while (!correct);
		}
	}
}