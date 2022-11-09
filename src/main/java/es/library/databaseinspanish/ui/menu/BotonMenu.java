package es.library.databaseinspanish.ui.menu;

import javax.swing.ImageIcon;

import es.library.databaseinspanish.ui.SwingApp;
import es.library.databaseinspanish.ui.utils.ImageButton;
import es.library.databaseinspanish.ui.utils.ProjectConstants;

public class BotonMenu extends ImageButton {

	private Menu menu;
	
	public BotonMenu(SwingApp parent) {
		super(new ImageIcon(BotonMenu.class.getResource("/es/library/databaseinspanish/ui/images/menu.png")),32,32);
		
		setBorder(null);
		setBackground(ProjectConstants.BACKGROUND_COLOR);
		
		menu = new Menu(parent);
		menu.pack();
		this.addActionListener((e) -> {	
			menu.show(parent.getContentPane(), parent.getContentPane().getWidth() - menu.getPreferredSize().width , 0);
		});
	}
	
	public Menu getMenu() {
		return menu;
	}
	
}
