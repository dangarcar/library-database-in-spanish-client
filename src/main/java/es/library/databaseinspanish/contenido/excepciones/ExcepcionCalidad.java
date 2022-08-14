package es.library.databaseinspanish.contenido.excepciones;

import es.library.databaseinspanish.contenido.Contenido;

public class ExcepcionCalidad extends ExcepcionContenido {
	private static final long serialVersionUID = 5687768921953349861L;
	private int calidad;
		
	public ExcepcionCalidad(String s, Contenido contenido, int calidad) {
		super(s, contenido);
		this.calidad = calidad;
	}
		
	public String getMessage() {
		return super.getMessage()+" con la calidad de "+calidad+"p";
	}

}
