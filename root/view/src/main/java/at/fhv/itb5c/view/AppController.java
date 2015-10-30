package at.fhv.itb5c.view;

import java.io.IOException;

import at.fhv.itb5c.view.login.LoginController;
import at.fhv.itb5c.view.mainview.MainViewController;
import at.fhv.itb5c.view.user.UserViewController;
import at.fhv.itb5c.view.user.states.DetailUserViewControlls;
import at.fhv.itb5c.view.user.states.NewUserViewControllsController;
import at.fhv.itb5c.view.usersearch.SearchUserController;
import at.fhv.itb5c.view.util.RouteProvider;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class AppController {
	private Stage _primaryStage;

	public void start(Stage stage) throws IOException {
		initializeRouteMapping();

		stage.setTitle("Hallo User");
		this._primaryStage = stage;

		loadMainStage();
		showStage();
	}

	private void initializeRouteMapping() {
		RouteProvider.getInstance().add(UserViewController.class, "/view/fxml/UserView.fxml");
		RouteProvider.getInstance().add(DetailUserViewControlls.class, "/view/fxml/DetailUserViewControlls.fxml");
		RouteProvider.getInstance().add(NewUserViewControllsController.class, "/view/fxml/NewUserViewControlls.fxml");
		
		RouteProvider.getInstance().add(MainViewController.class, "/view/fxml/MainView.fxml");
		RouteProvider.getInstance().add(LoginController.class, "/view/fxml/Login.fxml");
		RouteProvider.getInstance().add(SearchUserController.class, "/view/fxml/SearchUserView.fxml");
	}

	private void loadMainStage() throws IOException {
			closeStage();
			/*StageUtil.<BorderPane> loadScene(RouteProvider.getInstance().getRoot(MainViewController.class),
					_primaryStage);*/
			
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(RouteProvider.getInstance().get(MainViewController.class));
			loader.setController(new MainViewController());
			BorderPane rootLayout = (BorderPane) loader.load();

			Scene scene = new Scene((Parent)rootLayout);
			_primaryStage.setScene(scene); 
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
