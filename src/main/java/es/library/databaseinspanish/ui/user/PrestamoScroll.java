package es.library.databaseinspanish.ui.user;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import es.library.databaseinspanish.api.utils.StaticApis;
import es.library.databaseinspanish.model.prestamo.Prestamo;
import es.library.databaseinspanish.model.prestamo.PrestamoContenidoModel;
import es.library.databaseinspanish.ui.SwingApp;
import es.library.databaseinspanish.ui.utils.OptionPanes;
import es.library.databaseinspanish.ui.utils.ProjectConstants;

public class PrestamoScroll extends JScrollPane {

	private JPanel viewport = new JPanel();
	private JLabel noContentLabel;
	private List<PrestamoPicture> contenidoModels = new ArrayList<>();
	private GridLayout gridLayout;
	private SwingApp parent;

	public PrestamoScroll(SwingApp parent) {
		this.parent = parent;
		
		setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		setBorder(null);
		this.setViewportView(viewport);
		viewport.setBackground(ProjectConstants.BACKGROUND_COLOR);		
		
		noContentLabel = new JLabel("No tiene ningún contenido en préstamo actualmente");
		noContentLabel.setFont(ProjectConstants.font12P.deriveFont(Font.BOLD,24f));
		noContentLabel.setForeground(Color.gray);
		
		setPrestamos();
		setPictures();
		
		this.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				super.componentResized(e);
				setGridLayout();
			}			
		});
		
		setGridLayout();
	}

	private void setGridLayout() {
		int cols = (int) (parent.getWidth() / 270);
		cols -= 1;
		if(cols > contenidoModels.size()) cols = contenidoModels.size();
		gridLayout = new GridLayout(0, cols < 1 ? 1 : cols, 10, 10);
		viewport.setLayout(gridLayout);
	}
	
	private void removeContent(PrestamoPicture picture) {
		contenidoModels.remove(picture);
		viewport.remove(picture);
		prestamosEmpty();
		viewport.updateUI();
	}
	
	private void setPictures() {
		if(prestamosEmpty()) return;
		
		for(PrestamoPicture picture : contenidoModels) {	
			picture.getDevolverButton().addActionListener((e) -> {
				var idContenido = picture.getPrestamoContenidoModel().getPrestamo().getIDContenido();
				int opt = JOptionPane.showConfirmDialog(null, "Está seguro que desea devolver \" "+picture.getPrestamoContenidoModel().getContenido().getTitulo()+" \" con id "+idContenido, "Confirmar devolución", JOptionPane.YES_NO_OPTION);	
				if(opt == 0) {
					StaticApis.userApi().devolver(idContenido);
					OptionPanes.infoBlocking("Se ha devuelto el contenido con id " + idContenido);
					removeContent(picture);
				}
			});

			viewport.add(picture);
		};
	}
	
	private boolean prestamosEmpty() {
		boolean empty = false;
		if(contenidoModels.isEmpty()) {
			empty = true;
			viewport.add(noContentLabel);
		}
		return empty;
	}
	
	private void setPrestamos() {
		List<Prestamo> list = StaticApis.userApi().getPrestamos().stream()
				.filter(p -> !p.isDevuelto())
				.collect(Collectors.toList());
		
		for(Prestamo p : list){		
			var c = StaticApis.contenidoApi().getContenidoById(p.getIDContenido());
			contenidoModels.add(new PrestamoPicture(new PrestamoContenidoModel(p, c), 200, 150));
		};
	}
	
}
