package contenido;

import contenido.excepciones.ExcepcionAno;
import contenido.excepciones.ExcepcionDuracion;
import contenido.excepciones.ExcepcionSoporte;

public class Audio extends Contenido{
	private double duracion;
	
	/**
	 * 
	 * @param titulo
	 * @param autor
	 * @param descripcion
	 * @param ano
	 * @param idioma
	 * @param prestable
	 * @param soporte
	 * @param duracionEnMinutos
	 * @throws ExcepcionAno
	 * @throws ExcepcionDuracion
	 * @throws ExcepcionSoporte
	 */
	public Audio(String titulo, String autor, String descripcion, int ano, String idioma, boolean prestable, Soporte soporte, double duracionEnMinutos) 
			throws ExcepcionAno, ExcepcionDuracion, ExcepcionSoporte {
		super(titulo, autor, descripcion, ano, idioma, prestable, soporte);
		if (!(soporte.isMultimedia())) {
			throw new ExcepcionSoporte("El soporte seleccionado no es compatible con audio",this,soporte);
		}
		setDuracion(duracionEnMinutos);
	}
	
	public double getDuracion() { return duracion; }
	
	public void setDuracion(double duracion) throws ExcepcionDuracion {
		if (duracion < 0) {
			throw new ExcepcionDuracion("La duracion debe ser un numero positivo",this,duracion);
		}
		this.duracion = duracion;
	}
}
