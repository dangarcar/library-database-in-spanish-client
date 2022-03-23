package database;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Esta clase crea una ventana que hereda de JFrame, que será la ventana principal del programa
 * @author me
 *
 */
public class Ventana extends JFrame{
	//Atributos
	private JPanel panel;
	private JTextField entradaTexto;
	private JButton boton;
	private JLabel texto;
	private JLabel info;
	private static final long serialVersionUID = 1L;

	public Ventana(Color background) {
		//Configuro el tamaño y otras cosas
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500,500);
		setTitle("Test 1");
		
		//Creo un panel
		panel = new JPanel();
		panel.setBackground(background);
		panel.setLayout(null);
		panel.setBounds(30,50,400,200);
		
		//Creo la entrada de texto
		entradaTexto = new JTextField();
		entradaTexto.setBounds(50,10,300,50);
		entradaTexto.setToolTipText("Escriba algo aquí");
		
		//Creo dos etiquetas
		info = new JLabel();
		info.setBounds(100,150,200,50);
		
		//Creo un boton
		boton = new JButton("Pulse aquí");
		boton.setBounds(150,80,100,50);
		boton.setBackground(new Color(0,255,0));
		boton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent i) {
				String text = entradaTexto.getText();
				entradaTexto.setText("");
				info.setText(text);
			}
		});
		
		//Añado los componentes al panel y añado el panel al frame 
		panel.add(boton);
		panel.add(info);
		panel.add(entradaTexto);
		getContentPane().add(panel);
		
		//Hago que se vea la ventana
		setVisible(true);
	}
}
