package at.fhv.itb5c.view.mainview;

import java.io.IOException;

import at.fhv.itb5c.model.UserModel;
import at.fhv.itb5c.util.StageUtil;
import at.fhv.itb5c.view.user.create.CreateUserController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/*
 * TODO:
 * 	create general loader for an fxml
 */

public class MainViewController {
	
	@FXML
	BorderPane _rootPane;
	
	@FXML
	TabPane _mainTabView;
	
	@FXML
	private Button _loginButton;
	
	private static final String _relativLoginViewPath = "view/login/Login.fxml";

	@FXML
	public void LoginMenueItemActionHandler(ActionEvent event) {
		
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
	
	@FXML
	public void CloseMenueItemActionHandler(ActionEvent event) {
		// TODO close resources etc . . .
		((Stage)_rootPane.getScene().getWindow()).close(); 
	}
	
	@FXML
	public void CreateUserMenueItemActionHandler(ActionEvent event) {
		Tab userTab = new Tab();
		FXMLLoader loader = new FXMLLoader();

		loader.setLocation(this.getClass().getResource("../user/create/CreateUser.fxml"));
		CreateUserController createUserController = new CreateUserController(new UserModel());
		loader.setController(createUserController);
		try {
			userTab.setText("Create User");
			userTab.setContent(loader.load());
			//userTab.setContent((Node)FXMLLoader.load(this.getClass().getResource("../user/create/CreateUser.fxml")));
		} catch (IOException e) {
			// TDOO add logging
			e.printStackTrace();
		}
		createUserController.initialize();
		_mainTabView.getTabs().add(userTab);
	}
}
