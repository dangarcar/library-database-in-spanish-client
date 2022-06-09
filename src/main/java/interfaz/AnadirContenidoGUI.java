package interfaz;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import javax.swing.SwingConstants;

import database.ContenidoSQL;

import java.awt.Font;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.time.LocalDate;

import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import java.awt.Dimension;
import javax.swing.JTextArea;

import contenido.Audio;
import contenido.Contenido;
import contenido.Libros;
import contenido.Soporte;
import contenido.Videos;
import contenido.excepciones.ExcepcionContenido;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import java.awt.Component;
import java.awt.FlowLayout;

import javax.swing.JButton;

/**
 * Esta clase es la que añade contenidos a la BBDD a través de una interfaz gráfica
 * @author Daniel García
 *
 */
public class AnadirContenidoGUI extends JFrame implements ActionListener{
	private static final long serialVersionUID = 8747700342811372445L;
	private JTextField textFieldTitulo;
	private JTextField textAutor;
	private JTextField textFieldIdioma;
	private JTextField textFieldID;
	private JSpinner spinnerDiasPrestado;
	private JSpinner spinnerAno;
	private JTextArea textAreaDescripcion;
	private JRadioButton rdBtnPrestable;
	private JComboBox<Soporte> comboBoxSoporte;
	private JComboBox<String> comboBoxTipo;
	private JSpinner spinnerPag;
	private JSpinner spinnerDur;
	private JSpinner spinnerEdad;
	private JSpinner spinnerCal;
	private JTextField textFieldEdit;

	private Contenido contenido;
	
