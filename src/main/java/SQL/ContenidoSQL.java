package SQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import contenido.Contenido;
import contenido.Libros;
import contenido.Soporte;
import contenido.excepciones.ExcepcionAno;
import contenido.excepciones.ExcepcionContenido;
import contenido.excepciones.ExcepcionDisponibilidad;
import contenido.excepciones.ExcepcionPaginas;
import contenido.excepciones.ExcepcionSoporte;
import perfiles.excepciones.ExcepcionPerfil;

public class ContenidoSQL {
	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	
	/**
	 * Este método consigue un libro de la base de datos a partir de un ISBN pasado por parámetro 
	 * @param isbn El ISBN del libro a elegir
	 * @return El objeto de la clase Libro que corresponda a ese ISBN
	 * @throws ExcepcionContenido 
	 */
	public static Libros getLibro(long isbn) throws ExcepcionContenido {
		Libros libro = null;
		ConectorSQL conector = null;
		ResultSet resultado = null;
		
		String peticionSQL = "SELECT ID,Titulo,Autor,Descripcion,Año,Idioma,Soporte,DiasDePrestamo,Prestable,Disponible,FechadeDisponibilidad,Detalles_Libro,DT.Paginas,DT.Editorial "
				+ "FROM Contenidos LEFT OUTER JOIN Detalles_libros AS DT ON Detalles_Libro = DT.ISBN "
				+ "WHERE Detalles_Libro = "+isbn+" "
				+ "GROUP BY Detalles_Libro;";
		
		String titulo;
		String autor;
		String descripcion;
		int ano;
		String idioma;
		Soporte soporte;
		int diasDePrestamo;
		boolean prestable;
		long ISBN;
		int paginas;
		boolean disponible;
		LocalDate fechaDisponibilidad;
		String editorial;
		int ID;
		
		try {
			//Creo el objeto cone l que conectarme a la BD
			conector = new ConectorSQL();
			conector.conectar();
			
			//Hago al peticion SQL a la base de datos y almaceno el ResultSet
			resultado = conector.seleccionar(peticionSQL);
			
			titulo = resultado.getString("Titulo");
			autor = resultado.getString("Autor");
			descripcion = resultado.getString("Descripcion");
			ano = resultado.getInt("Año");
			idioma = resultado.getString("Idioma");
			soporte = Soporte.valueOf(resultado.getString("Soporte"));
			diasDePrestamo = resultado.getInt("DiasDePrestamo");
			prestable = resultado.getBoolean("Prestable");
			ISBN = resultado.getLong("Detalles_Libro");
			paginas = resultado.getInt("Paginas");
			editorial = resultado.getString("Editorial");
			disponible = resultado.getBoolean("Disponible");
			fechaDisponibilidad = (resultado.getString("FechaDeDisponibilidad") != null)? LocalDate.parse(resultado.getString("FechaDeDisponibilidad"),formatter):null;
			ID = resultado.getInt("ID");
			
			libro = new Libros(ID,titulo,autor,descripcion,ano,idioma,prestable,soporte,diasDePrestamo,disponible,fechaDisponibilidad,ISBN,paginas,editorial);
			
		} catch (SQLException e) {
			throw new ExcepcionContenido("Hubo un error con la base de datos, es probable que el contenido que buscas no exista en esta biblioteca",libro);
		} catch (ExcepcionAno e) {
			System.out.println("Error: "+e.getMessage());
		} catch (ExcepcionSoporte e) {
			System.out.println("Error: "+e.getMessage());
		} catch (ExcepcionPaginas e) {
			System.out.println("Error: "+e.getMessage());
		} finally {
			//Si la conexion a la base de datos existe, la cierro
			if(conector != null) {
				conector.cerrar();
			}
		}
		
		return libro;
	}
	
