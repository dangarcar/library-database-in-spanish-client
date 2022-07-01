package interfaz;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import static database.ContenidoSQL.*;
import static database.PerfilSQL.*;
import contenido.Contenido;
import contenido.excepciones.ExcepcionContenido;
import contenido.excepciones.ExcepcionDisponibilidad;
import perfiles.Admin;
import perfiles.Perfil;
import perfiles.excepciones.ExcepcionPerfil;

import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.GridBagConstraints;
import javax.swing.JTabbedPane;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextPane;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JList;

/**
 * Esta clase es la que da la descripción avanzada de cada perfil dando los detalles sobre los perfiles
 * @author Daniel García
 *
 */
class ExtendedGUIPerfil extends JPanel{
	private static final long serialVersionUID = 7322827919145869636L;
	private Perfil p;
	
	public ExtendedGUIPerfil(Perfil prf,List<? extends Perfil> listaD) {
		this.p = prf;
		setBackground(Color.yellow);
		setPreferredSize(new Dimension(450, 600));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{450, 0};
		gridBagLayout.rowHeights = new int[]{300, 44, 238, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblTitulo = new JLabel(p.getNombre()+" "+p.getApellido());
		lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblTitulo.setVerticalTextPosition(SwingConstants.TOP);
		lblTitulo.setHorizontalTextPosition(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 32));
		lblTitulo.setPreferredSize(new Dimension(0,250));
		lblTitulo.setIcon(new ImageIcon(((Perfil) p).getIcon().getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH)));
		GridBagConstraints gbc_lblTitulo = new GridBagConstraints();
		gbc_lblTitulo.insets = new Insets(0, 0, 5, 0);
		gbc_lblTitulo.gridx = 0;
		gbc_lblTitulo.gridy = 0;
		add(lblTitulo, gbc_lblTitulo);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		GridBagConstraints gbc_tabbedPane = new GridBagConstraints();
		gbc_tabbedPane.insets = new Insets(10, 10, 10, 10);
		gbc_tabbedPane.fill = GridBagConstraints.BOTH;
		gbc_tabbedPane.gridx = 0;
		gbc_tabbedPane.gridy = 2;
		add(tabbedPane, gbc_tabbedPane);
		
		JPanel panelDescripcion = new JPanel();
		panelDescripcion.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		tabbedPane.addTab("Descripcion", null, panelDescripcion, null);
		GridBagLayout gbl_panelDescripcion = new GridBagLayout();
		gbl_panelDescripcion.columnWidths = new int[]{425, 0};
		gbl_panelDescripcion.rowHeights = new int[] {175, 37, 0};
		gbl_panelDescripcion.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panelDescripcion.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		panelDescripcion.setLayout(gbl_panelDescripcion);
		
		JTextPane txtDescripcion = new JTextPane();
		GridBagConstraints gbc_txtDescripcion = new GridBagConstraints();
		gbc_txtDescripcion.fill = GridBagConstraints.BOTH;
		gbc_txtDescripcion.gridx = 0;
		gbc_txtDescripcion.gridy = 0;
		txtDescripcion.setBorder(new EmptyBorder(0,10,0,10));
		txtDescripcion.setFont(new Font("Segoe UI",Font.BOLD,14));
		txtDescripcion.setContentType("text/html");
		txtDescripcion.setText(getExtendedDescripcion());
		panelDescripcion.add(txtDescripcion, gbc_txtDescripcion);
		
		GridBagConstraints gbc_btnID = new GridBagConstraints();
		gbc_btnID.fill = GridBagConstraints.BOTH;
		gbc_btnID.gridx = 0;
		gbc_btnID.gridy = 1;
		panelDescripcion.add(new ButtonToAdminOrPerfil(p), gbc_btnID);
		
		JPanel panelContenidos = new JPanel();
		tabbedPane.addTab("Contenidos en pr\u00E9stamo", null, panelContenidos, null);
		GridBagLayout gbl_panelContenidos = new GridBagLayout();
		gbl_panelContenidos.columnWidths = new int[]{0, 0};
		gbl_panelContenidos.rowHeights = new int[]{173, 0, 0};
		gbl_panelContenidos.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panelContenidos.rowWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		panelContenidos.setLayout(gbl_panelContenidos);
		
		ArrayList<Contenido> contenidosEnPrestamo = new ArrayList<Contenido>();
		for(int i:p.getEnPrestamo()) {
			Contenido c = null;
			try {
				c = GetContenidoByID(i);
				contenidosEnPrestamo.add(c);
			} catch (ExcepcionContenido e1) {
				System.out.println(e1.getMessage());
			}
		}
		
