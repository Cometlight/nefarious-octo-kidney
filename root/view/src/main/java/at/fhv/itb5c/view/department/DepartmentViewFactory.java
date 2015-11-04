package at.fhv.itb5c.view.department;

import java.io.IOException;

import at.fhv.itb5c.commons.dto.rmi.IDepartmentRMI;
import at.fhv.itb5c.view.util.RouteProvider;
import at.fhv.itb5c.view.util.interfaces.IViewFactory;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

public class DepartmentViewFactory implements IViewFactory{

	static {
		RouteProvider.getInstance().add(DepartmentViewController.class, "/view/fxml/department/view.fxml");	
	}
	
	private IDepartmentRMI _department; 
	
	public DepartmentViewFactory(IDepartmentRMI department) {
		_department = department;
	}
	
	@Override
	public void create(Pane paneToPlaceIn) throws IOException {
		paneToPlaceIn.getChildren().clear();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(RouteProvider.getInstance().get(DepartmentViewController.class));
		loader.setController(new DepartmentViewController(new DepartmentViewModel(_department)));
		paneToPlaceIn.getChildren().add(loader.load());
	}

}
