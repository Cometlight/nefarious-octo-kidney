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
		if(MandatoryFieldsSet()) {
			//Todo call an event that indicats that the creation is done and the MainViewController can open an detailed view
			System.out.println("Valide");
		}
		else{
			System.out.println("Not Valide");
			//Todo add indication which fields are not valid
		}
	}
	
	private boolean MandatoryFieldsSet() {
		//TODO: add Date of Birth, Gender, Role,
		if((_userModel.getFirstName().getValue() != null) && (_userModel.getFirstName().getValue() != "") &&
				(_userModel.getLastName().getValue() != null) && (_userModel.getFirstName().getValue() != "") &&
				(_userModel.getAdress().getValue() != null) && (_userModel.getFirstName().getValue() != "")) {
			return true;
		}
		else {
			return false;
		}
	}

	@FXML
	public void CancleButtonMouseReleasedEventHandler(MouseEvent event) {
		//open popup to ask if the changes should be thrown away
		
		//register callback for yes now
		
		//act accordingle
	}
}
