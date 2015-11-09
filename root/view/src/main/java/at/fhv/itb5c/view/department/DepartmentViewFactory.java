package at.fhv.itb5c.view.department;

import at.fhv.itb5c.commons.dto.rmi.IDepartmentRMI;
import at.fhv.itb5c.view.util.factories.AbstractFactory;

public class DepartmentViewFactory extends AbstractFactory{
	public DepartmentViewFactory(IDepartmentRMI department) {
		super("/view/fxml/department/view.fxml", new DepartmentViewController(new DepartmentViewModel(department)));
	}	
}
