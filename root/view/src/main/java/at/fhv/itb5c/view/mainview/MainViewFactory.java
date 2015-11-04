package at.fhv.itb5c.view.mainview;

import java.io.IOException;

import at.fhv.itb5c.view.util.RouteProvider;
import at.fhv.itb5c.view.util.interfaces.IViewFactory;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

public class MainViewFactory implements IViewFactory{

	@Override
	public void create(Pane paneToPlaceIn) throws IOException {
		
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(RouteProvider.getInstance().get(MainViewController.class));
		loader.setController(new MainViewController());
		paneToPlaceIn.getChildren().add(loader.load());
	}
}
