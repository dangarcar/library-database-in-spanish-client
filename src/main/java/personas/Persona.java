package personas;

import java.time.LocalDate;

public class Persona {
	private final char[] letrasDNI = {'T','R','W','A','G','M','Y','F','P','D','X','B','N','J','Z','S','Q','V','H','L','C','K','E'};
	private String nombre;
	private String apellido;
	private LocalDate fechaNacimiento;
	private String direccionDeCasa;
	private String correoElectronico;
	private int DNI;
	
	public Persona(String nombre, String apellido, LocalDate fechaDeNacimiento, int dni, String direccionCasa, String correoElectronico) {
		setNombre(nombre);
		setApellido(apellido);
		try {
			setFechaNacimiento(fechaDeNacimiento);
		} catch (ExcepcionEdadPersona e) {
			System.out.println(e.getMessage());
		}
		setDNI(dni);
		setDireccionDeCasa(direccionCasa);
		setCorreoElectronico(correoElectronico);
		
	}

	/* Setters: Aquí compruebo si son correctos los datos */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	/**
	 * Es un setter que comprueba que la edad de la persona es menor de 120 años y mayor de 6
	 * @param fechaNacimiento
	 * @throws ExcepcionEdadPersona 
	 */
	public void setFechaNacimiento(LocalDate fechaNacimiento) throws ExcepcionEdadPersona {
		if (LocalDate.now().getYear() < fechaNacimiento.getYear()+6) {
			throw new ExcepcionEdadPersona("La fecha introducida es demasiado baja, debe ser mayor de 6 años",this);
		}
		if (LocalDate.now().getYear() > fechaNacimiento.getYear()+120) {
			throw new ExcepcionEdadPersona("La fecha introducida es demasiado elevada, debe ser menor de 120 años",this);
		}
		this.fechaNacimiento = fechaNacimiento;
	}

	public void setDireccionDeCasa(String direccionDeCasa) {
		this.direccionDeCasa = direccionDeCasa;
	}

	public void setCorreoElectronico(String correoElectronico) {
		if (!(correoElectronico.contains("@"))) {
			throw new IllegalArgumentException("La direccion de correo electronico no contiene arroba, no es valida");
		}
		this.correoElectronico = correoElectronico;
	}

	public void setDNI(int dni) {
		if (dni > 99999999 || dni < 0) {
			throw new IllegalArgumentException("El numero de DNI no es correcto");
		}
		DNI = dni;
	}

	
	/* Getters */
	public String getNombre() {
		return nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public String getDireccionDeCasa() {
		return direccionDeCasa;
	}

	public String getCorreoElectronico() {
		return correoElectronico;
	}

	public int getDNI() {
		return DNI;
	}

	public char getLetraDNI() {
		int resto = this.DNI % 23;
		return this.letrasDNI[resto];
	}
	
	/* Metodos sobreescritos de la clase Object, toString y equals*/
	public String toString() {
		return "Nombre: "+nombre+"\nApellidos: "+apellido+"Fecha de nacimiento: "+fechaNacimiento.toString()+"\nDNI: "+DNI;
	}
	
	/**
	 * Devuelve true si el DNI pasado por parametro es igual al de la persona
	 * @param dni
	 * @return
	 */
	public boolean equals(int dni) {
		if (this.DNI == dni) {
			return true;
		}
		return false;
	}
	/**
	 * Devuelve true si el DNI de la persona pasada por parametro es igual al this.DNI
	 * @param persona
	 * @return
	 */
	public boolean equals(Persona persona) {
		if(persona.getDNI() == this.DNI) {
			return true;
		}
		return false;
	}
	
	
}
