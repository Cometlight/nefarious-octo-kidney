package at.fhv.itb5c.view.view.mainview;

import java.io.IOException;

import at.fhv.itb5c.commons.dto.DepartmentDTO;
import at.fhv.itb5c.logging.ILogger;
import at.fhv.itb5c.view.app.AppState;
import at.fhv.itb5c.view.communication.CommunicationErrorException;
import at.fhv.itb5c.view.communication.CommunicationFacadeProvider;
import at.fhv.itb5c.view.view.department.DepartmentViewFactory;
import at.fhv.itb5c.view.view.user.UserViewFactory;
import at.fhv.itb5c.view.view.usersearch.SearchUserViewFactory;
import at.fhv.itb5c.view.view.util.listcell.DepartmentListCell;
import at.fhv.itb5c.view.view.util.popup.ErrorPopUp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Callback;

public class MainViewController implements ILogger {

	@FXML
	BorderPane _rootPane;
	@FXML
	Pane _mainPanel;
	@FXML
	ListView<DepartmentDTO> _departmentsListView;

	public MainViewModel _mainViewModel;

	public MainViewController() {
		try {
			_mainViewModel = new MainViewModel(CommunicationFacadeProvider.getInstance().getCurrentFacade()
					.getAllDepartments(AppState.getInstance().getSessionID()));
		} catch (CommunicationErrorException e) {
			log.error(e.getMessage());
			ErrorPopUp.connectionError();
		}
	}

	public void initialize() {
		_departmentsListView.setItems(_mainViewModel.getDepartments());
		_departmentsListView.setCellFactory(new Callback<ListView<DepartmentDTO>, ListCell<DepartmentDTO>>() {
			@Override
			public ListCell<DepartmentDTO> call(ListView<DepartmentDTO> param) {
				return new DepartmentListCell();
			}
		});
	}

	@FXML
	public void closeMenueItemActionHandler(ActionEvent event) {
		((Stage) _rootPane.getScene().getWindow()).close();
	}

	@FXML
	public void addUserMenueItemActionHandler(ActionEvent event) throws IOException {
		new UserViewFactory().create(_mainPanel);
	}

	@FXML
	public void searchUserMenueItemActionHandler(ActionEvent event) throws IOException {
		new SearchUserViewFactory().create(_mainPanel);
	}

	@FXML
	public void departmentListViewOnMouseClick(MouseEvent mouseEvent) throws IOException {
		DepartmentDTO dept = _departmentsListView.getSelectionModel().getSelectedItem();
		if(dept != null) {
			new DepartmentViewFactory(dept).create(_mainPanel);
		}
	}
}
