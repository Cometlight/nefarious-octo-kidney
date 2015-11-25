package at.fhv.itb5c.view.team.invite;

import java.rmi.RemoteException;

import at.fhv.itb5c.commons.dto.rmi.ITeamRMI;
import at.fhv.itb5c.commons.dto.rmi.ITournamentRMI;
import at.fhv.itb5c.commons.dto.rmi.IUserRMI;
import at.fhv.itb5c.logging.ILogger;
import at.fhv.itb5c.rmi.client.RMIClient;
import at.fhv.itb5c.view.AppState;
import at.fhv.itb5c.view.util.popup.ErrorPopUp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class InvitePlayerToTournamentModel implements ILogger{
	private ITeamRMI _team;
	private ITournamentRMI _tournament;
	private ObservableList<IUserRMI> _teamMembers;
	
	public InvitePlayerToTournamentModel(ITeamRMI team, ITournamentRMI tournament) {
		_team = team;
		_tournament = tournament;
		
		_teamMembers = FXCollections.observableArrayList();	
		try {
			for(Long userID : team.getMemberIds()) {
				_teamMembers.add(RMIClient.getRMIClient().getApplicationFacade().getUserById(AppState.getInstance().getSessionID(),userID));
			}
		} catch (RemoteException e) {
			log.error(e.getMessage());
			ErrorPopUp.connectionError();
		}
	}
	
	public ITeamRMI getTeam() {
		return _team;
	}
	
	public ITournamentRMI getTournament() {
		return _tournament;
	}
	
	public ObservableList<IUserRMI> getPlayers() {
		return _teamMembers;
	}
}
