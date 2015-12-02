package at.fhv.itb5c.view.team.invite;

import javafx.fxml.FXML;
import java.rmi.RemoteException;
import org.controlsfx.control.CheckListView;

import at.fhv.itb5c.app.AppState;
import at.fhv.itb5c.commons.dto.rmi.ITeamRMI;
import at.fhv.itb5c.commons.dto.rmi.ITournamentRMI;
import at.fhv.itb5c.commons.dto.rmi.IUserRMI;
import at.fhv.itb5c.logging.ILogger;
import at.fhv.itb5c.rmi.client.RMIClient;
import at.fhv.itb5c.view.util.popup.ErrorPopUp;
import at.fhv.itb5c.view.util.stringconverter.IUserRMIStringConverter;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

public class InvitePlayersToTournamentController implements ILogger {
	@FXML
	private VBox _root;
	@FXML
	private Label _tournamentLabel;
	@FXML
	private Label _teamLabel;
	@FXML
	private CheckListView<IUserRMI> _teamMemberCheckListView;

	private InvitePlayerToTournamentModel _model;

	public InvitePlayersToTournamentController(ITournamentRMI tournament, ITeamRMI team) {
		_model = new InvitePlayerToTournamentModel(team, tournament);
	}

	@FXML
	public void initialize() {
		try {
			_tournamentLabel.setText(_model.getTournament().getName());
			_teamLabel.setText(_model.getTeam().getName());
			_teamMemberCheckListView.getItems().addAll(_model.getPlayers());
			_teamMemberCheckListView.setCellFactory(new Callback<ListView<IUserRMI>, ListCell<IUserRMI>>() {
				@Override
				public ListCell<IUserRMI> call(ListView<IUserRMI> listView) {
					return new CheckBoxListCell<IUserRMI>(item -> _teamMemberCheckListView.getItemBooleanProperty(item), new IUserRMIStringConverter());
				}
			});
			

		} catch (RemoteException e) {
			log.error(e.getMessage());
			ErrorPopUp.connectionError();
		}
	}
	
	// Event Listener on Button.onAction
	@FXML
	public void _acceptButtonOnActionEventHandler(ActionEvent event) {
		ObservableList<IUserRMI> players = _teamMemberCheckListView.getCheckModel().getCheckedItems();
		for (IUserRMI player : players) {
			try {
				RMIClient.getRMIClient().getApplicationFacade().invitePlayer(AppState.getInstance().getSessionID(),
						player, _model.getTeam(), _model.getTournament());
			} catch (RemoteException e) {
				log.error(e.getMessage());
				ErrorPopUp.connectionError();
			}
		}
		
		close();
	}

	@FXML
	public void _cancelButtonOnActionEventHandler(ActionEvent event) {
		close();
	}

	private void close() {
		Stage stage = (Stage)_root.getScene().getWindow();
		stage.close();
	}
}
