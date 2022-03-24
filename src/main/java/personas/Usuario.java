package personas;

import java.time.LocalDate;

public class Usuario {
	private final char[] letrasDNI = {'T','R','W','A','G','M','Y','F','P','D','X','B','N','J','Z','S','Q','V','H','L','C','K','E'};
	private String nombre;
	private String apellido;
	private LocalDate fechaNacimiento = null;
	private String direccionDeCasa;
	private String correoElectronico;
	private int DNI;
	
	/**
	 * Constructor del Usuario base
	 * @param nombre
	 * @param apellido
	 * @param fecha
	 * @param dni
	 * @param direccionCasa
	 * @param correoElectronico
	 * @throws ExcepcionUsuario
	 */
	public Usuario(String nombre, String apellido, LocalDate fecha, int dni, String direccionCasa, String correoElectronico) throws ExcepcionUsuario{
		setNombre(nombre);
		setApellido(apellido);
		setDNI(dni);
		setFechaNacimiento(fecha);
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
	public void setFechaNacimiento(LocalDate fechaDeNacimiento) throws ExcepcionUsuario {
		if (getEdad(fechaDeNacimiento) < 6) {
			throw new ExcepcionUsuario("La fecha introducida es demasiado reciente, debe ser mayor de 6 años",this);
		}
		if (getEdad(fechaDeNacimiento) > 120) {
			throw new ExcepcionUsuario("La fecha introducida es demasiado antigua, debe ser menor de 120 años",this);
		}
		this.fechaNacimiento = fechaDeNacimiento;
	}

	public void setDireccionDeCasa(String direccionDeCasa) {
		this.direccionDeCasa = direccionDeCasa;
	}

	public void setCorreoElectronico(String correoElectronico) throws ExcepcionUsuario {
		if (!(correoElectronico.contains("@"))) {
			throw new ExcepcionUsuario("La direccion de correo electronico no contiene arroba, no es valida",this);
		}
		this.correoElectronico = correoElectronico;
	}

	public void setDNI(int dni) throws ExcepcionDNIUsuario {
		if (dni > 99999999 || dni < 0) {
			throw new ExcepcionDNIUsuario("El numero de DNI no es correcto",this);
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
	
	/**
	 * Devuelve la edad en años del usuario respecto a la actual
	 * @return
	 */
	public int getEdad() {
		int edad = LocalDate.now().getYear() - fechaNacimiento.getYear();
		return edad;
	}
	
	/**
	 * Devuelve los años que ha pasado desde la fecha que se pasa por parametro
	 * @param fecha
	 * @return
	 */
	public int getEdad(LocalDate fecha) {
		int edad = LocalDate.now().getYear() - fecha.getYear();
		return edad;
	}
	
	@Override
	/* Metodos sobreescritos de la clase Object, toString y equals*/
	public String toString() {
		return "Nombre: "+nombre+"\nApellidos: "+apellido+"\nFecha de nacimiento: "+fechaNacimiento.toString()+"\nDNI: "+DNI+getLetraDNI();
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
	public boolean equals(Usuario user) {
		if(user.getDNI() == this.DNI) {
			return true;
		}
		return false;
	}
	
	
}
