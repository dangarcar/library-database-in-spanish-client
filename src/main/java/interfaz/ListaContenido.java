package interfaz;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.time.LocalDate;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import contenido.Contenido;

public class ListaContenido extends JList<Contenido>{
	private static final long serialVersionUID = 8815617365332730999L;

	public ListaContenido(List<? extends Contenido> listaD,boolean disponibilidad) {
		ListaContenidoModel listModel = new ListaContenidoModel(listaD);
		this.setModel(listModel);
		this.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		this.setCellRenderer(new ListaRenderer(disponibilidad));
	}
	
}

class ListaContenidoModel extends DefaultListModel<Contenido>{
	private static final long serialVersionUID = -1874164554702212843L;
	private List<? extends Contenido> listaD;
	
	public ListaContenidoModel(List<? extends Contenido> listaD) {
		this.listaD = listaD;
		init();
	}
	
	private void init() {
		for(Contenido c: listaD) {
			addElement(c);
		}
	}
}

class ListaRenderer extends JLabel implements ListCellRenderer<Contenido>{
	private static final long serialVersionUID = -8677926298714112631L;
	private boolean disponibilidad;

	/**
	 * Constructor del renderer de la lista de contenidos
	 * @param disponibilidad Si se quire consultar la disponibilidad del contenido o no
	 */
	public ListaRenderer(boolean disponibilidad) {
		setOpaque(true);
		this.setBorder(new EmptyBorder(6,5,6,5));
		this.disponibilidad = disponibilidad;
	}
	
	@Override
	public Component getListCellRendererComponent(JList<? extends Contenido> list, Contenido c, int index,
			boolean isSelected, boolean cellHasFocus) {
		
		Color bg = null;
		Color fg = null;
		if(disponibilidad) {
			setText("<html><body><p>"+c.getTitulo()+", "+c.getAutor()+"<br>ID interna: "+c.getID()+"<br>Prestable: "+((c.getPrestable())? "Sí":"No")+"<br>Disponible: "+((c.getDisponibilidad())? "Sí":"No")+((c.getDisponibilidad())? "<br>":"<br> Fecha de disponibilidad: "+c.getFechaDisponibilidad()+"<br>")+"</p></body></html>");
		} else {
			setText("<html><body><p>"+c.getTitulo()+", "+c.getAutor()+"<br>ID interna: "+c.getID()+((c.getFechaDisponibilidad() != null)? (( (c.getFechaDisponibilidad().isBefore(LocalDate.now()))? "<br><font color=\"red\"><b>Días desde que cumplió la fecha de entrega: "+(LocalDate.now().toEpochDay()-c.getFechaDisponibilidad().toEpochDay())+"</b></font>":"<br><font color=\"green\">Días para fecha de entrega: "+(c.getFechaDisponibilidad().toEpochDay()-LocalDate.now().toEpochDay())+"</font>" ) ) :"")+"</p></body></html>");
			setIcon(new ImageIcon(c.getSoporte().getIcon().getImage().getScaledInstance(64, 64, Image.SCALE_SMOOTH)));
		}
		
		JList.DropLocation dropLocation = list.getDropLocation();
        if (dropLocation != null
                && !dropLocation.isInsert()
                && dropLocation.getIndex() == index) {

            bg = Color.BLUE;
            fg = Color.black;

            isSelected = true;
        }
		
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

}