package es.library.databaseinspanish.contenido.excepciones;

import es.library.databaseinspanish.contenido.Contenido;

/**
 * Excepcion base para el resto de las excepciones basadas en el es.library.databaseinspanish.contenido de la biblioteca, libros y multimedia
 * @author danie
 *
 */
public class ExcepcionContenido extends Exception {
	private static final long serialVersionUID = -89723951L;
	private String info;
	public Contenido contenido;
	
	public ExcepcionContenido(String s, Contenido contenido) {
		super(s);
		this.info = s;
		this.contenido = contenido;
	}
	
	public String getInfo() { return info; }
	public Contenido getContenido() { return contenido; }
	
	@Override
	public String getMessage() {
		return info+" in es.library.databaseinspanish.contenido "+" "+((contenido != null)? contenido.getTitulo():"");
	}
}
