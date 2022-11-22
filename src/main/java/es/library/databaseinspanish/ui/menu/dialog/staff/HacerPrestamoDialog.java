package es.library.databaseinspanish.ui.menu.dialog.staff;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import es.library.databaseinspanish.api.utils.StaticApis;
import es.library.databaseinspanish.exceptions.contenido.ContenidoNotFoundException;
import es.library.databaseinspanish.exceptions.perfil.PerfilNotFoundException;
import es.library.databaseinspanish.exceptions.perfil.UnexpectedPerfilException;
import es.library.databaseinspanish.exceptions.prestamo.PrestamoNotAllowedException;
import es.library.databaseinspanish.exceptions.prestamo.UnexpectedPrestamoException;
import es.library.databaseinspanish.model.prestamo.Prestamo;
import es.library.databaseinspanish.ui.utils.OptionPanes;
import es.library.databaseinspanish.ui.utils.ProjectConstants;
import es.library.databaseinspanish.ui.utils.Utils;
import es.library.databaseinspanish.ui.utils.components.RoundedButton;
import net.miginfocom.swing.MigLayout;

public class HacerPrestamoDialog extends JDialog {
	
	private Logger logger = LogManager.getLogger();
	
	private JTextField idContenidoField;
	private JTextField emailField;
	
	public HacerPrestamoDialog() {
		setResizable(false);
		setBounds(200,200,392,256);
		setTitle("Prestar contenido");
		setIconImage(Toolkit.getDefaultToolkit().getImage(HacerPrestamoDialog.class.getResource("/es/library/databaseinspanish/ui/images/contenidos.png")));
		getContentPane().setBackground(ProjectConstants.BACKGROUND_COLOR);
		getContentPane().setLayout(new MigLayout("", "[grow][grow]", "[][grow][grow][]"));

		JLabel titulo = new JLabel("Prestar contenido");
		titulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
		getContentPane().add(titulo, "cell 0 0 2 1,alignx center");

		JLabel contenido = new JLabel("Id del contenido: ");
		contenido.setFont(ProjectConstants.font12P);
		getContentPane().add(contenido, "cell 0 1,alignx right");

		idContenidoField = new JTextField();
		idContenidoField.setFont(ProjectConstants.font12P);
		idContenidoField.setColumns(10);
		getContentPane().add(idContenidoField, "cell 1 1");
		
		JLabel perfil = new JLabel("Email del perfil: ");
		perfil.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		getContentPane().add(perfil, "cell 0 2,alignx trailing");
		
		emailField = new JTextField();
		emailField.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		emailField.setColumns(10);
		getContentPane().add(emailField, "cell 1 2");
		
		RoundedButton prestarButton = new RoundedButton("Prestar");
		prestarButton.getBtnAnadir().addActionListener(prestarListener);
		prestarButton.setBackgroundBorder(ProjectConstants.GREEN_COLOR);
		getContentPane().add(prestarButton, "cell 0 3 2 1,grow");

		setLocationRelativeTo(null);
		setVisible(true);
	}

	private ActionListener prestarListener = (ActionEvent e) -> {
		try {
			Long idContenido = Long.valueOf(idContenidoField.getText());
			String email = emailField.getText();

			Utils.checkEmail(email, emailField);
			
			int opt = JOptionPane.showConfirmDialog(null, "<html><b>¿Son correctos estos datos?</b>:<br>&emsp;Id del contenido: "+idContenido+"<br>&emsp;Email del perfil: "+email+"</html>", "", JOptionPane.YES_NO_OPTION);
			
			if(opt != 0) return;

			Prestamo p = StaticApis.prestamoApi().prestar(idContenido, StaticApis.perfilApi().getPerfilByUsername(email).getID());

			logger.info("Se ha realizado el préstamo {} correctamente", p);
			OptionPanes.info("El perfil "+email+" ha cogido prestado el contenido "+idContenido+" hasta el "+ProjectConstants.ZONED_DATE_TIME_FORMATTER.format(p.getFechaHoraPrestamo().plusDays(p.getDiasdePrestamo())));

			this.setVisible(false);
			this.dispose();
		} catch (ContenidoNotFoundException|PerfilNotFoundException e1) {
			OptionPanes.error("No encontrado "+e1.getMessage());
			logger.warn("Error al realizar el préstamo", e1);
		} catch (PrestamoNotAllowedException e1) {
			OptionPanes.error("Error al realizar el préstamo "+e1.getMessage());
			logger.warn("Error al realizar el préstamo", e1);
		} catch (UnexpectedPrestamoException|UnexpectedPerfilException e1) {
			OptionPanes.error("Error al realizar el préstamo "+e1.getMessage());
			logger.warn("Error al realizar el préstamo", e1);
		} catch (NumberFormatException e1) {
			OptionPanes.warn("Error al leer la id del contenido");
			idContenidoField.setText(null);
		}
		
	};
	
}