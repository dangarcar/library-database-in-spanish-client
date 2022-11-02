package es.library.databaseinspanish.ui.user;

import javax.swing.ImageIcon;

import es.library.databaseinspanish.ui.SwingApp;
import es.library.databaseinspanish.ui.login.LoginWindow;
import es.library.databaseinspanish.ui.utils.ImageButton;
import es.library.databaseinspanish.ui.utils.ProjectConstants;
import javax.swing.SwingConstants;
import java.awt.Font;

public class GuestButton extends ImageButton {
	
	public GuestButton(SwingApp parent) {
		super(new ImageIcon(GuestButton.class.getResource("/es/library/databaseinspanish/ui/images/guest.png")),232,232);
		setFont(ProjectConstants.font12P.deriveFont(Font.BOLD, 24f));
		setVerticalTextPosition(SwingConstants.BOTTOM);
		setHorizontalTextPosition(SwingConstants.CENTER);

		this.setText("Iniciar sesión");
		
		this.setBackground(ProjectConstants.BACKGROUND_COLOR);
		this.setBorder(null);

		this.addActionListener((e) -> {
			new LoginWindow(parent);
		});
	}

}
