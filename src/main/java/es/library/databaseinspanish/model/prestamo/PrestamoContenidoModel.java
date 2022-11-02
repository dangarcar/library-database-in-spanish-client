package es.library.databaseinspanish.model.prestamo;

import java.util.Objects;

import es.library.databaseinspanish.model.Model;
import es.library.databaseinspanish.model.contenido.Contenido;

public class PrestamoContenidoModel implements Model {

	private Prestamo prestamo;
	private Contenido contenido;
	
	public PrestamoContenidoModel() {}
	
	public PrestamoContenidoModel(Prestamo prestamo, Contenido contenido) {
		this.prestamo = prestamo;
		this.contenido = contenido;
	}
	
	public Prestamo getPrestamo() {
		return prestamo;
	}
	public void setPrestamo(Prestamo prestamo) {
		this.prestamo = prestamo;
	}
	public Contenido getContenido() {
		return contenido;
	}
	public void setContenido(Contenido contenido) {
		this.contenido = contenido;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(contenido, prestamo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PrestamoContenidoModel other = (PrestamoContenidoModel) obj;
		return Objects.equals(contenido, other.contenido) && Objects.equals(prestamo, other.prestamo);
	}

}
