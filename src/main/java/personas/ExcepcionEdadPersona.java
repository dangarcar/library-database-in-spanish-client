package personas;

public class ExcepcionEdadPersona extends Exception {
	private static final long serialVersionUID = 1L;
	Persona persona;
	String info;
	public ExcepcionEdadPersona(String s, Persona p) {
		super(s);
		info = s;
		persona = p;
	}
	
	public String getMessage() {
		
		return "Error: "+info+"in Persona "+persona.getDNI();
	}
}
