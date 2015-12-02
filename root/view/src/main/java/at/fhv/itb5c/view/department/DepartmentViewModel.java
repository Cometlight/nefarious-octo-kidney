package at.fhv.itb5c.view.department;

import at.fhv.itb5c.app.AppState;
import at.fhv.itb5c.application.dto.DepartmentDTO;
import at.fhv.itb5c.application.dto.TeamDTO;
import at.fhv.itb5c.application.dto.TournamentDTO;
import at.fhv.itb5c.application.dto.UserDTO;
import at.fhv.itb5c.communication.CommunicationErrorException;
import at.fhv.itb5c.communication.CommunicationFacadeProvider;
import at.fhv.itb5c.logging.ILogger;
import at.fhv.itb5c.view.util.popup.ErrorPopUp;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DepartmentViewModel implements ILogger{

	private DepartmentDTO _department;
	private StringProperty _departmentName;
	private StringProperty _nameHeadOfDepartment;
	private StringProperty _typeOfSport;
	private ObservableList<TeamDTO> _teams;
	private ObservableList<TournamentDTO> _tournaments;
	
	public DepartmentViewModel(DepartmentDTO department) {
		_department = department;
		
		try {
			_departmentName = new SimpleStringProperty(_department.getName());
			UserDTO head = CommunicationFacadeProvider.getInstance().getCurrentFacade().getUserById(AppState.getInstance().getSessionID(), _department.getHeadId());
			_nameHeadOfDepartment = new SimpleStringProperty(head.getLastName() + " " + head.getFirstName());
			_typeOfSport = new SimpleStringProperty(_department.getTypeOfSport().toString());
			_teams = FXCollections.observableArrayList();
			_tournaments = FXCollections.observableArrayList();
		} catch (CommunicationErrorException e) {
			ErrorPopUp.connectionError();
			log.error(e.getMessage());
		}
	}
	
	public DepartmentDTO getDepartment() {
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

	public ObservableList<TeamDTO> getTeams() {
		return _teams;
	}

	public ObservableList<TournamentDTO> getTournaments() {
		return _tournaments;
	}
}
