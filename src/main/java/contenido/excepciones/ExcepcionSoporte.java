package contenido.excepciones;

import contenido.Contenido;
import contenido.Soporte;

public class ExcepcionSoporte extends ExcepcionContenido{
	private static final long serialVersionUID = -2585189838017099984L;
	private Soporte soporte;
	
	public ExcepcionSoporte(String s, Contenido contenido, Soporte soporte) {
		super(s,contenido);
		this.soporte = soporte;
	}
	
	public String getMessage() {
		return super.getMessage()+" en soporte "+soporte.getClass();
	}
}
