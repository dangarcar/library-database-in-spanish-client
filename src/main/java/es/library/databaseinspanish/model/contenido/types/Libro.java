package es.library.databaseinspanish.model.contenido.types;

import java.net.URL;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import es.library.databaseinspanish.model.contenido.Contenido;
import es.library.databaseinspanish.model.contenido.Soporte;

@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Libro extends Contenido{
	
	private String ISBN;
	private Integer paginas;
	private String editorial;
	
	@JsonCreator
	public Libro() {}
	
	public Libro(Long iD, String titulo, String autor, String descripcion, Integer ano, String idioma, Soporte soporte,
			boolean prestable, Integer diasDePrestamo, boolean disponible, URL imagen, String iSBN,
			Integer paginas, String editorial) {
		super(iD, titulo, autor, descripcion, ano, idioma, soporte, prestable, diasDePrestamo, disponible, imagen);
		ISBN = iSBN;
		this.paginas = paginas;
		this.editorial = editorial;
	}
	
	public String getISBN() {return ISBN;}
	public void setISBN(String iSBN) {ISBN = iSBN;}
	
	public Integer getPaginas() {return paginas;}
	public void setPaginas(Integer paginas) {this.paginas = paginas;}
	
	public String getEditorial() {return editorial;}
	public void setEditorial(String editorial) {this.editorial = editorial;}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(ISBN, editorial, paginas);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Libro other = (Libro) obj;
		return super.equals(obj) && Objects.equals(ISBN, other.ISBN) && Objects.equals(editorial, other.editorial)
				&& Objects.equals(paginas, other.paginas);
	}

	@Override
	public String toString() {
		return super.toString()+"\nLibros [ISBN=" + ISBN + ", paginas=" + paginas + ", editorial=" + editorial + "]";
	}
	
	
}