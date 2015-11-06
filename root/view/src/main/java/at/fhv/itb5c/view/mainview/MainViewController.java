package at.fhv.itb5c.view.mainview;

import java.io.IOException;
import java.rmi.RemoteException;

import at.fhv.itb5c.view.user.UserViewController.UserViewState;
import at.fhv.itb5c.commons.dto.IUser;
import at.fhv.itb5c.commons.dto.rmi.IDepartmentRMI;
import at.fhv.itb5c.commons.dto.rmi.IUserRMI;
import at.fhv.itb5c.commons.enums.TypeOfSport;
import at.fhv.itb5c.logging.ILogger;
import at.fhv.itb5c.rmi.client.RMIClient;
import at.fhv.itb5c.view.department.DepartmentViewFactory;
import at.fhv.itb5c.view.user.UserViewFactory;
import at.fhv.itb5c.view.usersearch.SearchUserViewFactory;
import at.fhv.itb5c.view.util.popup.ErrorPopUp;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
		//TODO: remove when test values are in the db
		_mainViewModel.getDepartments().add(new DummyDepartment("Fußball"));
		_mainViewModel.getDepartments().add(new DummyDepartment("Tennis"));
		_mainViewModel.getDepartments().add(new DummyDepartment("Basketball"));
		_departmentsListView.setItems(_mainViewModel.getDepartments());
		
		//TODO(san7985) when there are departments in the db
		//_departmentsListView.setItems(_mainViewModel.getDepartments());
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
		new UserViewFactory(UserViewState.addState).create(_mainPanel);
	}
	
	@FXML
	public void searchUserMenueItemActionHandler(ActionEvent event) throws IOException {
		new SearchUserViewFactory().create(_mainPanel);
	}
	
	@FXML
	public void departmentListViewOnMouseClick(MouseEvent mouseEvent) throws IOException {
		_departmentsListView.getFocusModel().focus(_departmentsListView.getSelectionModel().getSelectedIndex());
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
	
	//TODO: remove when test values are in the db
	private class DummyDepartment implements IDepartmentRMI {

		private String _name;
		
		public DummyDepartment(String name) {
			_name = name;
		}
		
		@Override
		public void setId(Long id) throws RemoteException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public Long getId() throws RemoteException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void setVersion(Long version) throws RemoteException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public Long getVersion() throws RemoteException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void setName(String name) throws RemoteException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public String getName() throws RemoteException {
			return _name;
		}

		@Override
		public IUserRMI getHead() throws RemoteException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void setHead(IUser head) throws RemoteException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public TypeOfSport getTypeOfSport() throws RemoteException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void setTypeOfSport(TypeOfSport typeOfSport) throws RemoteException {
			// TODO Auto-generated method stub
			
		}
		
	}
}
