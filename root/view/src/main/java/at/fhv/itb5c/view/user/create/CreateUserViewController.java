package at.fhv.itb5c.view.user.create;

import java.text.DecimalFormat;
import org.controlsfx.control.CheckListView;
import at.fhv.itb5c.commons.enums.Gender;
import at.fhv.itb5c.commons.enums.TypeOfSport;
import at.fhv.itb5c.commons.enums.UserRole;
import at.fhv.itb5c.model.UserModel;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;


public class CreateUserViewController {
	
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
	
	@FXML 
	private ComboBox<Gender> _genderComboBox;
	
	@FXML
	private DatePicker _birthdayDatePicker;
	
	@FXML
	private CheckListView<TypeOfSport> _typeOfSportCheckListView;
	
	@FXML 
	private TextField _memebershipFeeTextBox;
	
	@FXML
	private ComboBox<UserRole> _roleComboBox;
	
	private UserModel _userModel;
	
	public CreateUserViewController(UserModel userModel) {
		_userModel = userModel;
	}
	
	public void initialize() {
		_firstNameTextField.textProperty().bindBidirectional(_userModel.getFirstName());
        _lastNameTextField.textProperty().bindBidirectional(_userModel.getLastName());
		_eMailTextField.textProperty().bindBidirectional(_userModel.getEMail());
		_adressTextField.textProperty().bindBidirectional(_userModel.getAdress());
        _telephoneNumberTextField.textProperty().bindBidirectional(_userModel.getTelephonenumber());   
        _genderComboBox.setItems(FXCollections.observableArrayList(Gender.values()));   
        _birthdayDatePicker.valueProperty().bindBidirectional(_userModel.getBirthDate());
	
        _typeOfSportCheckListView.setItems(FXCollections.observableArrayList(TypeOfSport.values()));
        _typeOfSportCheckListView.getCheckModel().getCheckedItems().addListener(new ListChangeListener<TypeOfSport>() {
			@Override
			public void onChanged(javafx.collections.ListChangeListener.Change<? extends TypeOfSport> c) {
				_userModel.setTypeOfSports(_typeOfSportCheckListView.getCheckModel().getCheckedItems());
			}
		});
        
        _memebershipFeeTextBox.textProperty().bindBidirectional(_userModel.getMemberShipFee().asObject(), new DecimalFormat());
        _roleComboBox.setItems(FXCollections.observableArrayList(UserRole.values()));
	}
	
	@FXML
	public void genderComboBoxOnActionEventHandler(ActionEvent event) {
		if(_genderComboBox.getValue() != null) 
		{
			_userModel.setGender(_genderComboBox.getValue());
		}
	}
	
	@FXML
	public void roleComboBoxOnActionEventHandler(ActionEvent event) {
		if(_roleComboBox.getValue() != null) 
		{
			_userModel.setUserRole(_roleComboBox.getValue());
		}
	}

	@FXML
	public void saveButtonMouseReleasedEventHandler(MouseEvent event) {
		if(MandatoryFieldsSet()) {
			//Todo deligat to an CreateUserController that deligates to rmi
			System.out.println("Valide");
		}
		else{
			System.out.println("Not Valide");
			//Todo add indication which fields are not valid
		}
	}
	
	private boolean MandatoryFieldsSet() {
		//refactor to validater -> string not empty validator ...
		if((_userModel.getFirstName().getValue() != null) && (_userModel.getFirstName().getValue() != "") &&
				(_userModel.getLastName().getValue() != null) && (_userModel.getFirstName().getValue() != "") &&
				(_userModel.getAdress().getValue() != null) && (_userModel.getFirstName().getValue() != "") &&
				(_userModel.getBirthDate().getValue() != null) && 
				(_userModel.getGender() != null) &&
				(_userModel.getUserRole() != null)) {
			return true;
		}
		else {
			return false;
		}
	}

	@FXML
	public void cancleButtonMouseReleasedEventHandler(MouseEvent event) {
		//open popup to ask if the changes should be thrown away
		
		//register callback for yes now
		
		//act accordingle
	}
}
