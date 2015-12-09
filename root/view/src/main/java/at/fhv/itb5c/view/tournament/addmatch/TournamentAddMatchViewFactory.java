package at.fhv.itb5c.view.tournament.addmatch;

import at.fhv.itb5c.commons.dto.TournamentDTO;
import at.fhv.itb5c.view.util.factories.AbstractViewFactory;

public class TournamentAddMatchViewFactory extends AbstractViewFactory {
	public TournamentAddMatchViewFactory(TournamentDTO tournament) {
		super("/view/fxml/tournament/addmatch.fxml", new TournamentAddMatchViewController(tournament));
	}
}
