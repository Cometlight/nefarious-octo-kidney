package at.fhv.itb5c.view;

import java.io.IOException;

import at.fhv.itb5c.rmi.client.RMIClient;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/*
 * TODO:
 * 	optimize:
 * 		load factories at startup
 */

public class Program extends Application {
	public AppController _appController;
	
	public Program() {
		RMIClient.getRMIClient().startUp();
		_appController = new AppController();
	}

	@Override
	public void start(Stage stage) throws IOException {
		_appController.start(stage);
		stage.getIcons().add(new Image(getClass().getResourceAsStream("/view/image/sports_icon.png")));
		stage.setTitle("Sports Management");
	}

	public static void main(String[] args) {
		launch(args);
	}
}
