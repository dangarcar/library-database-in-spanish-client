package SQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import contenido.Audio;
import contenido.Contenido;
import contenido.Libros;
import contenido.Soporte;
import contenido.Videos;
import contenido.excepciones.ExcepcionAno;
import contenido.excepciones.ExcepcionCalidad;
import contenido.excepciones.ExcepcionContenido;
import contenido.excepciones.ExcepcionDisponibilidad;
import contenido.excepciones.ExcepcionDuracion;
import contenido.excepciones.ExcepcionEdadRecomendada;
import contenido.excepciones.ExcepcionPaginas;
import contenido.excepciones.ExcepcionSoporte;
import perfiles.Perfil;
import perfiles.excepciones.ExcepcionPerfil;

public class ContenidoSQL {
	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	
	/**
	 * Este método consigue un libro de la base de datos a partir de un ISBN pasado por parámetro 
	 * @param isbn El ISBN del libro a elegir
	 * @param d Si selecciona los disponibles, los no disponibles o todos(null)
	 * @return El objeto de la clase Libro que corresponda a ese ISBN
	 * @throws ExcepcionContenido 
	 */
	public static ArrayList<Libros> getLibro(long isbn,Boolean d) throws ExcepcionContenido {
		ConectorSQL conector = null;
		ResultSet resultado = null;
		ArrayList<Libros> libros = new ArrayList<Libros>();
		
		String peticionSQL = "SELECT ID,Titulo,Autor,Descripcion,Año,Idioma,Soporte,DiasDePrestamo,Prestable,Disponible,FechadeDisponibilidad,Detalles_Libro,DT.Paginas,DT.Editorial "
				+ "FROM Contenidos LEFT OUTER JOIN Detalles_libros AS DT ON Detalles_Libro = DT.ISBN "
				+ "WHERE Detalles_Libro = "+isbn;
		if(d != null) {
			System.out.println("Estoy en el d");
			if(d == true) {
				peticionSQL = peticionSQL.concat(" AND Disponible = 1;");
				System.out.println(true);
			} else {
				peticionSQL = peticionSQL.concat(" AND Disponible = 0;");
				System.out.println(false);
			}
		}
		
		//System.out.println(peticionSQL);
		
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
			
			while(resultado.next()) {
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
			
				libros.add(new Libros(ID,titulo,autor,descripcion,ano,idioma,prestable,soporte,diasDePrestamo,disponible,fechaDisponibilidad,ISBN,paginas,editorial));
			}

		} catch (SQLException e) {
			throw new ExcepcionContenido("Hubo un error con la base de datos, es probable que el contenido que buscas no exista en esta biblioteca",libros.get(0));
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
		
		return libros;
	}
	
