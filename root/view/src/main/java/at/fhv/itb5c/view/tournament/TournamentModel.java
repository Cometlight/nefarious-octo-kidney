package at.fhv.itb5c.view.tournament;

import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import at.fhv.itb5c.commons.dto.rmi.IMatchRMI;
import at.fhv.itb5c.commons.dto.rmi.ITeamRMI;
import at.fhv.itb5c.commons.dto.rmi.ITournamentRMI;
import at.fhv.itb5c.logging.ILogger;
import at.fhv.itb5c.rmi.client.RMIClient;
import at.fhv.itb5c.view.AppState;
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
	private Property<ObservableList<ITeamRMI>> _homeTeams;
	private Property<ObservableList<Object>> _teams;
	private Property<ObservableList<IMatchRMI>> _matches;
	private Long _departmentId;

	private ITournamentRMI _rmiTournament;
	
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

	public void setITournamentRMI(ITournamentRMI tournament) throws RemoteException {
		_rmiTournament = tournament;
		_tournamentName.setValue(tournament.getName());
		_date.setValue(tournament.getDate());
		_fee.setValue(tournament.getFee());
		_guestTeams.setValue(FXCollections.observableList(new ArrayList<>(tournament.getGuestTeams())));
		ArrayList<ITeamRMI> homeTeams = new ArrayList<>();
		for (Long teamId : tournament.getHomeTeamsIds()) {
			homeTeams.add(RMIClient.getRMIClient().getApplicationFacade()
					.getTeamById(AppState.getInstance().getSessionID(), teamId));
		}
		_homeTeams.setValue(FXCollections.observableList(homeTeams));
		_teams.setValue(FXCollections.observableList(Stream
				.concat(_guestTeams.getValue().stream(), _homeTeams.getValue().stream()).collect(Collectors.toList())));
		_departmentId = tournament.getDepartmentId();
	}

	public Property<ObservableList<String>> getGuestTeams() {
		return _guestTeams;
	}

	public Property<ObservableList<ITeamRMI>> getHomeTeams() {
		return _homeTeams;
	}

	public ITournamentRMI getITournamentRMI() throws RemoteException {
		_rmiTournament.setName(_tournamentName.getValue());
		_rmiTournament.setDate(_date.getValue());
		_rmiTournament.setFee(_fee.doubleValue());
		_rmiTournament.setGuestTeams(_guestTeams.getValue().stream().collect(Collectors.toSet()));
		Set<Long> homeTeamsIds = new HashSet<>();
		for (ITeamRMI homeTeam : _homeTeams.getValue()) {
			homeTeamsIds.add(homeTeam.getId());
		}
		_rmiTournament.setHomeTeamsIds(homeTeamsIds);
		_rmiTournament.setDepartmentId(_departmentId);
		return _rmiTournament;
	}

	public Property<ObservableList<Object>> getTeams() {
		return _teams;
	}

	public Property<ObservableList<IMatchRMI>> getMatches() {
		return _matches;
	}
}
