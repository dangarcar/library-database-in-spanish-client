package es.library.databaseinspanish.contenido.excepciones;

import es.library.databaseinspanish.contenido.Contenido;

public class ExcepcionAno extends ExcepcionContenido{
	private static final long serialVersionUID = 5687792195315349861L;
	private int ano;
	
	public ExcepcionAno(String s, Contenido contenido, int ano) {
		super(s, contenido);
		this.ano = ano;
	}
	
	public String getMessage() {
		return super.getMessage()+" en el a�o "+ano;
	}
}
