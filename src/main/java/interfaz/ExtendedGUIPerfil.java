package interfaz;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import perfiles.Admin;
import perfiles.Perfil;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.GridBagConstraints;
import javax.swing.JTabbedPane;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JTextPane;
import javax.swing.JButton;

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
		GridBagConstraints gbc_tabbedPane = new GridBagConstraints();
		gbc_tabbedPane.insets = new Insets(10, 10, 10, 10);
		gbc_tabbedPane.fill = GridBagConstraints.BOTH;
		gbc_tabbedPane.gridx = 0;
		gbc_tabbedPane.gridy = 2;
		add(tabbedPane, gbc_tabbedPane);
		
		JPanel panelDescripcion = new JPanel();
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
		
		JButton btnID = new JButton("Copiar ID");
		GridBagConstraints gbc_btnID = new GridBagConstraints();
		gbc_btnID.fill = GridBagConstraints.BOTH;
		gbc_btnID.gridx = 0;
		gbc_btnID.gridy = 1;
		btnID.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					//Copia la ID en el portapapeles
					StringSelection ss = new StringSelection(((Integer)p.getID()).toString());
					Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
				} catch (HeadlessException e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "No se ha podido copiar la ID en el portapapeles", "Error", JOptionPane.WARNING_MESSAGE, new ImageIcon("files/images/error.png"));
				}
			}
			
		});
		panelDescripcion.add(btnID, gbc_btnID);
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
}