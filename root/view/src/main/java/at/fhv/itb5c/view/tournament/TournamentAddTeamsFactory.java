package at.fhv.itb5c.view.tournament;

import at.fhv.itb5c.commons.dto.rmi.IDepartmentRMI;
import at.fhv.itb5c.commons.dto.rmi.ITournamentRMI;
import at.fhv.itb5c.view.util.factories.AbstractViewFactory;

public class TournamentAddTeamsFactory extends AbstractViewFactory {

	public TournamentAddTeamsFactory(IDepartmentRMI department, ITournamentRMI tournament) {
		super("/view/fxml/tournament/addTeams.fxml", new TournamentAddTeamsController(department,tournament));
	}

}
