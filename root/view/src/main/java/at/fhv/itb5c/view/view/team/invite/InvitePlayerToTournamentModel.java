package at.fhv.itb5c.view.view.team.invite;

import at.fhv.itb5c.commons.dto.TeamDTO;
import at.fhv.itb5c.commons.dto.TournamentDTO;
import at.fhv.itb5c.commons.dto.UserDTO;
import at.fhv.itb5c.logging.ILogger;
import at.fhv.itb5c.view.app.AppState;
import at.fhv.itb5c.view.communication.CommunicationErrorException;
import at.fhv.itb5c.view.communication.CommunicationFacadeProvider;
import at.fhv.itb5c.view.view.util.popup.ErrorPopUp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class InvitePlayerToTournamentModel implements ILogger{
	private TeamDTO _team;
	private TournamentDTO _tournament;
	private ObservableList<UserDTO> _teamMembers;
	
	public InvitePlayerToTournamentModel(TeamDTO team, TournamentDTO tournament) {
		_team = team;
		_tournament = tournament;
		
		_teamMembers = FXCollections.observableArrayList();	
		try {
			for(Long userID : team.getMemberIds()) {
				_teamMembers.add(CommunicationFacadeProvider.getInstance().getCurrentFacade().getUserById(AppState.getInstance().getSessionID(),userID));
			}
		} catch (CommunicationErrorException e) {
			log.error(e.getMessage());
			ErrorPopUp.connectionError();
		}
	}
	
	public TeamDTO getTeam() {
		return _team;
	}
	
	public TournamentDTO getTournament() {
		return _tournament;
	}
	
	public ObservableList<UserDTO> getPlayers() {
		return _teamMembers;
	}
}