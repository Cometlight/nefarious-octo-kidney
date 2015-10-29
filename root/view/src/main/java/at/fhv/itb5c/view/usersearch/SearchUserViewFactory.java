package at.fhv.itb5c.view.usersearch;

import java.io.IOException;
import java.rmi.RemoteException;
import at.fhv.itb5c.view.util.AlertUtil;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

public final class SearchUserViewFactory {
	
	public static void CreateNewUserView(Pane paneToPlaceIn) throws IOException{
		FXMLLoader loader = new FXMLLoader();

		loader.setLocation(SearchUserViewFactory.class.getResource("SearchUserView.fxml"));

		try {

			paneToPlaceIn.getChildren().add(loader.load());
			paneToPlaceIn.autosize();
			
		} catch (RemoteException e1) {
			AlertUtil.ConnectionAlert();
		}
	}
}
