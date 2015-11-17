package at.fhv.itb5c.view.tournament.addmatch;

import at.fhv.itb5c.commons.dto.rmi.ITournamentRMI;
import at.fhv.itb5c.view.util.factories.AbstractViewFactory;

public class TournamentAddMatchViewFactory extends AbstractViewFactory {
	public TournamentAddMatchViewFactory(ITournamentRMI tournament) {
		super("/view/fxml/tournament/addmatch.fxml", new TournamentAddMatchViewController(tournament));
	}
}
