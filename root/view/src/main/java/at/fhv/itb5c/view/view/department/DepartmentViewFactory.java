package at.fhv.itb5c.view.view.department;

import at.fhv.itb5c.commons.dto.DepartmentDTO;
import at.fhv.itb5c.view.view.util.factories.AbstractViewFactory;

public class DepartmentViewFactory extends AbstractViewFactory{
	public DepartmentViewFactory(DepartmentDTO department) {
		super("/view/fxml/department/view.fxml", new DepartmentViewController(new DepartmentViewModel(department)));
	}	
}
