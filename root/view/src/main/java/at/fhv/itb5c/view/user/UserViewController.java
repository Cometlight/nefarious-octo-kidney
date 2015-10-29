package at.fhv.itb5c.view.user;

import java.io.Closeable;
import java.io.IOException;
import java.rmi.RemoteException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import org.controlsfx.control.CheckListView;
import at.fhv.itb5c.commons.enums.Gender;
import at.fhv.itb5c.commons.enums.TypeOfSport;
import at.fhv.itb5c.commons.enums.UserRole;
import at.fhv.itb5c.model.UserModel;
import at.fhv.itb5c.rmi.client.RMIClient;
import at.fhv.itb5c.util.AlertUtil;
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

public class UserViewController implements PanelClosable, Closeable {
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
	private CheckListView<UserRole> _userRoleCheckListView;

	@FXML
	private Pane _controlPane;

	private UserModel _userModel;

	public enum UserViewState {
		newState, 
		detailState, 
		modifieState
	}
	
	private UserViewState _initialiseState;

	public UserViewController(UserModel userModel, UserViewState initialiseState) {
		_userModel = userModel;
		_userViewStates = new HashMap<>();

		_userViewStates.put(UserViewState.newState, new NewUserViewControllsController(this));
		_userViewStates.put(UserViewState.detailState, new DetailUserViewControlls(this));
		_initialiseState = initialiseState;
	}

	public void initialize() throws IOException {
		_firstNameTextField.textProperty().bindBidirectional(_userModel.getFirstName());
		_lastNameTextField.textProperty().bindBidirectional(_userModel.getLastName());
		_eMailTextField.textProperty().bindBidirectional(_userModel.getEMail());
		_adressTextField.textProperty().bindBidirectional(_userModel.getAdress());
		_telephoneNumberTextField.textProperty().bindBidirectional(_userModel.getTelephonenumber());
		_genderComboBox.setItems(FXCollections.observableArrayList(Gender.values()));
		_birthdayDatePicker.valueProperty().bindBidirectional(_userModel.getBirthDate());

		_typeOfSportCheckListView.setItems(FXCollections.observableArrayList(TypeOfSport.values()));

		for (TypeOfSport typeofSport : _userModel.getTypeOfSports()) {
			_typeOfSportCheckListView.getCheckModel().check(typeofSport);
		}

		_typeOfSportCheckListView.getCheckModel().getCheckedItems().addListener(new ListChangeListener<TypeOfSport>() {
			@Override
			public void onChanged(javafx.collections.ListChangeListener.Change<? extends TypeOfSport> c) {
				_userModel.setTypeOfSports(_typeOfSportCheckListView.getCheckModel().getCheckedItems());
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
				_userModel.setUserRoles(_userRoleCheckListView.getCheckModel().getCheckedItems());
			}
		});
		setState(_initialiseState);
	}

	@FXML
	public void genderComboBoxOnActionEventHandler(ActionEvent event) {
		if (_genderComboBox.getValue() != null) {
			_userModel.setGender(_genderComboBox.getValue());
		}
	}

	private PanelCloseHandler _panelCloseHandler;

	@Override
	public void setPanelCloseHandler(PanelCloseHandler panelCloseHandler) {
		_panelCloseHandler = panelCloseHandler;
	}

	public boolean saveModel() {
		if (mandatoryFieldsSet()) {
			try {
				RMIClient.getRMIClient().getUserFactory().save(_userModel.getRMIUser());
			} catch (RemoteException e) {
				AlertUtil.ConnectionAlert();
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
				&& (_userModel.getLastName().getValue() != null) && (_userModel.getFirstName().getValue() != "")
				&& (_userModel.getAdress().getValue() != null) && (_userModel.getFirstName().getValue() != "")
				&& (_userModel.getBirthDate().getValue() != null) && (_userModel.getGender() != null)
				&& (_userModel.getUserRoles().size() > 0)) {
			return true;
		} else {
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
		_userRoleCheckListView.setDisable(isDisabled);
	}

	public UserModel getUserModel() {
		return _userModel;
	}
}
