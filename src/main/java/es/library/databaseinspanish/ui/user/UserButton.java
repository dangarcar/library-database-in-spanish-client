package es.library.databaseinspanish.ui.user;

import es.library.databaseinspanish.ui.SwingApp;
import es.library.databaseinspanish.ui.utils.ImageButton;
import es.library.databaseinspanish.ui.utils.ProjectConstants;
import es.library.databaseinspanish.utils.Utils;

public class UserButton extends ImageButton {

	SwingApp parent;
	
	public UserButton(SwingApp parent) {
		super(Utils.getImageFromPerfil(parent.getUserLoggenIn().getNombre()));
		
		this.setBackground(ProjectConstants.BACKGROUND_COLOR);
		this.setBorder(null);
		
		this.addActionListener((e) -> {
				UserPanel userPanel = new UserPanel(parent);
				userPanel.setName("userPanel");
				parent.changePanel(userPanel);
		});
	}
	
}
