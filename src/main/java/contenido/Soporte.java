package contenido;

import javax.swing.ImageIcon;

public enum Soporte {
	FISICO(false,new ImageIcon("files/images/libro.png")),E_BOOK(false,new ImageIcon("files/images/ebook.png")),CD(true,true,new ImageIcon("files/images/cd.png")),DVD(true,true,new ImageIcon("files/images/dvd.png")),BLURAY(true,true,new ImageIcon("files/images/bluray.png")),VINILO(true,false,new ImageIcon("files/images/vinilo.png")),CASETE(true,false,new ImageIcon("files/images/casete.png"));
	
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
