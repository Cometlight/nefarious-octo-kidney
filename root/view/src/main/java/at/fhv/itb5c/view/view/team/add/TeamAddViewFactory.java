package at.fhv.itb5c.view.view.team.add;

import at.fhv.itb5c.commons.dto.DepartmentDTO;
import at.fhv.itb5c.view.view.util.factories.AbstractViewFactory;

public class TeamAddViewFactory extends AbstractViewFactory{
	public TeamAddViewFactory(DepartmentDTO department) {
		super("/view/fxml/team/add.fxml", new TeamAddController(department));
	}
}
