package perfiles;

public class ExcepcionDNIPerfil extends ExcepcionPerfil{
	private static final long serialVersionUID = 1L;
	
	public ExcepcionDNIPerfil(String s,Perfil p) {
		super(s,p);
	}
	
	@Override
	public String getMessage() {
		return super.getInfo()+" in perfil "+super.getPerfil().getNombre()+" "+super.getPerfil().getApellido();
	}
}
