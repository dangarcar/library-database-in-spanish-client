package BBDD.excepciones;

import java.io.File;

public class ExcepcionBBDD extends Exception{
	private static final long serialVersionUID = -7887149174684281144L;
	private String info;
	private File fichero;
	
	public ExcepcionBBDD(String s,File f) {
		super(s);
		info = s;
		fichero = f;
	}
	
	public String getMessage() {
		return info+" in BBDD "+fichero;
	}
	
}
