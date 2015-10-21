package at.fhv.itb5c.view;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Program extends Application {

	public Stage primaryStage;
	public BorderPane rootLayout;
	
	@Override
	public void start(Stage stage) {
		stage.setTitle("Hallo User");
		this.primaryStage = stage;
		
		loadMainStage();
		showStage();
	}
	
	public void loadMainStage() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("MainView.fxml"));
			
			rootLayout = (BorderPane) loader.load();
			
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			
		}
		catch(IOException e) {
			//todo add loggin
			e.printStackTrace();
		}
	}
	
	public void showStage() {
		primaryStage.show();
	}
	public static void main(String[] args) {
		launch(args);
	}
}
