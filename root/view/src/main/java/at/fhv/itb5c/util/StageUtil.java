package at.fhv.itb5c.util;

import java.io.IOException;
import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public final class StageUtil {
	
	public static <T> void loadScene(URL fxmlPath, Object controller, Stage stage) throws IOException {
		FXMLLoader loader = new FXMLLoader();

		loader.setLocation(fxmlPath);
		loader.setController(controller);
		
		@SuppressWarnings("unchecked")
		T rootLayout = (T) loader.load();

		Scene scene = new Scene((Parent)rootLayout);
		stage.setScene(scene);
	}
	
	public static <T> void loadScene(URL fxmlPath, Stage stage) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(fxmlPath);
		
		@SuppressWarnings("unchecked")
		T rootLayout = (T) loader.load();

		Scene scene = new Scene((Parent)rootLayout);
		stage.setScene(scene);
	}
}
