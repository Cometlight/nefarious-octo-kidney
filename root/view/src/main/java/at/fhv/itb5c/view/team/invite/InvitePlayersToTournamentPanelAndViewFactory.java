package at.fhv.itb5c.view.team.invite;

import at.fhv.itb5c.commons.dto.rmi.ITeamRMI;
import at.fhv.itb5c.commons.dto.rmi.ITournamentRMI;
import at.fhv.itb5c.view.util.factories.AbstractPanelAndViewFactory;

public class InvitePlayersToTournamentPanelAndViewFactory extends AbstractPanelAndViewFactory{

	public InvitePlayersToTournamentPanelAndViewFactory(ITournamentRMI tournament, ITeamRMI team) {
		super("/view/fxml/team/invitePlayerToTournament.fxml", new InvitePlayersToTournamentController(tournament, team));
	}

}
