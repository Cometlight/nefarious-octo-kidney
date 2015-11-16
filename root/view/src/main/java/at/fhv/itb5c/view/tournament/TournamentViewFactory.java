package at.fhv.itb5c.view.tournament;

import at.fhv.itb5c.commons.dto.rmi.IDepartmentRMI;
import at.fhv.itb5c.commons.dto.rmi.ITournamentRMI;
import at.fhv.itb5c.view.util.factories.AbstractViewFactory;

public class TournamentViewFactory extends AbstractViewFactory {

	public TournamentViewFactory(IDepartmentRMI department, ITournamentRMI tournament) {
		super("/view/fxml/tournament/view.fxml", new TournamentViewController(department,tournament));
	}

}
