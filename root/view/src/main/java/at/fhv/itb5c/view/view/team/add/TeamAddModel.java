package at.fhv.itb5c.view.view.team.add;

import at.fhv.itb5c.commons.dto.DepartmentDTO;
import at.fhv.itb5c.commons.dto.LeagueDTO;
import at.fhv.itb5c.commons.dto.UserDTO;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TeamAddModel {
	
	private DepartmentDTO _department;
	private ObjectProperty<LeagueDTO> _league;
	private StringProperty _teamName;
	private ObservableList<UserDTO> _searchResult;
	private ObjectProperty<UserDTO> _coach;
	private StringProperty _searchInput;
	
	public TeamAddModel(DepartmentDTO department) {
		_department = department;
		_league = new SimpleObjectProperty<>();
		_teamName = new SimpleStringProperty();
		_searchResult = FXCollections.observableArrayList();
		_searchInput = new SimpleStringProperty();
		_coach = new SimpleObjectProperty<>();
	}
	
	public DepartmentDTO getDepartment() {
		return _department;
	}
	
	public ObjectProperty<LeagueDTO> getLeague() {
		return _league;
	}
	
	public StringProperty getTeamName() {
		return _teamName;
	}
	
	public ObservableList<UserDTO> getSearchResults() {
		return _searchResult;
	}
	
	public ObjectProperty<UserDTO> getCoach() {
		return _coach;
	}
	
	public StringProperty getSearchInput() {
		return _searchInput;
	}
}
