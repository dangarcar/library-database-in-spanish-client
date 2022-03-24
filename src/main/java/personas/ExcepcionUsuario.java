package personas;

public class ExcepcionUsuario extends Exception {
	private static final long serialVersionUID = 1L;
	Usuario user;
	String info;
	
	/**
	 * Esta clase engloba todas las excepciones que le puedan pasar a un usuario
	 * @param s el texto que se enviara a la excepcion
	 * @param p el usuario que ha producido la excepcion
	 */
	public ExcepcionUsuario(String s, Usuario p) {
		super(s);
		info = s;
		user = p;
	}
	
	public String getMessage() {
		
		return info+" in Usuario "+user.getDNI()+user.getLetraDNI();
	}
}
