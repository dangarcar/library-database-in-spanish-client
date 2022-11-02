package es.library.databaseinspanish.ui.user;

import es.library.databaseinspanish.ui.SwingApp;
import es.library.databaseinspanish.ui.utils.ImageButton;
import es.library.databaseinspanish.ui.utils.ProjectConstants;
import es.library.databaseinspanish.utils.ImageUtils;

public class UserButton extends ImageButton {
	
	public UserButton(SwingApp parent) {
		super(ImageUtils.getImagenFromPerfil(parent.getUserLoggenIn().getNombre()));
		
		this.setBackground(ProjectConstants.BACKGROUND_COLOR);
		this.setBorder(null);
		
		this.addActionListener((e) -> {
			new UserPanelController(parent);
		});
	}
	
}
