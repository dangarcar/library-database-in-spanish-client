package es.library.databaseinspanish.ui.contenido.buscar;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import es.library.databaseinspanish.model.contenido.modeltypes.ContenidoModel;
import es.library.databaseinspanish.ui.SwingApp;
import es.library.databaseinspanish.ui.contenido.ContenidoPicture;
import es.library.databaseinspanish.ui.utils.ProjectConstants;
import es.library.databaseinspanish.ui.utils.components.NoContentLabel;
import net.miginfocom.swing.MigLayout;

public class ContenidoBusquedaResultScroll extends JScrollPane {

	private SwingApp parent;
	private List<ContenidoPicture> contenidos;
	private JPanel viewport = new JPanel();
	
	public ContenidoBusquedaResultScroll(SwingApp parent, List<ContenidoModel> contenidos) {
		this.parent = parent;
		this.contenidos = getPicturesFromModels(contenidos);
		
		setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		setBorder(null);
		this.setViewportView(viewport);
		viewport.setBackground(ProjectConstants.BACKGROUND_COLOR);
		
		this.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				super.componentResized(e);
				setGridLayout();
			}			
		});
		
		setGridLayout();
	}
	
	private void setPictures(int cols) {
		if(contenidos.isEmpty()) {
			viewport.add(new NoContentLabel("Tu búsqueda no ha devuelto ningún resultado"));
			return;
		}
		
		int row = 0,col = 0;
		for(var i=0;i<contenidos.size();i++) {
			viewport.add(contenidos.get(i),"cell "+ col +" "+ row +", alignx center");
			col++;
			if(col >= cols) {
				row++;
				col = 0;
			}
		}
	}

	private void setGridLayout() {
		int size = contenidos.size();
		int cols = Math.min(size, parent.getWidth()/250);	
		int rows = (int) Math.ceil((double) size/cols);
		
		String colsString = "";
		String rowsString = "";
		for(var i=0; i<cols;i++) {
			colsString += "[grow]";
		}
		for(var i=0;i<rows;i++) {
			rowsString += "[grow]";
		}
		
		viewport.setLayout(new MigLayout("", colsString, rowsString));
		viewport.removeAll();
		
		setPictures(cols);
	}
	
	private List<ContenidoPicture> getPicturesFromModels(List<ContenidoModel> models){
		return models.stream()
				.map(c -> new ContenidoPicture(c, 200, 200, parent))
				.collect(Collectors.toCollection(ArrayList::new));
	}
}
