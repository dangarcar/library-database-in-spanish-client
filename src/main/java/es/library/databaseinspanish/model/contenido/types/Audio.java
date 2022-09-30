package es.library.databaseinspanish.model.contenido.types;

import java.time.LocalDate;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import es.library.databaseinspanish.model.contenido.Contenido;
import es.library.databaseinspanish.model.contenido.Soporte;

@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Audio extends Contenido{
	
	private Double duracion;

	@JsonCreator
	public Audio() {}
	
	public Audio(Long iD, String titulo, String autor, String descripcion, Integer ano, String idioma, Soporte soporte,
			boolean prestable, Integer diasDePrestamo, boolean disponible, LocalDate fechaDisponibilidad,
			Double duracion) {
		super(iD, titulo, autor, descripcion, ano, idioma, soporte, prestable, diasDePrestamo, disponible,
				fechaDisponibilidad);
		this.duracion = duracion;
	}
	
	public Double getDuracion() {return duracion;}
	public void setDuracion(Double duracion) {this.duracion = duracion;}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(duracion);
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
		Audio other = (Audio) obj;
		return super.equals(obj) && Objects.equals(duracion, other.duracion);
	}

	@Override
	public String toString() {
		return super.toString()+"\nAudio [duracion=" + duracion +"]";
	}
}