package at.fhv.itb5c.view.view.tournament.add;

import at.fhv.itb5c.commons.dto.DepartmentDTO;
import at.fhv.itb5c.view.view.util.factories.AbstractViewFactory;

public class TournamentAddFactory extends AbstractViewFactory {

	public TournamentAddFactory(DepartmentDTO department) {
		super("/view/fxml/tournament/add.fxml", new TournamentAddController(department));
	}

}
