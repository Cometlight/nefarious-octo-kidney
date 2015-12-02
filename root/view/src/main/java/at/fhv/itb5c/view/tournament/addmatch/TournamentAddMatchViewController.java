package at.fhv.itb5c.view.tournament.addmatch;

import java.io.IOException;
import java.rmi.RemoteException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collection;
import java.util.stream.Collectors;

import at.fhv.itb5c.app.AppState;
import at.fhv.itb5c.commons.dto.rmi.IDepartmentRMI;
import at.fhv.itb5c.commons.dto.rmi.IMatchRMI;
import at.fhv.itb5c.commons.dto.rmi.ITeamRMI;
import at.fhv.itb5c.commons.dto.rmi.ITournamentRMI;
import at.fhv.itb5c.commons.util.time.TimeUtility;
import at.fhv.itb5c.logging.ILogger;
import at.fhv.itb5c.rmi.client.RMIClient;
import at.fhv.itb5c.view.tournament.TournamentViewFactory;
import at.fhv.itb5c.view.util.interfaces.IPanelClosable;
import at.fhv.itb5c.view.util.interfaces.IPanelCloseHandler;
import at.fhv.itb5c.view.util.listcell.ObjectListCell;
import at.fhv.itb5c.view.util.popup.ErrorPopUp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.util.Callback;
import javafx.util.StringConverter;

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
			// load home team data from database
			Collection<Object> teams = _model.getTournament().getHomeTeamsIds().stream().map(id -> {
				try {
					return RMIClient.getRMIClient().getApplicationFacade().getTeamById(AppState.getInstance().getSessionID(), id);
				} catch (Exception e) {
					log.error(e.getMessage());
					ErrorPopUp.connectionError();
					return null;
				}
			}).collect(Collectors.toList());
			
			// just use Strings for guest teams
			teams.addAll(_model.getTournament().getGuestTeams());
			_model.getTeams().setAll(teams);

			_team1Choice.setCellFactory(new Callback<ListView<Object>, ListCell<Object>>() {
				@Override
				public ListCell<Object> call(ListView<Object> param) {
					return new ObjectListCell();
				}
			});
			_team1Choice.setConverter(getStringConverter());
			_team1Choice.setItems(_model.getTeams());

			_team2Choice.setCellFactory(new Callback<ListView<Object>, ListCell<Object>>() {
				@Override
				public ListCell<Object> call(ListView<Object> param) {
					return new ObjectListCell();
				}
			});
			_team2Choice.setConverter(getStringConverter());
			_team2Choice.setItems(_model.getTeams());

			_startDatePicker.valueProperty().bindBidirectional(_model.getStartDate());

			_startTimeInput.textProperty().bindBidirectional(_model.getStartTime());
		} catch (RemoteException e) {
			log.error(e.getMessage());
			ErrorPopUp.connectionError();
		}
	}

	private StringConverter<Object> getStringConverter() {
		return new StringConverter<Object>() {
			
			@Override
			public String toString(Object object) {
				if(object == null) {
					return null;
				} else if (object instanceof String) {
					return (String) object;
				} else if (object instanceof ITeamRMI) {
					try {
						return ((ITeamRMI)object).getName();
					} catch (RemoteException e) {
						log.error(e.getMessage());
						ErrorPopUp.connectionError();
					}
				}
				return null;
			}
			
			@Override
			public Object fromString(String string) {
				return null;
			}
		};
	}

	@FXML
	void _onSaveButtonClick(ActionEvent event) {
		ITournamentRMI updatedTournament = null;

		if ((updatedTournament = addMatchToTournament()) != null) {
			try {
				IDepartmentRMI department = RMIClient.getRMIClient().getApplicationFacade()
						.getDepartmentById(AppState.getInstance().getSessionID(), updatedTournament.getDepartmentId());
				_panelCloseHandler.closeNext(new TournamentViewFactory(department, updatedTournament));
			} catch (IOException e) {
				ErrorPopUp.criticalSystemError();
				log.error(e.getMessage());
			}
		}
	}

	private ITournamentRMI addMatchToTournament() {
		Object team1 = _team1Choice.getSelectionModel().getSelectedItem();
		Object team2 = _team2Choice.getSelectionModel().getSelectedItem();
		if(team1.equals(team2)) {
			ErrorPopUp.generalError("Distinct Teams needed",
					"Please select two different teams.");
		} else if (_model.getStartDate().getValue() != null && _model.getStartTime().getValue() != null
				&& team1 != null && team2 != null) {
			String sessionId = AppState.getInstance().getSessionID();
			try {
				LocalTime startTime = TimeUtility.timeStringToLocalTime(_model.getStartTime().getValue());
				if (startTime != null) {
					LocalDateTime startDateTime = LocalDateTime.of(_model.getStartDate().getValue(), startTime);
					if(startDateTime.isAfter(LocalDateTime.now()) || startDateTime.isEqual(LocalDateTime.now())) {
						IMatchRMI match = RMIClient.getRMIClient().getApplicationFacade().createMatch(sessionId);
						if (match != null) {
							match.setStartDate(startDateTime);
							if (team1 instanceof ITeamRMI) {
								team1 = ((ITeamRMI)team1).getId();
							}
							if (team2 instanceof ITeamRMI) {
								team2 = ((ITeamRMI)team2).getId();
							}
							match.setTeamOne(team1);
							match.setTeamTwo(team2);
							ITournamentRMI updatedTournament = RMIClient.getRMIClient().getApplicationFacade()
									.addMatchToTournament(sessionId, _model.getTournament(), match);
							return updatedTournament;
						}
					} else {
						ErrorPopUp.generalError("Invalid Date",
								"Please select a date in the future.");
					}
				}
			} catch (RemoteException e) {
				log.error(e.getMessage());
				ErrorPopUp.connectionError();
			}
		} else {
			ErrorPopUp.generalError("Mandatory Fields",
					"Please set the mandatory field: Team 1, Team 2, Start Date and Time");
		}
		return null;
	}

	@FXML
	void _onCancelButtonClick(ActionEvent event) {
		try {
			IDepartmentRMI department = RMIClient.getRMIClient().getApplicationFacade()
					.getDepartmentById(AppState.getInstance().getSessionID(), _model.getTournament().getDepartmentId());
			_panelCloseHandler.closeNext(new TournamentViewFactory(department, _model.getTournament()));
		} catch (IOException e) {
			ErrorPopUp.criticalSystemError();
			log.error(e.getMessage());
		}
	}

	@Override
	public void setPanelCloseHandler(IPanelCloseHandler panelCloseHandler) {
		_panelCloseHandler = panelCloseHandler;
	}
}
