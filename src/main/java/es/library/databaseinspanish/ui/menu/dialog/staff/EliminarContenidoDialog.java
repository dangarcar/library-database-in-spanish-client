package es.library.databaseinspanish.ui.menu.dialog.staff;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import es.library.databaseinspanish.api.utils.StaticApis;
import es.library.databaseinspanish.exceptions.contenido.ContenidoNotFoundException;
import es.library.databaseinspanish.model.contenido.Contenido;
import es.library.databaseinspanish.ui.SwingApp;
import es.library.databaseinspanish.ui.utils.OptionPanes;
import es.library.databaseinspanish.ui.utils.ProjectConstants;
import es.library.databaseinspanish.ui.utils.Utils;
import es.library.databaseinspanish.ui.utils.components.RoundedButton;
import net.miginfocom.swing.MigLayout;

public class EliminarContenidoDialog extends JDialog {
	
	private Logger logger = LogManager.getLogger();
	private JTextField idField;

	public EliminarContenidoDialog() {
		setResizable(false);
		setBounds(200,200,392,256);
		setTitle("Eliminar contenido");
		setIconImage(Toolkit.getDefaultToolkit().getImage(SwingApp.class.getResource("/es/library/databaseinspanish/ui/images/papelera.png")));
		getContentPane().setBackground(ProjectConstants.BACKGROUND_COLOR);
		getContentPane().setLayout(new MigLayout("", "[grow][grow]", "[][grow][]"));

		JLabel titulo = new JLabel("Eliminar contenido");
		titulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
		getContentPane().add(titulo, "cell 0 0 2 1,alignx center");

		JLabel email = new JLabel("Id del contenido: ");
		email.setFont(ProjectConstants.font12P);
		getContentPane().add(email, "cell 0 1,alignx right");

		idField = new JTextField();
		idField.setFont(ProjectConstants.font12P);
		idField.setColumns(10);
		getContentPane().add(idField, "cell 1 1");

		RoundedButton eliminarButton = new RoundedButton("Eliminar");
		eliminarButton.setBackgroundBorder(new Color(145, 10, 10));
		eliminarButton.addActionListener(listener);
		getContentPane().add(eliminarButton, "cell 0 2 2 1,grow");

		setLocationRelativeTo(null);
		setVisible(true);
	}

	private ActionListener listener = (ActionEvent e) -> {
		Contenido c = null;

		try {
			long id = Long.valueOf(idField.getText());
			c = StaticApis.contenidoApi().getContenidoById(id);
		} catch (ContenidoNotFoundException|NumberFormatException e1) {
			OptionPanes.errorBlocking("No existe ningún contenido con id "+idField.getText());
			logger.warn("No existe contenido con id "+idField.getText(), e1);
			Utils.setNotValidJTextField(idField);
			return;
		} catch (Exception e1) {
			OptionPanes.error(e1.getMessage());
			logger.warn("Error en la eliminación del contenido",e1);
			Utils.setNotValidJTextField(idField);
			return;
		}

		try {
			StaticApis.contenidoApi().deleteContenido(c.getID());
			logger.info("Se ha eliminado el contenido {}",c.getTitulo() + ":"+c.getID());

			OptionPanes.info("Se ha eliminado el contenido " + c.getTitulo() + ":"+c.getID());

			this.setVisible(false);
			this.dispose();
		} catch (Exception e1) {
			OptionPanes.error(e1.getMessage());
			logger.warn("Error en la eliminación del contenido",e1);
		}	
	};
}