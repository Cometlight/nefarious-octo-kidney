package at.fhv.itb5c.view.team.addplayer;

import at.fhv.itb5c.commons.dto.rmi.ITeamRMI;
import at.fhv.itb5c.commons.dto.rmi.IUserRMI;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TeamAddPlayerModel {
	private ITeamRMI _team;
	private StringProperty _searchInput;
	private ObservableList<IUserRMI> _searchResults;
	private ObjectProperty<IUserRMI> _player;
	
	/**
	 * @param team the team to which the player will be added
	 */
	public TeamAddPlayerModel(ITeamRMI team) {
		_team = team;
		_searchInput = new SimpleStringProperty();
		_searchResults = FXCollections.observableArrayList();
		_player = new SimpleObjectProperty<>();
	}
	
	public ITeamRMI getTeam() {
		return _team;
	}

	public StringProperty getSearchInput() {
		return _searchInput;
	}

	public ObservableList<IUserRMI> getSearchResults() {
		return _searchResults;
	}

	public ObjectProperty<IUserRMI> getPlayer() {
		return _player;
	}
}
