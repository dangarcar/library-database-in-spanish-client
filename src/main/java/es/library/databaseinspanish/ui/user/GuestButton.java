package es.library.databaseinspanish.ui.user;

import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

import es.library.databaseinspanish.ui.SwingApp;
import es.library.databaseinspanish.ui.login.LoginWindow;
import es.library.databaseinspanish.ui.utils.ProjectConstants;
import es.library.databaseinspanish.ui.utils.Utils;
import es.library.databaseinspanish.ui.utils.components.ImageButton;

public class GuestButton extends ImageButton {
	
	public GuestButton(SwingApp parent) {
		super(new ImageIcon(GuestButton.class.getResource("/es/library/databaseinspanish/ui/images/guest.png")),232,232);
		setFont(ProjectConstants.font12P.deriveFont(Font.BOLD, 24f));
		setVerticalTextPosition(SwingConstants.BOTTOM);
		setHorizontalTextPosition(SwingConstants.CENTER);

		this.setText(Utils.getHtmlText("<span style=\"color:#027000\">Iniciar sesión</span>"));
		
		this.setBackground(ProjectConstants.BACKGROUND_COLOR);
		this.setBorder(null);

		this.addActionListener((e) -> {
			new LoginWindow(parent);
		});
	}

}
