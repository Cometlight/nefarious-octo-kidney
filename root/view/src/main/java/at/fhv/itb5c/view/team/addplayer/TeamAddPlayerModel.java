package at.fhv.itb5c.view.team.addplayer;

import at.fhv.itb5c.commons.dto.TeamDTO;
import at.fhv.itb5c.commons.dto.UserDTO;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TeamAddPlayerModel {
	private TeamDTO _team;
	private StringProperty _searchInput;
	private ObservableList<UserDTO> _searchResults;
	private ObjectProperty<UserDTO> _player;
	
	/**
	 * @param team the team to which the player will be added
	 */
	public TeamAddPlayerModel(TeamDTO team) {
		_team = team;
		_searchInput = new SimpleStringProperty();
		_searchResults = FXCollections.observableArrayList();
		_player = new SimpleObjectProperty<>();
	}
	
	public TeamDTO getTeam() {
		return _team;
	}

	public StringProperty getSearchInput() {
		return _searchInput;
	}

	public ObservableList<UserDTO> getSearchResults() {
		return _searchResults;
	}

	public ObjectProperty<UserDTO> getPlayer() {
		return _player;
	}
}
