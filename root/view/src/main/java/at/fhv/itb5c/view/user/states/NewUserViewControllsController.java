package at.fhv.itb5c.view.user.states;

import java.net.URL;

import at.fhv.itb5c.view.user.UserViewController;
import at.fhv.itb5c.view.user.UserViewController.ViewState;
import at.fhv.itb5c.view.user.UserViewState;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

public class NewUserViewControllsController implements UserViewState {
	
	private UserViewController _userViewController;
	private String _titel;
	private URL _controlsFXMLURL;
	
	public NewUserViewControllsController(UserViewController userViewController) {
		_userViewController = userViewController;
		_titel = "Create New User";
		_controlsFXMLURL = this.getClass().getResource("NewUserViewControlls.fxml");
	}	
	
	@FXML 
	public void saveButtonMouseReleasedEventHandler(MouseEvent mouseEvent) {
		boolean saved = _userViewController.saveModel();
		if(saved) {
			_userViewController.setState(ViewState.detailState);
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
