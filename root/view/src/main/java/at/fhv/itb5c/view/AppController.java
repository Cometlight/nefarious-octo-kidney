package at.fhv.itb5c.view;

import java.io.IOException;

import at.fhv.itb5c.view.login.LoginController;
import at.fhv.itb5c.view.mainview.MainViewFactory;
import at.fhv.itb5c.view.util.RouteProvider;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/*Todo(san7985)
 *  abstract factories
 *	opimice rmi by loading factories at the beginning
 *	add loggin when exceptions are thrown
 */

public class AppController {
	private Stage _primaryStage;

	static {
		RouteProvider.getInstance().add(LoginController.class, "/view/fxml/Login.fxml");
	}
	
	public void start(Stage stage) throws IOException {
		stage.setTitle("Enterprise Application Project");
		stage.setMinHeight(600);
		stage.setMinWidth(800);
		this._primaryStage = stage;

		loadMainStage();
		showStage();
	}

	private void loadMainStage() throws IOException {
			closeStage();	
			Pane rootPane = new MainViewFactory().create();
			Scene scene = new Scene((Parent)rootPane);
			_primaryStage.setScene(scene); 		
			showStage();
	}

	private void showStage() {
		_primaryStage.show();
	}

	private void closeStage() {
		_primaryStage.close();
	}
}
