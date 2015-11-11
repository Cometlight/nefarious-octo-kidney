package at.fhv.itb5c.view.team.add;

import at.fhv.itb5c.commons.dto.rmi.IDepartmentRMI;
import at.fhv.itb5c.view.util.factories.AbstractViewFactory;

public class TeamAddViewFactory extends AbstractViewFactory{
	public TeamAddViewFactory(IDepartmentRMI department) {
		super("/view/fxml/team/add.fxml", new TeamAddController(department));
	}
}
