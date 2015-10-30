package at.fhv.itb5c.view.util;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Modality;

public final class AlertUtil {
	
	public static void ConnectionAlert() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Connection Error");
		alert.setHeaderText("Could not connect to the Severer! Try to login again andcontact the system administrator.");
		alert.initModality(Modality.WINDOW_MODAL);
		alert.showAndWait();
	}
}
