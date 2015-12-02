package at.fhv.itb5c;

import java.io.IOException;
import at.fhv.itb5c.app.AppController;
import at.fhv.itb5c.communication.CommunicationErrorException;
import at.fhv.itb5c.communication.CommunicationFacadeProvider;
import at.fhv.itb5c.communication.CommunicationType;
import at.fhv.itb5c.logging.ILogger;
import at.fhv.itb5c.message.MessageHandler;
import at.fhv.itb5c.view.util.popup.ErrorPopUp;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Program extends Application implements ILogger {
	public AppController _appController;
	
	public Program() {
		_appController = new AppController();
	}

	@Override
	public void start(Stage stage) throws IOException {
		connect();

		_appController.start(stage);
		stage.getIcons().add(new Image(getClass().getResourceAsStream("/view/image/sports_icon.png")));
		stage.setTitle("Sports Management");
	}

	private void connect() {
		CommunicationFacadeProvider.getInstance().configureCommunicationFacade(CommunicationType.RMI);
		try {
			CommunicationFacadeProvider.getInstance().getCurrentFacade().startUp();
		} catch (CommunicationErrorException e) {
			log.error(e.getMessage());
			ErrorPopUp.connectionError();
		}
	}
	
	@Override
	public void stop() throws Exception {
		log.info("Shutdown initiated...");
		
		CommunicationFacadeProvider.getInstance().getCurrentFacade().close();
		log.info("\tRMI Connection closed.");
		
		MessageHandler.shutdown();
		log.info("\tJMS listening stoped.");
		
		super.stop();
		log.info("Shutdown complete.");
	}

	public static void main(String[] args) {
		launch(args);
	}
}
