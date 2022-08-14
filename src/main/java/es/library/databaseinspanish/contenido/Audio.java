package es.library.databaseinspanish.contenido;

import java.time.LocalDate;

import es.library.databaseinspanish.contenido.excepciones.ExcepcionAno;
import es.library.databaseinspanish.contenido.excepciones.ExcepcionDuracion;
import es.library.databaseinspanish.contenido.excepciones.ExcepcionSoporte;

/**
 * Clase hija de Contenido encargada de los audios de la biblioteca
 * @author Daniel García
 *
 */
public class Audio extends Contenido{
	private static final long serialVersionUID = -549498529487274208L;
	private double duracion;
	private final int IDAudio;
	
	/**
	 * el constructor para crear el objeto desde cero
	 * @param titulo
	 * @param autor
	 * @param descripcion
	 * @param ano
	 * @param idioma
	 * @param prestable
	 * @param soporte
	 * @param diasDePrestamo
	 * @param duracionEnMinutos
	 * @throws ExcepcionAno
	 * @throws ExcepcionDuracion
	 * @throws ExcepcionSoporte
	 */
	public Audio(String titulo, String autor, String descripcion, int ano, String idioma, boolean prestable, Soporte soporte, int diasDePrestamo, double duracionEnMinutos) 
			throws ExcepcionAno, ExcepcionDuracion, ExcepcionSoporte {
		super(titulo, autor, descripcion, ano, idioma, prestable, soporte,diasDePrestamo);
		if (!(soporte.isMultimedia())) {
			throw new ExcepcionSoporte("El soporte seleccionado no es compatible con audio",this,soporte);
		}
		setDuracion(duracionEnMinutos);
		this.IDAudio = getIdentifier();
	}
	
	/**
	 * El constructor para la clase ya sacada de la BBDD
	 * @param id
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
	 * @param duracionEnMinutos
	 * @param IDAudio
	 * @throws ExcepcionAno
	 * @throws ExcepcionDuracion
	 * @throws ExcepcionSoporte
	 */
	public Audio(int id, String titulo, String autor, String descripcion, int ano, String idioma, boolean prestable, Soporte soporte, int diasDePrestamo, boolean disponible, LocalDate fechaDisponibilidad, double duracionEnMinutos, int IDAudio) 
			throws ExcepcionAno, ExcepcionDuracion, ExcepcionSoporte {
		super(id,titulo, autor, descripcion, ano, idioma, prestable, soporte,diasDePrestamo,disponible,fechaDisponibilidad);
		if (!(soporte.isMultimedia())) {
			throw new ExcepcionSoporte("El soporte seleccionado no es compatible con audio",this,soporte);
		}
		setDuracion(duracionEnMinutos);
		this.IDAudio = IDAudio;
	}
	
	public double getDuracion() { return duracion; }
	
	public void setDuracion(double duracion) throws ExcepcionDuracion {
		if (duracion < 0) {
			throw new ExcepcionDuracion("La duracion debe ser un numero positivo",this,duracion);
		}
		this.duracion = duracion;
	}
	
	public int getIDAudio() { return IDAudio; }
	private int getIdentifier() {
		return (int) (getTitulo().hashCode()+Math.sqrt(duracion));
	}

	@Override
	public long getSpecificID() {
		return IDAudio;
	}
	
}
