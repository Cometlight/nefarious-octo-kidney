package at.fhv.itb5c.view.message;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import at.fhv.itb5c.commons.dto.rmi.IMessageRMI;
import at.fhv.itb5c.commons.dto.rmi.ITeamRMI;
import at.fhv.itb5c.commons.dto.rmi.ITournamentRMI;
import at.fhv.itb5c.logging.ILogger;
import at.fhv.itb5c.rmi.client.RMIClient;
import at.fhv.itb5c.view.AppState;
import at.fhv.itb5c.view.team.invite.InvitePlayersToTournamentPanelAndViewFactory;
import at.fhv.itb5c.view.tournament.invitation.TournamentInvitationPopup;
import at.fhv.itb5c.view.util.popup.ErrorPopUp;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MessageHandler implements ILogger {
	private static MessageHandler _instance = new MessageHandler();
	private static ExecutorService _executorService = Executors.newFixedThreadPool(1);
	private static boolean _isListening = false;
	
	private MessageHandler() { }
	
	public static MessageHandler getInstance() {
		return _instance;
	}
	
	public static void listen(Long userID) {
		if(!_isListening) {
			_executorService.submit((Runnable) () -> {
				while(!Thread.interrupted()) {
					try {
						handleIncomingMessage(RMIClient.getRMIClient().getApplicationFacade().getMessage(AppState.getInstance().getSessionID()));
					} catch (Exception e) {
						ErrorPopUp.connectionError();
						log.error(e.getMessage());
					}
				}
			});
		}
	}
	
	public static void shutdown() {
		if(!_executorService.isShutdown()) {
			_executorService.shutdownNow();
		}
	}
	
	private static void handleIncomingMessage(IMessageRMI msg) {
		log.info("Message received: " + msg);
		if(msg != null) {
			try {
				switch(msg.getKind()) {
				case("INVITE_PLAYER_TOURNAMENT"): {
					ITournamentRMI tournament = RMIClient.getRMIClient().getApplicationFacade().getTournamentById(AppState.getInstance().getSessionID(), (Long)msg.get("tournamentId"));
					ITeamRMI team = RMIClient.getRMIClient().getApplicationFacade().getTeamById(AppState.getInstance().getSessionID(), (Long)msg.get("teamId"));
					
					TournamentInvitationPopup.display(tournament, team);
					break;
				}
				case("NOTIFY_COACH_TOURNAMENT"): {
					ITournamentRMI tournament = RMIClient.getRMIClient().getApplicationFacade().getTournamentById(AppState.getInstance().getSessionID(), (Long)msg.get("tournamentId"));
					ITeamRMI team = RMIClient.getRMIClient().getApplicationFacade().getTeamById(AppState.getInstance().getSessionID(), (Long)msg.get("teamId"));
					
					try {
						Pane root = new InvitePlayersToTournamentPanelAndViewFactory(tournament, team).create();
						
						Stage stage = new Stage();
						stage.setTitle("PopUp");
						stage.setScene(new Scene(root));
						stage.showAndWait();
						
					} catch (IOException e) {
						log.error(e.getMessage());
						ErrorPopUp.criticalSystemError();
					}
					
					break;
				}
				default: {
					log.error("Can not parse message -> " + msg.getKind());
					break;
				}
				}
			} catch (RemoteException e) {
				ErrorPopUp.connectionError();
				log.error(e.getMessage());
			}
		}
	}
}
