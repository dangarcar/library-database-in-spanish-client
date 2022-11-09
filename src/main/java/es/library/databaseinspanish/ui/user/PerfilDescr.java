package es.library.databaseinspanish.ui.user;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

import es.library.databaseinspanish.model.perfil.Perfil;
import es.library.databaseinspanish.model.perfil.Roles;
import es.library.databaseinspanish.ui.utils.ImageLabel;
import es.library.databaseinspanish.ui.utils.ProjectConstants;
import es.library.databaseinspanish.utils.ImageUtils;
import es.library.databaseinspanish.utils.Utils;
import net.miginfocom.swing.MigLayout;

public class PerfilDescr extends JPanel {

	public PerfilDescr(Perfil perfil) {
		setLayout(new MigLayout("", "[][20px][]", "[][][][][]"));
		
		JLabel roleImagenLabel = new ImageLabel(ImageUtils.getImagenFromRole(perfil.getRole()),64,64);
		add(roleImagenLabel, "cell 0 4,alignx right,aligny bottom");
		
		JLabel imagenPerfil = new ImageLabel(ImageUtils.getImagenFromPerfil(perfil.getNombre()),162,162);
		add(imagenPerfil, "flowx,cell 0 0 1 5,alignx left,aligny top");
		
		JLabel nombreLabel = new JLabel();
		nombreLabel.setText(perfil.getNombre());
		nombreLabel.setFont(ProjectConstants.font12P.deriveFont(Font.BOLD,24f));
		add(nombreLabel, "cell 2 0");
		
		JLabel emailLabel = new JLabel();
		emailLabel.setText(Utils.getHtmlText("Email", perfil.getCorreoElectronico()));
		emailLabel.setFont(ProjectConstants.font12P.deriveFont(Font.PLAIN,16f));
		add(emailLabel, "cell 2 1");
		
		JLabel fechaNacimientoLabel = new JLabel();
		fechaNacimientoLabel.setFont(ProjectConstants.font12P.deriveFont(Font.PLAIN,16f));
		fechaNacimientoLabel.setText(Utils.getHtmlText("Fecha de nacimiento",ProjectConstants.LOCAL_DATE_FORMATTER.format(perfil.getFechaNacimiento())));
		add(fechaNacimientoLabel, "cell 2 2");
		
		JLabel roleLabel = new JLabel();
		roleLabel.setFont(ProjectConstants.font12P.deriveFont(Font.PLAIN,16f));
		roleLabel.setText(Utils.getHtmlText("Rol",Utils.getFormattedStringFromRole(perfil.getRole())));
		if(perfil.getRole() != Roles.ROLE_USER) add(roleLabel, "cell 2 3");
	}
	
}
