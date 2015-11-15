package at.fhv.itb5c.view.tournament.addmatch;

import java.io.IOException;
import java.rmi.RemoteException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collection;

import at.fhv.itb5c.commons.dto.rmi.IDepartmentRMI;
import at.fhv.itb5c.commons.dto.rmi.IMatchRMI;
import at.fhv.itb5c.commons.dto.rmi.ITeamRMI;
import at.fhv.itb5c.commons.dto.rmi.ITournamentRMI;
import at.fhv.itb5c.commons.util.time.TimeUtility;
import at.fhv.itb5c.logging.ILogger;
import at.fhv.itb5c.rmi.client.RMIClient;
import at.fhv.itb5c.view.AppState;
import at.fhv.itb5c.view.team.view.TeamViewFactory;
import at.fhv.itb5c.view.util.interfaces.IPanelClosable;
import at.fhv.itb5c.view.util.interfaces.IPanelCloseHandler;
import at.fhv.itb5c.view.util.popup.ErrorPopUp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.util.Callback;

public class TournamentAddMatchViewController implements IPanelClosable, ILogger {
	@FXML
	private DatePicker _startDatePicker;
	@FXML
	private ComboBox<Object> _team1Choice; // Object: either Long or String
	@FXML
	private ComboBox<Object> _team2Choice; // Object: either Long or String
	@FXML
	private TextField _startTimeInput;
	@FXML
	private Button _saveButton;
	@FXML
	private Button _cancelButton;

	private TournamentAddMatchViewModel _model;
	private IPanelCloseHandler _panelCloseHandler;

	public TournamentAddMatchViewController(ITournamentRMI tournament) {
		_model = new TournamentAddMatchViewModel(tournament);
	}

	@FXML
	public void initialize() {
		try {
			Long departmentId = _model.getTournament().getDepartmentId();
			Collection<ITeamRMI> teams = RMIClient.getRMIClient().getApplicationFacade()
					.findTeams(AppState.getInstance().getSessionID(), null, null, departmentId, null);
			_model.getTeams().setAll(teams);

			_team1Choice.setCellFactory(getTeamComboBoxCellFactoryCallback());
			_team1Choice.setItems(_model.getTeams());

			_team2Choice.setCellFactory(getTeamComboBoxCellFactoryCallback());
			_team2Choice.setItems(_model.getTeams());

			_startDatePicker.valueProperty().bindBidirectional(_model.getStartDate());

			_startTimeInput.textProperty().bindBidirectional(_model.getStartTime());
		} catch (RemoteException e) {
			log.error(e.getMessage());
			ErrorPopUp.connectionError();
		}
	}

	private Callback<ListView<Object>, ListCell<Object>> getTeamComboBoxCellFactoryCallback() {
		return new Callback<ListView<Object>, ListCell<Object>>() {

			@Override
			public ListCell<Object> call(ListView<Object> param) {
				return new ListCell<Object>() {

					private final Button btn;

					{
						setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
						btn = new Button();
					}

					@Override
					protected void updateItem(Object item, boolean empty) {
						super.updateItem(item, empty);

						if (item == null || empty) {
							setGraphic(null);
						} else {
							String text = "-";
							if (item instanceof Long) {
								// item == teamId
								try {
									ITeamRMI team = RMIClient.getRMIClient().getApplicationFacade()
											.getTeamById(AppState.getInstance().getSessionID(), (Long) item);
									text = team.getName();
								} catch (RemoteException e) {
									log.error(e.getMessage());
									ErrorPopUp.connectionError();
								}
							} else if (item instanceof String) {
								// item == team name
								text = (String) item;
							} else {
								log.error("team object is neither of type Long or String");
								ErrorPopUp.generalError("Failed to load teams",
										"Failed to load teams. Please contact the system administrator. No changes have been made.");
								// _panelCloseHandler.closeNext(new
								// TournamentViewFactory()); TODO
							}
							btn.setText(text);
							setGraphic(btn);
						}
					}
				};
			}

		};
	}

	@FXML
	void _onSaveButtonClick(ActionEvent event) {
		ITournamentRMI updatedTournament = null;

		if ((updatedTournament = addMatchToTournament()) != null) {

		} else {
			ErrorPopUp.generalError("Match not added",
					"Failed to add match to tournament. Please try again or contact the system administrator.");
		}
	}

	private ITournamentRMI addMatchToTournament() {
		if (_model.getStartDate().getValue() != null && _model.getStartTime().getValue() != null
				&& _model.getTeam1().getValue() != null && _model.getTeam2().getValue() != null) {
			String sessionId = AppState.getInstance().getSessionID();
			try {
				LocalTime startTime = TimeUtility.timeStringToLocalTime(_model.getStartTime().getValue());
				if (startTime != null) {
					LocalDateTime startDateTime = LocalDateTime.of(_model.getStartDate().getValue(), startTime);
					
					IMatchRMI match = RMIClient.getRMIClient().getApplicationFacade()
							.createMatch(sessionId);
					if (match != null) {
						match.setStartDate(startDateTime); 
						match.setTeamOne(teamOne);
						ITournamentRMI updatedTournament = RMIClient.getRMIClient().getApplicationFacade().addMatchToTournament(sessionId, _model.getTournament(), match);
						return updatedTournament;
					}
				}
			} catch (RemoteException e) {
				log.error(e.getMessage());
				ErrorPopUp.connectionError();
			}
		}
		return null;
	}

	@FXML
	void _onCancelButtonClick(ActionEvent event) {
		// try {
		// _panelCloseHandler.closeNext(new TournamentViewFactory()); TODO
		// } catch (IOException e) {
		// ErrorPopUp.criticalSystemError();
		// log.error(e.getMessage());
		// }
	}

	@Override
	public void setPanelCloseHandler(IPanelCloseHandler panelCloseHandler) {
		_panelCloseHandler = panelCloseHandler;
	}
}
