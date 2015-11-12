package at.fhv.itb5c.view.team.add;

import at.fhv.itb5c.commons.dto.rmi.IDepartmentRMI;
import at.fhv.itb5c.commons.dto.rmi.ILeagueRMI;
import at.fhv.itb5c.commons.dto.rmi.IUserRMI;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TeamAddModel {
	
	private IDepartmentRMI _department;
	private ObjectProperty<ILeagueRMI> _league;
	private StringProperty _teamName;
	private ObservableList<IUserRMI> _searchResult;
	private ObjectProperty<IUserRMI> _coach;
	private StringProperty _searchInput;
	
	public TeamAddModel(IDepartmentRMI department) {
		_department = department;
		_league = new SimpleObjectProperty<>();
		_teamName = new SimpleStringProperty();
		_searchResult = FXCollections.observableArrayList();
		_searchInput = new SimpleStringProperty();
		_coach = new SimpleObjectProperty<>();
	}
	
	public IDepartmentRMI getDepartment() {
		return _department;
	}
	
	public ObjectProperty<ILeagueRMI> getLeague() {
		return _league;
	}
	
	public StringProperty getTeamName() {
		return _teamName;
	}
	
	public ObservableList<IUserRMI> getSearchResults() {
		return _searchResult;
	}
	
	public ObjectProperty<IUserRMI> getCoach() {
		return _coach;
	}
	
	public StringProperty getSearchInput() {
		return _searchInput;
	}
}
