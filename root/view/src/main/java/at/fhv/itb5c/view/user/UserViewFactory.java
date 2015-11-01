package at.fhv.itb5c.view.user;

import java.io.IOException;
import java.rmi.RemoteException;

import at.fhv.itb5c.commons.dto.rmi.IUserRMI;
import at.fhv.itb5c.rmi.client.RMIClient;
import at.fhv.itb5c.view.user.UserViewController.UserViewState;
import at.fhv.itb5c.view.util.AlertUtil;
import at.fhv.itb5c.view.util.IViewFactory;
import at.fhv.itb5c.view.util.PanelCloseHandler;
import at.fhv.itb5c.view.util.RouteProvider;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

public class UserViewFactory implements IViewFactory {
	
	private UserViewState _userViewState;
	private IUserRMI _user;
	
	public UserViewFactory(UserViewState userViewState) {
		_userViewState = userViewState;
	}
	
	public UserViewFactory(UserViewState userViewState, IUserRMI user) {
		_userViewState = userViewState;
		_user = user;
	}
	
	@Override
	public void create(Pane paneToPlaceIn) throws IOException {
		paneToPlaceIn.getChildren().clear();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(RouteProvider.getInstance().get(UserViewController.class));
		UserViewController userViewController;

		try {
			UserModel userModel;
			if(_userViewState == UserViewState.newState) {
				userModel = UserModel.createUserModel(RMIClient.getRMIClient().getUserFactory().createUser());
			}
			else {
				userModel = UserModel.createUserModel(_user);
			}
			userViewController = new UserViewController(userModel, _userViewState);
			
			userViewController.setPanelCloseHandler(new PanelCloseHandler() {

				@Override
				public void close() {
					paneToPlaceIn.getChildren().clear();
				}

				@Override
				public void closeNext(IViewFactory viewFactory) throws IOException {
					viewFactory.create(paneToPlaceIn);
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
