package at.fhv.itb5c.view.view.tournament.addmatch;

import java.time.LocalDate;

import at.fhv.itb5c.commons.dto.TournamentDTO;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TournamentAddMatchViewModel {
	private ObjectProperty<LocalDate> _startDate;
	private SimpleStringProperty _startTime;
	private ObservableList<Object> _teams;
	
	private TournamentDTO _tournament;
	
	public TournamentAddMatchViewModel(TournamentDTO tournament) {
		_tournament = tournament;
		_startDate = new SimpleObjectProperty<>();
		_startTime = new SimpleStringProperty();
		_teams = FXCollections.observableArrayList();
	}

	public ObjectProperty<LocalDate> getStartDate() {
		return _startDate;
	}

	public TournamentDTO getTournament() {
		return _tournament;
	}

	public ObservableList<Object> getTeams() {
		return _teams;
	}

	public SimpleStringProperty getStartTime() {
		return _startTime;
	}
}
