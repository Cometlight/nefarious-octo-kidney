package at.fhv.itb5c.view.tournament.invitation;

import java.rmi.RemoteException;
import java.util.Optional;

import at.fhv.itb5c.commons.dto.rmi.ITeamRMI;
import at.fhv.itb5c.commons.dto.rmi.ITournamentRMI;
import at.fhv.itb5c.logging.ILogger;
import at.fhv.itb5c.rmi.client.RMIClient;
import at.fhv.itb5c.view.AppState;
import at.fhv.itb5c.view.util.popup.ErrorPopUp;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

public abstract class TournamentInvitationPopup implements ILogger {
	public static void display(ITournamentRMI tournament, ITeamRMI team) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Tournament Invitation");
		alert.setHeaderText("You've been invited to a tournament!");
		try {
			alert.setContentText("Tournament: " + tournament.getName() + "\n" 
					+ "Date: " + tournament.getDate() + "\n\n"
					+ "Please choose your option:");
		} catch (RemoteException e) {
			log.error(e.getMessage());
			ErrorPopUp.connectionError();
		}

		ButtonType buttonTypeAccept = new ButtonType("Accept");
		ButtonType buttonTypeDecline = new ButtonType("Decline");

		alert.getButtonTypes().setAll(buttonTypeAccept, buttonTypeDecline);

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == buttonTypeAccept) {
			try {
				RMIClient.getRMIClient().getApplicationFacade().rsvp(AppState.getInstance().getSessionID(), team, true);
			} catch (RemoteException e) {
				log.error(e.getMessage());
				ErrorPopUp.connectionError();
			}
		} else if (result.get() == buttonTypeDecline) {
			try {
				RMIClient.getRMIClient().getApplicationFacade().rsvp(AppState.getInstance().getSessionID(), team, false);
			} catch (RemoteException e) {
				log.error(e.getMessage());
				ErrorPopUp.connectionError();
			}
		} else {
			log.error("Invalide button type -> " + result.get());
		}
	}
}
