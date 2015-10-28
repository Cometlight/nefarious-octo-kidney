package at.fhv.itb5c;

import javafx.application.Application;
import javafx.stage.Stage;

public class Program extends Application {
	public AppController _appController;
	
	public Program() {
		_appController = new AppController();
	}

	@Override
	public void start(Stage stage) {
		_appController.start(stage);
	}

	public static void main(String[] args) {
		launch(args);
	}
}
