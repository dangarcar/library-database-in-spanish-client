package interfaz;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;

import static database.PerfilSQL.*;
import perfiles.Admin;
import perfiles.Perfil;
import perfiles.excepciones.ExcepcionPerfil;

import javax.swing.JSpinner;
import javax.swing.JButton;
import javax.swing.JRadioButton;

/**
 * Clase encargada de añadir perfil a la BBDD a través de interfaz gráfica
 * @author Daniel García
 *
 */
public class AnadirPerfilGUI extends JFrame implements ActionListener{
	private static final long serialVersionUID = -2800608918457961661L;
	private JTextField textFieldNombre;
	private JTextField textFieldApellidos;
	private JTextField textFieldDNI;
	private JSpinner spinnerNacimiento;
	private JTextField textFieldDireccion;
	private JTextField textFieldCorreo;
	private JButton btnAnadir;
	private JRadioButton rdBtnAdmin;
	
	public AnadirPerfilGUI() {
		setResizable(false);
		setBounds(200,200,400,450);
		setTitle("Añadir perfil");
		setIconImage(Toolkit.getDefaultToolkit().getImage("src/main/resources/files/images/perfilFino.png"));
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{145, 216};
		gbl_panel.rowHeights = new int[]{25, 0, 0, 0, 0, 0, 20, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 1.0};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel lblEnunciado = new JLabel("Añadir perfil");
		lblEnunciado.setFont(new Font("Segoe UI", Font.BOLD, 18));
		GridBagConstraints gbc_lblEnunciado = new GridBagConstraints();
		gbc_lblEnunciado.insets = new Insets(0, 0, 5, 0);
		gbc_lblEnunciado.gridwidth = 2;
		gbc_lblEnunciado.gridx = 0;
		gbc_lblEnunciado.gridy = 0;
		panel.add(lblEnunciado, gbc_lblEnunciado);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		GridBagConstraints gbc_lblNombre = new GridBagConstraints();
		gbc_lblNombre.anchor = GridBagConstraints.EAST;
		gbc_lblNombre.insets = new Insets(0, 0, 5, 5);
		gbc_lblNombre.gridx = 0;
		gbc_lblNombre.gridy = 1;
		panel.add(lblNombre, gbc_lblNombre);
		
		textFieldNombre = new JTextField();
		textFieldNombre.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		GridBagConstraints gbc_textFieldNombre = new GridBagConstraints();
		gbc_textFieldNombre.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldNombre.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldNombre.gridx = 1;
		gbc_textFieldNombre.gridy = 1;
		panel.add(textFieldNombre, gbc_textFieldNombre);
		textFieldNombre.setColumns(10);
		
		JLabel lblApellidos = new JLabel("Apellidos:");
		lblApellidos.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		GridBagConstraints gbc_lblApellidos = new GridBagConstraints();
		gbc_lblApellidos.insets = new Insets(0, 0, 5, 5);
		gbc_lblApellidos.anchor = GridBagConstraints.EAST;
		gbc_lblApellidos.gridx = 0;
		gbc_lblApellidos.gridy = 2;
		panel.add(lblApellidos, gbc_lblApellidos);
		
		textFieldApellidos = new JTextField();
		textFieldApellidos.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		textFieldApellidos.setColumns(10);
		GridBagConstraints gbc_textFieldApellidos = new GridBagConstraints();
		gbc_textFieldApellidos.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldApellidos.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldApellidos.gridx = 1;
		gbc_textFieldApellidos.gridy = 2;
		panel.add(textFieldApellidos, gbc_textFieldApellidos);
		
		JLabel lblDni = new JLabel("DNI:");
		lblDni.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		GridBagConstraints gbc_lblDni = new GridBagConstraints();
		gbc_lblDni.insets = new Insets(0, 0, 5, 5);
		gbc_lblDni.anchor = GridBagConstraints.EAST;
		gbc_lblDni.gridx = 0;
		gbc_lblDni.gridy = 3;
		panel.add(lblDni, gbc_lblDni);
		
		textFieldDNI = new JTextField();
		textFieldDNI.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		textFieldDNI.setColumns(10);
		GridBagConstraints gbc_textFieldDNI = new GridBagConstraints();
		gbc_textFieldDNI.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldDNI.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldDNI.gridx = 1;
		gbc_textFieldDNI.gridy = 3;
		panel.add(textFieldDNI, gbc_textFieldDNI);
		
		JLabel lblFechaDeNacimiento = new JLabel("Fecha de nacimiento:");
		lblFechaDeNacimiento.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		GridBagConstraints gbc_lblFechaDeNacimiento = new GridBagConstraints();
		gbc_lblFechaDeNacimiento.anchor = GridBagConstraints.EAST;
		gbc_lblFechaDeNacimiento.insets = new Insets(0, 0, 5, 5);
		gbc_lblFechaDeNacimiento.gridx = 0;
		gbc_lblFechaDeNacimiento.gridy = 4;
		panel.add(lblFechaDeNacimiento, gbc_lblFechaDeNacimiento);
		
		spinnerNacimiento = new JSpinner();
		GridBagConstraints gbc_spinnerNacimiento = new GridBagConstraints();
		gbc_spinnerNacimiento.fill = GridBagConstraints.BOTH;
		gbc_spinnerNacimiento.insets = new Insets(0, 0, 5, 0);
		gbc_spinnerNacimiento.gridx = 1;
		gbc_spinnerNacimiento.gridy = 4;
		spinnerNacimiento.setModel(new SpinnerDateModel(new Date(), Date.from(LocalDate.of(1900,1,1).atStartOfDay(ZoneId.systemDefault()).toInstant()), new Date(), Calendar.DAY_OF_WEEK_IN_MONTH));
		spinnerNacimiento.setEditor(new JSpinner.DateEditor(spinnerNacimiento, "dd-MM-yyyy"));
		panel.add(spinnerNacimiento, gbc_spinnerNacimiento);
		
		JLabel lblDireccion = new JLabel("Dirección de casa:");
		lblDireccion.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		GridBagConstraints gbc_lblDireccion = new GridBagConstraints();
		gbc_lblDireccion.anchor = GridBagConstraints.EAST;
		gbc_lblDireccion.insets = new Insets(0, 0, 5, 5);
		gbc_lblDireccion.gridx = 0;
		gbc_lblDireccion.gridy = 5;
		panel.add(lblDireccion, gbc_lblDireccion);
		
		textFieldDireccion = new JTextField();
		textFieldDireccion.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		textFieldDireccion.setColumns(10);
		GridBagConstraints gbc_textFieldDireccion = new GridBagConstraints();
		gbc_textFieldDireccion.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldDireccion.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldDireccion.gridx = 1;
		gbc_textFieldDireccion.gridy = 5;
		panel.add(textFieldDireccion, gbc_textFieldDireccion);
		
		JLabel lblCorreoElectronico = new JLabel("Correo electrónico:");
		lblCorreoElectronico.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		GridBagConstraints gbc_lblCorreoElectronico = new GridBagConstraints();
		gbc_lblCorreoElectronico.anchor = GridBagConstraints.EAST;
		gbc_lblCorreoElectronico.insets = new Insets(0, 0, 5, 5);
		gbc_lblCorreoElectronico.gridx = 0;
		gbc_lblCorreoElectronico.gridy = 6;
		panel.add(lblCorreoElectronico, gbc_lblCorreoElectronico);
		
		textFieldCorreo = new JTextField();
		textFieldCorreo.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		textFieldCorreo.setColumns(10);
		GridBagConstraints gbc_textFieldCorreo = new GridBagConstraints();
		gbc_textFieldCorreo.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldCorreo.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldCorreo.gridx = 1;
		gbc_textFieldCorreo.gridy = 6;
		panel.add(textFieldCorreo, gbc_textFieldCorreo);
		
		rdBtnAdmin = new JRadioButton("Administrador");
		rdBtnAdmin.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		GridBagConstraints gbc_rdBtnAdmin = new GridBagConstraints();
		gbc_rdBtnAdmin.gridwidth = 2;
		gbc_rdBtnAdmin.insets = new Insets(0, 0, 5, 0);
		gbc_rdBtnAdmin.gridx = 0;
		gbc_rdBtnAdmin.gridy = 7;
		rdBtnAdmin.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(rdBtnAdmin.isSelected()) {
					//Si no está seguro de querer hacerlo administrador
					if(JOptionPane.showConfirmDialog(null, "¿Está seguro que desea hacer administrador a este perfil?","Confirmación",JOptionPane.YES_NO_OPTION) != 0) {
						rdBtnAdmin.setSelected(false);
					}
				}
			}
			
		});
		panel.add(rdBtnAdmin, gbc_rdBtnAdmin);
		
		btnAnadir = new JButton("Añadir");
		GridBagConstraints gbc_btnAnadir = new GridBagConstraints();
		gbc_btnAnadir.fill = GridBagConstraints.BOTH;
		gbc_btnAnadir.gridwidth = 2;
		gbc_btnAnadir.gridx = 0;
		gbc_btnAnadir.gridy = 9;
		panel.add(btnAnadir, gbc_btnAnadir);
		btnAnadir.addActionListener(this);
		
		setVisible(true);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Perfil perfil = null;
		boolean admin = rdBtnAdmin.isSelected();
		try {
		if(admin) {
			if(textFieldApellidos.getText() == null ||textFieldApellidos.getText().equals("")) {
				perfil = new Admin(textFieldNombre.getText(),Integer.parseInt(textFieldDNI.getText()));
				
			} else {
				perfil = new Admin(textFieldNombre.getText(),textFieldApellidos.getText(),((Date)spinnerNacimiento.getValue()).toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),Integer.parseInt(textFieldDNI.getText()),textFieldDireccion.getText(),textFieldCorreo.getText());
			}
		} else {
			perfil = new Perfil(textFieldNombre.getText(),textFieldApellidos.getText(),((Date)spinnerNacimiento.getValue()).toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),Integer.parseInt(textFieldDNI.getText()),textFieldDireccion.getText(),textFieldCorreo.getText());
		}
		} catch(ExcepcionPerfil e1) {
			JOptionPane.showMessageDialog(null, "Error: "+e1.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
		}
		
		if(perfil != null) {
			try {
				//Si lo confirma
				if(JOptionPane.showConfirmDialog(null, "¿Está seguro que desea añadir a "+perfil.getNombre()+" ?","Confirmación",JOptionPane.YES_NO_OPTION) == 0) {
					if (admin) WriteAdmin((Admin)perfil);
					else WritePerfil(perfil);
					setVisible(false);
					JOptionPane.showMessageDialog(null, "Felicidades por añadido a "+perfil.getNombre()+" a la base de datos", "Felicidades" ,JOptionPane.INFORMATION_MESSAGE);
				}
			} catch (ExcepcionPerfil e1) {
				JOptionPane.showMessageDialog(null, "Error: "+e1.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			}
		}
	}

}
