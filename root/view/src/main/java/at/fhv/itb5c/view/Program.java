package at.fhv.itb5c.view;

import java.io.IOException;

import at.fhv.itb5c.logging.ILogger;
import at.fhv.itb5c.rmi.client.RMIClient;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Program extends Application implements ILogger {
	public AppController _appController;
	
	public Program() {
		RMIClient.getRMIClient().startUp();
		_appController = new AppController();
	}

	@Override
	public void start(Stage stage) throws IOException {
		_appController.start(stage);
		stage.getIcons().add(new Image(getClass().getResourceAsStream("/view/image/sports_icon.png")));
		stage.setTitle("Sports Management");
	}
	
	@Override
	public void stop() throws Exception {
		log.info("Shutdown initiated...");
		
		RMIClient.getRMIClient().close();
		log.info("\tRMI Connection closed.");
		
		JMSHandler.shutdown();
		log.info("\tJMS listening stoped.");
		
		super.stop();
		log.info("Shutdown complete.");
	}

	public static void main(String[] args) {
		launch(args);
	}
}
