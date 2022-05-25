package interfaz;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import contenido.Audio;
import contenido.Contenido;
import contenido.Libros;
import contenido.Videos;
import contenido.excepciones.ExcepcionContenido;
import perfiles.Perfil;
import perfiles.excepciones.ExcepcionDNIPerfil;
import perfiles.excepciones.ExcepcionPerfil;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import SQL.ContenidoSQL;
import SQL.PerfilSQL;

import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.Dimension;
import javax.swing.JEditorPane;

/**
 * Esta clase sirve es la nterfaz para coger prestado un libro pasado por parámetro
 * @author danie
 *
 */
public class PrestarContenidoGUI extends JFrame{
	private static final long serialVersionUID = 5166313481514388343L;
	private Contenido c;
	private JTextField textField;
	
	
	public PrestarContenidoGUI(Contenido c) {
		this.c = c;
		setBounds(200,200,400,500);
		
		setTitle("Prestar contenido");
		setIconImage(Toolkit.getDefaultToolkit().getImage("files\\images\\contenidos.png"));
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.controlHighlight);
		getContentPane().add(panel, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{284, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel lblTitulo = new JLabel("Pr\u00E9stamo de contenidos");
		lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
		GridBagConstraints gbc_lblTitulo = new GridBagConstraints();
		gbc_lblTitulo.insets = new Insets(0, 0, 5, 0);
		gbc_lblTitulo.gridx = 0;
		gbc_lblTitulo.gridy = 0;
		panel.add(lblTitulo, gbc_lblTitulo);
		
		JPanel IntroduzcaID = new JPanel();
		IntroduzcaID.setBorder(null);
		IntroduzcaID.setBackground(SystemColor.controlHighlight);
		GridBagConstraints gbc_IntroduzcaID = new GridBagConstraints();
		gbc_IntroduzcaID.anchor = GridBagConstraints.WEST;
		gbc_IntroduzcaID.insets = new Insets(10, 10, 5, 10);
		gbc_IntroduzcaID.fill = GridBagConstraints.VERTICAL;
		gbc_IntroduzcaID.gridx = 0;
		gbc_IntroduzcaID.gridy = 2;
		panel.add(IntroduzcaID, gbc_IntroduzcaID);
		IntroduzcaID.setLayout(new FlowLayout(FlowLayout.RIGHT, 0, 5));
		
		JLabel lblD = new JLabel("Introduzca la ID del usuario:");
		lblD.setPreferredSize(new Dimension(150, 30));
		lblD.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		IntroduzcaID.add(lblD);
		
		textField = new JTextField();
		textField.setPreferredSize(new Dimension(7, 30));
		textField.setColumns(15);
		textField.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		IntroduzcaID.add(textField);
		
		JEditorPane editorPane = new JEditorPane();
		editorPane.setEditable(false);
		editorPane.setPreferredSize(new Dimension(107, 250));
		editorPane.setBackground(SystemColor.controlHighlight);
		editorPane.setBorder(null);
		editorPane.setContentType("text/html");
		editorPane.setText(getDescripcion());
		GridBagConstraints gbc_editorPane = new GridBagConstraints();
		gbc_editorPane.insets = new Insets(10, 10, 5, 10);
		gbc_editorPane.fill = GridBagConstraints.BOTH;
		gbc_editorPane.gridx = 0;
		gbc_editorPane.gridy = 1;
		panel.add(editorPane, gbc_editorPane);
		
		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setPreferredSize(new Dimension(79, 30));
		btnConfirmar.setBorder(null);
		btnConfirmar.setBackground(Color.GREEN);
		btnConfirmar.setFont(new Font("Segoe UI", Font.BOLD, 16));
		GridBagConstraints gbc_btnConfirmar = new GridBagConstraints();
		gbc_btnConfirmar.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnConfirmar.anchor = GridBagConstraints.SOUTH;
		gbc_btnConfirmar.gridx = 0;
		gbc_btnConfirmar.gridy = 3;
		btnConfirmar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int dni = 0;
				Perfil perfil = null;
				
				try {
					dni= Integer.parseInt(textField.getText());
				} catch(NumberFormatException e1) {
					JOptionPane.showMessageDialog(null, "No se ha reconocido un número en el campo \"ID\"", "Error", JOptionPane.WARNING_MESSAGE, new ImageIcon("files/images/error.png"));
					textField.setBorder(new LineBorder(Color.RED,3));
				}
				
				try {
					perfil = PerfilSQL.getPerfil(dni);
				} catch (ExcepcionDNIPerfil e1) {
					JOptionPane.showMessageDialog(null, "No se ha reconocido un usuario con el DNI"+dni, "Error", JOptionPane.WARNING_MESSAGE, new ImageIcon("files/images/error.png"));
					textField.setBorder(new LineBorder(Color.RED,3));
				} catch (ExcepcionPerfil e1) {
					JOptionPane.showMessageDialog(null, "Ha habido un error la identificación de usuario", "Error", JOptionPane.WARNING_MESSAGE, new ImageIcon("files/images/error.png"));
					textField.setBorder(new LineBorder(Color.RED,3));
				}
				
				if(perfil != null && dni != 0) {
					try {
						int opt = JOptionPane.showConfirmDialog(null, "¿Confirma que quiere que "+perfil.getDNI()+" coja prestado el contenido "+c.getID()+"?", "Confirmación",JOptionPane.YES_NO_OPTION);
						if(opt == 0) {
							ContenidoSQL.prestarBBDD(c, perfil);
							setVisible(false);
							JOptionPane.showMessageDialog(null, "Felicidades por haber cogido prestado el contenido "+c.getID(),"Felicidades",JOptionPane.INFORMATION_MESSAGE);
							return;
						}
					} catch (ExcepcionContenido | ExcepcionPerfil e1) {
						e1.printStackTrace();
						JOptionPane.showMessageDialog(null, "Ha habido un error con el préstamo del contenido", "Error", JOptionPane.WARNING_MESSAGE, new ImageIcon("files/images/error.png"));
					}
				}
				
			}
			
		});
		panel.add(btnConfirmar, gbc_btnConfirmar);
	}
	
	public String getDescripcion() {
		return "<html><body><p>"+
				"<FONT FACE=\"Segoe UI\" SIZE=5 COLOR=\"black\"<b>Título: </b>"+c.getTitulo()+"</FONT>"+
				"<FONT FACE=\"Segoe UI\" SIZE=4 COLOR=\"black\">"+
				"<br><b>Autor: </b>"+c.getAutor()+
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
				"<br><br><b>ID: </b>"+c.getID()+
				"<br><b>Fecha de entrega: </b>"+LocalDate.now().plusDays(c.getDiasDePrestamo())+
				"</FONT></p></body></html>";
	}
	
}

