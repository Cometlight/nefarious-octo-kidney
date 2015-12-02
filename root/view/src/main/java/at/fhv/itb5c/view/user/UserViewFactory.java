package at.fhv.itb5c.view.user;

import java.rmi.RemoteException;

import at.fhv.itb5c.app.AppState;
import at.fhv.itb5c.commons.dto.rmi.IUserRMI;
import at.fhv.itb5c.rmi.client.RMIClient;
import at.fhv.itb5c.view.user.UserViewController.UserViewState;
import at.fhv.itb5c.view.util.factories.AbstractViewFactory;
import at.fhv.itb5c.view.util.popup.ErrorPopUp;

public class UserViewFactory extends AbstractViewFactory {

	public UserViewFactory() {
		super("/view/fxml/user/UserView.fxml", createController(UserViewState.addState, null));
	}
	
	public UserViewFactory(UserViewState userViewState, IUserRMI user) {
		super("/view/fxml/user/UserView.fxml", createController(userViewState, user));
	}

	public static Object createController(UserViewState userViewState, IUserRMI user) {
		try {
			UserModel userModel;
			if (userViewState == UserViewState.addState) {
				userModel = UserModel.createUserModel(RMIClient.getRMIClient().getApplicationFacade().createUser(AppState.getInstance().getSessionID()));
			} else {
				userModel = UserModel.createUserModel(user);
			}
			UserViewController userViewController = new UserViewController(userModel, userViewState);
			return userViewController;
		} 
		catch (RemoteException e1)
		{
			ErrorPopUp.connectionError();
			return null;
		}
	}
}
