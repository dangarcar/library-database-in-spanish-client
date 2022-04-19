package contenido.excepciones;

import contenido.Contenido;

public class ExcepcionDisponibilidad extends ExcepcionContenido{
	private static final long serialVersionUID = -615457430019475157L;

	public ExcepcionDisponibilidad(String s, Contenido contenido) {
		super(s, contenido);
	}
	
}
