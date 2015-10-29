package at.fhv.itb5c.view.user;

import java.io.IOException;
import java.rmi.RemoteException;

import at.fhv.itb5c.rmi.client.RMIClient;
import at.fhv.itb5c.view.user.UserViewController.UserViewState;
import at.fhv.itb5c.view.util.AlertUtil;
import at.fhv.itb5c.view.util.PanelCloseHandler;
import at.fhv.itb5c.view.util.RouteProvider;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

public class UserViewFactory {
	
	public static void CreateNewUserView(Pane paneToPlaceIn, UserViewState initializeState) throws IOException {
		FXMLLoader loader = new FXMLLoader();

		loader.setLocation(RouteProvider.getInstance().getRoot(UserViewController.class));
		UserViewController userViewController;

		try {
			userViewController = new UserViewController(UserModel.createUserModel(RMIClient.getRMIClient().getUserFactory().createUser()), initializeState);
			userViewController.setPanelCloseHandler(new PanelCloseHandler() {

				@Override
				public void close() {
					paneToPlaceIn.getChildren().clear();
				}
			});
			loader.setController(userViewController);

			paneToPlaceIn.getChildren().add(loader.load());
			paneToPlaceIn.autosize();

			userViewController.initialize();
			
		} catch (RemoteException e1) {
			AlertUtil.ConnectionAlert();
		}
	}
}
