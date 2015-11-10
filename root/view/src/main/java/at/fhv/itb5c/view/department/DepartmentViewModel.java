package at.fhv.itb5c.view.department;

import at.fhv.itb5c.logging.ILogger;

public class DepartmentViewModel implements ILogger{

	/*private IDepartmentRMI _department;
	
	private StringProperty _departmentName;
	private StringProperty _nameHeadOfDepartment;
	private StringProperty _typeOfSport;
	
	public DepartmentViewModel(IDepartmentRMI department) {
		_department = department;
		
		try {
			_departmentName = new SimpleStringProperty(_department.getName());
			_nameHeadOfDepartment = new SimpleStringProperty(_department.getHead().getLastName() + " " + _department.getHead().getFirstName());
			_typeOfSport = new SimpleStringProperty(_department.getTypeOfSport().toString());
		} catch (RemoteException e) {
			ErrorPopUp.connectionError();
			log.error(e.getMessage());
		}
	}
	
	public StringProperty getDepartmentName() {
		return _departmentName;
	}
	
	public StringProperty getNameHeadOfDepartment() {
		return _nameHeadOfDepartment;
	}
	
	public StringProperty getTypeOfSport() {
		return _typeOfSport;
	}*/
}
