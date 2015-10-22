package at.fhv.itb5c.util;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public final class SceneUtil {

	private static final String _relativPath = "../"; 
	
	public static <T> void loadScene(String fxmlPath, Object controller, Stage stage) throws IOException {
		FXMLLoader loader = new FXMLLoader();

		loader.setLocation(SceneUtil.class.getResource(_relativPath + fxmlPath));
		loader.setController(controller);
		
		@SuppressWarnings("unchecked")
		T rootLayout = (T) loader.load();

		Scene scene = new Scene((Parent)rootLayout);
		stage.setScene(scene);
	}
	
	public static <T> void loadScene(String fxmlPath, Stage stage) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(SceneUtil.class.getResource(_relativPath + fxmlPath));
		
		@SuppressWarnings("unchecked")
		T rootLayout = (T) loader.load();

		Scene scene = new Scene((Parent)rootLayout);
		stage.setScene(scene);
	}
}
