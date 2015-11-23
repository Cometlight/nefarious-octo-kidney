package at.fhv.itb5c.view.tournament.invitation;

import java.rmi.RemoteException;
import java.util.Optional;

import at.fhv.itb5c.commons.dto.rmi.ITournamentRMI;
import at.fhv.itb5c.logging.ILogger;
import at.fhv.itb5c.view.util.popup.ErrorPopUp;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

public abstract class TournamentInvitationPopup implements ILogger {
	public static void display(ITournamentRMI tournament) {
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

		ButtonType buttonTypeOne = new ButtonType("Accept");
		ButtonType buttonTypeTwo = new ButtonType("Decline");

		alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo);

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == buttonTypeOne) {
			System.out.println("Accepted");	// TODO remove
//			RMIClient.getRMIClient().getApplicationFacade().acceptTournamentInvitation();
		} else if (result.get() == buttonTypeTwo) {
			System.out.println("Declined");// TODO remove
//			RMIClient.getRMIClient().getApplicationFacade().declineTournamentInvitation();
		}
	}
}
