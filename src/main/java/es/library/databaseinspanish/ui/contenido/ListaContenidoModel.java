package es.library.databaseinspanish.ui.contenido;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.DefaultListModel;

import es.library.databaseinspanish.model.contenido.Contenido;

public class ListaContenidoModel extends DefaultListModel<Contenido>{
	
	public ListaContenidoModel(List<Contenido> listaD) {
		this.addAll(listaD.stream()
				.sorted((c1,c2) -> Boolean.compare(c2.getDisponible(), c1.getDisponible()))
				.collect(Collectors.toCollection(ArrayList::new)));
	}
	
}