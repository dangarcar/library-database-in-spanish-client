package es.library.databaseinspanish.ui.contenido.buscar;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import es.library.databaseinspanish.api.contenido.dto.ContenidoParamsDto;
import es.library.databaseinspanish.api.utils.StaticApis;
import es.library.databaseinspanish.model.contenido.modeltypes.ContenidoModel;
import es.library.databaseinspanish.ui.SwingApp;
import es.library.databaseinspanish.ui.pantallainicio.PantallaInicioUser;
import es.library.databaseinspanish.ui.utils.OptionPanes;
import es.library.databaseinspanish.ui.utils.ProjectConstants;
import es.library.databaseinspanish.ui.utils.components.ImageLabel;
import net.miginfocom.swing.MigLayout;

public class BuscarContenidoPanel extends JPanel{

	private Logger logger = LogManager.getLogger(getClass());
	
	private JTextField contenidoTextField;
	private JButton btnBuscarContenido;
	private SwingApp parent;
	
	public BuscarContenidoPanel(SwingApp parent) {
		this.parent = parent;
		
		setLayout(new MigLayout("", "[]", "[grow][][][grow]"));
		setBackground(ProjectConstants.BACKGROUND_COLOR);
		
		ImageLabel imageLabel = new ImageLabel(new ImageIcon(PantallaInicioUser.class.getResource("/es/library/databaseinspanish/ui/images/contenidos.png")),128,128);
		add(imageLabel,"flowy,cell 0 0,alignx center,aligny bottom");
		
		contenidoTextField = new JTextField();
		contenidoTextField.setFont(ProjectConstants.font12P);
		contenidoTextField.setPreferredSize(new Dimension(7, 25));
		contenidoTextField.setColumns(20);
		add(contenidoTextField, "cell 0 2,alignx center");

		btnBuscarContenido = new JButton("Buscar contenidos");
		btnBuscarContenido.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnBuscarContenido.setForeground(Color.WHITE);
		btnBuscarContenido.setBorderPainted(false);
		btnBuscarContenido.setBackground(new Color(0, 128, 0));
		btnBuscarContenido.setFont(ProjectConstants.font12P);
		btnBuscarContenido.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(btnBuscarContenido, "cell 0 3,alignx center,aligny top");
		
		JLabel contenidoLabel = new JLabel("Contenidos");
		contenidoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		contenidoLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
		add(contenidoLabel, "cell 0 1,alignx center,aligny top");
		
		addListeners();
	}
	
	private void addListeners() {
		btnBuscarContenido.addActionListener(buscarContenido);
		
		contenidoTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					buscarContenido.actionPerformed(null);
				}
			}	
		});
	}
	
	private ActionListener buscarContenido = (ActionEvent e) -> {		
		try {
			String query = contenidoTextField.getText();
			
			var params = new ContenidoParamsDto();
			if(!query.isBlank()) params.setQuery(query);
			
			List<ContenidoModel> contenidos =  StaticApis.contenidoApi().getContenidoByParams(params);
			
			var resultPanel = new ContenidoBusquedaResultPanel(parent, contenidos);
			resultPanel.setName("Resultado_n"+contenidos.hashCode());
			parent.changePanel(resultPanel);
			
			contenidoTextField.setText(null);
			logger.info("Se ha hecho la búsqueda {} en los contenidos", query);
			
		} catch (Exception e1) {
			logger.warn("No se ha podido realizar la búsqueda de los contenidos correctamente",e1);
			OptionPanes.errorBlocking("No se ha podido realizar la búsqueda de los contenidos correctamente");
		}
	};
	
}
