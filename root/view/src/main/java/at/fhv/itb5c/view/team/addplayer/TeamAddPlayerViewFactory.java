package at.fhv.itb5c.view.team.addplayer;

import at.fhv.itb5c.application.dto.TeamDTO;
import at.fhv.itb5c.view.util.factories.AbstractViewFactory;

public class TeamAddPlayerViewFactory extends AbstractViewFactory {
	public TeamAddPlayerViewFactory(TeamDTO team) {
		super("/view/fxml/team/addplayer.fxml", new TeamAddPlayerController(team));
	}
}
