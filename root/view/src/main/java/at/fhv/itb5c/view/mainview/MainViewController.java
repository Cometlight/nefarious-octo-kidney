package at.fhv.itb5c.view.mainview;

import java.io.IOException;
import java.rmi.RemoteException;
import at.fhv.itb5c.commons.dto.rmi.IDepartmentRMI;
import at.fhv.itb5c.logging.ILogger;
import at.fhv.itb5c.rmi.client.RMIClient;
import at.fhv.itb5c.view.department.DepartmentViewFactory;
import at.fhv.itb5c.view.user.UserViewFactory;
import at.fhv.itb5c.view.usersearch.SearchUserViewFactory;
import at.fhv.itb5c.view.util.popup.ErrorPopUp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Callback;

public class MainViewController implements ILogger{

	@FXML BorderPane _rootPane;
	@FXML Pane _mainPanel;
	@FXML ListView<IDepartmentRMI> _departmentsListView;
	
	public MainViewModel _mainViewModel;
	
	public MainViewController() {
		try {
			_mainViewModel = new MainViewModel(RMIClient.getRMIClient().getDepartmentFactory().getAllDepartments());
		} catch (RemoteException e) {
			log.error(e.getMessage());
			ErrorPopUp.connectionError();
		}
	}
	
	public void initialize() {
		_departmentsListView.setItems(_mainViewModel.getDepartments());
		_departmentsListView.setCellFactory(new Callback<ListView<IDepartmentRMI>, ListCell<IDepartmentRMI>>() {	
			@Override
			public ListCell<IDepartmentRMI> call(ListView<IDepartmentRMI> param) {
				return new DepartmentListView();
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
		new DepartmentViewFactory(_departmentsListView.getSelectionModel().getSelectedItem()).create(_mainPanel);
	}
	
	private class DepartmentListView extends ListCell<IDepartmentRMI> {
		@Override
		protected void updateItem(IDepartmentRMI item, boolean empty) {
			super.updateItem(item, empty);
			if((item != null) && (!empty)) {
				try {
					setText(item.getName());
				} catch (RemoteException e) {
					log.error(e.getMessage());
					ErrorPopUp.connectionError();
				}
			}
		}
	}
}
