package at.fhv.itb5c.view.tournament.addmatchresult;

import at.fhv.itb5c.commons.dto.rmi.IDepartmentRMI;
import at.fhv.itb5c.commons.dto.rmi.IMatchRMI;
import at.fhv.itb5c.commons.dto.rmi.ITournamentRMI;
import at.fhv.itb5c.view.util.factories.AbstractViewFactory;

public class MatchAddResultFactory extends AbstractViewFactory {

	public MatchAddResultFactory(IMatchRMI match, IDepartmentRMI department, ITournamentRMI tournament) {
		super("/view/fxml/tournament/addMatchResult.fxml", new MatchAddResultController(match, department, tournament));
	}

}
