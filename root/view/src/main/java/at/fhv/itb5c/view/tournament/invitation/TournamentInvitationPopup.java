package at.fhv.itb5c.view.tournament.invitation;

import java.util.Optional;
import at.fhv.itb5c.app.AppState;
import at.fhv.itb5c.application.dto.TeamDTO;
import at.fhv.itb5c.application.dto.TournamentDTO;
import at.fhv.itb5c.commons.enums.TeamInvitationStatus;
import at.fhv.itb5c.communication.CommunicationErrorException;
import at.fhv.itb5c.communication.CommunicationFacadeProvider;
import at.fhv.itb5c.logging.ILogger;
import at.fhv.itb5c.view.util.popup.ErrorPopUp;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

public abstract class TournamentInvitationPopup implements ILogger {
	public static void display(TournamentDTO tournament, TeamDTO team) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Tournament Invitation");
		alert.setHeaderText("You've been invited to a tournament!");
		alert.setContentText("Tournament: " + tournament.getName() + "\n" 
				+ "Date: " + tournament.getDate() + "\n\n"
				+ "Please choose your option:");

		ButtonType buttonTypeAccept = new ButtonType("Accept");
		ButtonType buttonTypeDecline = new ButtonType("Decline");

		alert.getButtonTypes().setAll(buttonTypeAccept, buttonTypeDecline);

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == buttonTypeAccept) {
			try {
				CommunicationFacadeProvider.getInstance().getCurrentFacade().rsvp(AppState.getInstance().getSessionID(), team, TeamInvitationStatus.Accepted);
			} catch (CommunicationErrorException e) {
				log.error(e.getMessage());
				ErrorPopUp.connectionError();
			}
		} else if (result.get() == buttonTypeDecline) {
			try {
				CommunicationFacadeProvider.getInstance().getCurrentFacade().rsvp(AppState.getInstance().getSessionID(), team, TeamInvitationStatus.Declined);
			} catch (CommunicationErrorException e) {
				log.error(e.getMessage());
				ErrorPopUp.connectionError();
			}
		} else {
			log.error("Invalide button type -> " + result.get());
		}
	}
}
