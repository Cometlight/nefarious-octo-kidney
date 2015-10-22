package at.fhv.itb5c.view.login;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class LoginController {
	
	@FXML
	BorderPane _rootPane;
	
	@FXML
	private Button _loginButton;

	@FXML
	public void LoginButton_OnMouseReleased_Button_Handler(MouseEvent event) {
		
		//todo login checks
		
		((Stage)_rootPane.getScene().getWindow()).close();
	}
	
}
