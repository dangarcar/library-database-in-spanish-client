package contenido;

import contenido.excepciones.ExcepcionAno;
import contenido.excepciones.ExcepcionPaginas;
import contenido.excepciones.ExcepcionSoporte;

/**
 * Esta clase sirve tanto como para libros como para revistas o prensa, o incluso e-books
 * @author danie
 *
 */
public class Libros extends Contenido{
	private static final long serialVersionUID = 7380692865029324516L;
	private long ISBN;
	private int paginas;
	private String editorial;
	
	/**
	 * 
	 * @param titulo
	 * @param autor
	 * @param descripcion
	 * @param ano
	 * @param idioma
	 * @param prestable
	 * @param soporte
	 * @param isbn
	 * @param pags
	 * @param editorial
	 * @throws ExcepcionAno
	 * @throws ExcepcionSoporte
	 * @throws ExcepcionPaginas
	 */
	public Libros(String titulo, String autor, String descripcion, int ano, String idioma, boolean prestable, Soporte soporte, int diasDePrestamo, long isbn, int pags, String editorial) 
			throws ExcepcionAno, ExcepcionSoporte, ExcepcionPaginas {
		super(titulo, autor, descripcion, ano, idioma, prestable, soporte, diasDePrestamo);
		if (soporte.isMultimedia()) {
			throw new ExcepcionSoporte("El soporte seleccionado no es compatible con video",this,soporte);
		}
		setPaginas(pags);
		this.editorial = editorial;
		this.ISBN = isbn;
	}

	/* Getters */
	public long getISBN() { return ISBN; }
	public int getPaginas() { return paginas; }
	public String getEditorial() { return editorial; }

	
	public void setPaginas(int pags) throws ExcepcionPaginas {
		if (pags < 0) {
			throw new ExcepcionPaginas("El número de páginas no debe ser un numero negativo",this,pags);
		}
		if (pags == 0) {
			throw new ExcepcionPaginas("El número de páginas no puede ser cero, no tiene sentido",this,pags);
		}
		this.paginas = pags;
	}
	
}
