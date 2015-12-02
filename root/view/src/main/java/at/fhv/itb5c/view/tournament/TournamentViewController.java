package at.fhv.itb5c.view.tournament;

import java.io.IOException;
import java.rmi.RemoteException;

import at.fhv.itb5c.app.AppState;
import at.fhv.itb5c.commons.dto.rmi.IDepartmentRMI;
import at.fhv.itb5c.commons.dto.rmi.IMatchRMI;
import at.fhv.itb5c.commons.dto.rmi.ITournamentRMI;
import at.fhv.itb5c.commons.enums.UserRole;
import at.fhv.itb5c.logging.ILogger;
import at.fhv.itb5c.rmi.client.ApplicationFacadeRMIStub;
import at.fhv.itb5c.rmi.client.RMIClient;
import at.fhv.itb5c.view.tournament.addmatch.TournamentAddMatchViewFactory;
import at.fhv.itb5c.view.tournament.addmatchresult.MatchAddResultFactory;
import at.fhv.itb5c.view.tournament.addteams.TournamentAddTeamsFactory;
import at.fhv.itb5c.view.util.interfaces.IPanelClosable;
import at.fhv.itb5c.view.util.interfaces.IPanelCloseHandler;
import at.fhv.itb5c.view.util.listcell.MatchListCell;
import at.fhv.itb5c.view.util.listcell.ObjectListCell;
import at.fhv.itb5c.view.util.popup.ErrorPopUp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

public class TournamentViewController implements IPanelClosable, ILogger {
	@FXML
	private Label _tournamentLabel;
	@FXML
	private ListView<Object> _teamsList;
	@FXML
	private Button _addTeamsButton;
	@FXML
	private ListView<IMatchRMI> _matchesList;
	@FXML
	private Button _addMatchesButton;

	private IDepartmentRMI _department;

	private TournamentModel _tournamentModel;

	public TournamentViewController(IDepartmentRMI department, ITournamentRMI tournament) {
		_department = department;
		_tournamentModel = new TournamentModel();
		try {
			_tournamentModel.setITournamentRMI(tournament);
		} catch (RemoteException e) {
			log.error(e.getMessage());
			ErrorPopUp.connectionError();
		}
	}

	@FXML
	public void initialize() {
		_tournamentLabel.textProperty().bindBidirectional(_tournamentModel.getTournamentName());

		_teamsList.itemsProperty().bindBidirectional(_tournamentModel.getTeams());
		_teamsList.setCellFactory(new Callback<ListView<Object>, ListCell<Object>>() {
			@Override
			public ListCell<Object> call(ListView<Object> param) {
				return new ObjectListCell();
			}
		});

		_matchesList.itemsProperty().bindBidirectional(_tournamentModel.getMatches());
		_matchesList.setCellFactory(new Callback<ListView<IMatchRMI>, ListCell<IMatchRMI>>() {
			@Override
			public ListCell<IMatchRMI> call(ListView<IMatchRMI> param) {
				return new MatchListCell(AppState.getInstance().getSessionID());
			}
		});
		
		// deactivate addMatchButton and addTeamsButton if user is not ADMIN or Head of Department
		String sessionId = AppState.getInstance().getSessionID();
    	ApplicationFacadeRMIStub afRMI = RMIClient.getRMIClient().getApplicationFacade();
    	try {
			if(!afRMI.hasRole(sessionId, UserRole.Admin) && !afRMI.isDepartmentHead(sessionId, _department)) {
				_addTeamsButton.setDisable(true);
				_addMatchesButton.setDisable(true);
			}
		} catch (RemoteException e) {
			log.error(e.getMessage());
			ErrorPopUp.connectionError();
		}
	}

	// Event Listener on Button[#_addTeamsButton].onAction
	@FXML
	public void addTeamsButtonAction(ActionEvent event) {
		try {
			_panelCloseHandler
					.closeNext(new TournamentAddTeamsFactory(_department, _tournamentModel.getITournamentRMI()));
		} catch (IOException e) {
			log.error(e.getMessage());
			ErrorPopUp.criticalSystemError();
		}
	}

	// Event Listener on Button[#_addMatchesButton].onAction
	@FXML
	public void addMatchesButtonAction(ActionEvent event) {
		try {
			_panelCloseHandler.closeNext(new TournamentAddMatchViewFactory(_tournamentModel.getITournamentRMI()));
		} catch (IOException e) {
			log.error(e.getMessage());
			ErrorPopUp.criticalSystemError();
		}
	}

	// Event Listener on Button[#_addMatchesButton].onAction
	@FXML
	public void matchOnclick(MouseEvent event) {
		// only allowed to edit match if user is ADMIN or HeadOfDepartmentCoach
		String sessionId = AppState.getInstance().getSessionID();
    	ApplicationFacadeRMIStub afRMI = RMIClient.getRMIClient().getApplicationFacade();
    	try {
			if(!afRMI.hasRole(sessionId, UserRole.Admin) && !afRMI.isDepartmentHead(sessionId, _department)) {
				return;
			}
		} catch (RemoteException e) {
			log.error(e.getMessage());
			ErrorPopUp.connectionError();
		}
    	
		IMatchRMI match = _matchesList.getSelectionModel().getSelectedItem();
		if(match != null){
			try {
				_panelCloseHandler.closeNext(new MatchAddResultFactory(match, _department, _tournamentModel.getITournamentRMI()));
			} catch (IOException e) {
				log.error(e.getMessage());
				ErrorPopUp.criticalSystemError();
			}
		}
	}

	private IPanelCloseHandler _panelCloseHandler;

	@Override
	public void setPanelCloseHandler(IPanelCloseHandler panelCloseHandler) {
		_panelCloseHandler = panelCloseHandler;
	}
}
