package at.fhv.itb5c.view.view.tournament;

import java.io.IOException;

import at.fhv.itb5c.commons.dto.DepartmentDTO;
import at.fhv.itb5c.commons.dto.MatchDTO;
import at.fhv.itb5c.commons.dto.TournamentDTO;
import at.fhv.itb5c.commons.enums.UserRole;
import at.fhv.itb5c.logging.ILogger;
import at.fhv.itb5c.view.app.AppState;
import at.fhv.itb5c.view.communication.CommunicationErrorException;
import at.fhv.itb5c.view.communication.CommunicationFacadeProvider;
import at.fhv.itb5c.view.view.tournament.addmatch.TournamentAddMatchViewFactory;
import at.fhv.itb5c.view.view.tournament.addmatchresult.MatchAddResultFactory;
import at.fhv.itb5c.view.view.tournament.addteams.TournamentAddTeamsFactory;
import at.fhv.itb5c.view.view.util.interfaces.IPanelClosable;
import at.fhv.itb5c.view.view.util.interfaces.IPanelCloseHandler;
import at.fhv.itb5c.view.view.util.listcell.MatchListCell;
import at.fhv.itb5c.view.view.util.listcell.ObjectListCell;
import at.fhv.itb5c.view.view.util.popup.ErrorPopUp;
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
	private ListView<MatchDTO> _matchesList;
	@FXML
	private Button _addMatchesButton;

	private DepartmentDTO _department;

	private TournamentModel _tournamentModel;

	public TournamentViewController(DepartmentDTO department, TournamentDTO tournament) {
		_department = department;
		_tournamentModel = new TournamentModel();
		try {
			_tournamentModel.setTournamentDTO(tournament);
		} catch (CommunicationErrorException e) {
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
		_matchesList.setCellFactory(new Callback<ListView<MatchDTO>, ListCell<MatchDTO>>() {
			@Override
			public ListCell<MatchDTO> call(ListView<MatchDTO> param) {
				return new MatchListCell(AppState.getInstance().getSessionID());
			}
		});

		// deactivate addMatchButton and addTeamsButton if user is not ADMIN or
		// Head of Department
		String sessionId = AppState.getInstance().getSessionID();
		try {
			if (!CommunicationFacadeProvider.getInstance().getCurrentFacade().hasRole(sessionId, UserRole.Admin)
					&& !CommunicationFacadeProvider.getInstance().getCurrentFacade().isDepartmentHead(sessionId,
							_department)) {
				_addTeamsButton.setDisable(true);
				_addMatchesButton.setDisable(true);
			}
		} catch (CommunicationErrorException e) {
			log.error(e.getMessage());
			ErrorPopUp.connectionError();
		}
	}

	// Event Listener on Button[#_addTeamsButton].onAction
	@FXML
	public void addTeamsButtonAction(ActionEvent event) {
		try {
			_panelCloseHandler
					.closeNext(new TournamentAddTeamsFactory(_department, _tournamentModel.getTournamentDTO()));
		} catch (IOException e) {
			log.error(e.getMessage());
			ErrorPopUp.criticalSystemError();
		} catch (CommunicationErrorException e) {
			log.error(e.getMessage());
			ErrorPopUp.connectionError();
		}
	}

	// Event Listener on Button[#_addMatchesButton].onAction
	@FXML
	public void addMatchesButtonAction(ActionEvent event) {
		try {
			_panelCloseHandler.closeNext(new TournamentAddMatchViewFactory(_tournamentModel.getTournamentDTO()));
		} catch (IOException e) {
			log.error(e.getMessage());
			ErrorPopUp.criticalSystemError();
		} catch (CommunicationErrorException e) {
			log.error(e.getMessage());
			ErrorPopUp.connectionError();
		}
	}

	// Event Listener on Button[#_addMatchesButton].onAction
	@FXML
	public void matchOnclick(MouseEvent event) {
		// only allowed to edit match if user is ADMIN or HeadOfDepartmentCoach
		String sessionId = AppState.getInstance().getSessionID();
		try {
			if (!CommunicationFacadeProvider.getInstance().getCurrentFacade().hasRole(sessionId, UserRole.Admin)
					&& !CommunicationFacadeProvider.getInstance().getCurrentFacade().isDepartmentHead(sessionId,
							_department)) {
				return;
			}
		} catch (CommunicationErrorException e) {
			log.error(e.getMessage());
			ErrorPopUp.connectionError();
		}

		MatchDTO match = _matchesList.getSelectionModel().getSelectedItem();
		if (match != null) {
			try {
				_panelCloseHandler
						.closeNext(new MatchAddResultFactory(match, _department, _tournamentModel.getTournamentDTO()));
			} catch (IOException e) {
				log.error(e.getMessage());
				ErrorPopUp.criticalSystemError();
			} catch (CommunicationErrorException e) {
				log.error(e.getMessage());
				ErrorPopUp.connectionError();
			}
		}
	}

	private IPanelCloseHandler _panelCloseHandler;

	@Override
	public void setPanelCloseHandler(IPanelCloseHandler panelCloseHandler) {
		_panelCloseHandler = panelCloseHandler;
	}
}
