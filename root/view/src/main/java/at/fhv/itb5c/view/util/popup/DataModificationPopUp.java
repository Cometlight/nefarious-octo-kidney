package at.fhv.itb5c.view.util.popup;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Modality;

public class DataModificationPopUp {
	public static void dataSavedPopUp(String text) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Data saved");
		alert.setHeaderText(text);
		alert.initModality(Modality.WINDOW_MODAL);
		alert.showAndWait();
	}
}