	/**
	 * Este método consigue un libro de la BBDD a partir de su ID
	 * @param id El ID del audiovisual a elegir
	 * @param d Si selecciona los disponibles, los no disponibles o todos(null)
	 * @return El objeto de la clase Audio que corresponda a ese ID
	 * @throws ExcepcionContenido 
	 */
	public static ArrayList<Audio> getAudiovisual(int id,Boolean d) throws ExcepcionContenido{
		ConectorSQL conector = null;
		ResultSet resultado = null;
		ArrayList<Audio> audiovisuales = new ArrayList<Audio>();
		
		String peticionSQL = "SELECT ID,Titulo,Autor,Descripcion,Año,Idioma,Soporte,DiasDePrestamo,Prestable,Disponible,FechadeDisponibilidad,Detalles_Audiovisual, DT.Duracion, DT.IsVideo, DT.EdadRecomendada, DT.Calidad "
				+ "FROM Contenidos LEFT OUTER JOIN Detalles_Audiovisual AS DT ON Detalles_Audiovisual = IDAudio "
				+ "WHERE Detalles_Audiovisual = "+id+";";
		if(d != null) {
			System.out.println("Estoy en el d");
			if(d == true) {
				peticionSQL = peticionSQL.concat(" AND Disponible = 1;");
				System.out.println(true);
			} else {
				peticionSQL = peticionSQL.concat(" AND Disponible = 0;");
				System.out.println(false);
			}
		}
		
		//System.out.println(peticionSQL);
		
		String titulo;
		String autor;
		String descripcion;
		int ano;
		String idioma;
		Soporte soporte;
		int diasDePrestamo;
		boolean prestable;
		boolean disponible;
		LocalDate fechaDisponibilidad;
		int ID;
		int IDAudio;
		double duracion;
		boolean isVideo;
		int edadRecomendada;
		int calidad;
		
		try {
			//Creo el objeto cone l que conectarme a la BD
			conector = new ConectorSQL();
			conector.conectar();
			
			//Hago al peticion SQL a la base de datos y almaceno el ResultSet
			resultado = conector.seleccionar(peticionSQL);
			
			while(resultado.next()) {
				titulo = resultado.getString("Titulo");
				autor = resultado.getString("Autor");
				descripcion = resultado.getString("Descripcion");
				ano = resultado.getInt("Año");
				idioma = resultado.getString("Idioma");
				soporte = Soporte.valueOf(resultado.getString("Soporte"));
				diasDePrestamo = resultado.getInt("DiasDePrestamo");
				prestable = resultado.getBoolean("Prestable");
				disponible = resultado.getBoolean("Disponible");
				fechaDisponibilidad = (resultado.getString("FechaDeDisponibilidad") != null)? LocalDate.parse(resultado.getString("FechaDeDisponibilidad"),formatter):null;
				ID = resultado.getInt("ID");
				IDAudio = resultado.getInt("Detalles_Audiovisual");
				duracion = resultado.getDouble("Duracion");
				isVideo = resultado.getBoolean("IsVideo");
				edadRecomendada = resultado.getInt("EdadRecomendada");
				calidad = resultado.getInt("Calidad");
			
				//Si es un video añado un video, si no añado un audio
				if(isVideo) {
					//System.out.println("Estamos haciendo un video");
					audiovisuales.add(new Videos(ID,titulo,autor,descripcion,ano,idioma,prestable,soporte,diasDePrestamo,disponible,fechaDisponibilidad,duracion,IDAudio,edadRecomendada,calidad));
				} else {
					//System.out.println("Estamos haciendo un audio");
					audiovisuales.add(new Audio(ID,titulo,autor,descripcion,ano,idioma,prestable,soporte,diasDePrestamo,disponible,fechaDisponibilidad,duracion,IDAudio));
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
			//throw new ExcepcionContenido("Hubo un error con la base de datos, es probable que el contenido que buscas no exista en esta biblioteca",audiovisuales.get(0));
		} catch (ExcepcionAno e) {
			System.out.println("Error: "+e.getMessage());
		} catch (ExcepcionSoporte e) {
			System.out.println("Error: "+e.getMessage());
		} catch(ExcepcionDuracion e) {
			System.out.println("Error: "+e.getMessage());
		} catch(ExcepcionEdadRecomendada e) {
			System.out.println("Error: "+e.getMessage());
		} catch(ExcepcionCalidad e) {
			System.out.println("Error: "+e.getMessage());
		} finally {
			//Si la conexion a la base de datos existe, la cierro
			if(conector != null) {
				conector.cerrar();
			}
		}
		
		return audiovisuales;
	}
	

	/**
	 * Este método guarda un libro pasado por parámetro en la base de datos
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
				
				String peticionSQLContenidos = "INSERT INTO Contenidos(Titulo,Autor,Descripcion,Año,Idioma,Soporte,DiasDePrestamo,Prestable,Disponible,FechaDeDisponibilidad,Detalles_Libro) "
						+ "VALUES(?,?,?,?,?,?,?,?,?,?,?);";
				String peticionSQLDetalles = "INSERT INTO Detalles_libros (ISBN,Paginas,Editorial) SELECT ?,?,? WHERE NOT EXISTS (SELECT ISBN FROM Detalles_libros WHERE ISBN = ?);";
			
				stDetalles = connect.prepareStatement(peticionSQLDetalles);
				stDetalles.setLong(1, libro.getISBN());
				stDetalles.setInt(2, libro.getPaginas());
				stDetalles.setString(3, libro.getEditorial());
				stDetalles.setLong(4, libro.getISBN());
				stDetalles.executeUpdate();
				stDetalles.clearParameters();
				
				st = connect.prepareStatement(peticionSQLContenidos);
				st.setString(1, libro.getTitulo());
				st.setString(2, libro.getAutor());
				st.setString(3, libro.getDescripcion());
				st.setInt(4, libro.getAno());
				st.setString(5, libro.getIdioma());
				st.setString(6, libro.getSoporte().toString());
				st.setInt(7, libro.getDiasDePrestamo());
				st.setBoolean(8, libro.getPrestable());
				st.setBoolean(9, libro.getDisponibilidad());
				st.setString(10, (libro.getFechaDisponibilidad() != null)? libro.getFechaDisponibilidad().format(formatter):null);
				st.setLong(11, libro.getISBN());
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
	 * Este método guarda un contenido audiovisual pasado por parámetro en la base de datos
	 * @param libro El objeto Audio a ser guardado en la BBDD
	 * @throws ExcepcionContenido si 
	 */
	public static void writeAudiovisual(Audio audiovisual) throws ExcepcionContenido {
		ConectorSQL conector = null;
		PreparedStatement st;
		PreparedStatement stDetalles;
		Connection connect = null;
		
		if (audiovisual != null) {
			try{
				//Me conecto a la base de datos
				conector = new ConectorSQL();
				connect = conector.conectar();
				connect.setAutoCommit(false);
				
				String peticionSQLContenidos = "INSERT INTO Contenidos(Titulo,Autor,Descripcion,Año,Idioma,Soporte,DiasDePrestamo,Prestable,Disponible,FechaDeDisponibilidad,Detalles_Audiovisual) "
						+ "VALUES(?,?,?,?,?,?,?,?,?,?,?);";
				String peticionSQLDetalles = "INSERT INTO Detalles_Audiovisual (IDAudio,Duracion,IsVideo,EdadRecomendada,Calidad) SELECT ?,?,?,?,? WHERE NOT EXISTS (SELECT IDAudio FROM Detalles_Audiovisual WHERE IDAudio = ?);";
			
				stDetalles = connect.prepareStatement(peticionSQLDetalles);
				stDetalles.setInt(1,audiovisual.getIDAudio());
				stDetalles.setDouble(2,audiovisual.getDuracion());
				stDetalles.setBoolean(3,audiovisual instanceof Videos);
				stDetalles.setInt(4,(audiovisual instanceof Videos)? ((Videos) audiovisual).getEdadRecomendada():null);
				stDetalles.setDouble(5,(audiovisual instanceof Videos)? ((Videos) audiovisual).getCalidad():null);
				stDetalles.setDouble(6,audiovisual.getIDAudio());
				stDetalles.executeUpdate();
				stDetalles.clearParameters();
				
				st = connect.prepareStatement(peticionSQLContenidos);
				st.setString(1, audiovisual.getTitulo());
				st.setString(2, audiovisual.getAutor());
				st.setString(3, audiovisual.getDescripcion());
				st.setInt(4, audiovisual.getAno());
				st.setString(5, audiovisual.getIdioma());
				st.setString(6, audiovisual.getSoporte().toString());
				st.setInt(7, audiovisual.getDiasDePrestamo());
				st.setBoolean(8, audiovisual.getPrestable());
				st.setBoolean(9, audiovisual.getDisponibilidad());
				st.setString(10, (audiovisual.getFechaDisponibilidad() != null)? audiovisual.getFechaDisponibilidad().format(formatter):null);
				st.setInt(11, audiovisual.getIDAudio());
				st.executeUpdate();
				st.clearParameters();
				
				connect.commit();
				
				System.out.println("Se ha guardado el contenido audiovisual en la base de datos");
			
			} catch (SQLException e) {
				e.printStackTrace();
				try {
					connect.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				throw new ExcepcionContenido("Ocurrió un error. Es posible que este contenido audiovisual ya exsta en la base de datos",audiovisual);
			} finally {
				//Si la conexión a la base de datos existe, la cierro
				if(conector != null) {
					conector.cerrar();
				}
			}
		} else {
			throw new ExcepcionContenido("El contenido audiovisual seleccionado no es válido",audiovisual);
		}
	}
	
	/**
	 * Este método modifica el objeto Contenido pasado por parámetro en la BBDD<br>
	 * @param c El objeto que hereda de Contenido a ser prestado y figurar como ello en la BBDD
	 * @param p El perfil que toma prestado el contenido c
	 * @throws ExcepcionContenido
	 * @throws ExcepcionDsiponibilidad
	 */
	public static boolean prestarBBDD(Contenido c,Perfil p) throws ExcepcionDisponibilidad, ExcepcionContenido {
		ConectorSQL conector = null;
		PreparedStatement st;
		Connection connect = null;
		boolean t = false;
		
		if (c != null && p != null) {
			try{
				//Me conecto a la base de datos
				conector = new ConectorSQL();
				connect = conector.conectar();
				connect.setAutoCommit(false);
				
				//Compruebo que el contenido esté disponible en la base de datos y en el objeto
				st = connect.prepareStatement("SELECT Disponible FROM Contenidos WHERE ID = ?");
				st.setInt(1, c.getID());
				ResultSet rs = st.executeQuery();
				st.clearParameters();
				t = rs.getBoolean("Disponible");
					
				if(t && c.getDisponibilidad()) {
					//Modifico los valores de la variable disponible y fecha de disponibilidad
					c.prestarContenido();
					
					String peticionSQLContenidos = "UPDATE Contenidos SET Disponible = 0, FechaDeDisponibilidad = ? WHERE ID = ? AND Disponible = 1";
			
					st = connect.prepareStatement(peticionSQLContenidos);
					st.setString(1, c.getFechaDisponibilidad().format(formatter));
					st.setInt(2, c.getID());
					st.executeUpdate();
					st.clearParameters();
				
					//Modifico tambien la base de datos de los perfiles añadiendo el contenido a la lista de prestados del perfil pasado por parámetro
					PerfilSQL.prestarPerfilBBDD(connect,c, p);
					
					connect.commit();
					
					System.out.println("Se ha prestado el contenido en la base de datos");
					
					t = true;
				} else {
					t = false;
					connect.rollback();
				}
					
			} catch (SQLException e) {
				t = false;
				e.printStackTrace();
				//Si ha habido un error en el SQL, deshacer el prestarContenido()
				c.devolverContenido();
				p.devolverPerfil(c);
				try {
					connect.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} finally {
				//Si la conexión a la base de datos existe, la cierro
				if(conector != null) {
					conector.cerrar();
				}					
			}
		}
		
		return t;
	}
	
	/**
	 * 
	 * @param c El objeto que hereda de Contenido a ser devuelto y figurar como ello en la BBDD
	 * @param p El perfil que devuelve el objeto
	 * @throws ExcepcionDisponibilidad
	 */
	public static boolean devolverBBDD(Contenido c, Perfil p) throws ExcepcionDisponibilidad {
		ConectorSQL conector = null;
		PreparedStatement st;
		Connection connect = null;
		boolean t = false;
		
		if(c != null && p != null) {
			try {
				//Me conecto a la base de datos
				conector = new ConectorSQL();
				connect = conector.conectar();
				connect.setAutoCommit(false);
				
				//Compruebo que el contenido no esté disponible en la base de datos y en el objeto
				st = connect.prepareStatement("SELECT Disponible FROM Contenidos WHERE ID = ?");
				st.setInt(1, c.getID());
				ResultSet rs = st.executeQuery();
				st.clearParameters();
				//Compruebo que el contenido no está disponible ni en la base de datos ni en el objeto en sí
				t = !(rs.getBoolean("Disponible"));
				
				//Si el contenido no está disponible y el perfil que devuelve es propietario actualmente de ese contenido
				if(t && p.getEnPrestamo().contains(c.getID())) {
					//Modifico los valores de la variable disponible y fecha de disponibilidad
					c.devolverContenido();
						
					String peticionSQLContenidos = "UPDATE Contenidos SET Disponible = 1, FechaDeDisponibilidad = NULL WHERE ID = ? AND Disponible = 0";
			
					st = connect.prepareStatement(peticionSQLContenidos);
					st.setInt(1, c.getID());
					st.executeUpdate();
					st.clearParameters();
				
					//Modifico tambien la base de datos de los perfiles añadiendo el contenido a la lista de prestados del perfil pasado por parámetro
					PerfilSQL.devolverPerfilBBDD(connect,c, p);
					
					connect.commit();
					
					System.out.println("Se ha devuelto el contenido en la base de datos");
					
					t = true;
				} else {
					t = false;
					connect.rollback();
				}
				
			} catch(SQLException e) {
				t = false;
				e.printStackTrace();
				//Si ha habido un error en el SQL, deshacer el prestarContenido()
				c.prestarContenido();
				p.prestarPerfil(c);
				try {
					connect.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} finally {
				//Si la conexión a la base de datos existe, la cierro
				if(conector != null) {
					conector.cerrar();
				}
			}
		}
		
		return t;
	}
}
