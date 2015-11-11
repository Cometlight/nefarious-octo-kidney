package at.fhv.itb5c.view.team.view;

import at.fhv.itb5c.view.util.factories.AbstractViewFactory;

public class TeamViewFactory extends AbstractViewFactory {

	public TeamViewFactory() {
		super("/view/fxml/team/view.fxml", new TeamViewController());
	}
}
