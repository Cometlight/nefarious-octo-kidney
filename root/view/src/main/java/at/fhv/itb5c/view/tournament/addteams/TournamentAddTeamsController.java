package at.fhv.itb5c.view.tournament.addteams;

import java.io.IOException;
import java.rmi.RemoteException;

import at.fhv.itb5c.app.AppState;
import at.fhv.itb5c.commons.dto.rmi.IDepartmentRMI;
import at.fhv.itb5c.commons.dto.rmi.ITeamRMI;
import at.fhv.itb5c.commons.dto.rmi.ITournamentRMI;
import at.fhv.itb5c.logging.ILogger;
import at.fhv.itb5c.rmi.client.RMIClient;
import at.fhv.itb5c.view.tournament.TournamentModel;
import at.fhv.itb5c.view.tournament.TournamentViewFactory;
import at.fhv.itb5c.view.util.interfaces.IPanelClosable;
import at.fhv.itb5c.view.util.interfaces.IPanelCloseHandler;
import at.fhv.itb5c.view.util.listcell.TeamListCell;
import at.fhv.itb5c.view.util.popup.ErrorPopUp;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.util.Callback;

public class TournamentAddTeamsController implements IPanelClosable, ILogger {
	@FXML
	private ComboBox<ITeamRMI> _homeTeamComboBox;
	@FXML
	private Button _homeTeamAddButton;
	@FXML
	private ListView<ITeamRMI> _homeTeamsList;
	@FXML
	private Button _homeTeamRemoveButton;
	@FXML
	private TextField _guestTeamTextArea;
	@FXML
	private Button _guestTeamAddButton;
	@FXML
	private ListView<String> _guestTeamsList;
	@FXML
	private Button _guestTeamRemoveButton;
	@FXML
	private Button _saveButton;
	@FXML
	private Button _cancelButton;

	private IDepartmentRMI _department;
	private ITournamentRMI _tournament;
	private TournamentModel _tournamentModel;

	public TournamentAddTeamsController(IDepartmentRMI department, ITournamentRMI tournament) {
		_department = department;
		_tournament = tournament;
		_tournamentModel = new TournamentModel();
		try {
			_tournamentModel.setITournamentRMI(tournament);
		} catch (RemoteException e) {
			log.error(e.getMessage());
			ErrorPopUp.connectionError();
		}
	}
	
	@FXML
	public void initialize() throws RemoteException {
		// initialize ComboBox
		_homeTeamComboBox.setCellFactory(new Callback<ListView<ITeamRMI>, ListCell<ITeamRMI>>() {
			@Override
			public ListCell<ITeamRMI> call(ListView<ITeamRMI> param) {
				return new TeamListCell();
			}
		});
		_homeTeamComboBox.setButtonCell(new TeamListCell());
		_homeTeamComboBox.getItems().addAll(RMIClient.getRMIClient().getApplicationFacade().findTeams(AppState.getInstance().getSessionID(), null, null, _department.getId(), null, null));
		// bind properties
		_homeTeamsList.setCellFactory(new Callback<ListView<ITeamRMI>, ListCell<ITeamRMI>>() {
			@Override
			public ListCell<ITeamRMI> call(ListView<ITeamRMI> param) {
				return new TeamListCell();
			}
		});
		_homeTeamsList.itemsProperty().bindBidirectional(_tournamentModel.getHomeTeams());
		_guestTeamsList.itemsProperty().bindBidirectional(_tournamentModel.getGuestTeams());
	}

	// Event Listener on Button[#_homeTeamAddButton].onAction
	@FXML
	public void addHomeTeamAction(ActionEvent event) {
		if (_homeTeamComboBox.getValue() != null && !_homeTeamsList.getItems().contains(_homeTeamComboBox.getValue())) {
			ObservableList<ITeamRMI> homeTeamsItems = _homeTeamsList.getItems();
			homeTeamsItems.add(_homeTeamComboBox.getValue());
			_homeTeamsList.setItems(homeTeamsItems);
		}
	}

	// Event Listener on Button[#_homeTeamRemoveButton].onAction
	@FXML
	public void removeHomeTeamAction(ActionEvent event) {
		if (_homeTeamsList.getSelectionModel().getSelectedItem() != null) {
			ObservableList<ITeamRMI> homeTeamsItems = _homeTeamsList.getItems();
			homeTeamsItems.remove(_homeTeamsList.getSelectionModel().getSelectedItem());
			_homeTeamsList.setItems(homeTeamsItems);
		}
	}

	// Event Listener on Button[#_guestTeamAddButton].onAction
	@FXML
	public void addGuestTeamAction(ActionEvent event) {
		if (_guestTeamTextArea.getText() != null && !_guestTeamTextArea.getText().isEmpty() && !_guestTeamsList.getItems().contains(_guestTeamTextArea.getText())) {
			ObservableList<String> guestTeamsItems = _guestTeamsList.getItems();
			guestTeamsItems.add(_guestTeamTextArea.getText());
			_guestTeamsList.setItems(guestTeamsItems);
			_guestTeamTextArea.setText(null);
		}
	}

	// Event Listener on Button[#_guestTeamRemoveButton].onAction
	@FXML
	public void removeGuestTeamAction(ActionEvent event) {
		if (_guestTeamsList.getSelectionModel().getSelectedItem() != null) {
			ObservableList<String> guestTeamsItems = _guestTeamsList.getItems();
			guestTeamsItems.remove(_guestTeamsList.getSelectionModel().getSelectedItem());
			_guestTeamsList.setItems(guestTeamsItems);
		}
	}

	// Event Listener on Button[#_saveButton].onAction
	@FXML
	public void saveButtonAction(ActionEvent event) {
		try {
			ITournamentRMI tournamentUpdated = RMIClient.getRMIClient().getApplicationFacade().saveTournament(AppState.getInstance().getSessionID(),
					_tournamentModel.getITournamentRMI(), _department);
			_panelCloseHandler.closeNext(new TournamentViewFactory(_department, tournamentUpdated));
		} catch (IOException e) {
			log.error(e.getMessage());
			ErrorPopUp.criticalSystemError();
		}
	}

	private IPanelCloseHandler _panelCloseHandler;

	@Override
	public void setPanelCloseHandler(IPanelCloseHandler panelCloseHandler) {
		_panelCloseHandler = panelCloseHandler;
	}
	
	// Event Listener on Button[#_cancelButton].onAction
	@FXML
	public void cancelButtonAction(ActionEvent event) {
		try {
			_panelCloseHandler.closeNext(new TournamentViewFactory(_department, _tournament));
		} catch (IOException e) {
			log.error(e.getMessage());
			ErrorPopUp.criticalSystemError();
		}
	}
}
