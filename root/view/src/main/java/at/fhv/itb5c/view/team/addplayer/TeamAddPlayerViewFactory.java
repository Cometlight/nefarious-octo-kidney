package at.fhv.itb5c.view.team.addplayer;

import at.fhv.itb5c.commons.dto.rmi.ITeamRMI;
import at.fhv.itb5c.view.util.factories.AbstractViewFactory;

public class TeamAddPlayerViewFactory extends AbstractViewFactory {
	public TeamAddPlayerViewFactory(ITeamRMI team) {
		super("/view/fxml/team/addplayer.fxml", new TeamAddPlayerController(team));
	}
}
