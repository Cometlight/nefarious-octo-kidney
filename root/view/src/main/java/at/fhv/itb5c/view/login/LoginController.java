package at.fhv.itb5c.view.login;

import java.rmi.RemoteException;

import at.fhv.itb5c.commons.dto.rmi.IUserRMI;
import at.fhv.itb5c.logging.ILogger;
import at.fhv.itb5c.rmi.client.RMIClient;
import at.fhv.itb5c.view.AppState;
import at.fhv.itb5c.view.message.MessageHandler;
import at.fhv.itb5c.view.util.popup.ErrorPopUp;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class LoginController implements ILogger {

	@FXML
	TextField _usernameInput;

	@FXML
	PasswordField _passwordInput;

	private LoginModel _loginModel;

	public LoginController() {
		_loginModel = new LoginModel();
	}

	@FXML
	public void initialize() {
		_usernameInput.textProperty().bindBidirectional(_loginModel.getUserName());
		_passwordInput.textProperty().bindBidirectional(_loginModel.getPassword());
	}

	@FXML
	public void onSignInMouseReleasedHandler(MouseEvent event) {
		login();

	}

	@FXML
	public void onActionHandler() {
		login();
	}

	private void login() {
		try {

			String sessionID = RMIClient.getRMIClient().getApplicationFacade()
					.loginLDAP(_loginModel.getUserName().getValue(), _loginModel.getPassword().getValue());
			if (sessionID == null) {
				ErrorPopUp.invalidLoginCredentials();
			} else {
				// valid credentials
				AppState.getInstance().setSessionID(sessionID);
				IUserRMI currentUser = RMIClient.getRMIClient().getApplicationFacade().getCurrentUser(sessionID);
				MessageHandler.getInstance().listen(currentUser.getId());
			}

		} catch (RemoteException e) {
			log.error(e.getMessage());
			ErrorPopUp.connectionError();
		}
	}
}
