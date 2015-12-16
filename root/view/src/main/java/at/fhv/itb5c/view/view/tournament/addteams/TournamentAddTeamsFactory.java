package at.fhv.itb5c.view.view.tournament.addteams;

import at.fhv.itb5c.commons.dto.DepartmentDTO;
import at.fhv.itb5c.commons.dto.TournamentDTO;
import at.fhv.itb5c.view.view.util.factories.AbstractViewFactory;

public class TournamentAddTeamsFactory extends AbstractViewFactory {

	public TournamentAddTeamsFactory(DepartmentDTO department, TournamentDTO tournament) {
		super("/view/fxml/tournament/addTeams.fxml", new TournamentAddTeamsController(department,tournament));
	}

}
