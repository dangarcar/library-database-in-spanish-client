package BBDD;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

import BBDD.excepciones.ExcepcionNoID;


public class BBDDReaderWriter{
	private Map<Integer,EscribibleEnBBDD> diccionario = new HashMap<Integer,EscribibleEnBBDD>();
	private ObjectOutputStream archivoOutput; 
	private ObjectInputStream archivoInput;
	private File archivo;
	
	/**
	 * Constructor de la clase encargada de comunicarse directamente con los archivos <br>
	 * Esta clase no es nada eficiente, pero por ahora no quiero usar SQL, que sería lo más lógico
	 * @param fichero El objeto File
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 * @throws ClassNotFoundException 
	 */
	public BBDDReaderWriter(File fichero) throws FileNotFoundException, IOException, ClassNotFoundException {
		archivo = fichero;
		archivoOutput = new ObjectOutputStream(new FileOutputStream(archivo));
		archivoInput = new ObjectInputStream(new FileInputStream(archivo));
		diccionario = (Map<Integer, EscribibleEnBBDD>) archivoInput.readObject();
	}
	
	public EscribibleEnBBDD read(int id) throws ExcepcionNoID {
		EscribibleEnBBDD c;
		if(diccionario.containsKey(id)) {
			c = diccionario.get(id);
		}
		else {
			throw new ExcepcionNoID("No existe tal ID en esa tabla",archivo,id);
		}
		return c;
	}

	
	public boolean write(EscribibleEnBBDD c) throws IOException {
		if(diccionario.containsValue(c)) {
			return false;
		}
		else {
			diccionario.put(c.getID(), c);
			//Estoy guardando otra vez todo el diccionario para modificar el dato
			archivoOutput.writeObject(diccionario);
			return true;
		}
	}
	
	public boolean remove(EscribibleEnBBDD c) throws IOException {
		if(diccionario.containsValue(c)) {
			return false;
		}
		else {
			diccionario.remove(c.getID());
			//Estoy guardando otra vez todo el diccionario para modificar el dato
			archivoOutput.writeObject(diccionario);
			return true;
		}
	}
}