		if(!contenidosEnPrestamo.isEmpty()) {
			JScrollPane scrollPaneEnPrestamo = new JScrollPane();
			GridBagConstraints gbc_scrollPaneEnPrestamo = new GridBagConstraints();
			gbc_scrollPaneEnPrestamo.insets = new Insets(0, 0, 5, 0);
			gbc_scrollPaneEnPrestamo.fill = GridBagConstraints.BOTH;
			gbc_scrollPaneEnPrestamo.gridx = 0;
			gbc_scrollPaneEnPrestamo.gridy = 0;
			panelContenidos.add(scrollPaneEnPrestamo, gbc_scrollPaneEnPrestamo);
			
			JList<Contenido> list = new ListaContenido(contenidosEnPrestamo,false);
			scrollPaneEnPrestamo.setViewportView(list);
		
			JButton btnDevolver = new JButton("Devolver");
			btnDevolver.setFont(new Font("Segoe UI", Font.PLAIN, 14));
			GridBagConstraints gbc_btnDevolver = new GridBagConstraints();
			gbc_btnDevolver.fill = GridBagConstraints.BOTH;
			gbc_btnDevolver.gridx = 0;
			gbc_btnDevolver.gridy = 1;
			btnDevolver.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					Contenido c = list.getSelectedValue();
					
					if(JOptionPane.showConfirmDialog(null, "¿Estás seguro de que quieres devolver el contenido seleccionado?", "Confirmación", JOptionPane.YES_NO_OPTION) == 0) {
						try {
							if(Devolver(c, p)) {
								list.remove(list.getSelectedIndex());
								JOptionPane.showMessageDialog(null, "Gracias por haber devuelto el contenido "+c.getID(),"Operación realizada correctamente",JOptionPane.INFORMATION_MESSAGE);
							} else {
								JOptionPane.showMessageDialog(null, "No se ha podido devolver el contenido", "Error", JOptionPane.ERROR_MESSAGE);
							}
						} catch (ExcepcionDisponibilidad | HeadlessException | ExcepcionPerfil e1) {
							JOptionPane.showMessageDialog(null, "No se ha podido devolver el contenido, el contenido no está disponible", "Error", JOptionPane.ERROR_MESSAGE);
						}
					}
					
				}
				
			});
			panelContenidos.add(btnDevolver, gbc_btnDevolver);
		} else {
			JLabel lblSeSiente = new JLabel("Este perfil no tiene contenidos actualmente en préstamo");
			lblSeSiente.setFont(new Font("Segoe UI",Font.PLAIN,14));
			lblSeSiente.setHorizontalAlignment(SwingConstants.CENTER);
			GridBagConstraints gbc_lblSeSiente = new GridBagConstraints();
			gbc_lblSeSiente.fill = GridBagConstraints.BOTH;
			gbc_lblSeSiente.gridx = 0;
			gbc_lblSeSiente.gridy = 0;
			panelContenidos.add(lblSeSiente, gbc_lblSeSiente);
		}
	}
	
	
	
	public String getExtendedDescripcion() {
		return "<html><body><p style = \"font-family:Segoe UI,Frutiger,Frutiger Linotype,Dejavu Sans,Helvetica Neue,Arial,sans-serif;\">"+
				"<b>Nombre: </b>"+p.getApellido()+", "+p.getNombre()+
				"<br><b>DNI: </b>"+p.getDNI()+p.getLetraDNI()+
				"<br><b>Fecha de nacimiento: </b>"+p.getFechaNacimiento().toString()+
				"<br><b>Correo Electrónico: </b>"+p.getCorreoElectronico()+
				"<br><b>Dirección: </b>"+p.getDireccionDeCasa()+
				"<br><b>Administrador: </b>"+((p instanceof Admin)? "Sí":"No")+
				"</p></body></html>";
	}
	
	public void copiarEnPortapapeles() {
		try {
			//Copia la ID en el portapapeles
			StringSelection ss = new StringSelection(((Integer)p.getID()).toString());
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
		} catch (HeadlessException e1) {
			JOptionPane.showMessageDialog(null, "No se ha podido copiar la ID en el portapapeles", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}

class ButtonToAdminOrPerfil extends JButton implements ActionListener{
	private static final long serialVersionUID = -1566180518423739800L;
	private Perfil p;
	
	public ButtonToAdminOrPerfil(Perfil p) {
		this.p = p;
		setFont(new Font("Segoe UI", Font.PLAIN, 14));
		setText("Convertir en administrador");
		if(p instanceof Admin) setText("Convertir en perfil normal");
		addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(p instanceof Admin) {
			try {
				if(JOptionPane.showConfirmDialog(null, "¿Desea conventir a "+p.getDNI()+" en perfil normal?", "Confirmación", JOptionPane.YES_NO_OPTION) == 0) {
					AdminToPerfil((Admin)p);
					JOptionPane.showMessageDialog(null, "Felicidades:"+p.getDNI()+" ahora es un perfil normal", "¡Felicidades!", JOptionPane.INFORMATION_MESSAGE);
				}
			} catch (ExcepcionPerfil e1) {
				JOptionPane.showMessageDialog(null, "Error: no se ha podido convertir a "+p.getDNI()+" en perfil normal", "Error", JOptionPane.ERROR_MESSAGE);
			}
		} else {
			try {
				if(JOptionPane.showConfirmDialog(null, "¿Desea conventir a "+p.getDNI()+" en administrador?", "Confirmación", JOptionPane.YES_NO_OPTION) == 0) {
					PerfilToAdmin(p);
					JOptionPane.showMessageDialog(null, "Felicidades:"+p.getDNI()+" ahora es administrador", "¡Felicidades!", JOptionPane.INFORMATION_MESSAGE);
				}
			} catch (ExcepcionPerfil e1) {
				JOptionPane.showMessageDialog(null, "Error: no se ha podido convertir a "+p.getDNI()+" en administrador", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
}