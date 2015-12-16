package at.fhv.itb5c.view.view.team.view;

import at.fhv.itb5c.commons.dto.DepartmentDTO;
import at.fhv.itb5c.commons.dto.LeagueDTO;
import at.fhv.itb5c.commons.dto.TeamDTO;
import at.fhv.itb5c.commons.dto.UserDTO;
import at.fhv.itb5c.logging.ILogger;
import at.fhv.itb5c.view.app.AppState;
import at.fhv.itb5c.view.communication.CommunicationErrorException;
import at.fhv.itb5c.view.communication.CommunicationFacadeProvider;
import at.fhv.itb5c.view.view.util.popup.ErrorPopUp;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TeamViewModel implements ILogger {
	private DepartmentDTO _department;
	private TeamDTO _team;
	private StringProperty _teamName;
	private StringProperty _coachName;
	private StringProperty _leagueName;
	private ObservableList<UserDTO> _members;

	public TeamViewModel(DepartmentDTO department, TeamDTO team) {
		_department = department;
		_team = team;
		_members = FXCollections.observableArrayList();

		try {
			_teamName = new SimpleStringProperty(_team.getName());
			LeagueDTO league = CommunicationFacadeProvider.getInstance().getCurrentFacade().getLeagueById(AppState.getInstance().getSessionID(), team.getLeagueId());
			if (league != null) {
				_leagueName = new SimpleStringProperty(league.getName());
			} else {
				_leagueName = new SimpleStringProperty("");
			}
			UserDTO coach = CommunicationFacadeProvider.getInstance().getCurrentFacade().getUserById(AppState.getInstance().getSessionID(), _team.getCoachId());
			_coachName = new SimpleStringProperty(coach.getFirstName() + " " + coach.getLastName());
			
			// load all members
			String sessionId = AppState.getInstance().getSessionID();
			for(Long userId : _team.getMemberIds()) {
				_members.add(CommunicationFacadeProvider.getInstance().getCurrentFacade().getUserById(sessionId, userId));
			}
		} catch (CommunicationErrorException e) {
			log.error(e.getMessage());
			ErrorPopUp.connectionError();
		}
	}

	public DepartmentDTO getDepartment() {
		return _department;
	}

	public TeamDTO getTeam() {
		return _team;
	}

	public StringProperty getTeamName() {
		return _teamName;
	}

	public StringProperty getCoachName() {
		return _coachName;
	}

	public StringProperty getLeagueName() {
		return _leagueName;
	}

	public ObservableList<UserDTO> getMembers() {
		return _members;
	}
}
