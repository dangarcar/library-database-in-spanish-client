package es.library.databaseinspanish.contenido.types;

import java.time.LocalDate;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import es.library.databaseinspanish.contenido.Soporte;

@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Video extends Audio{
	
	private Integer edadRecomendada;
	private Integer calidad;
	
	@JsonCreator
	public Video() {}
	
	public Video(Long iD, String titulo, String autor, String descripcion, Integer ano, String idioma, Soporte soporte,
			boolean prestable, Integer diasDePrestamo, boolean disponible, LocalDate fechaDisponibilidad,
			Double duracion, Integer edadRecomendada, Integer calidad) {
		super(iD, titulo, autor, descripcion, ano, idioma, soporte, prestable, diasDePrestamo, disponible,
				fechaDisponibilidad, duracion);
		this.edadRecomendada = edadRecomendada;
		this.calidad = calidad;
	}
	
	public Integer getEdadRecomendada() {return edadRecomendada;}
	public void setEdadRecomendada(Integer edadRecomendada) {this.edadRecomendada = edadRecomendada;}
	
	public Integer getCalidad() {return calidad;}
	public void setCalidad(Integer calidad) {this.calidad = calidad;}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(calidad, edadRecomendada);
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
		Video other = (Video) obj;
		return super.equals(obj) && Objects.equals(calidad, other.calidad) && Objects.equals(edadRecomendada, other.edadRecomendada);
	}

	@Override
	public String toString() {
		return super.toString()+"\nVideos [edadRecomendada=" + edadRecomendada + ", calidad=" + calidad + "]";
	}
}