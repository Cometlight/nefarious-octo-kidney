package at.fhv.itb5c.view.team.invite;

import at.fhv.itb5c.commons.dto.TeamDTO;
import at.fhv.itb5c.commons.dto.TournamentDTO;
import at.fhv.itb5c.view.util.factories.AbstractPanelAndViewFactory;

public class InvitePlayersToTournamentPanelAndViewFactory extends AbstractPanelAndViewFactory{

	public InvitePlayersToTournamentPanelAndViewFactory(TournamentDTO tournament, TeamDTO team) {
		super("/view/fxml/team/invitePlayerToTournament.fxml", new InvitePlayersToTournamentController(tournament, team));
	}

}
