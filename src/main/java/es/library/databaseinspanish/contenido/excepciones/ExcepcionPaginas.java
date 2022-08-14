package es.library.databaseinspanish.contenido.excepciones;

import es.library.databaseinspanish.contenido.Contenido;

public class ExcepcionPaginas extends ExcepcionContenido{
	private static final long serialVersionUID = 6585802980626946208L;
	private int paginas;
	
	public ExcepcionPaginas(String s, Contenido contenido, int paginas) {
		super(s, contenido);
		this.paginas = paginas;
	}
	
	public String getMessage() {
		return super.getMessage()+" con "+paginas+" paginas";
	}
	
}
