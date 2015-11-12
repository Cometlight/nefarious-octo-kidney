package at.fhv.itb5c.view.team.view;

import java.rmi.RemoteException;

import at.fhv.itb5c.commons.dto.rmi.IDepartmentRMI;
import at.fhv.itb5c.commons.dto.rmi.ITeamRMI;
import at.fhv.itb5c.commons.dto.rmi.IUserRMI;
import at.fhv.itb5c.logging.ILogger;
import at.fhv.itb5c.rmi.client.RMIClient;
import at.fhv.itb5c.view.util.popup.ErrorPopUp;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class TeamViewModel implements ILogger{
	private IDepartmentRMI _department;
	private ITeamRMI _team;
	private StringProperty _teamName;
	private StringProperty _coachName;
	
	public TeamViewModel(IDepartmentRMI department, ITeamRMI team) {
		_department = department;
		_team = team;
		
		try {
			_teamName = new SimpleStringProperty(_team.getName());
			IUserRMI coach = RMIClient.getRMIClient().getApplicationFacade().getUserById(_team.getCoachId());
			_coachName = new SimpleStringProperty(coach.getFirstName() + " " + coach.getLastName());
		} catch (RemoteException e) {
			log.error(e.getMessage());
			ErrorPopUp.connectionError();
		}
	}
	
	public IDepartmentRMI getDepartment() {
		return _department;
	}
	
	public ITeamRMI getTeam() {
		return _team;
	}
	
	public StringProperty getTeamName() {
		return _teamName;
	}
	
	public StringProperty getCoachName() {
		return _coachName;
	}
}
