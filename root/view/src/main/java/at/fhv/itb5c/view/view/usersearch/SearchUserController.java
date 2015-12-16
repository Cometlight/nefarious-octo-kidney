package at.fhv.itb5c.view.view.usersearch;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import at.fhv.itb5c.commons.dto.DepartmentDTO;
import at.fhv.itb5c.commons.dto.UserDTO;
import at.fhv.itb5c.view.app.AppState;
import at.fhv.itb5c.view.communication.CommunicationErrorException;
import at.fhv.itb5c.view.communication.CommunicationFacadeProvider;
import at.fhv.itb5c.view.view.user.UserViewFactory;
import at.fhv.itb5c.view.view.user.UserViewController.UserViewState;
import at.fhv.itb5c.view.view.util.interfaces.IPanelClosable;
import at.fhv.itb5c.view.view.util.interfaces.IPanelCloseHandler;
import at.fhv.itb5c.view.view.util.popup.ErrorPopUp;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

public class SearchUserController implements IPanelClosable {

	@FXML
	private TextField _firstNameTextField;
	@FXML
	private TextField _lastNameTextField;
	@FXML
	private CheckBox _paidCheckBox;
	@FXML
	private ComboBox<DepartmentDTO> _departmentsCombobox;
	@FXML
	private TableView<UserDTO> _searchResultTableView;
	@FXML
	private TableColumn<UserDTO, String> _firstNameTableColumn;
	@FXML
	private TableColumn<UserDTO, String> _lastNameTableColumn;
	@FXML
	private TableColumn<UserDTO, String> _addressTableColumn;
	@FXML
	private TableColumn<UserDTO, String> _dateOfBirthTableColumn;
	@FXML
	private TableColumn<UserDTO, String> _membershipFeeTableColumn;
	@FXML
	private Label _searchResultCountLable;

	private IPanelCloseHandler _panelCloseHandler;
	private SearchUserModel _searchUserModel;

	public SearchUserController() {
		_searchUserModel = new SearchUserModel();
	}

	public void initialize() {
		_firstNameTextField.textProperty().bindBidirectional(_searchUserModel.getFirstName());
		_lastNameTextField.textProperty().bindBidirectional(_searchUserModel.getLastName());
		_paidCheckBox.selectedProperty().bindBidirectional(_searchUserModel.getIsPaid());
		_searchResultTableView.setItems(_searchUserModel.getSearchResult());

		try {
			_departmentsCombobox.setItems(FXCollections.observableArrayList(CommunicationFacadeProvider.getInstance()
					.getCurrentFacade().getAllDepartments(AppState.getInstance().getSessionID())));
		} catch (CommunicationErrorException e) {
			ErrorPopUp.connectionError();
		}
		_departmentsCombobox.valueProperty().bindBidirectional(_searchUserModel.getDepartment());

		initializeTable();
	}

	private void showUser(UserDTO selectedUser) {
		try {
			_panelCloseHandler.closeNext(new UserViewFactory(UserViewState.detailState, selectedUser));
		} catch (IOException e) {
			ErrorPopUp.criticalSystemError();
		}
	}

	public void initializeTable() {
		_searchResultTableView.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> showUser(newValue));

		_firstNameTableColumn.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<UserDTO, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(CellDataFeatures<UserDTO, String> param) {
						String value;
						if (param.getValue().getFirstName() != null) {
							value = param.getValue().getFirstName().toString();
						} else {
							value = "";
						}

						return new SimpleStringProperty(value);
					}
				});

		_lastNameTableColumn.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<UserDTO, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(CellDataFeatures<UserDTO, String> param) {
						String value;

						if (param.getValue().getLastName() != null) {
							value = param.getValue().getLastName().toString();
						} else {
							value = "";
						}

						return new SimpleStringProperty(value);
					}
				});

		_addressTableColumn.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<UserDTO, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(CellDataFeatures<UserDTO, String> param) {
						String value;

						if (param.getValue().getAddress() != null) {
							value = param.getValue().getAddress().toString();
						} else {
							value = "";
						}

						return new SimpleStringProperty(value);
					}
				});

		_dateOfBirthTableColumn.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<UserDTO, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(CellDataFeatures<UserDTO, String> param) {
						String value;

						if (param.getValue().getDateOfBirth() != null) {
							value = param.getValue().getDateOfBirth().toString();
						} else {
							value = "";
						}

						return new SimpleStringProperty(value);
					}
				});

		_membershipFeeTableColumn.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<UserDTO, String>, ObservableValue<String>>() {

					@Override
					public ObservableValue<String> call(CellDataFeatures<UserDTO, String> param) {
						String value;

						if (param.getValue().getMembershipFeePaid()) {
							value = "Yes";
						} else {
							value = "No";
						}

						return new SimpleStringProperty(value);
					}
				});
	}

	@FXML
	public void searchButtonOnReleasedEventHandler(MouseEvent mouseEvent) {
		try {
			Collection<UserDTO> users = null;
			if (_searchUserModel.getDepartment().get() == null) {
				users = new ArrayList<>(CommunicationFacadeProvider.getInstance().getCurrentFacade().findUsers(
						AppState.getInstance().getSessionID(), _searchUserModel.getFirstName().getValue(),
						_searchUserModel.getLastName().getValue(), null, _searchUserModel.getIsPaid().getValue()));
			} else {
				users = CommunicationFacadeProvider.getInstance().getCurrentFacade().findUsers(AppState.getInstance().getSessionID(),
						_searchUserModel.getFirstName().getValue(), _searchUserModel.getLastName().getValue(),
						_searchUserModel.getDepartment().get().getId(), _searchUserModel.getIsPaid().getValue());
			}

			_searchResultCountLable.setText(Integer.toString(users.size()));

			_searchUserModel.getSearchResult().clear();
			_searchUserModel.getSearchResult().addAll(users);
		} catch (CommunicationErrorException e) {
			ErrorPopUp.connectionError();
		}
	}

	@FXML
	public void cancelButtonOnMouseReleasedEventHandler(MouseEvent mouseEvent) {
		_panelCloseHandler.close();
	}

	@Override
	public void setPanelCloseHandler(IPanelCloseHandler panelCloseHandler) {
		_panelCloseHandler = panelCloseHandler;
	}
}
