package at.fhv.itb5c.view.tournament;

import at.fhv.itb5c.commons.dto.rmi.IDepartmentRMI;
import at.fhv.itb5c.view.util.factories.AbstractViewFactory;

public class TournamentAddFactory extends AbstractViewFactory {

	public TournamentAddFactory(IDepartmentRMI department) {
		super("/view/fxml/tournament/add.fxml", new TournamentAddController(department));
	}

}
