package at.fhv.itb5c.view.mainview;

import java.io.IOException;

import at.fhv.itb5c.util.StageUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class MainViewController {
	
	@FXML
	BorderPane _rootPane;
	
	@FXML
	private Button _loginButton;
	
	private static final String _relativLoginViewPath = "view/login/Login.fxml";

	@FXML
	public void LoginButtonReleasedHandler(MouseEvent event) {
		
		Stage loginStage = new Stage();
		loginStage.initModality(Modality.WINDOW_MODAL);
		loginStage.initOwner(_rootPane.getScene().getWindow());
		
		try {
			StageUtil.loadScene(_relativLoginViewPath, loginStage);
		} catch (IOException e) {
			// TODO add logging
			e.printStackTrace();
		}
		
		loginStage.show();
	}
}
