package personas;

public class ExcepcionDNIUsuario extends ExcepcionUsuario{
	private static final long serialVersionUID = 1L;
	Usuario user;
	String info;
	
	public ExcepcionDNIUsuario(String s,Usuario p) {
		super(s,p);
		info = s;
		user = p;
	}
	
	@Override
	public String getMessage() {
		return info+" in Usuario "+user.getNombre()+" "+user.getApellido();
	}
}
