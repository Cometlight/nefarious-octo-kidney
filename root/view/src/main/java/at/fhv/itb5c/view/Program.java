package at.fhv.itb5c.view;

import java.io.IOException;

import at.fhv.itb5c.commons.property.PropertyManager;
import at.fhv.itb5c.logging.ILogger;
import at.fhv.itb5c.view.app.AppController;
import at.fhv.itb5c.view.communication.CommunicationErrorException;
import at.fhv.itb5c.view.communication.CommunicationFacadeProvider;
import at.fhv.itb5c.view.communication.CommunicationType;
import at.fhv.itb5c.view.message.MessageHandler;
import at.fhv.itb5c.view.view.util.popup.ErrorPopUp;
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
		String communicationType = PropertyManager.getInstance().getProperty("at.fhv.itb5c.view.communicationtype");
		if (communicationType.equalsIgnoreCase("rmi")) {
			CommunicationFacadeProvider.getInstance().configureCommunicationFacade(CommunicationType.RMI);
		} else if (communicationType.equalsIgnoreCase("ejb")) {
			CommunicationFacadeProvider.getInstance().configureCommunicationFacade(CommunicationType.EJB);
		} else {
			log.error("Unknown communication type: " + communicationType);
			ErrorPopUp.connectionError();
		}

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
