package es.library.databaseinspanish.model.contenido.modeltypes;

import java.net.URL;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import es.library.databaseinspanish.model.Model;
import es.library.databaseinspanish.model.contenido.Contenido;
import es.library.databaseinspanish.model.contenido.Soporte;
import es.library.databaseinspanish.model.contenido.types.Audio;
import es.library.databaseinspanish.model.contenido.types.Libro;
import es.library.databaseinspanish.model.contenido.types.Video;

@JsonInclude(Include.NON_NULL)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = AudioModel.class, name = "audio"),
        @JsonSubTypes.Type(value = VideoModel.class, name = "video"),
        @JsonSubTypes.Type(value = LibroModel.class, name = "libro")
})
@JsonIgnoreProperties(ignoreUnknown = true)
public class ContenidoModel implements Model {

	protected String titulo;
	protected String autor;
	protected String descripcion;
	protected int ano;
	protected String idioma;
	protected Soporte soporte;
	protected List<Long> ids;
	protected List<Contenido> contenidos;
	protected URL imagen;
	
	protected ContenidoModel() {}
	
	protected ContenidoModel(String titulo, String autor, String descripcion, int ano, String idioma, Soporte soporte, URL imagen) {
		this.titulo = titulo;
		this.autor = autor;
		this.descripcion = descripcion;
		this.ano = ano;
		this.idioma = idioma;
		this.soporte = soporte;
		this.imagen = imagen;
	}	
	
	public List<Long> getIds() {return ids;}

	public void setIds(List<Long> ids) {this.ids = ids;}

	public String getTitulo() {return titulo;}

	public void setTitulo(String titulo) {this.titulo = titulo;}

	public String getAutor() {return autor;}

	public void setAutor(String autor) {this.autor = autor;}

	public String getDescripcion() {return descripcion;}

	public void setDescripcion(String descripcion) {this.descripcion = descripcion;}

	public int getAno() {return ano;}

	public void setAno(int ano) {this.ano = ano;}

	public String getIdioma() {return idioma;}

	public void setIdioma(String idioma) {this.idioma = idioma;}

	public Soporte getSoporte() {return soporte;}

	public void setSoporte(Soporte soporte) {this.soporte = soporte;}

	public URL getImagen() {return imagen;}
	public void setImagen(URL imagen) {this.imagen = imagen;}
	
	@JsonIgnore
	public List<Contenido> getContenidos() {return contenidos;}
	@JsonIgnore
	public void setContenidos(List<Contenido> contenidos) {this.contenidos = contenidos;}
	
	@Override
	public int hashCode() {
		return Objects.hash(ano, autor, descripcion, idioma, soporte, titulo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ContenidoModel other = (ContenidoModel) obj;
		return ano == other.ano && Objects.equals(autor, other.autor) && Objects.equals(descripcion, other.descripcion)
				&& Objects.equals(idioma, other.idioma) && soporte == other.soporte
				&& Objects.equals(titulo, other.titulo);
	}

	public static ContenidoModel ofContenido(Contenido c) {	
		return switch (Type.ofContenido(c)) {
		case libro -> new LibroModel(
				c.getTitulo(), 
				c.getAutor(), 
				c.getDescripcion(),
				c.getAno(), 
				c.getIdioma(), 
				c.getSoporte(),
				c.getImagen(), 
				((Libro)c).getISBN(), ((Libro)c).getPaginas(), ((Libro)c).getEditorial());
		case video -> new VideoModel(c.getTitulo(), 
				c.getAutor(), 
				c.getDescripcion(),
				c.getAno(), 
				c.getIdioma(), 
				c.getSoporte(),
				c.getImagen(),
				((Video)c).getDuracion(),
				((Video)c).getEdadRecomendada(),
				((Video)c).getCalidad());
		case audio -> new AudioModel(c.getTitulo(), 
				c.getAutor(), 
				c.getDescripcion(),
				c.getAno(), 
				c.getIdioma(), 
				c.getSoporte(),
				c.getImagen(),
				((Audio)c).getDuracion());
		};
	}
	
	
	@Override
	public String toString() {
		return "ContenidoModel [titulo=" + titulo + ", autor=" + autor + ", descripcion=" + descripcion + ", ano=" + ano
				+ ", idioma=" + idioma + ", soporte=" + soporte + "]";
	}

	public Contenido toContenido() {
		return new Contenido(null, titulo, autor, descripcion, ano, idioma, soporte, false, null, false, imagen);
	}
	
	public enum Type{
		audio,video,libro;
		
		public static Type ofContenido(Contenido c) {
			Type tipo = null;
			if(c instanceof Video) {
				tipo = Type.video;
			}
			else if (c instanceof Audio) {
				tipo = Type.audio;
			}
			else if (c instanceof Libro) {
				tipo = Type.libro;
			}
			return tipo;
		}
	}
}
