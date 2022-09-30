package es.library.databaseinspanish.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

/**
 * Clase encargada de mostrar en pantalla el resultado de cierta busqueda
 * @author Daniel García
 *
 */
public class ResultadoTXT extends JPanel {
	private static final long serialVersionUID = -810498298324250318L;
//	private List<? extends DatabaseWritable> resultado = new ArrayList<DatabaseWritable>();
	private JLabel tituloResultadoBusqueda;
	private JSplitPane splitPane;
	private JPanel panelLateral;
	private JPanel panelBotones;
	private JScrollPane scrollPane;
	private JButton botonVolverAtras;
	private JScrollPane scrollPanelLateral;
	
	/*public ResultadoTXT(List<? extends DatabaseWritable> resultado, SwingApp parent) {
		setBackground(Color.LIGHT_GRAY);
		setBounds(0, 0, 984, 661);
		setLayout(new BorderLayout(0, 0));
		
		this.resultado = resultado;
		
		tituloResultadoBusqueda = new JLabel("Resultado de busqueda");
		tituloResultadoBusqueda.setBackground(Color.WHITE);
		tituloResultadoBusqueda.setSize(new Dimension(0, 100));
		tituloResultadoBusqueda.setHorizontalAlignment(SwingConstants.CENTER);
		tituloResultadoBusqueda.setFont(new Font("Segoe UI", Font.BOLD, 24));
		add(tituloResultadoBusqueda, BorderLayout.NORTH);		

		splitPane = new JSplitPane();
		splitPane.setBackground(Color.BLACK);
		splitPane.setBorder(UIManager.getBorder("SplitPane.border"));
		add(splitPane, BorderLayout.CENTER);
		
		panelLateral = new JPanel();
		panelLateral.setBackground(Color.GRAY);
		panelLateral.setPreferredSize(new Dimension(450, 0));
		panelLateral.setLayout(new BorderLayout(0, 0));
		scrollPanelLateral = new JScrollPane();
		scrollPanelLateral.setViewportView(panelLateral);
		splitPane.setLeftComponent(scrollPanelLateral);
		
		panelBotones = new JPanel();
		panelBotones.setLayout(new GridLayout(0, 1, 10, 0));
		panelBotones.setBackground(Color.WHITE);
		
		scrollPane = new JScrollPane();
		scrollPane.setMinimumSize(new Dimension(200, 0));
		splitPane.setRightComponent(scrollPane);
		
		//Para cada objeto no repetido, creo un boton
		for(DatabaseWritable o:searchObjects()) {
			JButton boton = null;
			try {
				boton = o.getGUIRepresentation(resultado);
				boton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						splitPane.updateUI();
						panelLateral.removeAll();
						panelLateral.add(o.getExtendedGUIRepresentation(resultado));
					}
				});;
			} catch (ExcepcionContenido e1) {
				e1.printStackTrace();
			} catch (ExcepcionPerfil e1) {
				e1.printStackTrace();
			}
			panelBotones.add(boton,Component.CENTER_ALIGNMENT);
		}
		scrollPane.setViewportView(panelBotones);
		
		//El boton de volver a la pantalla principal
		botonVolverAtras = new JButton("Volver");
		botonVolverAtras.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				parent.getWindowSwitcher().first(((Container)e.getSource()).getParent().getParent());
				parent.getLobby().getTxtFieldContenidos().setText("");
				parent.getLobby().getTxtFieldPerfiles().setText("");
			}
		});
		add(botonVolverAtras, BorderLayout.SOUTH);
	}
	
	/**
	 * Devuelve los objetos de la lista delresultado de busqueda no repetidos
	 * @return ArrayList de DatabaseWritable
	 */
	/*public List<? extends DatabaseWritable> searchObjects(){
		List<DatabaseWritable> resultados = new ArrayList<DatabaseWritable>();
		
		if(this.resultado != null) {
			List<Long> listaIDS = new ArrayList<Long>();
			for (DatabaseWritable o:resultado){
				if(!(listaIDS.contains(o.getSpecificID()))) {
					if(o != null) {
						resultados.add(o);
						listaIDS.add(o.getSpecificID());
					}
				}
			}
			if (resultado.isEmpty()){
				new Thread(new Runnable() { @Override public void run() {JOptionPane.showMessageDialog(panelBotones,"No existe ningun objeto que coincida con la busqueda");} }).start();;
			}
		}
		return resultados;
	}*/
	
}
