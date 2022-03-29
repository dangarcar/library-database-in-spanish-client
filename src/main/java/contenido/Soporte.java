package contenido;

public enum Soporte {
	FISICO(false),E_BOOK(false),CD(true,true),DVD(true,true),BLURAY(true,true),VINILO(true,false),CASETE(true,false);
	
	private boolean multimedia;
	private boolean audiovisual;
	
	Soporte(boolean multimedia) {
		this.multimedia = multimedia;
		this.audiovisual = false;
	}
	
	Soporte(boolean multimedia, boolean audiovisual){
		this.multimedia = multimedia;
		this.audiovisual = audiovisual;
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
}
