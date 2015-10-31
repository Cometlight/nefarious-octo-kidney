package at.fhv.itb5c.view.usersearch;

import at.fhv.itb5c.view.util.PanelClosable;
import at.fhv.itb5c.view.util.PanelCloseHandler;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class SearchUserController implements PanelClosable{
	
	@FXML private TextField _firstNameTextField;	
	@FXML private TextField _lastNameTextField;	
	@FXML private DatePicker _dateOfBirthDatePicker;
	@FXML private CheckBox _paidCheckBox;
	
	private PanelCloseHandler _panelCloseHandler;
	private SearchUserModel _searchUserModel;
	
	public SearchUserController() {
		_searchUserModel = new SearchUserModel();
	}
	
	public void initialize() {
		_firstNameTextField.textProperty().bindBidirectional(_searchUserModel.getFirstName());
		_lastNameTextField.textProperty().bindBidirectional(_searchUserModel.getLastName());
		_dateOfBirthDatePicker.valueProperty().bindBidirectional(_searchUserModel.getDateOfBirth());
		_paidCheckBox.selectedProperty().bindBidirectional(_searchUserModel.getIsPaid());
	}
	
	@FXML
	public void searchButtonOnReleasedEventHandler(MouseEvent mouseEvent){
		//TODO(san7985) add functionality for search user
	}
	
	@FXML
	public void cancelButtonOnMouseReleasedEventHandler(MouseEvent mouseEvent){
		_panelCloseHandler.close();
	}

	@Override
	public void setPanelCloseHandler(PanelCloseHandler panelCloseHandler) {
		_panelCloseHandler = panelCloseHandler;
	}
}
