package at.fhv.itb5c.view;

import java.io.IOException;

import at.fhv.itb5c.rmi.client.RMIClient;
import javafx.application.Application;
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
	}

	public static void main(String[] args) {
		launch(args);
	}
}
