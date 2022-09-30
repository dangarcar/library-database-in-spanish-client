package es.library.databaseinspanish.api.perfil.dto;

import java.time.LocalDate;

import es.library.databaseinspanish.model.perfil.Roles;

public class PerfilParamsDto {

	private String query; 
	private String nombre; 
	private String email; 
	private LocalDate fromNacimiento;
	private LocalDate toNacimiento; 
	private Roles roles;
	
	public PerfilParamsDto() {}

	public PerfilParamsDto(String query, String nombre, String email, LocalDate fromNacimiento, LocalDate toNacimiento,
			Roles roles) {
		this.query = query;
		this.nombre = nombre;
		this.email = email;
		this.fromNacimiento = fromNacimiento;
		this.toNacimiento = toNacimiento;
		this.roles = roles;
	}
	
	public String getQuery() {return query;}
	public void setQuery(String query) {this.query = query;}

	public String getNombre() {return nombre;}
	public void setNombre(String nombre) {this.nombre = nombre;}

	public String getEmail() {return email;}
	public void setEmail(String email) {this.email = email;}

	public LocalDate getFromNacimiento() {return fromNacimiento;}
	public void setFromNacimiento(LocalDate fromNacimiento) {this.fromNacimiento = fromNacimiento;}

	public LocalDate getToNacimiento() {return toNacimiento;}
	public void setToNacimiento(LocalDate toNacimiento) {this.toNacimiento = toNacimiento;}

	public Roles getRoles() {return roles;}
	public void setRoles(Roles roles) {this.roles = roles;}

	@Override
	public String toString() {
		return "PerfilParamsDto [query=" + query + ", nombre=" + nombre + ", email=" + email + ", fromNacimiento="
				+ fromNacimiento + ", toNacimiento=" + toNacimiento + ", roles=" + roles + "]";
	}
	
}
