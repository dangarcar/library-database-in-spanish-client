package contenido;

import contenido.excepciones.ExcepcionAno;
import contenido.excepciones.ExcepcionCalidad;
import contenido.excepciones.ExcepcionDuracion;
import contenido.excepciones.ExcepcionEdadRecomendada;
import contenido.excepciones.ExcepcionSoporte;

public class Videos extends Audio{
	private static final long serialVersionUID = 168879872457815054L;
	private int edadRecomendada;
	private int calidad;
	
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
	 * @param edad
	 * @param calidad
	 * @throws ExcepcionAno
	 * @throws ExcepcionEdadRecomendada
	 * @throws ExcepcionDuracion
	 * @throws ExcepcionCalidad
	 * @throws ExcepcionSoporte
	 */
	public Videos(String titulo, String autor, String descripcion, int ano, String idioma, boolean prestable, Soporte soporte, int diasDePrestamo, double duracionEnMinutos, int edad, int calidad) 
			throws ExcepcionAno, ExcepcionEdadRecomendada, ExcepcionDuracion, ExcepcionCalidad, ExcepcionSoporte {
		super(titulo, autor, descripcion, ano, idioma, prestable, soporte, diasDePrestamo, duracionEnMinutos);
		if (!(soporte.isAudiovisual())) {
			throw new ExcepcionSoporte("El soporte seleccionado no es compatible con video",this,soporte);
		}
		setEdadRecomendada(edad);
		setCalidad(calidad);
	}
	
	public int getEdadRecomendada() { return edadRecomendada; }
	public int getCalidad() { return calidad; }
	
	public void setCalidad(int calidad) throws ExcepcionCalidad {
		if (calidad < 0) {
			throw new ExcepcionCalidad("La calidad no puede ser un numero negativo",this,calidad);
		}
		this.calidad = calidad;
	}
	
	public void setEdadRecomendada(int edad) throws ExcepcionEdadRecomendada {
		if (edad < 0) {
			throw new ExcepcionEdadRecomendada("La edad recomendada no puede ser un numero negativo",this,edad);
		}
		if (edad > 18) {
			throw new ExcepcionEdadRecomendada("La edad recomnedada no puede ser mayor de 18 años",this,edad);
		}
		this.edadRecomendada = edad;
	}
}
