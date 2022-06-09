package contenido;

import javax.swing.ImageIcon;

/**
 * Enum que define los soportes en los que pueden estar los contenidos de la biblioteca
 * @author Daniel García
 *
 */
public enum Soporte {
	FISICO(false,new ImageIcon("src/main/resources/files/images/libro.png")),E_BOOK(false,new ImageIcon("src/main/resources/files/images/ebook.png")),CD(true,true,new ImageIcon("src/main/resources/files/images/cd.png")),DVD(true,true,new ImageIcon("src/main/resources/files/images/dvd.png")),BLURAY(true,true,new ImageIcon("src/main/resources/files/images/bluray.png")),VINILO(true,false,new ImageIcon("src/main/resources/files/images/vinilo.png")),CASETE(true,false,new ImageIcon("src/main/resources/files/images/casete.png"));
	
	private boolean multimedia;
	private boolean audiovisual;
	private ImageIcon icono;
	
	Soporte(boolean multimedia,ImageIcon ico) {
		this.multimedia = multimedia;
		this.audiovisual = false;
		icono = ico;
	}
	
	Soporte(boolean multimedia, boolean audiovisual,ImageIcon ico){
		this.multimedia = multimedia;
		this.audiovisual = audiovisual;
		icono = ico;
	}
	
	/**
	 * @return Si el soporte es multimedia o no
	 */
	boolean isMultimedia() {
		return multimedia;
	}
	
	/**
	 * @return Si el soporte puede contener tanto video como audio
	 */
	boolean isAudiovisual() {
		return audiovisual;
	}
	
	public ImageIcon getIcon() {
		return icono;
	}
	
	@Override
	public String toString() {
		return this.name();
	}
}
