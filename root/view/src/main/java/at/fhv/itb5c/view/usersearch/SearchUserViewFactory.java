package at.fhv.itb5c.view.usersearch;

import java.io.IOException;
import java.rmi.RemoteException;

import at.fhv.itb5c.view.util.RouteProvider;
import at.fhv.itb5c.view.util.interfaces.IViewFactory;
import at.fhv.itb5c.view.util.interfaces.PanelCloseHandler;
import at.fhv.itb5c.view.util.popup.AlertPopUp;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

public final class SearchUserViewFactory implements IViewFactory {
	
	public void create(Pane paneToPlaceIn) throws IOException {
		paneToPlaceIn.getChildren().clear();
		FXMLLoader loader = new FXMLLoader();

		loader.setLocation(RouteProvider.getInstance().get(SearchUserController.class));
		SearchUserController searchUserController = new SearchUserController();
		searchUserController.setPanelCloseHandler(new PanelCloseHandler() {
			@Override
			public void close() {
				paneToPlaceIn.getChildren().clear();
			}

			@Override
			public void closeNext(IViewFactory viewFactory) throws IOException {
				viewFactory.create(paneToPlaceIn);
			}
		});
			
		loader.setController(searchUserController);
		try {

			paneToPlaceIn.getChildren().add(loader.load());
			paneToPlaceIn.autosize();
			
		} catch (RemoteException e1) {
			AlertPopUp.ConnectionAlert();
		}
		
		searchUserController.initialize();
	}
}
