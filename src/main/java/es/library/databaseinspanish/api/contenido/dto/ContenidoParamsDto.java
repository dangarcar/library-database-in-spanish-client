package es.library.databaseinspanish.api.contenido.dto;

import es.library.databaseinspanish.model.contenido.Soporte;

public class ContenidoParamsDto {

	private String query;
	private String titulo; 
	private String autor; 
	private Integer minAno; 
	private Integer maxAno;
	private String idioma; 
	private Soporte soporte; 
	private Integer minPaginas; 
	private Integer maxPaginas; 
	private String editorial; 
	private String isbn;
	private Integer minEdad; 
	private Integer maxEdad; 
	private Double minDuracion; 
	private Double maxDuracion; 
	private Integer minCalidad;
	private Integer maxCalidad; 
	private String type;
	
	public ContenidoParamsDto() {}

	public ContenidoParamsDto(String query, String titulo, String autor, Integer minAno, Integer maxAno, String idioma,
			Soporte soporte, Integer minPaginas, Integer maxPaginas, String editorial, String isbn, Integer minEdad,
			Integer maxEdad, Double minDuracion, Double maxDuracion, Integer minCalidad, Integer maxCalidad,
			String type) {
		this.query = query;
		this.titulo = titulo;
		this.autor = autor;
		this.minAno = minAno;
		this.maxAno = maxAno;
		this.idioma = idioma;
		this.soporte = soporte;
		this.minPaginas = minPaginas;
		this.maxPaginas = maxPaginas;
		this.editorial = editorial;
		this.isbn = isbn;
		this.minEdad = minEdad;
		this.maxEdad = maxEdad;
		this.minDuracion = minDuracion;
		this.maxDuracion = maxDuracion;
		this.minCalidad = minCalidad;
		this.maxCalidad = maxCalidad;
		this.type = type;
	}
	
	
	public String getQuery() {return query;}
	public void setQuery(String query) {this.query = query;}
	
	public String getTitulo() {return titulo;}
	public void setTitulo(String titulo) {this.titulo = titulo;}
	
	public String getAutor() {return autor;}
	public void setAutor(String autor) {this.autor = autor;}
	
	public Integer getMinAno() {return minAno;}
	public void setMinAno(Integer minAno) {this.minAno = minAno;}
	
	public Integer getMaxAno() {return maxAno;}
	public void setMaxAno(Integer maxAno) {this.maxAno = maxAno;}
	
	public String getIdioma() {return idioma;}
	public void setIdioma(String idioma) {this.idioma = idioma;}
	
	public Soporte getSoporte() {return soporte;}
	public void setSoporte(Soporte soporte) {this.soporte = soporte;}
	
	public Integer getMinPaginas() {return minPaginas;}
	public void setMinPaginas(Integer minPaginas) {this.minPaginas = minPaginas;}
	
	public Integer getMaxPaginas() {return maxPaginas;}
	public void setMaxPaginas(Integer maxPaginas) {this.maxPaginas = maxPaginas;}
	
	public String getEditorial() {return editorial;}	
	public void setEditorial(String editorial) {this.editorial = editorial;}
	
	public String getIsbn() {return isbn;}
	public void setIsbn(String isbn) {this.isbn = isbn;}
	
	public Integer getMinEdad() {return minEdad;}
	public void setMinEdad(Integer minEdad) {this.minEdad = minEdad;}
	
	public Integer getMaxEdad() {return maxEdad;}
	public void setMaxEdad(Integer maxEdad) {this.maxEdad = maxEdad;}
	
	public Double getMinDuracion() {return minDuracion;}
	public void setMinDuracion(Double minDuracion) {this.minDuracion = minDuracion;}
	
	public Double getMaxDuracion() {return maxDuracion;}
	public void setMaxDuracion(Double maxDuracion) {this.maxDuracion = maxDuracion;}
	
	public Integer getMinCalidad() {return minCalidad;}
	public void setMinCalidad(Integer minCalidad) {this.minCalidad = minCalidad;}
	
	public Integer getMaxCalidad() {return maxCalidad;}
	public void setMaxCalidad(Integer maxCalidad) {this.maxCalidad = maxCalidad;}
	
	public String getType() {return type;}
	public void setType(String type) {this.type = type;}

	
	
	@Override
	public String toString() {
		return "ContenidoParamsDto [query=" + query + ", titulo=" + titulo + ", autor=" + autor + ", minAno=" + minAno
				+ ", maxAno=" + maxAno + ", idioma=" + idioma + ", soporte=" + soporte + ", minPaginas=" + minPaginas
				+ ", maxPaginas=" + maxPaginas + ", editorial=" + editorial + ", isbn=" + isbn + ", minEdad=" + minEdad
				+ ", maxEdad=" + maxEdad + ", minDuracion=" + minDuracion + ", maxDuracion=" + maxDuracion
				+ ", minCalidad=" + minCalidad + ", maxCalidad=" + maxCalidad + ", type=" + type + "]";
	}
	
}
