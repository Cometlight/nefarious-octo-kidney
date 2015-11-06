package at.fhv.itb5c.view.util.factories;

import java.io.IOException;
import java.net.URL;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

public class AbstractPanelAndViewFactory implements IPanelAndViewFactory{

	private URL _viewPath;
	private Object _controller;
	
	public AbstractPanelAndViewFactory(String viewPath, Object controller) {
		_viewPath = getClass().getResource(viewPath);
		_controller = controller;
	}
	
	@Override
	public Pane create() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(_viewPath);
		loader.setController(_controller);
		Pane rootLayout = (Pane) loader.load();
		return  rootLayout;
	}

}