	/**
	 * Este método guarda un libor pasado por parámetro en la base de datos
	 * @param libro El objeto libora ser guardado en la BBDD
	 * @throws ExcepcionContenido si 
	 */
	public static void writeLibro(Libros libro) throws ExcepcionContenido {
		ConectorSQL conector = null;
		PreparedStatement st;
		PreparedStatement stDetalles;
		Connection connect = null;
		
		if (libro != null) {
			try{
				//Me conecto a la base de datos
				conector = new ConectorSQL();
				connect = conector.conectar();
				connect.setAutoCommit(false);
				
				String peticionSQLContenidos = "INSERT INTO Contenidos(ID,Titulo,Autor,Descripcion,Año,Idioma,Soporte,DiasDePrestamo,Prestable,Disponible,FechaDeDisponibilidad,Detalles_Libro) "
						+ "VALUES(?,?,?,?,?,?,?,?,?,?,?,?);";
				String peticionSQLDetalles = "INSERT INTO Detalles_libros (ISBN,Paginas,Editorial) VALUES (?,?,?)";
			
				stDetalles = connect.prepareStatement(peticionSQLDetalles);
				stDetalles.setLong(1, libro.getISBN());
				stDetalles.setInt(2, libro.getPaginas());
				stDetalles.setString(3, libro.getEditorial());
				stDetalles.executeUpdate();
				stDetalles.clearParameters();
				
				st = connect.prepareStatement(peticionSQLContenidos);
				st.setInt(1, libro.getID());
				st.setString(2, libro.getTitulo());
				st.setString(3, libro.getAutor());
				st.setString(4, libro.getDescripcion());
				st.setInt(5, libro.getAno());
				st.setString(6, libro.getIdioma());
				st.setString(7, libro.getSoporte().toString());
				st.setInt(8, libro.getDiasDePrestamo());
				st.setBoolean(9, libro.getPrestable());
				st.setBoolean(10, libro.getDisponibilidad());
				st.setString(11, (libro.getFechaDisponibilidad() != null)? libro.getFechaDisponibilidad().format(formatter):null);
				st.setLong(12, libro.getISBN());
				st.executeUpdate();
				st.clearParameters();
				
				connect.commit();
				
				System.out.println("Se ha guardado el libro en la base de datos");
			
			} catch (SQLException e) {
				e.printStackTrace();
				try {
					connect.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				throw new ExcepcionContenido("Ocurrió un error. Es posible que este libro ya exsta en la base de datos",libro);
			} finally {
				//Si la conexión a la base de datos existe, la cierro
				if(conector != null) {
					conector.cerrar();
				}
			}
		} else {
			throw new ExcepcionContenido("El libro seleccionado no es válido",libro);
		}
	}
	
	/**
	 * Este método modifica el objeto Contenido pasado por parámetro en la BBDD<br>
	 * @param c El objeto que hereda de Contenido a ser prestado y figurar como ello en la BBDD
	 * @throws ExcepcionContenido
	 * @throws ExcepcionDsiponibilidad
	 */
	public static void prestarBBDD(Contenido c) throws ExcepcionDisponibilidad, ExcepcionContenido {
		ConectorSQL conector = null;
		PreparedStatement st;
		Connection connect = null;
		
		if (c != null) {
			if (c.getDisponibilidad()) {
				try{
					//Me conecto a la base de datos
					conector = new ConectorSQL();
					connect = conector.conectar();
				
					//Modifico los valores de la variable disponible y fecha de disponibilidad
					c.prestarContenido();
					
					String peticionSQLContenidos = "UPDATE Contenidos SET Disponible = ?, FechaDeDisponibilidad = ? WHERE ID = ?";
			
					st = connect.prepareStatement(peticionSQLContenidos);
					st.setBoolean(1, c.getDisponibilidad());
					st.setString(2, c.getFechaDisponibilidad().format(formatter));
					st.setInt(3, c.getID());
					st.executeUpdate();
					st.clearParameters();

					
					
					System.out.println("Se ha prestado el contenido en la base de datos");
			
				} catch (SQLException e) {
					e.printStackTrace();
					//Si ha habido un error en el SQL, deshacer el prestarContenido()
					c.devolverContenido();
				} finally {
					//Si la conexión a la base de datos existe, la cierro
					if(conector != null) {
						conector.cerrar();
					}
				}
			} else {
				throw new ExcepcionContenido("El contenido seleccionado no está disponible",c);
			}
		} else {
			throw new ExcepcionContenido("El contenido seleccionado no es válido",c);
		}
	}
}
