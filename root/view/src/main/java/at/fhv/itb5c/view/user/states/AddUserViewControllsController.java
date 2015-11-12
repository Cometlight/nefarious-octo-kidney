package at.fhv.itb5c.view.user.states;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;

import at.fhv.itb5c.view.user.UserViewController;
import at.fhv.itb5c.view.user.UserViewController.UserViewState;
import at.fhv.itb5c.view.util.RouteProvider;
import at.fhv.itb5c.view.util.popup.ErrorPopUp;
import at.fhv.itb5c.view.user.IUserViewState;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

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
		if(saved) {
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
		try {
			_userViewController.setDisable(false);
		} catch (RemoteException e) {
			log.error(e.getMessage());
			ErrorPopUp.connectionError();
		}
	}
}
