package at.fhv.itb5c.view;

import java.rmi.RemoteException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import at.fhv.itb5c.commons.dto.rmi.ITournamentRMI;
import at.fhv.itb5c.jms.QueueManager;
import at.fhv.itb5c.logging.ILogger;
import at.fhv.itb5c.rmi.client.RMIClient;
import at.fhv.itb5c.view.tournament.invitation.TournamentInvitationPopup;
import at.fhv.itb5c.view.util.popup.ErrorPopUp;
import javafx.application.Platform;

public class JMSHandler implements ILogger {
	private static JMSHandler _instance = new JMSHandler();
	private static ExecutorService _executorService = Executors.newFixedThreadPool(1);
	private static boolean _isListening = false;
	
	private JMSHandler() { }
	
	public static JMSHandler getInstance() {
		return _instance;
	}
	
	public static void listen(Long userID) {
		// TODO: Listen for the specified userID
		if(!_isListening) {
			QueueManager queueManager = new QueueManager();
			_executorService.submit(() -> {
				while(!Thread.interrupted()) {
					handleIncomingMessage(queueManager.consume());	// TODO: Add timeout to consume
				}
			});
		}
	}
	
	public static void shutdown() {
		if(!_executorService.isShutdown()) {
			_executorService.shutdownNow();
		}
	}
	
	private static void handleIncomingMessage(String msg) {
		log.info("JMS Message received: " + msg);
		// TODO
		// parseMessage(msg);
		ITournamentRMI randomTournament = null;
		try {
			Long rndDeptId = RMIClient.getRMIClient().getApplicationFacade().getAllDepartments(AppState.getInstance().getSessionID()).iterator().next().getId();
			randomTournament = RMIClient.getRMIClient().getApplicationFacade().findTournaments(AppState.getInstance().getSessionID(), null, rndDeptId).iterator().next();
		} catch (RemoteException e) {
			log.error(e.getMessage());
			ErrorPopUp.connectionError();
		}
		if(randomTournament != null) {
			final ITournamentRMI tournament = randomTournament;	// TODO: beautify
			Platform.runLater(() -> TournamentInvitationPopup.display(tournament));
		}
	}
}
