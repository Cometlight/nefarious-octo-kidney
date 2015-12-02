package at.fhv.itb5c.view.tournament.addmatchresult;

import at.fhv.itb5c.application.dto.DepartmentDTO;
import at.fhv.itb5c.application.dto.MatchDTO;
import at.fhv.itb5c.application.dto.TournamentDTO;
import at.fhv.itb5c.view.util.factories.AbstractViewFactory;

public class MatchAddResultFactory extends AbstractViewFactory {

	public MatchAddResultFactory(MatchDTO match, DepartmentDTO department, TournamentDTO tournament) {
		super("/view/fxml/tournament/addMatchResult.fxml", new MatchAddResultController(match, department, tournament));
	}

}
