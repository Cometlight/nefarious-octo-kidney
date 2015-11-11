package at.fhv.itb5c.view.team.view;

import at.fhv.itb5c.commons.dto.rmi.IDepartmentRMI;
import at.fhv.itb5c.commons.dto.rmi.ITeamRMI;
import at.fhv.itb5c.view.util.factories.AbstractViewFactory;

public class TeamViewFactory extends AbstractViewFactory {

	public TeamViewFactory(IDepartmentRMI department, ITeamRMI team) {
		super("/view/fxml/team/view.fxml", new TeamViewController(department, team));
	}
}
