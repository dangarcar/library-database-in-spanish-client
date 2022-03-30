package contenido.excepciones;

import contenido.Contenido;

public class ExcepcionDisponibilidad extends ExcepcionContenido{
	private static final long serialVersionUID = -615457430019475157L;
	private int dias;

	public ExcepcionDisponibilidad(String s, Contenido contenido,int dias) {
		super(s, contenido);
		this.dias = dias;
	}

	public String getMessage() {
		return super.getMessage()+" con "+dias+" días";
	}
	
}
