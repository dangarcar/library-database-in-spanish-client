package es.library.databaseinspanish.ui.utils;

import javax.swing.JOptionPane;

public class OptionPanes {

	public static void error(String msg) {
		new Thread(() -> JOptionPane.showMessageDialog(null,"<html><b>Error: </b><br>" + msg +"</html>", "Error", JOptionPane.ERROR_MESSAGE)).start();
	}
	
	public static void errorBlocking(String msg) {
		JOptionPane.showMessageDialog(null,"<html><b>Error: </b><br>" + msg +"</html>", "Error", JOptionPane.ERROR_MESSAGE);
	}
	
	public static void info(String msg) {
		new Thread(() -> JOptionPane.showMessageDialog(null, msg, "Info", JOptionPane.INFORMATION_MESSAGE)).start();
	}
	
	public static void infoBlocking(String msg) {
		JOptionPane.showMessageDialog(null, msg, "Info", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public static void warn(String msg) {
		new Thread(() -> JOptionPane.showMessageDialog(null, msg, "Warning", JOptionPane.WARNING_MESSAGE)).start();
	}
	
	public static void warnBlocking(String msg) {
		JOptionPane.showMessageDialog(null, msg, "Warning", JOptionPane.WARNING_MESSAGE);
	}
	
}
