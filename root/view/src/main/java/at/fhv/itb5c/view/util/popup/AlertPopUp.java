package at.fhv.itb5c.view.util.popup;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Modality;

public final class AlertPopUp {
	
	public static void ConnectionAlert() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Connection Error");
		alert.setHeaderText("Could not connect to the Severer! Try to login again and contact the system administrator.");
		alert.initModality(Modality.WINDOW_MODAL);
		alert.showAndWait();
	}
}
