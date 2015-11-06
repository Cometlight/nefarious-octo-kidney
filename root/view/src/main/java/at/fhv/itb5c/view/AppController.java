package at.fhv.itb5c.view;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import at.fhv.itb5c.logging.ILogger;
import at.fhv.itb5c.view.login.LoginViewFactory;
import at.fhv.itb5c.view.mainview.MainViewFactory;
import at.fhv.itb5c.view.util.interfaces.IPanelAndViewFactory;
import at.fhv.itb5c.view.util.popup.ErrorPopUp;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/*Todo(san7985)
 *  abstract factories -> remove static routepath registration
 *	opimice rmi by loading factories at the beginning
 *	add loggin when exceptions are thrown
 */

public class AppController implements Observer, ILogger {
	private Stage _primaryStage;

	public void start(Stage stage) throws IOException {
		AppState.getInstance().addObserver(this);

		stage.setTitle("Enterprise Application Project");
		stage.setMinHeight(600);
		stage.setMinWidth(800);
		this._primaryStage = stage;

		loadStage(new LoginViewFactory());
	}

	private void loadStage(IPanelAndViewFactory factory) throws IOException {
		closeStage();
		Pane rootPane = factory.create();
		Scene scene = new Scene((Parent) rootPane);
		_primaryStage.setScene(scene);
		showStage();
	}

	private void showStage() {
		_primaryStage.show();
	}

	private void closeStage() {
		_primaryStage.close();
	}

	@Override
	public void update(Observable o, Object arg) {
		AppState appState = (AppState) o;

		if (appState._user != null) {
			try {
				loadStage(new MainViewFactory());
			} catch (IOException e) {
				log.error(e.getMessage());
				ErrorPopUp.criticalSystemError();
				_primaryStage.close();
			}
		}

	}
}
