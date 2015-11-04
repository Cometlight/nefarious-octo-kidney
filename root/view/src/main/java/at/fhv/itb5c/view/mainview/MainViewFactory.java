package at.fhv.itb5c.view.mainview;

import java.io.IOException;

import at.fhv.itb5c.view.util.RouteProvider;
import at.fhv.itb5c.view.util.interfaces.IViewFactory;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class MainViewFactory implements IViewFactory{

	static {
		RouteProvider.getInstance().add(MainViewController.class, "/view/fxml/MainView.fxml");
	}
	
	@Override
	public void create(Pane paneToPlaceIn) throws IOException {
		throw new  UnsupportedOperationException();
	}

	@Override
	public Pane create() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(RouteProvider.getInstance().get(MainViewController.class));
		loader.setController(new MainViewController());
		BorderPane rootLayout = (BorderPane) loader.load();
		
		return  rootLayout;
	}
}
