package contenido;

import java.io.Serializable;
import java.time.LocalDate;
import contenido.excepciones.ExcepcionAno;
import contenido.excepciones.ExcepcionDisponibilidad;

abstract public class Contenido implements Serializable{
	private static final long serialVersionUID = 4858763186223118216L;
	private String titulo;
	private String autor;
	private String descripcion;
	private int ano;
	private String idioma;
	private Soporte soporte;
	private boolean prestable;
	private boolean disponible;
	private LocalDate fechaDisponibilidad;
	private int diasDePrestamo;
	private int ID;
	
	/**
	 * Constructor de la clase abstracta Contenido<br>
	 * Para construir el objeto por primera vez
	 * @param titulo Titulo del contenido
	 * @param autor Autor del contenido
	 * @param descripcion Un breve texto hablando sobre el contenido
	 * @param ano El año en el que se hizo el contenido
	 * @param idioma El idioma en el que está el contenido
	 * @param prestable Si el contenido se puede prestar o no
	 * @param Un elemento del enum Soporte en el que se guarda el contenido
	 * @throws ExcepcionAno
	 */
	public Contenido(String titulo, String autor, String descripcion, int ano, String idioma, boolean prestable, Soporte soporte, int diasDePrestamo) throws ExcepcionAno {
		this.titulo = titulo;
		this.autor = autor;
		this.descripcion = descripcion;
		setAno(ano);
		this.idioma = idioma;
		this.prestable = prestable;
		this.soporte = soporte;
		this.disponible = true;
		this.fechaDisponibilidad = null;
		this.setDiasDePrestamo((diasDePrestamo > 0)? diasDePrestamo: null);
	}
	
	/**
	 * Constructor de la clase abstracta contenido , pero definiendo las variables disponible y fecha de disponibilidad<br>
	 * Para construir la clase una vez ya sacada de la base de datos
	 * @param titulo
	 * @param autor
	 * @param descripcion
	 * @param ano
	 * @param idioma
	 * @param prestable
	 * @param soporte
	 * @param diasDePrestamo
	 * @param disponible
	 * @param fechaDisponibilidad
	 * @throws ExcepcionAno
	 */
	public Contenido(int id, String titulo, String autor, String descripcion, int ano, String idioma, boolean prestable, Soporte soporte, int diasDePrestamo,boolean disponible,LocalDate fechaDisponibilidad) throws ExcepcionAno {
		this.titulo = titulo;
		this.autor = autor;
		this.descripcion = descripcion;
		setAno(ano);
		this.idioma = idioma;
		this.prestable = prestable;
		this.soporte = soporte;
		this.disponible = disponible;
		this.fechaDisponibilidad = fechaDisponibilidad;
		this.setDiasDePrestamo((diasDePrestamo > 0)? diasDePrestamo: null);
		this.ID = id;
	}

	public void setAno(int ano) throws ExcepcionAno {
		if (ano < -2000) {
			throw new ExcepcionAno("El año es demasiado antiguo",this,ano);
		}
		if (ano > LocalDate.now().getYear()) {
			throw new ExcepcionAno("El año es futuro, no es correcto",this,ano);
		}
		this.ano = ano;
	}
	
	/* Getters */
	public String getTitulo() { return titulo; }
	public String getAutor() { return autor; }
	public String getDescripcion() { return descripcion; }
	public int getAno() { return ano; }
	public String getIdioma() { return idioma; }
	public boolean getPrestable() { return prestable; }
	public boolean getDisponibilidad() { return disponible; }
	public Soporte getSoporte() { return soporte; }
	public LocalDate getFechaDisponibilidad() { return fechaDisponibilidad; }
	public int getID() { return ID; }
	
	/**
	 * Este método modifica el valor de la variable disponible y fecha disponibilidad
	 */
	public void devolverContenido() {
		disponible = true;
		fechaDisponibilidad = null;
	}
	
	/**
	 * Este método modifica el valor de la variable disponible y fecha disponibilidad
	 * @throws ExcepcionDisponibilidad 
	 */
	public void prestarContenido() throws ExcepcionDisponibilidad{
		if(disponible) {
			fechaDisponibilidad = LocalDate.now().plusDays(this.diasDePrestamo);
			disponible=false;
		} else throw new ExcepcionDisponibilidad("El contenido que pide no está actualmente disponible",this);
	}
	
	@Override
	public int hashCode() {
		int hash = 67;
		hash = hash + 13 * autor.hashCode();
		hash = hash + 2 * titulo.hashCode();
		return hash;
	}

	public int getDiasDePrestamo() { return diasDePrestamo; }

	public void setDiasDePrestamo(int dias) { this.diasDePrestamo = (dias > 0)? dias:this.diasDePrestamo; }
}
