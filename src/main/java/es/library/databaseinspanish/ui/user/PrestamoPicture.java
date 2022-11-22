package es.library.databaseinspanish.ui.user;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.time.ZonedDateTime;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import es.library.databaseinspanish.model.prestamo.PrestamoContenidoModel;
import es.library.databaseinspanish.ui.utils.ImageUtils;
import es.library.databaseinspanish.ui.utils.ProjectConstants;
import es.library.databaseinspanish.ui.utils.Utils;
import es.library.databaseinspanish.ui.utils.components.ImageLabel;

class PrestamoPicture extends JPanel {
	
	private PrestamoContenidoModel prestamoContenidoModel;	
	private JButton devolverButton;
	
	public PrestamoPicture(PrestamoContenidoModel c, int width, int height) {
		this.prestamoContenidoModel = c;
		
		this.setBackground(ProjectConstants.BACKGROUND_COLOR);
		this.setSize(new Dimension(width,height));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWeights = new double[]{0.0, 0.0};
		gridBagLayout.rowWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0};
		setLayout(gridBagLayout);
		
		JLabel imageLabel = new ImageLabel(ImageUtils.getImagenFromContenido(prestamoContenidoModel.getContenido()), height, height);
		GridBagConstraints gbc_imageLabel = new GridBagConstraints();
		gbc_imageLabel.gridheight = 5;
		gbc_imageLabel.insets = new Insets(0, 0, 0, 5);
		gbc_imageLabel.gridx = 0;
		gbc_imageLabel.gridy = 0;
		add(imageLabel, gbc_imageLabel);
		
		JLabel tituloLabel = new JLabel(prestamoContenidoModel.getContenido().getTitulo());
		tituloLabel.setFont(ProjectConstants.font12P.deriveFont(Font.BOLD,16f));
		GridBagConstraints gbc_tituloLabel = new GridBagConstraints();
		gbc_tituloLabel.fill = GridBagConstraints.BOTH;
		gbc_tituloLabel.insets = new Insets(0, 0, 5, 0);
		gbc_tituloLabel.gridx = 1;
		gbc_tituloLabel.gridy = 0;
		add(tituloLabel, gbc_tituloLabel);
		
		JLabel autorLabel = new JLabel(prestamoContenidoModel.getContenido().getAutor());
		autorLabel.setFont(ProjectConstants.font12P.deriveFont(Font.BOLD,14f));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.fill = GridBagConstraints.BOTH;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 1;
		add(autorLabel, gbc_lblNewLabel);
		
		JLabel caducaLabel = new JLabel();
		caducaLabel.setFont(ProjectConstants.font12P);
		caducaLabel.setText(Utils.getHtmlText("Caduca",ProjectConstants.ZONED_DATE_TIME_FORMATTER.format(prestamoContenidoModel.getPrestamo().getFechaHoraPrestamo().plusDays(prestamoContenidoModel.getPrestamo().getDiasdePrestamo()))));
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_1.fill = GridBagConstraints.BOTH;
		gbc_lblNewLabel_1.gridx = 1;
		gbc_lblNewLabel_1.gridy = 2;
		add(caducaLabel, gbc_lblNewLabel_1);
		
		
		long restante = prestamoContenidoModel.getPrestamo().getFechaHoraPrestamo().plusDays(prestamoContenidoModel.getPrestamo().getDiasdePrestamo()).toEpochSecond() - ZonedDateTime.now().toEpochSecond();
		int day = (int) TimeUnit.SECONDS.toDays(restante);
		int hour = (int) (TimeUnit.SECONDS.toHours(restante) - day * TimeUnit.DAYS.toHours(1));
		JLabel restanteLabel = new JLabel();
		restanteLabel.setFont(ProjectConstants.font12P);
		restanteLabel.setText(Utils.getHtmlText("Restante", day + " días - " + hour + " horas"));
		GridBagConstraints gbc_restanteLabel;
		gbc_restanteLabel = new GridBagConstraints();
		gbc_restanteLabel.fill = GridBagConstraints.BOTH;
		gbc_restanteLabel.insets = new Insets(0, 0, 5, 0);
		gbc_restanteLabel.gridx = 1;
		gbc_restanteLabel.gridy = 3;
		add(restanteLabel, gbc_restanteLabel);
		
		devolverButton = new JButton(" DEVOLVER ");
		devolverButton.setOpaque(false);
		devolverButton.setContentAreaFilled(false);
		devolverButton.setBorder(new LineBorder(new Color(0, 0, 0)));
		devolverButton.setBackground(Color.GREEN);
		devolverButton.setFont(ProjectConstants.font12P.deriveFont(Font.BOLD));
		GridBagConstraints gbc_devolverButton = new GridBagConstraints();
		gbc_devolverButton.gridx = 1;
		gbc_devolverButton.gridy = 4;
		add(devolverButton, gbc_devolverButton);
	}
	
	public JButton getDevolverButton() {
		return devolverButton;
	}
	
	public PrestamoContenidoModel getPrestamoContenidoModel() {
		return prestamoContenidoModel;
	}
	
}
