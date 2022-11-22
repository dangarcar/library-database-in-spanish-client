package es.library.databaseinspanish.ui.perfil;

import javax.swing.JButton;

public class PerfilPicture extends JButton {

	/*private Perfil perfil;
	
	public PerfilPicture(Perfil p, int width, int height, SwingApp app) {
		super(p.getNombre());
		setBorder(new LineBorder(Color.ORANGE, 5, true));
		setBorderPainted(false);
		perfil = p;
		setBackground(Color.WHITE);
		setHorizontalTextPosition(SwingConstants.CENTER);
		setVerticalTextPosition(SwingConstants.BOTTOM);
		setFont(new Font("Segoe UI", Font.PLAIN,12));
		this.setCursor(new Cursor(Cursor.HAND_CURSOR));
		this.setIcon(new ImageLabel(ImageUtils.getImagenFromPerfil(perfil.getNombre()),width,height - getFontMetrics(getFont()).getHeight()).getIcon());
		this.setHorizontalAlignment(CENTER);
		setPreferredSize(new Dimension(width, height));
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				setBorderPainted(true);
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				setBorderPainted(false);
			}
		});
		this.addActionListener((e) -> {
			new PerfilRendererController(app, perfil);
		});
	}
	*/
}
