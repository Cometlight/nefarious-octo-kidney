package at.fhv.itb5c.view.mainview;

import java.io.IOException;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.Set;

import at.fhv.itb5c.commons.dto.IDepartment;
import at.fhv.itb5c.commons.dto.IUser;
import at.fhv.itb5c.commons.dto.rmi.IDepartmentRMI;
import at.fhv.itb5c.commons.dto.rmi.IUserRMI;
import at.fhv.itb5c.commons.enums.Gender;
import at.fhv.itb5c.commons.enums.TypeOfSport;
import at.fhv.itb5c.commons.enums.UserRole;
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
		//TODO(san7985): Remove when i FINALLY get my test data
		_mainViewModel.getDepartments().add(new DepartmentDummy());
		
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
		//_departmentsListView.getFocusModel().focus(_departmentsListView.getSelectionModel().getSelectedIndex());
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
	
	//TODO(san7985): remove when i FINALLY get some test data
	private class DepartmentDummy implements IDepartmentRMI {
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
			return "Some Dummy Name";
		}

		@Override
		public IUserRMI getHead() throws RemoteException {
			return new DummyUser();
		}

		@Override
		public void setHead(IUser head) throws RemoteException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public TypeOfSport getTypeOfSport() throws RemoteException {
			return TypeOfSport.Soccer;
		}

		@Override
		public void setTypeOfSport(TypeOfSport typeOfSport) throws RemoteException {
			// TODO Auto-generated method stub
			
		}
	}
	
	private class DummyUser implements IUserRMI {

		@Override
		public String getFirstName() throws RemoteException {
			return "Some";
		}

		@Override
		public void setFirstName(String firstName) throws RemoteException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public String getLastName() throws RemoteException {
			return "Dummy";
		}

		@Override
		public void setLastName(String lastName) throws RemoteException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public String getEmail() throws RemoteException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void setEmail(String email) throws RemoteException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public String getTelephoneNumber() throws RemoteException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void setTelephoneNumber(String telephoneNumber) throws RemoteException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public Gender getGender() throws RemoteException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void setGender(Gender gender) throws RemoteException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public String getAddress() throws RemoteException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void setAddress(String address) throws RemoteException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public LocalDate getDateOfBirth() throws RemoteException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void setDateOfBirth(LocalDate dateOfBirth) throws RemoteException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public double getMembershipFee() throws RemoteException {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public void setMembershipFee(double membershipFee) throws RemoteException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public Set<UserRole> getRoles() throws RemoteException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void setRoles(Set<UserRole> roles) throws RemoteException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public Set<TypeOfSport> getTypeOfSports() throws RemoteException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void setTypeOfSports(Set<TypeOfSport> typeOfSports) throws RemoteException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public Long getId() throws RemoteException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Long getVersion() throws RemoteException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void setId(Long id) throws RemoteException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void setVersion(Long version) throws RemoteException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public boolean getMembershipFeePaid() throws RemoteException {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public void setMembershipFeePaid(boolean membershipFeePaid) throws RemoteException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public IDepartmentRMI getDepartment() throws RemoteException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void setDepartment(IDepartment department) throws RemoteException {
			// TODO Auto-generated method stub
			
		}
		
	}
}
