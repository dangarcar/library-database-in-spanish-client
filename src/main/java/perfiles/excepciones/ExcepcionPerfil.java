package perfiles.excepciones;

import perfiles.Perfil;

public class ExcepcionPerfil extends Exception {
	private static final long serialVersionUID = 1L;
	private Perfil user;
	private String info;
	
	/**
	 * Esta clase engloba todas las excepciones que le puedan pasar a un perfil
	 * @param s el texto que se enviara a la excepcion
	 * @param p el perfil que ha producido la excepcion
	 */
	public ExcepcionPerfil(String s, Perfil p) {
		super(s);
		info = s;
		user = p;
	}
	
	public Perfil getPerfil() { return user; }
	public String getInfo() { return info; }
	
	public String getMessage() {
		
		return info+" in perfil "+user.getDNI()+user.getLetraDNI();
	}
}
