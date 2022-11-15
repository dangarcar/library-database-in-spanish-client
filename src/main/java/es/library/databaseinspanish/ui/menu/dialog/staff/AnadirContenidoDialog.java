package es.library.databaseinspanish.ui.menu.dialog.staff;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.TransferHandler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import es.library.databaseinspanish.api.utils.StaticApis;
import es.library.databaseinspanish.exceptions.contenido.IllegalContenidoException;
import es.library.databaseinspanish.exceptions.contenido.UnexpectedContenidoException;
import es.library.databaseinspanish.model.contenido.Contenido;
import es.library.databaseinspanish.model.contenido.Soporte;
import es.library.databaseinspanish.model.contenido.types.Audio;
import es.library.databaseinspanish.model.contenido.types.Libro;
import es.library.databaseinspanish.model.contenido.types.Video;
import es.library.databaseinspanish.ui.SwingApp;
import es.library.databaseinspanish.ui.utils.ImageUtils;
import es.library.databaseinspanish.ui.utils.OptionPanes;
import es.library.databaseinspanish.ui.utils.ProjectConstants;
import es.library.databaseinspanish.ui.utils.components.RoundedButton;
import net.miginfocom.swing.MigLayout;

public class AnadirContenidoDialog extends JDialog {

	private Logger logger = LogManager.getLogger();
	
	private JTextField textFieldTitulo;
	private JTextField textAutor;
	private JTextField textFieldIdioma;
	private JTextField textFieldISBN;
	private JSpinner spinnerAno;
	private JTextArea textAreaDescripcion;
	private JRadioButton rdBtnPrestable;
	private JComboBox<Soporte> comboBoxSoporte;
	private JComboBox<String> comboBoxTipo;
	private JSpinner spinnerPagina;
	private JSpinner spinnerDuracion;
	private JSpinner spinnerEdad;
	private JSpinner spinnerCalidad;
	private JTextField textFieldEditorial;
	private JTextField imageUrl;
	private JSpinner spinnerDiasPrestado;
	
