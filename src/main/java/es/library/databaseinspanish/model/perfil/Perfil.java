package es.library.databaseinspanish.model.perfil;

import java.time.LocalDate;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import es.library.databaseinspanish.model.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Perfil implements Model {
	
	private Long ID;
	private String nombre;
	private LocalDate fechaNacimiento;
	@JsonProperty("email") 
	private String correoElectronico;
	@JsonProperty("password") 
	private String contrasena;
	private Roles role;
	
	@JsonCreator
	public Perfil() {}
	
	public Perfil(Long iD, String nombre, LocalDate fechaNacimiento, String correoElectronico, String contrasena, Roles role) {
		ID = iD;
		this.nombre = nombre;
		this.fechaNacimiento = fechaNacimiento;
		this.correoElectronico = correoElectronico;
		this.contrasena = contrasena;
		this.role = role;
	}
	
	public Long getID() {return ID;}
	public void setID(Long iD) {ID = iD;}
	
	public String getNombre() { return nombre;}
	public void setNombre(String nombre) { this.nombre = nombre;}
	
	public LocalDate getFechaNacimiento() { return fechaNacimiento;}
	public void setFechaNacimiento(LocalDate fechaNacimiento) { this.fechaNacimiento = fechaNacimiento;}
	
	public String getCorreoElectronico() { return correoElectronico;}
	public void setCorreoElectronico(String correoElectronico) { this.correoElectronico = correoElectronico;}
	
	public String getContrasena() { return contrasena;}
	public void setContrasena(String contrasena) { this.contrasena = contrasena;}

	public Roles getRole() {return role;}

	public void setRole(Roles role) {this.role = role;}

	@Override
	public int hashCode() {
		return Objects.hash(ID, contrasena, correoElectronico, fechaNacimiento, nombre, role);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Perfil other = (Perfil) obj;
		return Objects.equals(ID, other.ID) && Objects.equals(contrasena, other.contrasena)
				&& Objects.equals(correoElectronico, other.correoElectronico)
				&& Objects.equals(fechaNacimiento, other.fechaNacimiento) && Objects.equals(nombre, other.nombre)
				&& role == other.role;
	}

	@Override
	public String toString() {
		return "Perfil [ID=" + ID + ", nombre=" + nombre + ", fechaNacimiento=" + fechaNacimiento
				+ ", correoElectronico=" + correoElectronico + ", contrasena=" + contrasena + ", role=" + role + "]";
	}

}