package database;

import java.awt.Component;
import java.io.Serializable;
import javax.swing.JPanel;

public interface DatabaseWritable extends Serializable{
	public Component getGUIRepresentation();
	public JPanel getExtendedGUIRepresentation();
	public long getSpecificID();
}
