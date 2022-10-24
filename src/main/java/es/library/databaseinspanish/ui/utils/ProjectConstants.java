package es.library.databaseinspanish.ui.utils;

import java.awt.Color;
import java.awt.Font;
import java.time.Month;
import java.util.Map;

public interface ProjectConstants {

	int SCREEN_WIDTH = 1000;
	int SCREEN_HEIGHT = 618;
	
	Color BACKGROUND_COLOR = new Color(148,238,229);
	
	Map<String,Month> MONTHS =  Map.ofEntries(
			Map.entry("Enero",Month.JANUARY),
			Map.entry("Febrero",Month.FEBRUARY),
			Map.entry("Marzo",Month.MARCH),
			Map.entry("Abril",Month.APRIL),
			Map.entry("Mayo",Month.MAY),
			Map.entry("Junio",Month.JUNE),
			Map.entry("Julio",Month.JULY),
			Map.entry("Agosto",Month.AUGUST),
			Map.entry("Septiembre",Month.SEPTEMBER),
			Map.entry("Octubre",Month.OCTOBER),
			Map.entry("Noviembre",Month.NOVEMBER),
			Map.entry("Diciembre",Month.DECEMBER)
		);
	
	Font font12P = new Font("Segoe UI", Font.PLAIN, 12);
	
}
