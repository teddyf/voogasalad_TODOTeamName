package battle.view;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * Warning to be displayed when HP is low.
 * 
 * @author Daniel Chai
 */
public class HPWarning {
	private static final AlertType type = AlertType.WARNING;
	private static final String text = "LOW HP!";
	
	private Alert alert;
	
	public HPWarning() {
		alert = new Alert(type);
		alert.setContentText(text);
	}
	
	public void showAlert() {
		alert.show();
	}
}
