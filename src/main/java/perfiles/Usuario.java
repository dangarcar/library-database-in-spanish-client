package perfiles;

import java.time.LocalDate;

import contenido.Contenido;
import perfiles.excepciones.ExcepcionPerfil;

public class Usuario extends Perfil{

	public Usuario(String nombre, String apellido, LocalDate fecha, int dni, String direccionCasa,String correoElectronico) 
			throws ExcepcionPerfil {
		super(nombre, apellido, fecha, dni, direccionCasa, correoElectronico);
	}
	
	public boolean solicitar(Contenido c) {
		
	}
	
	public boolean devolver(Contenido c) {
		
	}
	
	public boolean reservar(Contenido c) {
		
	}

}
