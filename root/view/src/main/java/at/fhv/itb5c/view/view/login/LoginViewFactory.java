package at.fhv.itb5c.view.view.login;

import at.fhv.itb5c.view.view.util.factories.AbstractPanelAndViewFactory;

public class LoginViewFactory extends AbstractPanelAndViewFactory {

	public LoginViewFactory() {
		super("/view/fxml/Login.fxml", new LoginController());
	}
}
