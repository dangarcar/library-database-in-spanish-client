package contenido.excepciones;

import contenido.Contenido;

public class ExcepcionDuracion extends ExcepcionContenido {
	private static final long serialVersionUID = 2387792195315349861L;
	private double duracion;
	
	public ExcepcionDuracion(String s, Contenido contenido, double duracion) {
		super(s, contenido);
		this.duracion = duracion;
	}
	
	public String getMessage() {
		return super.getMessage()+" con la duracion de "+duracion+" minutos";
	}
}
