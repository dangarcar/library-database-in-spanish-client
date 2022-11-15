package es.library.databaseinspanish.ui.utils.components;

import java.util.Arrays;

import javax.swing.JComboBox;

import es.library.databaseinspanish.model.perfil.Roles;

public class RolesComboBox extends JComboBox<String> {

	public RolesComboBox() {
		super(Arrays.stream(Roles.values())
				.filter(r -> !r.equals(Roles.ROLE_GUEST))
				.map(r -> r.toString().replace("ROLE_", ""))
				.toArray(String[]::new));
	}

	public Roles getRole() {
		return Roles.valueOf("ROLE_".concat((String) getSelectedItem()));
	}
	
}
