package at.fhv.itb5c.view.team.invite;

import at.fhv.itb5c.commons.dto.rmi.ITeamRMI;
import at.fhv.itb5c.commons.dto.rmi.ITournamentRMI;
import at.fhv.itb5c.commons.dto.rmi.IUserRMI;
import javafx.collections.ObservableList;

public class InvitePlayerToTournamentModel {
	private ITeamRMI _team;
	private ITournamentRMI _tournament;
	private ObservableList<IUserRMI> _teamMembers;
	
	public InvitePlayerToTournamentModel(ITeamRMI team, ITournamentRMI tournament) {
		_team = team;
		_tournament = tournament;
	}
	
	public ITeamRMI getTeam() {
		return _team;
	}
	
	public ITournamentRMI getTournament() {
		return _tournament;
	}
	
}
