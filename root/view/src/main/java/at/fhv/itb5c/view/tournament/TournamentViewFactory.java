package at.fhv.itb5c.view.tournament;

import at.fhv.itb5c.application.dto.DepartmentDTO;
import at.fhv.itb5c.application.dto.TournamentDTO;
import at.fhv.itb5c.view.util.factories.AbstractViewFactory;

public class TournamentViewFactory extends AbstractViewFactory {

	public TournamentViewFactory(DepartmentDTO department, TournamentDTO tournament) {
		super("/view/fxml/tournament/view.fxml", new TournamentViewController(department,tournament));
	}

}
