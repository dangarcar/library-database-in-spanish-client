package es.library.databaseinspanish.model.prestamo;

import java.time.ZonedDateTime;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import es.library.databaseinspanish.model.Model;

@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Prestamo implements Model {

	private Long ID;
	private Long IDPerfil;
	private Long IDContenido;
	private ZonedDateTime fechaHoraPrestamo;
	private ZonedDateTime fechaHoraDevolucion;
	private int diasdePrestamo;
	private boolean devuelto;
	
	@JsonCreator
	public Prestamo() {}
	
	public Prestamo(Long iD, Long iDContenido, Long iDPerfil, ZonedDateTime fechaHoraPrestamo, ZonedDateTime fechaHoraDevolucion,
			int diasdePrestamo, boolean devuelto) {
		super();
		ID = iD;
		IDPerfil = iDPerfil;
		IDContenido = iDContenido;
		this.fechaHoraPrestamo = fechaHoraPrestamo;
		this.fechaHoraDevolucion = fechaHoraDevolucion;
		this.diasdePrestamo = diasdePrestamo;
		this.devuelto = devuelto;
	}
	
	public Long getID() {return ID;}
	public void setID(Long iD) {ID = iD;}
	
	public Long getIDPerfil() {return IDPerfil;}
	public void setIDPerfil(Long iDPerfil) {IDPerfil = iDPerfil;}
	
	public Long getIDContenido() {return IDContenido;}
	public void setIDContenido(Long iDContenido) {IDContenido = iDContenido;}
	
	public ZonedDateTime getFechaHoraPrestamo() {return fechaHoraPrestamo;}
	public void setFechaHoraPrestamo(ZonedDateTime fechaHoraPrestamo) {this.fechaHoraPrestamo = fechaHoraPrestamo;}
	
	public ZonedDateTime getFechaHoraDevolucion() {return fechaHoraDevolucion;}
	public void setFechaHoraDevolucion(ZonedDateTime fechaHoraDevolucion) {this.fechaHoraDevolucion = fechaHoraDevolucion;}
	
	public int getDiasdePrestamo() {return diasdePrestamo;}
	public void setDiasdePrestamo(int diasdePrestamo) {this.diasdePrestamo = diasdePrestamo;}
	
	public boolean isDevuelto() {return devuelto;}
	public void setDevuelto(boolean devuelto) {this.devuelto = devuelto;}
	
	@Override
	public int hashCode() {
		return Objects.hash(ID, IDContenido, IDPerfil, devuelto, diasdePrestamo, fechaHoraDevolucion,
				fechaHoraPrestamo);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Prestamo other = (Prestamo) obj;
		return Objects.equals(ID, other.ID) && Objects.equals(IDContenido, other.IDContenido)
				&& Objects.equals(IDPerfil, other.IDPerfil) && devuelto == other.devuelto
				&& diasdePrestamo == other.diasdePrestamo
				&& Objects.equals(fechaHoraDevolucion, other.fechaHoraDevolucion)
				&& Objects.equals(fechaHoraPrestamo, other.fechaHoraPrestamo);
	}
	
	@Override
	public String toString() {
		return "Prestamo [ID=" + ID + ", IDPerfil=" + IDPerfil + ", IDContenido=" + IDContenido + ", fechaHoraPrestamo="
				+ fechaHoraPrestamo + ", fechaHoraDevolucion=" + fechaHoraDevolucion + ", diasdePrestamo="
				+ diasdePrestamo + ", devuelto=" + devuelto + "]";
	}
}

