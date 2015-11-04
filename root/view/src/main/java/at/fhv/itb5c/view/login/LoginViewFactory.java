package at.fhv.itb5c.view.login;

import java.io.IOException;

import at.fhv.itb5c.view.util.RouteProvider;
import at.fhv.itb5c.view.util.interfaces.IPanelAndViewFactory;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

public class LoginViewFactory implements IPanelAndViewFactory {

	static {
		RouteProvider.getInstance().add(LoginController.class, "/view/fxml/Login.fxml");
	}

	@Override
	public Pane create() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(RouteProvider.getInstance().get(LoginController.class));
		loader.setController(new LoginController());
		Pane rootLayout = (Pane) loader.load();
		
		return  rootLayout;
	}

}
