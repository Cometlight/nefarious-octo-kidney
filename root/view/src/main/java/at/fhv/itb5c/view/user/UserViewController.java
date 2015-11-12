package at.fhv.itb5c.view.user;

import java.io.Closeable;
import java.io.IOException;
import java.rmi.RemoteException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import org.controlsfx.control.CheckListView;

import at.fhv.itb5c.commons.dto.rmi.IUserRMI;
import at.fhv.itb5c.commons.enums.Gender;
import at.fhv.itb5c.commons.enums.TypeOfSport;
import at.fhv.itb5c.commons.enums.UserRole;
import at.fhv.itb5c.rmi.client.RMIClient;
import at.fhv.itb5c.view.user.states.DetailUserViewControlls;
import at.fhv.itb5c.view.user.states.ModifyUserViewControlls;
import at.fhv.itb5c.view.user.states.AddUserViewControllsController;
import at.fhv.itb5c.view.util.RouteProvider;
import at.fhv.itb5c.view.util.interfaces.IPanelClosable;
import at.fhv.itb5c.view.util.interfaces.IPanelCloseHandler;
import at.fhv.itb5c.view.util.popup.ErrorPopUp;
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

public class UserViewController implements IPanelClosable, Closeable {
	
	@FXML private Label _titelLabel;
	@FXML private BorderPane _borderPane;
	@FXML private TextField _firstNameTextField;
	@FXML private TextField _lastNameTextField;
	@FXML private TextField _adressTextField;
	@FXML private TextField _eMailTextField;
	@FXML private TextField _telephoneNumberTextField;
	@FXML private ComboBox<Gender> _genderComboBox;
	@FXML private DatePicker _birthdayDatePicker;
	@FXML private CheckListView<TypeOfSport> _typeOfSportCheckListView;
	@FXML private TextField _memebershipFeeTextBox;
	@FXML private CheckListView<UserRole> _userRoleCheckListView;
	@FXML private Pane _controlPane;
	@FXML private Label _errorField;

	private UserModel _userModel;

	public enum UserViewState {
		addState, 
		detailState, 
		modifieState
	}
	
	private UserViewState _initialiseState;
	
	static {
		RouteProvider.getInstance().add(DetailUserViewControlls.class, "/view/fxml/user/DetailUserViewControlls.fxml");
		RouteProvider.getInstance().add(AddUserViewControllsController.class, "/view/fxml/user/AddUserViewControlls.fxml");
		RouteProvider.getInstance().add(ModifyUserViewControlls.class, "/view/fxml/user/ModifyUserViewControlls.fxml");
	}
	
	public UserViewController(UserModel userModel, UserViewState initialiseState) {
		_userModel = userModel;
		_userViewStates = new HashMap<>();
		
		_userViewStates.put(UserViewState.addState, new AddUserViewControllsController(this));
		_userViewStates.put(UserViewState.detailState, new DetailUserViewControlls(this));
		_userViewStates.put(UserViewState.modifieState, new ModifyUserViewControlls(this));
		_initialiseState = initialiseState;
	}

