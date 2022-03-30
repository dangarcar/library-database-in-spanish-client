package contenido;

import java.time.LocalDate;

import contenido.excepciones.ExcepcionAno;
import contenido.excepciones.ExcepcionDisponibilidad;

abstract public class Contenido {
	private String titulo;
	private String autor;
	private String descripcion;
	private int ano;
	private String idioma;
	private Soporte soporte;
	private boolean prestable;
	private boolean disponible;
	private LocalDate fechaDisponibilidad;
	
	public Contenido(String titulo, String autor, String descripcion, int ano, String idioma, boolean prestable, Soporte soporte) throws ExcepcionAno {
		this.titulo = titulo;
		this.autor = autor;
		this.descripcion = descripcion;
		setAno(ano);
		this.idioma = idioma;
		this.prestable = prestable;
		this.soporte = soporte;
		this.disponible = true;
		this.fechaDisponibilidad = null;
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
	
	public void setDisponibilidad(boolean disp) {
		disponible = disp;
		fechaDisponibilidad = null;
	}
	
	public void setDisponibilidad(int diaDePrestamo) throws ExcepcionDisponibilidad {
		if(diaDePrestamo < 1) {
			throw new ExcepcionDisponibilidad("Un préstamo debe durar al menos un día",this,diaDePrestamo);
		}
		fechaDisponibilidad = LocalDate.now().plusDays(diaDePrestamo);
		disponible=false;
	}

}
