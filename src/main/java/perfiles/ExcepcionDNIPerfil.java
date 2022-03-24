package perfiles;

public class ExcepcionDNIPerfil extends ExcepcionPerfil{
	private static final long serialVersionUID = 1L;
	Perfil user;
	String info;
	
	public ExcepcionDNIPerfil(String s,Perfil p) {
		super(s,p);
		info = s;
		user = p;
	}
	
	@Override
	public String getMessage() {
		return info+" in perfil "+user.getNombre()+" "+user.getApellido();
	}
}
