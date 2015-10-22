package at.fhv.itb5c;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import at.fhv.itb5c.util.SceneUtil;
import at.fhv.itb5c.view.login.LoginController;
import javafx.application.Application;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/*
 * TODO:
 * 	Extract functionality into an general controller
 */

public class Program extends Application {

	public Stage primaryStage;
	public BorderPane rootLayout;

	@Override
	public void start(Stage stage) {
		stage.setTitle("Hallo User");
		this.primaryStage = stage;

		loadMainStage();
		showStage();
	}

	public void loadMainStage() {
		LoginController loginController = new LoginController();

		loginController.addObserver(new Observer() {
			public void update(Observable o, Object arg) {
				try {
					closeStage();			
					SceneUtil.<BorderPane>loadScene("view/mainview/MainView.fxml", primaryStage);
					showStage();
				} catch (IOException e) {
					// todo add logging
					e.printStackTrace();
				}
			}
		});

		try {
			SceneUtil.<BorderPane>loadScene("view/login/Login.fxml", loginController, primaryStage);
		} catch (IOException e) {
			// todo add logging
			e.printStackTrace();
		}
	}

	private void showStage() {
		primaryStage.show();
	}

	private void closeStage() {
		primaryStage.close();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
