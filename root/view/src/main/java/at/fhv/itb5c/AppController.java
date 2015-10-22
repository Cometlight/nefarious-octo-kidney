package at.fhv.itb5c;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import at.fhv.itb5c.util.StageUtil;
import at.fhv.itb5c.view.login.LoginController;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class AppController {
	
	//todo move to a general path provider for each view(xml or something similar)
	private static final String _relativMainViewPath = "view/mainview/MainView.fxml";
	
	private Stage _primaryStage;
	
	public void start(Stage stage) {
		stage.setTitle("Hallo User");
		this._primaryStage = stage;

		loadMainStage();
		showStage();
	}

	private void loadMainStage() {
		try {
			closeStage();			
			StageUtil.<BorderPane>loadScene(_relativMainViewPath, _primaryStage);
			showStage();
		} catch (IOException e) {
			// TODO add logging
			e.printStackTrace();
		}
	}

	private void showStage() {
		_primaryStage.show();
	}

	private void closeStage() {
		_primaryStage.close();
	}
}
