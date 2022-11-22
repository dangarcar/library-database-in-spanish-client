package es.library.databaseinspanish.ui.contenido;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import es.library.databaseinspanish.model.contenido.Contenido;
import es.library.databaseinspanish.model.contenido.modeltypes.AudioModel;
import es.library.databaseinspanish.model.contenido.modeltypes.ContenidoModel;
import es.library.databaseinspanish.model.contenido.modeltypes.LibroModel;
import es.library.databaseinspanish.model.contenido.modeltypes.VideoModel;
import es.library.databaseinspanish.ui.SwingApp;
import es.library.databaseinspanish.ui.utils.ImageUtils;
import es.library.databaseinspanish.ui.utils.ProjectConstants;
import es.library.databaseinspanish.ui.utils.Utils;
import es.library.databaseinspanish.ui.utils.components.BotonRetroceso;
import es.library.databaseinspanish.ui.utils.components.ImageLabel;
import net.miginfocom.swing.MigLayout;

public class ContenidoRenderer extends JPanel {

	private SwingApp app;	
	private ContenidoModel model;
	private BotonRetroceso retroceso;
	
	ContenidoRenderer(SwingApp app, ContenidoModel model) {
		super();
		this.app = app;
		this.model = model;
		
		this.setBackground(ProjectConstants.BACKGROUND_COLOR);
		this.setLayout(new MigLayout("", "[][][][][grow]", "[][][][][][growprio 75][][][grow]"));
		
		retroceso = new BotonRetroceso(app);
		add(retroceso, "cell 0 0");
		
		ImageLabel imagen = new ImageLabel(ImageUtils.getImagenFromContenido(model.toContenido()),300,300);
		imagen.setPreferredSize(new Dimension(300,300));
		add(imagen, "cell 1 1 1 7");
		
		JLabel titulo = new JLabel(model.getTitulo());
		titulo.setForeground(Color.BLACK);
		titulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
		add(titulo, "cell 3 1");
		
		JLabel autor = new JLabel(model.getAutor());
		autor.setForeground(Color.DARK_GRAY);
		autor.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		add(autor, "cell 3 2");
		
		JLabel descripcion = new JLabel(Utils.getHtmlText(model.getDescripcion()));
		descripcion.setFont(ProjectConstants.font12P);
		add(descripcion, "cell 3 4");
		
		JSeparator separator = new JSeparator();
		separator.setBackground(new Color(0, 0, 128));
		separator.setPreferredSize(new Dimension(12, 10));
		separator.setForeground(new Color(0, 0, 128));
		add(separator, "cell 3 5,growx,alignx center");
		
		this.extraInfoPanel = new ExtraInfoPanel();
		add(extraInfoPanel, "cell 3 6 1 2,grow");
		
		this.copiasPanel = new CopiasPanel();
		add(copiasPanel, "cell 1 8,grow");
		
		this.prestarPanel = new PrestarPanel();
		add(prestarPanel, "cell 4 1 1 7,alignx center,growy");
	}
	
	public JButton getBotonPrestar() {
		return prestarPanel.getBotonPrestar();
	}
	
	public JList<Contenido> getSelectionList(){
		return prestarPanel.getSelectionList();
	}
	
	public BotonRetroceso getRetroceso() {
		return retroceso;
	}
	
	private PrestarPanel prestarPanel;
	private ExtraInfoPanel extraInfoPanel;
	private CopiasPanel copiasPanel;
	
	class PrestarPanel extends JPanel {
		
		private JButton botonPrestar;
		private JList<Contenido> list;
		
