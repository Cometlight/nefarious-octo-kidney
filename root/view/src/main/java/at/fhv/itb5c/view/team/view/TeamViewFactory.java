package at.fhv.itb5c.view.team.view;

import at.fhv.itb5c.application.dto.DepartmentDTO;
import at.fhv.itb5c.application.dto.TeamDTO;
import at.fhv.itb5c.view.util.factories.AbstractViewFactory;

public class TeamViewFactory extends AbstractViewFactory {

	public TeamViewFactory(DepartmentDTO department, TeamDTO team) {
		super("/view/fxml/team/view.fxml", new TeamViewController(department, team));
	}
}
