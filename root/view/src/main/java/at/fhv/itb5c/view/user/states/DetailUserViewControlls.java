package at.fhv.itb5c.view.user.states;

import java.net.URL;

import at.fhv.itb5c.view.user.UserViewController;
import at.fhv.itb5c.view.user.UserViewState;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

public class DetailUserViewControlls  implements UserViewState {
	private UserViewController _userViewController;
	private URL _controlsFXMLURL;
	
	public DetailUserViewControlls(UserViewController userViewController) {
		_userViewController = userViewController;
		_controlsFXMLURL = this.getClass().getResource("DetailUserViewControlls.fxml");
	}
	
	@Override
	public String getTitel() {
		return "User: " + _userViewController.getUserModel().getFirstName().getValue() + " " + _userViewController.getUserModel().getLastName().getValue();
	}

	@Override
	public URL getControlsFXML() {
		return _controlsFXMLURL;
	}

	@Override
	public void activate() {
		_userViewController.setDisable(true);
	}
	
	@FXML 
	public void modifyButtonMouseReleasedEventHandler(MouseEvent mouseEvent) {
		//TODO: switch to modifie view
	}
	
	@FXML 
	public void closeButtonMouseReleasedEventHandler(MouseEvent mouseEvent) {
		_userViewController.close();
	}
}
