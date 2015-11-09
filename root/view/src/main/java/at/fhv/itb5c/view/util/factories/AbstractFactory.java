package at.fhv.itb5c.view.util.factories;

import java.io.IOException;
import java.net.URL;

import at.fhv.itb5c.view.util.interfaces.IPanelClosable;
import at.fhv.itb5c.view.util.interfaces.IPanelCloseHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

public abstract class AbstractFactory implements IViewFactory{

	private URL _viewPath;
	private Object _controller;
	
	public AbstractFactory(String viewPath, Object controller) {
		_viewPath = this.getClass().getResource(viewPath);
		_controller = controller;
	}
	
	@Override
	public void create(Pane paneToPlaceIn) throws IOException {
		paneToPlaceIn.getChildren().clear();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(_viewPath);
		
		if(_controller instanceof IPanelClosable) {
			((IPanelClosable)_controller).setPanelCloseHandler(new IPanelCloseHandler() {

				@Override
				public void close() {
					paneToPlaceIn.getChildren().clear(); 
				}

				@Override
				public void closeNext(IViewFactory viewFactory) throws IOException {
					viewFactory.create(paneToPlaceIn);
				}
				});
		}
		
		loader.setController(_controller);
		paneToPlaceIn.getChildren().add(loader.load());
	}

}
