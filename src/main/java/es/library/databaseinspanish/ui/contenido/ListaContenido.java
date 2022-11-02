package es.library.databaseinspanish.ui.contenido;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.border.EmptyBorder;

import es.library.databaseinspanish.model.contenido.Contenido;
import es.library.databaseinspanish.utils.ImageUtils;

public class ListaContenido extends JList<Contenido> {

	public ListaContenido(List<Contenido> listaD) {
		ListaContenidoModel listModel = new ListaContenidoModel(listaD);
		this.setModel(listModel);
		this.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		this.setCellRenderer(new ListaRenderer());
	}
}

class ListaRenderer extends JLabel implements ListCellRenderer<Contenido> {

	public ListaRenderer() {
		this.setOpaque(true);
		this.setBorder(new EmptyBorder(6,5,6,5));
	}
	
	@Override
	public Component getListCellRendererComponent(JList<? extends Contenido> list, Contenido c, int index,
			boolean isSelected, boolean cellHasFocus) {
		
		Color bg = null;
		Color fg = null;
		
		setIcon(ImageUtils.getScaledIcon(ImageUtils.getImagenFromContenido(c), 64, 64));
		setText("<html>"+"<b>ID interna: </b>"+c.getID()+"<br><b>Prestable: </b>"+formatBooleanHtml(c.getPrestable())+"<br><b>Disponible: </b>"+formatBooleanHtml(c.getDisponible())+"</html>");
		
        if (isSelected) {
            setBackground(bg == null ? list.getSelectionBackground() : bg);
            setForeground(fg == null ? list.getSelectionForeground() : fg);
        }
        else {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }
        
        setFont(list.getFont());
        
		return this;
	}
	
	public String formatBooleanHtml(boolean value) {
		return "<b><span style=\"color:" + (value ? "green":"red") + "\">" + (value ? "Sí":"No") + "</span></b>";
	}
	
}