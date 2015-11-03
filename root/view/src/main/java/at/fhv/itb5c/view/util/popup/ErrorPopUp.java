package at.fhv.itb5c.view.util.popup;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Modality;

public final class ErrorPopUp {
	
	public static void connectionError() {
		generalError("Connection Error", "Could not connect to the Severer! Try to login again and contact the system administrator.");
	}
	
	public static void criticalSystemError() {
		generalError("Critical System Error", "The System encountered a critical error. Please contact the system administrator.");
	}
	
	public static void generalError(String titel, String headerText) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(titel);
		alert.setHeaderText(headerText);
		alert.initModality(Modality.WINDOW_MODAL);
		alert.showAndWait();
	}
}
