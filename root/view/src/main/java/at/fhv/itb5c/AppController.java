package at.fhv.itb5c;

import java.io.IOException;

import at.fhv.itb5c.util.ControllerHasRouteException;
import at.fhv.itb5c.util.RouteProvider;
import at.fhv.itb5c.util.StageUtil;
import at.fhv.itb5c.view.login.LoginController;
import at.fhv.itb5c.view.mainview.MainViewController;
import at.fhv.itb5c.view.user.UserViewController;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class AppController {	
	private Stage _primaryStage;
	
	public void start(Stage stage) {
		initializeRouteMapping();
		
		stage.setTitle("Hallo User");
		this._primaryStage = stage;

		loadMainStage();
		showStage();
	}
	
	private void initializeRouteMapping() {
		try {
			RouteProvider.getInstance().addRoot(UserViewController.class, "at/fhv/itb5c/view/user/UserView.fxml");
			RouteProvider.getInstance().addRoot(MainViewController.class, "at/fhv/itb5c/view/mainview/MainView.fxml");
			RouteProvider.getInstance().addRoot(LoginController.class, "at/fhv/itb5c/view/login/Login.fxml");
			
		} catch (ControllerHasRouteException e) {
			// Todo: handle critical error
			e.printStackTrace();
		}
	}

	private void loadMainStage() {
		try {
			closeStage();			
			StageUtil.<BorderPane>loadScene(RouteProvider.getInstance().getRoot(MainViewController.class), _primaryStage);
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
