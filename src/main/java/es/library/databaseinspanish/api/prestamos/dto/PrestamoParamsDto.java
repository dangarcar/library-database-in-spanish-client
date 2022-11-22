package es.library.databaseinspanish.api.prestamos.dto;

import java.time.LocalDateTime;

public class PrestamoParamsDto {
	
	private Long idContenido;
	private Long idPerfil;
	private Integer minDias;
	private Integer maxDias;
	private LocalDateTime fromPrestamo;
	private LocalDateTime toPrestamo;
	private LocalDateTime fromDevolucion;
	private LocalDateTime toDevolucion;
	private Boolean devuelto;
	
	public PrestamoParamsDto() {}

	public PrestamoParamsDto(Long idContenido, Long idPerfil, Integer minDias, Integer maxDias,
			LocalDateTime fromPrestamo, LocalDateTime toPrestamo, LocalDateTime fromDevolucion,
			LocalDateTime toDevolucion, Boolean devuelto) {
		super();
		this.idContenido = idContenido;
		this.idPerfil = idPerfil;
		this.minDias = minDias;
		this.maxDias = maxDias;
		this.fromPrestamo = fromPrestamo;
		this.toPrestamo = toPrestamo;
		this.fromDevolucion = fromDevolucion;
		this.toDevolucion = toDevolucion;
		this.devuelto = devuelto;
	}
	
	public Long getIdContenido() {return idContenido;}
	public void setIdContenido(Long idContenido) {this.idContenido = idContenido;}

	public Long getIdPerfil() {return idPerfil;}
	public void setIdPerfil(Long idPerfil) {this.idPerfil = idPerfil;}

	public Integer getMinDias() {return minDias;}
	public void setMinDias(Integer minDias) {this.minDias = minDias;}

	public Integer getMaxDias() {return maxDias;}
	public void setMaxDias(Integer maxDias) {this.maxDias = maxDias;}

	public LocalDateTime getFromPrestamo() {return fromPrestamo;}
	public void setFromPrestamo(LocalDateTime fromPrestamo) {this.fromPrestamo = fromPrestamo;}

	public LocalDateTime getToPrestamo() {return toPrestamo;}
	public void setToPrestamo(LocalDateTime toPrestamo) {this.toPrestamo = toPrestamo;}

	public LocalDateTime getFromDevolucion() {return fromDevolucion;}
	public void setFromDevolucion(LocalDateTime fromDevolucion) {this.fromDevolucion = fromDevolucion;}

	public LocalDateTime getToDevolucion() {return toDevolucion;}
	public void setToDevolucion(LocalDateTime toDevolucion) {this.toDevolucion = toDevolucion;}

	public Boolean getDevuelto() {return devuelto;}
	public void setDevuelto(Boolean devuelto) {this.devuelto = devuelto;}

	@Override
	public String toString() {
		return "PrestamoParamsDto [idContenido=" + idContenido + ", idPerfil=" + idPerfil + ", minDias=" + minDias
				+ ", maxDias=" + maxDias + ", fromPrestamo=" + fromPrestamo + ", toPrestamo=" + toPrestamo
				+ ", fromDevolucion=" + fromDevolucion + ", toDevolucion=" + toDevolucion + ", devuelto=" + devuelto
				+ "]";
	}
	
}
