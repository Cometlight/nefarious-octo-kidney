package at.fhv.itb5c.view.team.invite;

import javafx.fxml.FXML;
import java.rmi.RemoteException;
import org.controlsfx.control.CheckListView;
import at.fhv.itb5c.app.AppState;
import at.fhv.itb5c.application.dto.TeamDTO;
import at.fhv.itb5c.application.dto.TournamentDTO;
import at.fhv.itb5c.application.dto.UserDTO;
import at.fhv.itb5c.communication.CommunicationErrorException;
import at.fhv.itb5c.communication.CommunicationFacadeProvider;
import at.fhv.itb5c.logging.ILogger;
import at.fhv.itb5c.view.util.popup.ErrorPopUp;
import at.fhv.itb5c.view.util.stringconverter.UserDTOStringConverter;
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
	private CheckListView<UserDTO> _teamMemberCheckListView;

	private InvitePlayerToTournamentModel _model;

	public InvitePlayersToTournamentController(TournamentDTO tournament, TeamDTO team) {
		_model = new InvitePlayerToTournamentModel(team, tournament);
	}

	@FXML
	public void initialize() throws RemoteException {
		_tournamentLabel.setText(_model.getTournament().getName());
		_teamLabel.setText(_model.getTeam().getName());
		_teamMemberCheckListView.getItems().addAll(_model.getPlayers());
		_teamMemberCheckListView.setCellFactory(new Callback<ListView<UserDTO>, ListCell<UserDTO>>() {
			@Override
			public ListCell<UserDTO> call(ListView<UserDTO> listView) {
				return new CheckBoxListCell<UserDTO>(item -> _teamMemberCheckListView.getItemBooleanProperty(item), new UserDTOStringConverter());
			}
		});
	}
	
	// Event Listener on Button.onAction
	@FXML
	public void _acceptButtonOnActionEventHandler(ActionEvent event) {
		ObservableList<UserDTO> players = _teamMemberCheckListView.getCheckModel().getCheckedItems();
		for (UserDTO player : players) {
			try {
				CommunicationFacadeProvider.getInstance().getCurrentFacade().invitePlayer(AppState.getInstance().getSessionID(),
						player, _model.getTeam(), _model.getTournament());
			} catch (CommunicationErrorException e) {
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
