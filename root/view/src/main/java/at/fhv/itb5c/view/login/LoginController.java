package at.fhv.itb5c.view.login;

import java.util.Observable;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class LoginController extends Observable{
	@FXML
	private Button _loginButton;
	
	private boolean _isLoggedIn;

	@FXML
	public void LoginButton_OnMouseReleased_Button_Handler(MouseEvent event) {
		
		//todo login checks
		
		_isLoggedIn = true;
		this.setChanged();
		this.notifyObservers();	
	}
	
	public boolean getIsLoggedIn() {
		return _isLoggedIn;
	}
}
