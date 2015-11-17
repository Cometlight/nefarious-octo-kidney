package at.fhv.itb5c.view.tournament;

import at.fhv.itb5c.commons.dto.rmi.IMatchRMI;
import at.fhv.itb5c.view.util.factories.AbstractViewFactory;

public class MatchAddResultFactory extends AbstractViewFactory {

	public MatchAddResultFactory(IMatchRMI match) {
		super("/view/fxml/addMatchResult.fxml", new MatchAddResultController(match));
	}

}
