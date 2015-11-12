package at.fhv.itb5c.view.department;

import java.rmi.RemoteException;

import at.fhv.itb5c.commons.dto.rmi.IDepartmentRMI;
import at.fhv.itb5c.commons.dto.rmi.ITeamRMI;
import at.fhv.itb5c.commons.dto.rmi.IUserRMI;
import at.fhv.itb5c.logging.ILogger;
import at.fhv.itb5c.rmi.client.RMIClient;
import at.fhv.itb5c.view.util.popup.ErrorPopUp;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DepartmentViewModel implements ILogger{

	private IDepartmentRMI _department;
	
	private StringProperty _departmentName;
	private StringProperty _nameHeadOfDepartment;
	private StringProperty _typeOfSport;
	private ObservableList<ITeamRMI> _teams;
	
	public DepartmentViewModel(IDepartmentRMI department) {
		_department = department;
		
		try {
			_departmentName = new SimpleStringProperty(_department.getName());
			IUserRMI head = RMIClient.getRMIClient().getApplicationFacade().getUserById(_department.getHeadId());
			_nameHeadOfDepartment = new SimpleStringProperty(head.getLastName() + " " + head.getFirstName());
			_typeOfSport = new SimpleStringProperty(_department.getTypeOfSport().toString());
			_teams = FXCollections.observableArrayList();
		} catch (RemoteException e) {
			ErrorPopUp.connectionError();
			log.error(e.getMessage());
		}
	}
	
	public IDepartmentRMI getDepartment() {
		return _department;
	}
	
	public StringProperty getDepartmentName() {
		return _departmentName;
	}
	
	public StringProperty getNameHeadOfDepartment() {
		return _nameHeadOfDepartment;
	}
	
	public StringProperty getTypeOfSport() {
		return _typeOfSport;
	}

	public ObservableList<ITeamRMI> getTeams() {
		return _teams;
	}
}
