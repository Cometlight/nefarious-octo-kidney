package at.fhv.itb5c.view.tournament;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import at.fhv.itb5c.app.AppState;
import at.fhv.itb5c.application.dto.MatchDTO;
import at.fhv.itb5c.application.dto.TeamDTO;
import at.fhv.itb5c.application.dto.TournamentDTO;
import at.fhv.itb5c.communication.CommunicationErrorException;
import at.fhv.itb5c.communication.CommunicationFacadeProvider;
import at.fhv.itb5c.logging.ILogger;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TournamentModel implements ILogger {
	private StringProperty _tournamentName;
	private ObjectProperty<LocalDate> _date;
	private DoubleProperty _fee;
	private Property<ObservableList<String>> _guestTeams;
	private Property<ObservableList<TeamDTO>> _homeTeams;
	private Property<ObservableList<Object>> _teams;
	private Property<ObservableList<MatchDTO>> _matches;
	private Long _departmentId;

	private TournamentDTO _rmiTournament;
	
	public TournamentModel(){
		_tournamentName = new SimpleStringProperty();
		_date = new SimpleObjectProperty<>();
		_fee = new SimpleDoubleProperty();
		_guestTeams = new SimpleListProperty<>();
		_homeTeams = new SimpleListProperty<>();
		_teams = new SimpleListProperty<>();
		_matches = new SimpleListProperty<>();
	}

	public StringProperty getTournamentName() {
		return _tournamentName;
	}

	public ObjectProperty<LocalDate> getDate() {
		return _date;
	}

	public DoubleProperty getFee() {
		return _fee;
	}

	public void setTournamentDTO(TournamentDTO tournament) throws CommunicationErrorException {
		_rmiTournament = tournament;
		_tournamentName.setValue(tournament.getName());
		_date.setValue(tournament.getDate());
		_fee.setValue(tournament.getFee());
		_guestTeams.setValue(FXCollections.observableList(new ArrayList<>(tournament.getGuestTeams())));
		
		ArrayList<TeamDTO> homeTeams = new ArrayList<>();
		for (Long teamId : tournament.getHomeTeamsIds()) {
			homeTeams.add(CommunicationFacadeProvider.getInstance().getCurrentFacade()
					.getTeamById(AppState.getInstance().getSessionID(), teamId));
		}
		_homeTeams.setValue(FXCollections.observableList(homeTeams));
		
		_teams.setValue(FXCollections.observableList(Stream
				.concat(_guestTeams.getValue().stream(), _homeTeams.getValue().stream()).collect(Collectors.toList())));
		
		_departmentId = tournament.getDepartmentId();
		
		ArrayList<MatchDTO> matches = new ArrayList<>();
		for (Long matchId : tournament.getMatchesIds()) {
			matches.add(CommunicationFacadeProvider.getInstance().getCurrentFacade()
					.getMatchById(AppState.getInstance().getSessionID(), matchId));
		}
		_matches.setValue(FXCollections.observableList(matches));
	}

	public Property<ObservableList<String>> getGuestTeams() {
		return _guestTeams;
	}

	public Property<ObservableList<TeamDTO>> getHomeTeams() {
		return _homeTeams;
	}

	public TournamentDTO getTournamentDTO() throws CommunicationErrorException {
		_rmiTournament.setName(_tournamentName.getValue());
		_rmiTournament.setDate(_date.getValue());
		_rmiTournament.setFee(_fee.doubleValue());
		_rmiTournament.setGuestTeams(_guestTeams.getValue().stream().collect(Collectors.toSet()));
		Set<Long> homeTeamsIds = new HashSet<>();
		for (TeamDTO homeTeam : _homeTeams.getValue()) {
			homeTeamsIds.add(homeTeam.getId());
		}
		_rmiTournament.setHomeTeamsIds(homeTeamsIds);
		_rmiTournament.setDepartmentId(_departmentId);
		return _rmiTournament;
	}

	public Property<ObservableList<Object>> getTeams() {
		return _teams;
	}

	public Property<ObservableList<MatchDTO>> getMatches() {
		return _matches;
	}
}