	public void initialize() throws IOException {
		_firstNameTextField.textProperty().bindBidirectional(_userModel.getFirstName());
		_lastNameTextField.textProperty().bindBidirectional(_userModel.getLastName());
		_eMailTextField.textProperty().bindBidirectional(_userModel.getEMail());
		_adressTextField.textProperty().bindBidirectional(_userModel.getAddress());
		_telephoneNumberTextField.textProperty().bindBidirectional(_userModel.getTelephonenumber());
		_genderComboBox.setItems(FXCollections.observableArrayList(Gender.values()));
		_genderComboBox.setValue(_userModel.getGender());
		_birthdayDatePicker.valueProperty().bindBidirectional(_userModel.getBirthDate());

		_typeOfSportCheckListView.setItems(FXCollections.observableArrayList(TypeOfSport.values()));

		for (TypeOfSport typeofSport : _userModel.getTypeOfSports()) {
			_typeOfSportCheckListView.getCheckModel().check(typeofSport);
		}

		_typeOfSportCheckListView.getCheckModel().getCheckedItems().addListener(new ListChangeListener<TypeOfSport>() {
			@Override
			public void onChanged(javafx.collections.ListChangeListener.Change<? extends TypeOfSport> c) {
				_userModel.setTypeOfSports(FXCollections.observableSet(new HashSet<TypeOfSport>(_typeOfSportCheckListView.getCheckModel().getCheckedItems())));
			}
		});

		_memebershipFeeTextBox.textProperty().bindBidirectional(_userModel.getMemberShipFee().asObject(),
				new DecimalFormat());

		_userRoleCheckListView.setItems(FXCollections.observableArrayList(UserRole.values()));


		for (UserRole userRole : _userModel.getUserRoles()) {
			_userRoleCheckListView.getCheckModel().check(userRole);
		}

		_userRoleCheckListView.getCheckModel().getCheckedItems().addListener(new ListChangeListener<UserRole>() {
			@Override
			public void onChanged(javafx.collections.ListChangeListener.Change<? extends UserRole> c) {
				_userModel.setUserRoles(FXCollections.observableSet(new HashSet<UserRole>( _userRoleCheckListView.getCheckModel().getCheckedItems())));
			}
		});
		
		_errorField.setVisible(false);
		if(!_userModel.getTypeOfSports().contains(UserRole.Admin)) {
			_userRoleCheckListView.setDisable(true);
		}
		setState(_initialiseState);
	}

	@FXML
	public void genderComboBoxOnActionEventHandler(ActionEvent event) {
		if (_genderComboBox.getValue() != null) {
			_userModel.setGender(_genderComboBox.getValue());
		}
	}

	private IPanelCloseHandler _panelCloseHandler;

	@Override
	public void setPanelCloseHandler(IPanelCloseHandler panelCloseHandler) {
		_panelCloseHandler = panelCloseHandler;
	}

	public boolean saveModel() {
		if (mandatoryFieldsSet()) {
			try {
				IUserRMI savedUser = RMIClient.getRMIClient().getApplicationFacade().saveUser(_userModel.getRMIUser());
				_userModel.setIUserRMI(savedUser);
			} catch (RemoteException e) {
				ErrorPopUp.connectionError();
				return false;
			}
			return true;
		} else {
			return false;
		}
	}

	private boolean mandatoryFieldsSet() {
		// refactor to validater -> string not empty validator ...
		if ((_userModel.getFirstName().getValue() != null) && (_userModel.getFirstName().getValue() != "")
				&& (_userModel.getLastName().getValue() != null) && (_userModel.getLastName().getValue() != "")
				&& (_userModel.getAddress().getValue() != null) && (_userModel.getAddress().getValue() != "")
				&& (_userModel.getBirthDate().getValue() != null) && (_userModel.getBirthDate() != null)
				&& (_userModel.getUserRoles().size() > 0)) {
			return true;
		} else {
			_errorField.setVisible(true);
			return false;
		}
	}

	public void close() {
		_panelCloseHandler.close();
	}

	private Map<UserViewState, IUserViewState> _userViewStates;

	public IUserViewState getState(UserViewState identifier) {
		if (_userViewStates.containsKey(identifier)) {
			return _userViewStates.get(identifier);
		} else {
			return null;
		}
	}

	public void setState(UserViewState nextState) throws IOException {
		IUserViewState userViewState = getState(nextState);

		if (userViewState != null) {
			FXMLLoader loader = new FXMLLoader();
			loader.setController(userViewState);
			loader.setLocation(userViewState.getControlsFXML());
			_titelLabel.setText(userViewState.getTitel());

			_controlPane.getChildren().clear();
			_controlPane.getChildren().add(loader.load());
			_borderPane.requestFocus();
			_errorField.setVisible(false);
			
			userViewState.activate();
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
		if(_userModel.getTypeOfSports().contains(UserRole.Admin)) {
			_userRoleCheckListView.setDisable(isDisabled);
		}
	}

	public UserModel getUserModel() {
		return _userModel;
	}
}