	public AnadirContenidoDialog() {
		setResizable(false);
		setBounds(200,200,800,600);
		setTitle("Añadir contenido");
		setIconImage(Toolkit.getDefaultToolkit().getImage(SwingApp.class.getResource("/es/library/databaseinspanish/ui/images/contenidos.png")));
		getContentPane().setBackground(ProjectConstants.BACKGROUND_COLOR);
		getContentPane().setLayout(new MigLayout("", "[grow][][grow][][grow][][grow]", "[grow][grow][grow][grow][grow][grow][grow][grow][grow][grow]"));
		
		JLabel lblEnunciado = new JLabel("Añadir Contenido");
		lblEnunciado.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblEnunciado.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(lblEnunciado, "cell 0 0 7 1,grow");
		
		JLabel lblTitulo = new JLabel("Título:");
		lblTitulo.setFont(ProjectConstants.font12P);
		getContentPane().add(lblTitulo, "cell 0 1,alignx right,aligny center");
		
		textFieldTitulo = new JTextField();
		textFieldTitulo.setFont(ProjectConstants.font12P);
		getContentPane().add(textFieldTitulo, "cell 2 1,growx,aligny center");
		textFieldTitulo.setColumns(10);
		
		JLabel lblAutor = new JLabel("Autor:");
		lblAutor.setFont(ProjectConstants.font12P);
		getContentPane().add(lblAutor, "cell 0 2,alignx right,aligny center");
		
		textAutor = new JTextField();
		textAutor.setFont(ProjectConstants.font12P);
		textAutor.setColumns(10);
		getContentPane().add(textAutor, "cell 2 2,growx,aligny center");
		
		JPanel panelTipo = new JPanel();
		panelTipo.setBackground(ProjectConstants.BACKGROUND_COLOR);
		panelTipo.setLayout(new BorderLayout());
		getContentPane().add(panelTipo, "cell 4 2 3 3,grow");
		JLabel labelTipo = new JLabel("<html><body><font size=\"5\" style=\"Segoe UI\">Seleccione un tipo de contenido</font></body></html>");
		labelTipo.setHorizontalAlignment(SwingConstants.CENTER);
		panelTipo.add(labelTipo);
		labelTipo.setPreferredSize(new Dimension(0, 0));
		
		String[] tipos = {"","TEXTO","AUDIO","VÍDEO"};
		JLabel lblTipo = new JLabel("Tipo de contenido:");
		lblTipo.setFont(ProjectConstants.font12P);
		getContentPane().add(lblTipo, "cell 4 1,alignx right,aligny center");
		comboBoxTipo = new JComboBox<>(tipos);
		getContentPane().add(comboBoxTipo, "cell 6 1,growx,aligny center");
		comboBoxTipo.addItemListener((e) -> {
				panelTipo.removeAll();
				switch((String)comboBoxTipo.getSelectedItem()) {
					case "TEXTO":
						panelTipo.add(new LibroPanel(),BorderLayout.NORTH);
						break;
					case "AUDIO":
						panelTipo.add(new AudioPanel(),BorderLayout.NORTH);
						break;
					case "VÍDEO":
						panelTipo.add(new VideoPanel(),BorderLayout.NORTH);
						break;
					default:
						panelTipo.add(labelTipo);
				}
				panelTipo.updateUI();
		});
		
		JLabel lblAno = new JLabel("Año de publicación:");
		lblAno.setFont(ProjectConstants.font12P);
		getContentPane().add(lblAno, "cell 0 3,alignx right,aligny center");
		
		spinnerAno = new JSpinner();
		spinnerAno.setModel(new SpinnerNumberModel(2000,0,LocalDate.now().getYear(),1));
		getContentPane().add(spinnerAno, "cell 2 3,growx,aligny center");
		
		JLabel lblIdioma = new JLabel("Idioma:");
		lblIdioma.setFont(ProjectConstants.font12P);
		getContentPane().add(lblIdioma, "cell 0 4,alignx right,aligny center");
		
		textFieldIdioma = new JTextField();
		textFieldIdioma.setFont(ProjectConstants.font12P);
		textFieldIdioma.setColumns(10);
		getContentPane().add(textFieldIdioma, "cell 2 4,growx,aligny center");
		
		JLabel lblDescripcion = new JLabel("Descripción:");
		lblDescripcion.setFont(ProjectConstants.font12P);
		getContentPane().add(lblDescripcion, "cell 0 5,alignx right,aligny center");
		
		textAreaDescripcion = new JTextArea();
		textAreaDescripcion.setLineWrap(true);
		textAreaDescripcion.setWrapStyleWord(true);
		textAreaDescripcion.setRows(4);
		textAreaDescripcion.setFont(ProjectConstants.font12P);
		textAreaDescripcion.setBorder(null);
		
		JScrollPane scrollPane = new JScrollPane(textAreaDescripcion);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		getContentPane().add(scrollPane, "cell 2 5,growx");
		
		JPanel panelPrestamo = new JPanel();
		panelPrestamo.setBackground(ProjectConstants.BACKGROUND_COLOR);
		getContentPane().add(panelPrestamo, "cell 2 6,growx,aligny center");
		
		JLabel lblDiasPrestado = new JLabel("Días a ser prestado:");
		panelPrestamo.add(lblDiasPrestado);
		lblDiasPrestado.setPreferredSize(new Dimension(105, 20));
		lblDiasPrestado.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		
		spinnerDiasPrestado = new JSpinner(new SpinnerNumberModel(7, 0, 365, 1));
		panelPrestamo.add(spinnerDiasPrestado);
		spinnerDiasPrestado.setPreferredSize(new Dimension(50, 20));
		
		rdBtnPrestable = new JRadioButton("Prestable:");
		rdBtnPrestable.setBackground(ProjectConstants.BACKGROUND_COLOR);
		rdBtnPrestable.setFont(ProjectConstants.font12P);
		rdBtnPrestable.setActionCommand("Prestable");
		rdBtnPrestable.setSelected(true);
		rdBtnPrestable.addActionListener((e) -> panelPrestamo.setVisible(rdBtnPrestable.isSelected()));
		getContentPane().add(rdBtnPrestable, "flowx,cell 0 6,alignx right,aligny center");
		
		JLabel lblSoporte = new JLabel("Soporte:");
		lblSoporte.setFont(ProjectConstants.font12P);
		getContentPane().add(lblSoporte, "cell 0 7,alignx right,aligny center");
		
		comboBoxSoporte = new JComboBox<>(Soporte.values());
		getContentPane().add(comboBoxSoporte, "cell 2 7,growx,aligny center");
		
		RoundedButton button = new RoundedButton("Añadir");
		button.getBtnAnadir().addActionListener(anadirContenido);
		button.setBackgroundBorder(ProjectConstants.GREEN_COLOR);
		getContentPane().add(button, "cell 0 9 7 1,grow");
		
		JPanel panel = new JPanel();
		panel.setBackground(ProjectConstants.BACKGROUND_COLOR);	
		getContentPane().add(panel, "cell 4 5 3 3,grow");
		panel.setLayout(new MigLayout("", "[][grow]", "[grow][]"));
		
		JLabel image = new JLabel("<html><body><font size=\"5\" style=\"Segoe UI\">Arrastre una imagen aquí desde el navegador <br>o escriba una URL de imagen <br>en el apartado de URL de imagen</font></body></html>");
		image.setHorizontalTextPosition(SwingConstants.CENTER);
		panel.add(image, "cell 0 0 2 1,grow");
		
		JLabel lblDescripcion_1 = new JLabel("URL de imagen:  ");
		lblDescripcion_1.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		panel.add(lblDescripcion_1, "cell 0 1");
		
		imageUrl = new JTextField();
		imageUrl.setColumns(10);
		imageUrl.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				super.keyPressed(e);
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					try {
						image.setIcon(ImageUtils.getScaledIcon(new ImageIcon(new URL(imageUrl.getText())), 128, 128));
						image.setText("");
					} catch (Exception e1) {
						OptionPanes.warn("No se ha podido encontrar la imagen");
						image.setText("<html><body><font size=\"5\" style=\"Segoe UI\">Arrastre una imagen aquí desde el navegador <br>o escriba una URL de imagen <br>en el apartado de URL de imagen</font></body></html>");
					}
				}
			}		
		});
		panel.add(imageUrl, "cell 1 1,growx,aligny top");
		
		panel.setTransferHandler(new TransferHandler() {
			@Override
			public boolean importData(JComponent comp, Transferable t) {
				try {
					String url = (String) t.getTransferData(DataFlavor.stringFlavor);
					imageUrl.setText(url);				
					image.setIcon(ImageUtils.getScaledIcon(new ImageIcon(new URL(url)), 128, 128));
					image.setText("");
				} catch (Exception e) {
					OptionPanes.warn("No se ha podido encontrar la imagen");
					image.setText("<html><body><font size=\"5\" style=\"Segoe UI\">Arrastre una imagen aquí desde el navegador <br>o escriba una URL de imagen <br>en el apartado de URL de imagen</font></body></html>");
				}
				return true;
			}

			@Override
			public boolean canImport(JComponent comp, DataFlavor[] transferFlavors) { return true; }
		});
		
		setVisible(true);
	}

	private ActionListener anadirContenido = (ActionEvent e) -> {
		try {
			Contenido c = getContenido();
			StaticApis.contenidoApi().addContenido(c);
			logger.info("El contenido {} ha sido añadido correctamente", c);
			OptionPanes.info("El contenido " + c.getTitulo() + " ha sido añadido correctamente");
			
			this.setVisible(false);
			this.dispose();
		} catch (MalformedURLException e1) {			
			logger.warn("Error en la adición de contenidos", e1);
			OptionPanes.warn("La URL de la imagen no es correcta "+e1.getMessage());
		} catch (IllegalContenidoException e1) {			
			logger.warn("Error en la adición de contenidos", e1);
			OptionPanes.error("Contenido inválido: "+e1.getMessage());
		} catch (UnexpectedContenidoException e1) {
			logger.warn("Error en la adición de contenidos", e1);
			OptionPanes.error("Error en la adición de contenidos + "+e1.getMessage());
		}
	};
	
	private Contenido getContenido() throws MalformedURLException, IllegalContenidoException {
		Contenido c = switch ((String) comboBoxTipo.getSelectedItem()) {
		case "TEXTO" -> {
			var l = new Libro();

			l.setISBN(textFieldISBN.getText());

			l.setPaginas((Integer) spinnerPagina.getValue());

			l.setEditorial(textFieldEditorial.getText());

			yield l;
		}
		case "AUDIO" -> {
			var a = new Audio();

			a.setDuracion((Double) spinnerDuracion.getValue());

			yield a;
		}
		case "VÍDEO" -> {
			var v = new Video();

			v.setEdadRecomendada((Integer) spinnerEdad.getValue());

			v.setCalidad((Integer) spinnerCalidad.getValue());

			yield v;
		}
		default -> throw new IllegalContenidoException("Debe el elegir un tipo de contenido");
		};
		
		c.setTitulo(textFieldTitulo.getText());
		
		c.setAutor(textAutor.getText());
		
		c.setDescripcion(textAreaDescripcion.getText());
		
		c.setAno((Integer) spinnerAno.getValue());
		
		c.setIdioma(textFieldIdioma.getText());
		
		c.setSoporte((Soporte) comboBoxSoporte.getSelectedItem());
		
		c.setPrestable(rdBtnPrestable.isSelected());
		
		if(c.getPrestable()) c.setDiasDePrestamo((Integer) spinnerDiasPrestado.getValue());
		
		c.setDisponible(true);
		
		c.setImagen(imageUrl.getText().isBlank()? null:new URL(imageUrl.getText()));
		
		return c;
	}
	
	private class LibroPanel extends JPanel {
		public LibroPanel() {
			setBackground(ProjectConstants.BACKGROUND_COLOR);
			setLayout(new MigLayout("", "[grow][][grow]", "[grow][grow][grow]"));

			JLabel lblISBN = new JLabel("ISBN:");
			lblISBN.setFont(ProjectConstants.font12P);
			add(lblISBN, "cell 0 0,alignx right,aligny center");

			textFieldISBN = new JTextField();
			textFieldISBN.setFont(ProjectConstants.font12P);
			textFieldISBN.setColumns(10);
			add(textFieldISBN, "cell 2 0,growx,aligny center");

			JLabel lblPag = new JLabel("Páginas:");
			lblPag.setFont(ProjectConstants.font12P);
			add(lblPag, "cell 0 1,alignx right,aligny center");

			spinnerPagina = new JSpinner();
			spinnerPagina.setModel(new SpinnerNumberModel(100, 0, 5000, 1));
			add(spinnerPagina, "cell 2 1,growx,aligny center");

			JLabel lblEdit = new JLabel("Editorial:");
			lblEdit.setFont(ProjectConstants.font12P);
			add(lblEdit, "cell 0 2,alignx right,aligny center");

			textFieldEditorial = new JTextField();
			textFieldEditorial.setFont(ProjectConstants.font12P);
			textFieldEditorial.setColumns(10);
			add(textFieldEditorial, "cell 2 2,growx,aligny center");
		}
	}
	
	private class AudioPanel extends JPanel {
		public AudioPanel() {
			setBackground(ProjectConstants.BACKGROUND_COLOR);
			setLayout(new MigLayout("", "[grow][][grow]", "[][][]"));

			JLabel lblDur = new JLabel("Duración (en minutos):");
			lblDur.setFont(ProjectConstants.font12P);
			add(lblDur, "cell 0 0,alignx right,aligny center");

			spinnerDuracion = new JSpinner();
			spinnerDuracion.setModel(new SpinnerNumberModel(60, 0, Short.MAX_VALUE, 0.1));
			add(spinnerDuracion, "cell 2 0,growx,aligny center");
		}
	}
	
	private class VideoPanel extends AudioPanel {
		public VideoPanel() {
			JLabel lblEdad = new JLabel("Edad recomendada:");
			lblEdad.setFont(ProjectConstants.font12P);
			add(lblEdad, "cell 0 1,alignx right");

			spinnerEdad = new JSpinner();
			spinnerEdad.setModel(new SpinnerNumberModel(0, 0, 18, 1));
			add(spinnerEdad, "cell 2 1,growx");

			JLabel lblCal = new JLabel("Calidad(en píxeles):");
			lblCal.setFont(ProjectConstants.font12P);
			add(lblCal, "cell 0 2,alignx right");

			spinnerCalidad = new JSpinner();
			spinnerCalidad.setModel(new SpinnerNumberModel(0, 0, 2160, 12));
			add(spinnerCalidad, "cell 2 2,growx");
		}
	}

}