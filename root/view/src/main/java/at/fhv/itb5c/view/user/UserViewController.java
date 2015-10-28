package at.fhv.itb5c.view.user;

import java.io.Closeable;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.HashMap;

import org.controlsfx.control.CheckListView;
import at.fhv.itb5c.commons.enums.Gender;
import at.fhv.itb5c.commons.enums.TypeOfSport;
import at.fhv.itb5c.commons.enums.UserRole;
import at.fhv.itb5c.model.UserModel;
import at.fhv.itb5c.util.PanelClosable;
import at.fhv.itb5c.util.PanelCloseHandler;
import at.fhv.itb5c.view.user.states.DetailUserViewControlls;
import at.fhv.itb5c.view.user.states.NewUserViewControllsController;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class UserViewController implements PanelClosable, Closeable{
	@FXML
	private Label _titelLabel;
			
	@FXML
	private BorderPane _borderPane;
	
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
	
	@FXML
	private Pane _controlPane;
	
	private UserModel _userModel;
	
	public enum ViewState{
		newState ("newState"),
		detailState ("detailState"),
		modifieState ("modifieState");
		
		private final String _name;  
		
		private ViewState(String name) {
		        _name = name;
		    }

	    public String toString() {
	       return _name;
	    }
	}
	
	public UserViewController(UserModel userModel) {
		_userModel = userModel;
		_userViewStates = new HashMap<>();
		
		_userViewStates.put(ViewState.newState, new NewUserViewControllsController(this));
		_userViewStates.put(ViewState.detailState, new DetailUserViewControlls(this));
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
        
        setState(ViewState.newState);
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

	private PanelCloseHandler _panelCloseHandler;
	
	@Override
	public void setPanelCloseHandler(PanelCloseHandler panelCloseHandler) {
		_panelCloseHandler = panelCloseHandler;
	}

	public boolean saveModel() {
		if(mandatoryFieldsSet()) {
			return true;
		} else {
			return false;
		}
	}
	
	private boolean mandatoryFieldsSet() {
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

	public void close() {
		_panelCloseHandler.close();
	}
	
	private HashMap<ViewState, UserViewState> _userViewStates;
	
	public UserViewState getState(ViewState identifier) {
		if(_userViewStates.containsKey(identifier)) {
			return _userViewStates.get(identifier);
		}
		else {
			return null;
		}
	}
	
	public void setState(ViewState nextState) {	
		UserViewState userViewState = getState(nextState);
		
		if(userViewState != null) {
			FXMLLoader loader = new FXMLLoader();
			loader.setController(userViewState);
			loader.setLocation(userViewState.getControlsFXML());
			_titelLabel.setText(userViewState.getTitel());

			try {
				_controlPane.getChildren().clear();
				_controlPane.getChildren().add(loader.load());
				userViewState.activate();
			} catch (IOException e) {
				//TODO handle error
				e.printStackTrace();
			}
		}
	}
	
	public void setDisable(boolean isDisabled) {
		_firstNameTextField.setDisable(isDisabled);
		_lastNameTextField.setDisable(isDisabled);
		_adressTextField.setDisable(isDisabled);
		_eMailTextField.setDisable(isDisabled);
		_telephoneNumberTextField.setDisable(isDisabled);
		_genderComboBox.setDisable(isDisabled);
		_birthdayDatePicker.setDisable(isDisabled);
		_typeOfSportCheckListView.setDisable(isDisabled);
		_memebershipFeeTextBox.setDisable(isDisabled);
		_roleComboBox.setDisable(isDisabled);
	}
	
	public UserModel getUserModel() {
		return _userModel;
	}
}
