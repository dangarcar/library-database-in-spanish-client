package es.library.databaseinspanish.ui.utils.components;

import java.awt.Dimension;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.stream.IntStream;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import es.library.databaseinspanish.exceptions.perfil.IllegalPerfilException;
import es.library.databaseinspanish.ui.utils.ProjectConstants;

public class LocalDateSelector extends JPanel {

	private JComboBox<Integer> dayComboBox;
	private JComboBox<Integer> yearComboBox;
	private JComboBox<String> monthComboBox;

	public LocalDateSelector() {
		setLayout(new BoxLayout(this,BoxLayout.X_AXIS));
		
		dayComboBox = new JComboBox<Integer>();
		dayComboBox.setMinimumSize(new Dimension(64, 22));
		dayComboBox.setFont(ProjectConstants.font12P);
		add(dayComboBox);
		add(Box.createHorizontalStrut(10));

		String[] months = ProjectConstants.MONTHS.keySet().stream().sorted((s1,s2) -> ProjectConstants.MONTHS.get(s1).compareTo(ProjectConstants.MONTHS.get(s2))).toArray(String[]::new);
		monthComboBox = new JComboBox<String>(months);
		monthComboBox.setMinimumSize(new Dimension(64, 22));
		monthComboBox.setFont(ProjectConstants.font12P);
		monthComboBox.addActionListener((e)->{
			dayComboBox.setModel(new DefaultComboBoxModel<>(getDaysFromMonth(ProjectConstants.MONTHS.get(monthComboBox.getSelectedItem()))));
		});
		add(monthComboBox);
		add(Box.createHorizontalStrut(10));
		
		Integer[] years = IntStream.rangeClosed(1, Year.now().getValue()).boxed().sorted((f1,f2) -> Integer.compare(f2, f1)).toArray(Integer[]::new);
		yearComboBox = new JComboBox<Integer>(years);
		yearComboBox.setMinimumSize(new Dimension(64, 22));
		yearComboBox.setFont(ProjectConstants.font12P);
		add(yearComboBox);
	}
	
	public LocalDate getLocalDate() throws IllegalPerfilException {
		try {
			return LocalDate.of(
					(Integer) yearComboBox.getSelectedItem(),
					ProjectConstants.MONTHS.get(monthComboBox.getSelectedItem()), 
					(Integer) dayComboBox.getSelectedItem()
				);
		} catch (Exception e) {
			throw new IllegalPerfilException("La fecha de nacimiento no es correcta",e);
		}
	}
	
	private Integer[] getDaysFromMonth(Month m) {
		return IntStream.rangeClosed(1, m.maxLength()).boxed().toArray(Integer[]::new);
	}
	
}
