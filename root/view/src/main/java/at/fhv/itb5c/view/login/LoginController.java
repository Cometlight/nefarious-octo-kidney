package at.fhv.itb5c.view.login;

import java.rmi.RemoteException;

import at.fhv.itb5c.commons.dto.rmi.IUserRMI;
import at.fhv.itb5c.logging.ILogger;
import at.fhv.itb5c.rmi.client.RMIClient;
import at.fhv.itb5c.view.AppState;
import at.fhv.itb5c.view.util.popup.ErrorPopUp;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class LoginController implements ILogger{
	
	@FXML
	private Button _loginButton;

	@FXML
	public void loginButtonOnMouseReleasedButtonHandler(MouseEvent event) {		
		try {
			//TODO(san7985) replace with rmi functionality for checking the login
			IUserRMI _loggedInUser = RMIClient.getRMIClient().getUserFactory().createUser();
			
			if(_loggedInUser == null) {
				ErrorPopUp.invalideLoginCredentials();
			} else {
				AppState.getInstance().setLoggedInUser(_loggedInUser);
			}
			
		} catch (RemoteException e) {
			log.error(e.getMessage());
			ErrorPopUp.connectionError();
		}
		
	}
	
}
