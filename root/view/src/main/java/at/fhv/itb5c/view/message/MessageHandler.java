package at.fhv.itb5c.view.message;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import at.fhv.itb5c.commons.dto.MessageDTO;
import at.fhv.itb5c.commons.dto.TeamDTO;
import at.fhv.itb5c.commons.dto.TournamentDTO;
import at.fhv.itb5c.logging.ILogger;
import at.fhv.itb5c.view.app.AppState;
import at.fhv.itb5c.view.communication.CommunicationErrorException;
import at.fhv.itb5c.view.communication.CommunicationFacadeProvider;
import at.fhv.itb5c.view.view.team.invite.InvitePlayersToTournamentPanelAndViewFactory;
import at.fhv.itb5c.view.view.tournament.invitation.TournamentInvitationPopup;
import at.fhv.itb5c.view.view.util.popup.ErrorPopUp;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MessageHandler implements ILogger {
	private static MessageHandler _instance = new MessageHandler();
	private static ExecutorService _executorService = Executors.newFixedThreadPool(1);
	private static boolean _isListening = false;
	private static long TIME_BETWEEN_POLLS = 10000l; // [ms]

	private MessageHandler() {
	}

	public static MessageHandler getInstance() {
		return _instance;
	}

	public void listen(Long userID) {
		if (!_isListening) {
			_executorService.submit((Runnable) () -> {
				while (!Thread.interrupted()) {
					try {
						handleIncomingMessage(CommunicationFacadeProvider.getInstance().getCurrentFacade()
								.getMessage(AppState.getInstance().getSessionID()));
						Thread.sleep(TIME_BETWEEN_POLLS);
					} catch (InterruptedException e) {
						break;	// --> Thread is going to stop
					} catch (Exception e) {
						log.error(e.getMessage());
					}
				}
			});
		}
	}

	public static void shutdown() {
		if (!_executorService.isShutdown()) {
			_executorService.shutdownNow();
		}
	}

	private static void handleIncomingMessage(MessageDTO msg) {
		if (msg != null) {
			log.info("Message received: " + msg + " " + msg.getKind());
			switch (msg.getKind()) {
				case ("INVITE_PLAYER_TOURNAMENT"): {
					log.info("Parse Message -> INVITE_PLAYER_TOURNAMENT");
					final TournamentDTO tournament;
					final TeamDTO team;
					try {
						tournament = CommunicationFacadeProvider.getInstance().getCurrentFacade()
								.getTournamentById(AppState.getInstance().getSessionID(), (Long) msg.get("tournamentId"));
						
						team = CommunicationFacadeProvider.getInstance().getCurrentFacade()
								.getTeamById(AppState.getInstance().getSessionID(), (Long) msg.get("teamId"));
						
						Platform.runLater(() -> TournamentInvitationPopup.display(tournament, team));
					} catch (CommunicationErrorException e) {
						Platform.runLater(() -> ErrorPopUp.connectionError());
						log.error(e.getMessage());
					}
					
					break;
				}
				case ("NOTIFY_COACH_TOURNAMENT"): {
					Platform.runLater(() ->  {
					TournamentDTO tournament = null;
					TeamDTO team = null;
					
					try {
						tournament = CommunicationFacadeProvider.getInstance().getCurrentFacade()
								.getTournamentById(AppState.getInstance().getSessionID(), (Long) msg.get("tournamentId"));
						team = CommunicationFacadeProvider.getInstance().getCurrentFacade()
								.getTeamById(AppState.getInstance().getSessionID(), (Long) msg.get("teamId"));
					} catch (Exception e) {
						Platform.runLater(() -> ErrorPopUp.connectionError());
						log.error(e.getMessage());
					}
					

					try {
						Pane root = new InvitePlayersToTournamentPanelAndViewFactory(tournament, team).create();

						Stage stage = new Stage();
						stage.setTitle("Choose Players");
						stage.setScene(new Scene(root));
						stage.showAndWait();

					} catch (IOException e) {
						log.error(e.getMessage());
						Platform.runLater(() -> ErrorPopUp.criticalSystemError());	
					}
					});
					

					break;
				}
				default: {
					log.error("Can not parse message -> " + msg.getKind());
					break;
				}
			}
		}
	}
}