 	public AnadirContenidoGUI() {
		setResizable(false);
		setBounds(200,200,400,450);
		setTitle("Añadir contenido");
		setIconImage(Toolkit.getDefaultToolkit().getImage("src/main/resources/files/images/contenidos.png"));
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{145, 216};
		gbl_panel.rowHeights = new int[]{25, 0, 0, 0, 0, 0, 20, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{1.0, 1.0};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel lblEnunciado = new JLabel("A\u00F1adir contenido");
		lblEnunciado.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblEnunciado.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblEnunciado = new GridBagConstraints();
		gbc_lblEnunciado.insets = new Insets(0, 0, 5, 0);
		gbc_lblEnunciado.gridwidth = 2;
		gbc_lblEnunciado.fill = GridBagConstraints.BOTH;
		gbc_lblEnunciado.gridx = 0;
		gbc_lblEnunciado.gridy = 0;
		panel.add(lblEnunciado, gbc_lblEnunciado);
		
		JLabel lblTitulo = new JLabel("T\u00EDtulo:");
		lblTitulo.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		GridBagConstraints gbc_lblTitulo = new GridBagConstraints();
		gbc_lblTitulo.anchor = GridBagConstraints.EAST;
		gbc_lblTitulo.insets = new Insets(0, 0, 5, 5);
		gbc_lblTitulo.gridx = 0;
		gbc_lblTitulo.gridy = 1;
		panel.add(lblTitulo, gbc_lblTitulo);
		
		textFieldTitulo = new JTextField();
		textFieldTitulo.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		GridBagConstraints gbc_textFieldTitulo = new GridBagConstraints();
		gbc_textFieldTitulo.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldTitulo.insets = new Insets(0, 5, 5, 0);
		gbc_textFieldTitulo.gridx = 1;
		gbc_textFieldTitulo.gridy = 1;
		panel.add(textFieldTitulo, gbc_textFieldTitulo);
		textFieldTitulo.setColumns(10);
		
		JLabel lblAutor = new JLabel("Autor:");
		lblAutor.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		GridBagConstraints gbc_lblAutor = new GridBagConstraints();
		gbc_lblAutor.insets = new Insets(0, 0, 5, 5);
		gbc_lblAutor.anchor = GridBagConstraints.EAST;
		gbc_lblAutor.gridx = 0;
		gbc_lblAutor.gridy = 2;
		panel.add(lblAutor, gbc_lblAutor);
		
		textAutor = new JTextField();
		textAutor.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		textAutor.setColumns(10);
		GridBagConstraints gbc_textAutor = new GridBagConstraints();
		gbc_textAutor.insets = new Insets(0, 5, 5, 0);
		gbc_textAutor.fill = GridBagConstraints.HORIZONTAL;
		gbc_textAutor.gridx = 1;
		gbc_textAutor.gridy = 2;
		panel.add(textAutor, gbc_textAutor);
		
		JLabel lblAno = new JLabel("A\u00F1o de publicaci\u00F3n:");
		lblAno.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		GridBagConstraints gbc_lblAno = new GridBagConstraints();
		gbc_lblAno.anchor = GridBagConstraints.EAST;
		gbc_lblAno.insets = new Insets(0, 0, 5, 5);
		gbc_lblAno.gridx = 0;
		gbc_lblAno.gridy = 3;
		panel.add(lblAno, gbc_lblAno);
		
		spinnerAno = new JSpinner();
		GridBagConstraints gbc_spinnerAno = new GridBagConstraints();
		gbc_spinnerAno.insets = new Insets(0, 5, 5, 0);
		gbc_spinnerAno.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinnerAno.gridx = 1;
		gbc_spinnerAno.gridy = 3;
		spinnerAno.setModel(new SpinnerNumberModel(2000,0,LocalDate.now().getYear(),1));
		panel.add(spinnerAno, gbc_spinnerAno);
		
		JLabel lblIdioma = new JLabel("Idioma:");
		lblIdioma.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		GridBagConstraints gbc_lblIdioma = new GridBagConstraints();
		gbc_lblIdioma.anchor = GridBagConstraints.EAST;
		gbc_lblIdioma.insets = new Insets(0, 0, 5, 5);
		gbc_lblIdioma.gridx = 0;
		gbc_lblIdioma.gridy = 4;
		panel.add(lblIdioma, gbc_lblIdioma);
		
		textFieldIdioma = new JTextField();
		textFieldIdioma.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		textFieldIdioma.setColumns(10);
		GridBagConstraints gbc_textFieldIdioma = new GridBagConstraints();
		gbc_textFieldIdioma.insets = new Insets(0, 5, 5, 0);
		gbc_textFieldIdioma.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldIdioma.gridx = 1;
		gbc_textFieldIdioma.gridy = 4;
		panel.add(textFieldIdioma, gbc_textFieldIdioma);
		
		JLabel lblDescripcion = new JLabel("Descripci\u00F3n:");
		lblDescripcion.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		GridBagConstraints gbc_lblDescripcion = new GridBagConstraints();
		gbc_lblDescripcion.anchor = GridBagConstraints.EAST;
		gbc_lblDescripcion.insets = new Insets(0, 0, 5, 5);
		gbc_lblDescripcion.gridx = 0;
		gbc_lblDescripcion.gridy = 5;
		panel.add(lblDescripcion, gbc_lblDescripcion);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 5, 5, 0);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 5;
		panel.add(scrollPane, gbc_scrollPane);
		
		textAreaDescripcion = new JTextArea();
		textAreaDescripcion.setMargin(new Insets(5, 5, 5, 5));
		textAreaDescripcion.setLineWrap(true);
		textAreaDescripcion.setWrapStyleWord(true);
		textAreaDescripcion.setRows(5);
		textAreaDescripcion.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		textAreaDescripcion.setBorder(null);
		scrollPane.setViewportView(textAreaDescripcion);
		
		JPanel panelDiasPrestado = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panelDiasPrestado.getLayout();
		flowLayout.setHgap(0);
		flowLayout.setVgap(0);
		panelDiasPrestado.setAlignmentY(Component.TOP_ALIGNMENT);
		panelDiasPrestado.setPreferredSize(new Dimension(0, 20));
		GridBagConstraints gbc_panelDiasPrestado = new GridBagConstraints();
		gbc_panelDiasPrestado.fill = GridBagConstraints.BOTH;
		gbc_panelDiasPrestado.insets = new Insets(0, 5, 5, 0);
		gbc_panelDiasPrestado.gridx = 1;
		gbc_panelDiasPrestado.gridy = 6;
		panel.add(panelDiasPrestado, gbc_panelDiasPrestado);
		
		JLabel lblDiasPrestado = new JLabel("D\u00EDas a ser prestado:");
		lblDiasPrestado.setPreferredSize(new Dimension(105, 20));
		lblDiasPrestado.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		panelDiasPrestado.add(lblDiasPrestado);
		
		spinnerDiasPrestado = new JSpinner(new SpinnerNumberModel(7,0,70,7));
		spinnerDiasPrestado.setPreferredSize(new Dimension(50, 20));
		panelDiasPrestado.add(spinnerDiasPrestado);
		
		rdBtnPrestable = new JRadioButton("Prestable:");
		rdBtnPrestable.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		rdBtnPrestable.setActionCommand("Prestable");
		rdBtnPrestable.setSelected(true);
		GridBagConstraints gbc_rdBtnPrestable = new GridBagConstraints();
		gbc_rdBtnPrestable.anchor = GridBagConstraints.EAST;
		gbc_rdBtnPrestable.insets = new Insets(0, 0, 5, 5);
		gbc_rdBtnPrestable.gridx = 0;
		gbc_rdBtnPrestable.gridy = 6;
		rdBtnPrestable.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				panelDiasPrestado.setVisible(rdBtnPrestable.isSelected());;
			}
			
		});
		panel.add(rdBtnPrestable, gbc_rdBtnPrestable);
		
		JLabel lblSoporte = new JLabel("Soporte:");
		lblSoporte.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		GridBagConstraints gbc_lblSoporte = new GridBagConstraints();
		gbc_lblSoporte.anchor = GridBagConstraints.EAST;
		gbc_lblSoporte.insets = new Insets(0, 0, 5, 5);
		gbc_lblSoporte.gridx = 0;
		gbc_lblSoporte.gridy = 7;
		panel.add(lblSoporte, gbc_lblSoporte);
		
		comboBoxSoporte = new JComboBox<Soporte>(Soporte.values());
		GridBagConstraints gbc_comboBoxSoporte = new GridBagConstraints();
		gbc_comboBoxSoporte.insets = new Insets(0, 5, 5, 0);
		gbc_comboBoxSoporte.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxSoporte.gridx = 1;
		gbc_comboBoxSoporte.gridy = 7;
		panel.add(comboBoxSoporte, gbc_comboBoxSoporte);
		
		JLabel lblTipo = new JLabel("Tipo de contenido:");
		lblTipo.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		GridBagConstraints gbc_lblTipo = new GridBagConstraints();
		gbc_lblTipo.anchor = GridBagConstraints.EAST;
		gbc_lblTipo.insets = new Insets(0, 0, 5, 5);
		gbc_lblTipo.gridx = 0;
		gbc_lblTipo.gridy = 8;
		panel.add(lblTipo, gbc_lblTipo);
		
		JPanel panelTipo = new JPanel();
		GridBagConstraints gbc_panelTipo = new GridBagConstraints();
		gbc_panelTipo.gridwidth = 2;
		gbc_panelTipo.fill = GridBagConstraints.BOTH;
		gbc_panelTipo.gridx = 0;
		gbc_panelTipo.gridy = 9;
		panelTipo.setLayout(new BorderLayout());
		panelTipo.add(new JLabel("<html><body><font size=\"5\" style=\"Segoe UI\">&emsp;&emsp;&emsp;Seleccione un tipo de contenido</font></body></html>"),BorderLayout.CENTER);
		panel.add(panelTipo, gbc_panelTipo);
		
		String[] tipos = {"","TEXTO","AUDIO","VÍDEO"};
		comboBoxTipo = new JComboBox<String>(tipos);
		GridBagConstraints gbc_comboBoxTipo = new GridBagConstraints();
		gbc_comboBoxTipo.insets = new Insets(0, 5, 5, 0);
		gbc_comboBoxTipo.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxTipo.gridx = 1;
		gbc_comboBoxTipo.gridy = 8;
		panel.add(comboBoxTipo, gbc_comboBoxTipo);
		comboBoxTipo.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				GridBagConstraints gbc_Tipo = new GridBagConstraints();
				gbc_Tipo.insets = new Insets(0, 5, 5, 0);
				gbc_Tipo.fill = GridBagConstraints.BOTH;
				gbc_Tipo.gridx = 0;
				gbc_Tipo.gridy = 9;
				gbc_Tipo.gridwidth = 2;
				panelTipo.removeAll();
				switch((String)comboBoxTipo.getSelectedItem()) {
					case "TEXTO":
						panelTipo.add(getLibroPanel(),BorderLayout.NORTH);
						break;
					case "AUDIO":
						panelTipo.add(getAudioPanel(),BorderLayout.NORTH);
						break;
					case "VÍDEO":
						panelTipo.add(getVideoPanel(),BorderLayout.NORTH);
						break;
				}
				panel.updateUI();
			}
			
		});
		
		JButton btnAnadir = new JButton("A\u00F1adir");
		GridBagConstraints gbc_btnAnadir = new GridBagConstraints();
		gbc_btnAnadir.fill = GridBagConstraints.BOTH;
		gbc_btnAnadir.gridwidth = 2;
		gbc_btnAnadir.gridx = 0;
		gbc_btnAnadir.gridy = 10;
		panel.add(btnAnadir, gbc_btnAnadir);
		btnAnadir.addActionListener(this);
		
		setVisible(true);
	}
	
	JPanel getLibroPanel() {
		JPanel panel = new JPanel();
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{145, 216};
		gbl_panel.columnWeights = new double[]{0.0, 1.0};
		panel.setLayout(gbl_panel);
		
		JLabel lblISBN = new JLabel("ISBN:");
		lblISBN.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		GridBagConstraints gbc_lblISBN = new GridBagConstraints();
		gbc_lblISBN.anchor = GridBagConstraints.EAST;
		gbc_lblISBN.gridx = 0;
		gbc_lblISBN.gridy = 0;
		panel.add(lblISBN, gbc_lblISBN);
		
		textFieldID = new JTextField();
		textFieldID.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		textFieldID.setColumns(10);
		GridBagConstraints gbc_textFieldID = new GridBagConstraints();
		gbc_textFieldID.insets = new Insets(0, 5, 5, 0);
		gbc_textFieldID.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldID.gridx = 1;
		gbc_textFieldID.gridy = 0;
		panel.add(textFieldID, gbc_textFieldID);
		
		JLabel lblPag = new JLabel("Páginas:");
		lblPag.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		GridBagConstraints gbc_lblPag = new GridBagConstraints();
		gbc_lblPag.anchor = GridBagConstraints.EAST;
		gbc_lblPag.insets = new Insets(0, 0, 5, 5);
		gbc_lblPag.gridx = 0;
		gbc_lblPag.gridy = 1;
		panel.add(lblPag, gbc_lblPag);
		
		spinnerPag = new JSpinner();
		GridBagConstraints gbc_spinnerPag = new GridBagConstraints();
		gbc_spinnerPag.insets = new Insets(0, 5, 5, 0);
		gbc_spinnerPag.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinnerPag.gridx = 1;
		gbc_spinnerPag.gridy = 1;
		spinnerPag.setModel(new SpinnerNumberModel(100,0,5000,1));
		panel.add(spinnerPag, gbc_spinnerPag);
		
		JLabel lblEdit = new JLabel("Editorial:");
		lblEdit.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		GridBagConstraints gbc_lblEdit = new GridBagConstraints();
		gbc_lblEdit.anchor = GridBagConstraints.EAST;
		gbc_lblEdit.gridx = 0;
		gbc_lblEdit.gridy = 2;
		panel.add(lblEdit, gbc_lblEdit);
		
		textFieldEdit = new JTextField();
		textFieldEdit.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		textFieldEdit.setColumns(10);
		GridBagConstraints gbc_textFieldEdit = new GridBagConstraints();
		gbc_textFieldEdit.insets = new Insets(0, 5, 5, 0);
		gbc_textFieldEdit.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldEdit.gridx = 1;
		gbc_textFieldEdit.gridy = 2;
		panel.add(textFieldEdit, gbc_textFieldEdit);
		
		return panel;
	}
	
	JPanel getAudioPanel() {
		JPanel panel = new JPanel();
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{145, 216};
		gbl_panel.columnWeights = new double[]{0.0, 1.0};
		panel.setLayout(gbl_panel);
		
		JLabel lblDur = new JLabel("Duración(en minutos):");
		lblDur.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		GridBagConstraints gbc_lblDur = new GridBagConstraints();
		gbc_lblDur.anchor = GridBagConstraints.EAST;
		gbc_lblDur.insets = new Insets(0, 0, 5, 5);
		gbc_lblDur.gridx = 0;
		gbc_lblDur.gridy = 0;
		panel.add(lblDur, gbc_lblDur);
		
		spinnerDur = new JSpinner();
		GridBagConstraints gbc_spinnerDur = new GridBagConstraints();
		gbc_spinnerDur.insets = new Insets(0, 5, 5, 0);
		gbc_spinnerDur.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinnerDur.gridx = 1;
		gbc_spinnerDur.gridy = 0;
		spinnerDur.setModel(new SpinnerNumberModel(60,0,Short.MAX_VALUE,0.1));
		panel.add(spinnerDur, gbc_spinnerDur);
		
		return panel;
	}
	
	JPanel getVideoPanel() {
		JPanel panel = getAudioPanel();
		
		JLabel lblEdad = new JLabel("Edad recomendada:");
		lblEdad.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		GridBagConstraints gbc_lblEdad = new GridBagConstraints();
		gbc_lblEdad.anchor = GridBagConstraints.EAST;
		gbc_lblEdad.insets = new Insets(0, 0, 5, 5);
		gbc_lblEdad.gridx = 0;
		gbc_lblEdad.gridy = 1;
		panel.add(lblEdad, gbc_lblEdad);
		
		spinnerEdad = new JSpinner();
		GridBagConstraints gbc_spinnerEdad = new GridBagConstraints();
		gbc_spinnerEdad.insets = new Insets(0, 5, 5, 0);
		gbc_spinnerEdad.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinnerEdad.gridx = 1;
		gbc_spinnerEdad.gridy = 1;
		spinnerEdad.setModel(new SpinnerNumberModel(0,0,18,1));
		panel.add(spinnerEdad, gbc_spinnerEdad);
		
		JLabel lblCal = new JLabel("Calidad(en píxeles):");
		lblCal.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		GridBagConstraints gbc_lblCal = new GridBagConstraints();
		gbc_lblCal.anchor = GridBagConstraints.EAST;
		gbc_lblCal.insets = new Insets(0, 0, 5, 5);
		gbc_lblCal.gridx = 0;
		gbc_lblCal.gridy = 2;
		panel.add(lblCal, gbc_lblCal);
		
		spinnerCal = new JSpinner();
		GridBagConstraints gbc_spinnerCal = new GridBagConstraints();
		gbc_spinnerCal.insets = new Insets(0, 5, 5, 0);
		gbc_spinnerCal.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinnerCal.gridx = 1;
		gbc_spinnerCal.gridy = 2;
		spinnerCal.setModel(new SpinnerNumberModel(0,0,2160,12));
		panel.add(spinnerCal, gbc_spinnerCal);
		
		return panel;
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {		
		try {
			switch((String)comboBoxTipo.getSelectedItem()) {
			case "TEXTO":
				contenido = new Libros(textFieldTitulo.getText(),textAutor.getText(),textAreaDescripcion.getText(),(Integer)spinnerAno.getValue(),textFieldIdioma.getText(),rdBtnPrestable.isSelected(),(Soporte)comboBoxSoporte.getSelectedItem(),(Integer)spinnerDiasPrestado.getValue()
						,Long.parseLong(textFieldID.getText()),(Integer)spinnerPag.getValue(),textFieldEdit.getText());
				break;
		
			case "AUDIO":
				contenido = new Audio(textFieldTitulo.getText(),textAutor.getText(),textAreaDescripcion.getText(),(Integer)spinnerAno.getValue(),textFieldIdioma.getText(),rdBtnPrestable.isSelected(),(Soporte)comboBoxSoporte.getSelectedItem(),(Integer)spinnerDiasPrestado.getValue()
						,(Double)spinnerDur.getValue());
				break;
			
			case "VÍDEO":
				contenido = new Videos(textFieldTitulo.getText(),textAutor.getText(),textAreaDescripcion.getText(),(Integer)spinnerAno.getValue(),textFieldIdioma.getText(),rdBtnPrestable.isSelected(),(Soporte)comboBoxSoporte.getSelectedItem(),(Integer)spinnerDiasPrestado.getValue()
						,(Double)spinnerDur.getValue(),(Integer)spinnerEdad.getValue(),(Integer)spinnerCal.getValue());
				break;
		
			default:
				throw new ExcepcionContenido("Deber elegir un tipo de contenido",contenido);
			}
			
			int j = Integer.parseInt(JOptionPane.showInputDialog("Seleccione el número de contenidos que desea añadir"));
			if(JOptionPane.showConfirmDialog(null, "Quiere añadir "+j+" ejemplares del contenido "+contenido.getTitulo(),"Confirmación",JOptionPane.YES_NO_OPTION)==0) {
				for(int i=0;i<j;i++) {
					if(contenido instanceof Libros) ContenidoSQL.writeLibro((Libros)contenido);
					else if(contenido instanceof Audio) ContenidoSQL.writeAudiovisual((Audio)contenido);
					else throw new ExcepcionContenido("Parece que hay un problema con el contenido que quiere añadir",contenido);
				}
				setVisible(false);
				JOptionPane.showMessageDialog(null, "Felicidades por añadido a "+contenido.getTitulo()+" a la base de datos", "Felicidades" ,JOptionPane.INFORMATION_MESSAGE);
			}
		} catch(ExcepcionContenido e1) {
			JOptionPane.showMessageDialog(null, "Error: "+e1.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
		} catch (NumberFormatException e1) {
			JOptionPane.showMessageDialog(null, "Error: Escriba un número de ejemplares por favor","Error",JOptionPane.ERROR_MESSAGE);
		}
	}
	
}
