package at.fhv.itb5c.view.user.states;

import java.io.IOException;
import java.net.URL;

import at.fhv.itb5c.view.user.IUserViewState;
import at.fhv.itb5c.view.user.UserViewController;
import at.fhv.itb5c.view.user.UserViewController.UserViewState;
import at.fhv.itb5c.view.util.RouteProvider;
import at.fhv.itb5c.view.util.popup.DataModificationPopUp;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

public class ModifyUserViewControlls implements IUserViewState {
	
	private UserViewController _userViewController;
	private URL _controlsFXMLURL;
	
	public ModifyUserViewControlls(UserViewController userViewController) {
		_userViewController = userViewController;
		_controlsFXMLURL =  RouteProvider.getInstance().get(this.getClass());
	}
	
	@Override
	public String getTitel() {
		return "Modify User: " + _userViewController.getUserModel().getFirstName().getValue() + " " + _userViewController.getUserModel().getLastName().getValue();
	}

	@Override
	public URL getControlsFXML() {
		return _controlsFXMLURL;
	}

	@Override
	public void activate() {
		_userViewController.setDisable(false);
	}
	
	@FXML 
	public void saveButtonMouseReleasedEventHandler(MouseEvent mouseEvent) throws IOException {
		boolean saved = _userViewController.saveModel();
		if(saved) {
			DataModificationPopUp.dataSavedPopUp("Your changes were saved!");
			_userViewController.setState(UserViewState.detailState);
		} 
	}
	
	@FXML 
	public void cancelButtonMouseReleasedEventHandler(MouseEvent mouseEvent) throws IOException {
		_userViewController.setState(UserViewState.detailState);
	}

}
