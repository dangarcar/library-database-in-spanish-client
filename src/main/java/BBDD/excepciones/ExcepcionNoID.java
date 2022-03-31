package BBDD.excepciones;

import java.io.File;

public class ExcepcionNoID extends ExcepcionBBDD{
	private static final long serialVersionUID = -9005643945393578805L;
	private int ID;
	
	public ExcepcionNoID(String s, File f, int id) {
		super(s,f);
		this.ID = id;
	}
	
	public String getMessage() {
		return super.getMessage()+" con ID "+ID;
	}
	
}
