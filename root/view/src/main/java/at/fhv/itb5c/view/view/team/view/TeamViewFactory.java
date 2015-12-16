package at.fhv.itb5c.view.view.team.view;

import at.fhv.itb5c.commons.dto.DepartmentDTO;
import at.fhv.itb5c.commons.dto.TeamDTO;
import at.fhv.itb5c.view.view.util.factories.AbstractViewFactory;

public class TeamViewFactory extends AbstractViewFactory {

	public TeamViewFactory(DepartmentDTO department, TeamDTO team) {
		super("/view/fxml/team/view.fxml", new TeamViewController(department, team));
	}
}
