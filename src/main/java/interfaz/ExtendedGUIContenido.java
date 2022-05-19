package interfaz;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import contenido.Audio;
import contenido.Contenido;
import contenido.Libros;
import contenido.Videos;

public class ExtendedGUIContenido extends JPanel{
	private static final long serialVersionUID = 7322827919145869636L;
	private Contenido c;
	private List<? extends Contenido> listaD;
	
	public ExtendedGUIContenido(Contenido cnt,List<? extends Contenido> listaC) {
		this.c = cnt;
		this.listaD = listaC;
		
		setBackground(Color.yellow);
		setPreferredSize(new Dimension(450, 600));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{86, 0};
		gridBagLayout.rowHeights = new int[]{46, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblTitulo = new JLabel(c.getTitulo());
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblTitulo.setVerticalTextPosition(SwingConstants.TOP);
		lblTitulo.setHorizontalTextPosition(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 32));
		lblTitulo.setPreferredSize(new Dimension(450,250));
		lblTitulo.setIcon(new ImageIcon(((Contenido) c).getSoporte().getIcon().getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH)));
		GridBagConstraints gbc_lblTitulo = new GridBagConstraints();
		gbc_lblTitulo.insets = new Insets(0, 0, 5, 0);
		gbc_lblTitulo.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblTitulo.gridx = 0;
		gbc_lblTitulo.gridy = 0;
		add(lblTitulo, gbc_lblTitulo);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		tabbedPane.setPreferredSize(new Dimension(0, 100));
		GridBagConstraints gbc_tabbedPane = new GridBagConstraints();
		gbc_tabbedPane.fill = GridBagConstraints.BOTH;
		gbc_tabbedPane.insets = new Insets(10, 10, 10, 10);
		gbc_tabbedPane.gridx = 0;
		gbc_tabbedPane.gridy = 2;
		
		
		add(tabbedPane, gbc_tabbedPane);
		
		JPanel prestamoTab = new JPanel();
		tabbedPane.addTab("Préstamos", prestamoTab);
		GridBagLayout gbl_prestamoTab = new GridBagLayout();
		gbl_prestamoTab.columnWidths = new int[]{419, 0};
		gbl_prestamoTab.rowHeights = new int[]{40, 191, 44, 0};
		gbl_prestamoTab.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_prestamoTab.rowWeights = new double[]{0.0, 1.0, 1.0, Double.MIN_VALUE};
		prestamoTab.setLayout(gbl_prestamoTab);
		
		JLabel lblPrestamo = new JLabel("Pr\u00E9stamos y ejemplares disponibles");
		lblPrestamo.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblPrestamo.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblPrestamo = new GridBagConstraints();
		gbc_lblPrestamo.fill = GridBagConstraints.BOTH;
		gbc_lblPrestamo.insets = new Insets(5, 5, 5, 0);
		gbc_lblPrestamo.gridx = 0;
		gbc_lblPrestamo.gridy = 0;
		prestamoTab.add(lblPrestamo, gbc_lblPrestamo);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 1;
		prestamoTab.add(scrollPane, gbc_scrollPane);
		
		JList<Contenido> list = new ListaContenido(listaD,true);
		scrollPane.setViewportView(list);
		
		JButton btnPrestar = new JButton("Prestar");
		btnPrestar.setFont(new Font("Segoe UI", Font.BOLD, 14));
		GridBagConstraints gbc_btnPrestar = new GridBagConstraints();
		gbc_btnPrestar.fill = GridBagConstraints.BOTH;
		gbc_btnPrestar.gridx = 0;
		gbc_btnPrestar.gridy = 2;
		btnPrestar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Contenido co = list.getSelectedValue();
				
				if (co ==  null)	{
					JOptionPane.showMessageDialog(null, "No ha elegido ningún contenido para prestar", "Error", JOptionPane.WARNING_MESSAGE, new ImageIcon("files/images/error.png"));
					return;
				}
			
				if(!co.getDisponibilidad()) { 
					JOptionPane.showMessageDialog(null, "<html><body><p>El contenido que ha elegido no está disponible.<br>Volverá a estar disponible el "+co.getFechaDisponibilidad().toString()+"</p></body></html>", 
							"Error", JOptionPane.WARNING_MESSAGE, new ImageIcon("files/images/error.png"));
					return;
				}
				
				if(!co.getPrestable()) {
					JOptionPane.showMessageDialog(null, "El contenido que ha elegido no es prestable, solo se puede consultar en la propia biblioteca", 
							"Error", JOptionPane.WARNING_MESSAGE, new ImageIcon("files/images/error.png"));
					return;
				}
				
				PrestarContenidoGUI ventana = new PrestarContenidoGUI(co);
				ventana.setVisible(true);
			}
			
		});
		prestamoTab.add(btnPrestar, gbc_btnPrestar);

		JPanel descripcionTab = new JPanel();
		tabbedPane.addTab("Descripción", descripcionTab);
		GridBagLayout gbl_descripcionTab = new GridBagLayout();
		gbl_descripcionTab.columnWidths = new int[]{0, 0};
		gbl_descripcionTab.rowHeights = new int[]{0, 0};
		gbl_descripcionTab.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_descripcionTab.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		descripcionTab.setLayout(gbl_descripcionTab);
		
		JTextPane txtDescripcion = new JTextPane();
		GridBagConstraints gbc_txtDescripcion = new GridBagConstraints();
		gbc_txtDescripcion.fill = GridBagConstraints.BOTH;
		gbc_txtDescripcion.gridx = 0;
		gbc_txtDescripcion.gridy = 0;
		txtDescripcion.setBorder(new EmptyBorder(0,10,0,10));
		txtDescripcion.setFont(new Font("Segoe UI",Font.BOLD,14));
		txtDescripcion.setContentType("text/html");
		txtDescripcion.setText(getExtendedDescripcion());
		descripcionTab.add(txtDescripcion, gbc_txtDescripcion);
	}
	
	public String getExtendedDescripcion() {
		return "<html><body><p style = \"font-family:Segoe UI,Frutiger,Frutiger Linotype,Dejavu Sans,Helvetica Neue,Arial,sans-serif;\">"+
				"<b>Autor: </b>"+c.getAutor()+
				"<br><b>Título: </b>"+c.getTitulo()+
				"<br><b>Año: </b>"+c.getAno()+
				"<br><b>Idioma: </b>"+c.getIdioma()+
				"<br><b>Soporte: </b>"+c.getSoporte()+
				((c instanceof Libros)? 
					"<br><b>ISBN: </b>"+((Libros)c).getISBN()+
					"<br><b>Páginas: </b>"+((Libros)c).getPaginas()+
					"<br><b>Editorial: </b>"+((Libros)c).getEditorial() 
				:"")+
				((c instanceof Audio)?
					"<br><b>Duración: </b>"+((Audio)c).getDuracion()+" minutos"+
					((c instanceof Videos)?
						"<br><b>Edad Recomendada: </b>"+((Videos)c).getEdadRecomendada()+" años"+
						"<br><b>Calidad: </b>"+((Videos)c).getCalidad()+" píxeles"
					:"")
				:"")+
				"<br><b>Descripción: </b>"+c.getDescripcion()+
				"</p></body></html>";
	}
	
}