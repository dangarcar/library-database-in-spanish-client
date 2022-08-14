package es.library.databaseinspanish.contenido.excepciones;

import es.library.databaseinspanish.contenido.Contenido;

public class ExcepcionEdadRecomendada extends ExcepcionContenido{
	private static final long serialVersionUID = -1014250849695883571L;
	private int edad;
	
	public ExcepcionEdadRecomendada(String s, Contenido contenido, int edad) {
		super(s, contenido);
		this.edad = edad;
	}
	
	public String getMessage() {
		return super.getMessage()+" con "+edad+" años de edad recomendada";
	}
}
