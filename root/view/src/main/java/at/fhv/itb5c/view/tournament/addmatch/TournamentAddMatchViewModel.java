package at.fhv.itb5c.view.tournament.addmatch;

import java.time.LocalDate;

import at.fhv.itb5c.commons.dto.rmi.ITournamentRMI;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TournamentAddMatchViewModel {
	private ObjectProperty<Object> _team1;
	private ObjectProperty<Object> _team2;
	private ObjectProperty<LocalDate> _startDate;
	private SimpleStringProperty _startTime;
	private ObservableList<Object> _teams;
	
	private ITournamentRMI _tournament;
	
	public TournamentAddMatchViewModel(ITournamentRMI tournament) {
		_tournament = tournament;
		_team1 = new SimpleObjectProperty<>();
		_team2 = new SimpleObjectProperty<>();
		_startDate = new SimpleObjectProperty<>();
		_startTime = new SimpleStringProperty();
		_teams = FXCollections.observableArrayList();
	}

	public ObjectProperty<Object> getTeam1() {
		return _team1;
	}

	public ObjectProperty<Object> getTeam2() {
		return _team2;
	}

	public ObjectProperty<LocalDate> getStartDate() {
		return _startDate;
	}

	public ITournamentRMI getTournament() {
		return _tournament;
	}

	public ObservableList<Object> getTeams() {
		return _teams;
	}

	public SimpleStringProperty getStartTime() {
		return _startTime;
	}
}
