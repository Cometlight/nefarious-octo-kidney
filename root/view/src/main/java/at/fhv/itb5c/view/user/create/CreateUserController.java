package at.fhv.itb5c.view.user.create;


import at.fhv.itb5c.model.UserModel;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

//TODO: 

public class CreateUserController {
	
	@FXML
	private TextField _firstNameTextField;
	
	private UserModel _userModel;
	
	public CreateUserController(UserModel userModel) {
		_userModel = userModel;
	}
	
	public void initialize() {
		_firstNameTextField.textProperty().bind(_userModel.getFirstName());
	}

	@FXML
	public void SaveButtonMouseReleasedEventHandler(MouseEvent event) {
		//validate (check manditory fields)
		
		//
	}

	@FXML
	public void CancleButtonMouseReleasedEventHandler(MouseEvent event) {
		//open popup to ask if shoure
		
		//register callback for yes now
		
		//act accordingle
	}
}
