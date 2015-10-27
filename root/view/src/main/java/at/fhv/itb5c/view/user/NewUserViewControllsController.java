package at.fhv.itb5c.view.user;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

public class NewUserViewControllsController{
	
	private UserViewController _userViewController;
	
	public NewUserViewControllsController(UserViewController userViewController) {
		_userViewController = userViewController;
	}	
	
	@FXML 
	public void saveButtonMouseReleasedEventHandler(MouseEvent mouseEvent) {
		_userViewController.saveModel();
	}
	
	@FXML 
	public void cancleButtonMouseReleasedEventHandler(MouseEvent mouseEvent) {
		_userViewController.close();
	}
	
}
