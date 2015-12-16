package at.fhv.itb5c.view.view.user.states;

import java.io.IOException;
import java.net.URL;

import at.fhv.itb5c.view.view.user.IUserViewState;
import at.fhv.itb5c.view.view.user.UserViewController;
import at.fhv.itb5c.view.view.user.UserViewController.UserViewState;
import at.fhv.itb5c.view.view.util.RouteProvider;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

@SuppressWarnings("deprecation")
public class AddUserViewControllsController implements IUserViewState {

	private UserViewController _userViewController;
	private String _titel;
	private URL _controlsFXMLURL;

	public AddUserViewControllsController(UserViewController userViewController) {
		_userViewController = userViewController;
		_titel = "Add User";
		_controlsFXMLURL = RouteProvider.getInstance().get(this.getClass());
	}

	@FXML
	public void saveButtonMouseReleasedEventHandler(MouseEvent mouseEvent) throws IOException {
		boolean saved = _userViewController.saveModel();
		if (saved) {
			_userViewController.setState(UserViewState.detailState);
		}
	}

	@FXML
	public void cancleButtonMouseReleasedEventHandler(MouseEvent mouseEvent) {
		_userViewController.close();
	}

	@Override
	public String getTitel() {
		return _titel;
	}

	@Override
	public URL getControlsFXML() {
		return _controlsFXMLURL;
	}

	@Override
	public void activate() {
		_userViewController.setDisable(false);
	}
}