		public PrestarPanel() {
			super();
			
			if(app.isGuest()) {
				setOpaque(false);
				return;	
			}
			
			setBorder(null);
			setBackground(ProjectConstants.BACKGROUND_COLOR);
			setLayout(new MigLayout("", "[272px]", "[][29px,grow][]"));
			
			JLabel lblPrestamo = new JLabel("Préstamos y ejemplares disponibles");
			lblPrestamo.setFont(new Font("Segoe UI", Font.BOLD, 16));
			lblPrestamo.setHorizontalAlignment(SwingConstants.CENTER);
			add(lblPrestamo, "cell 0 0,alignx center,aligny center");
			
			JScrollPane scrollPane = new JScrollPane();
			add(scrollPane, "cell 0 1,grow");
			
			list = new ListaContenido(model.getContenidos());
			scrollPane.setViewportView(list);
			
			botonPrestar = new JButton("Prestar");
			botonPrestar.setFont(new Font("Segoe UI", Font.BOLD, 14));
			add(botonPrestar, "cell 0 2,alignx center,aligny top");
		}
		
		public JList<Contenido> getSelectionList(){
			return list;
		}
		
		public JButton getBotonPrestar() {
			return botonPrestar;
		}
		
		
	}
	
	class ExtraInfoPanel extends JPanel {
		
		public ExtraInfoPanel() {
			this.setBackground(ProjectConstants.BACKGROUND_COLOR);
			this.setLayout(new MigLayout("", "[grow][grow][grow]", "[grow][grow]"));
			
			JLabel ano = new JLabel(Utils.getHtmlTextWithBr("Año", model.getAno()));
			ano.setFont(new Font("Segoe UI", Font.PLAIN, 14));
			this.add(ano, "cell 0 0");
			
			JLabel idioma = new JLabel(Utils.getHtmlTextWithBr("Idioma",model.getIdioma()));
			this.add(idioma, "cell 1 0");
			idioma.setFont(new Font("Segoe UI", Font.PLAIN, 14));
			
			JLabel soporte = new JLabel(Utils.getHtmlTextWithBr("Soporte",model.getSoporte()));
			this.add(soporte, "cell 2 0");
			soporte.setFont(new Font("Segoe UI", Font.PLAIN, 14));
			
			setSpecificInfo();
		}
		
		private void setSpecificInfo() {
			JLabel spec_1 = new JLabel();
			spec_1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
			this.add(spec_1, "cell 0 1");
			
			JLabel spec_2 = new JLabel();
			spec_2.setFont(new Font("Segoe UI", Font.PLAIN, 14));
			this.add(spec_2, "cell 1 1");
			
			JLabel spec_3 = new JLabel();
			spec_3.setFont(new Font("Segoe UI", Font.PLAIN, 14));
			this.add(spec_3, "cell 2 1");
			
			if(model instanceof VideoModel m) {
				spec_1.setText(Utils.getHtmlTextWithBr("Duración",m.getDuracion()));
				spec_2.setText(Utils.getHtmlTextWithBr("Calificación de edad",m.getEdadRecomendada()));
				spec_3.setText(Utils.getHtmlTextWithBr("Calidad (en píxeles)",m.getCalidad()));
			}
			else if(model instanceof LibroModel m) {
				spec_1.setText(Utils.getHtmlTextWithBr("ISBN",m.getISBN()));
				spec_2.setText(Utils.getHtmlTextWithBr("Páginas",m.getPaginas()));
				spec_3.setText(Utils.getHtmlTextWithBr("Editorial",m.getEditorial()));
			}
			else if(model instanceof AudioModel m) {
				spec_1.setText(Utils.getHtmlTextWithBr("Duración",m.getDuracion()));		
			}
		}
		
	}
	
	class CopiasPanel extends JPanel {
		
		public CopiasPanel() {
			this.setBorder(null);
			this.setBackground(ProjectConstants.BACKGROUND_COLOR);
			this.setLayout(new MigLayout("", "[grow][grow]", "[]"));
			
			JLabel copiasTotal = new JLabel(Utils.getHtmlTextWithBr("Total copias",model.getIds().size()));
			copiasTotal.setFont(ProjectConstants.font12P);
			this.add(copiasTotal, "cell 0 0");
			
			JLabel copiasDisponible = new JLabel(Utils.getHtmlTextWithBr("Copias disponibles",model.getContenidos().stream().filter(c -> c.getDisponible()).count()));
			copiasDisponible.setFont(ProjectConstants.font12P);
			this.add(copiasDisponible, "cell 1 0");
		}
	}
	
}