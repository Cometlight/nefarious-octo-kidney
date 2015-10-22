package at.fhv.itb5c.view.user.create;

import at.fhv.itb5c.model.UserModel;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

//TODO: 

public class CreateUserController {
	
	@FXML
	private TextField _firstNameTextField;
	
	@FXML
	private TextField _lastNameTextField;
	
	@FXML
	private TextField _adressTextField;

	@FXML
	private TextField _eMailTextField;

	@FXML
	private TextField _telephoneNumberTextField;
	
	private UserModel _userModel;
	
	public CreateUserController(UserModel userModel) {
		_userModel = userModel;
	}
	
	public void initialize() {
		_firstNameTextField.textProperty().bindBidirectional(_userModel.getFirstName());
        _lastNameTextField.textProperty().bindBidirectional(_userModel.getLastName());
		_eMailTextField.textProperty().bindBidirectional(_userModel.getEMail());
		_adressTextField.textProperty().bindBidirectional(_userModel.getAdress());
        _telephoneNumberTextField.textProperty().bindBidirectional(_userModel.getTelephonenumber());
        

	}

	@FXML
	public void SaveButtonMouseReleasedEventHandler(MouseEvent event) {
		//validate (check manditory fields)
		
		//
	}

	@FXML
	public void CancleButtonMouseReleasedEventHandler(MouseEvent event) {
		//open popup to ask if the changes should be thrown away
		
		//register callback for yes now
		
		//act accordingle
	}
}
