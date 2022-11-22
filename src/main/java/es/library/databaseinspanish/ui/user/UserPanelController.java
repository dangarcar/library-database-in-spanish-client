package es.library.databaseinspanish.ui.user;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import es.library.databaseinspanish.api.utils.StaticApis;
import es.library.databaseinspanish.ui.SwingApp;
import es.library.databaseinspanish.ui.utils.OptionPanes;

public class UserPanelController {

	private UserPanel userPanel;
	private SwingApp parent;
	
	private Logger logger = LogManager.getLogger();
	
	public UserPanelController(SwingApp parent) {
		this.parent = parent;	
		this.userPanel = new UserPanel(parent);
		
		init();
	}
	
	private void init() {
		userPanel.getCerrarSesionButton().addActionListener(cerrarSesion);
		userPanel.getDeleteCuentaButton().addActionListener(deleteCuenta);
		
		userPanel.setName("userPanel");
		parent.changePanel(userPanel);
	}
	
	private ActionListener cerrarSesion = (ActionEvent e) -> {	
		try {
			StaticApis.userApi().logout();
			logger.info("El usuario {} ha cerrado sesión correctamente",parent.getUserLoggenIn().getNombre());
			OptionPanes.info("Se ha cerrado la sesión con éxito");
			parent.changeToGuestApp();
		} catch (Exception e1) {
			OptionPanes.error("No se ha podido cerrar la sesión con éxito");
			logger.warn(e1);
		}
	};
	
	private ActionListener deleteCuenta = (ActionEvent e) -> {
		try {
			StaticApis.userApi().deleteMyPerfil();
			logger.info("El usuario {} eliminó su cuenta correctamente",parent.getUserLoggenIn().getNombre());
			OptionPanes.info("Se ha eliminado la cuenta de la biblioteca con éxito");
			parent.changeToGuestApp();
		} catch (Exception e1) {
			OptionPanes.error("No se ha podido borrar la cuenta con éxito");
			logger.warn(e1);
		}
	};
	
}
