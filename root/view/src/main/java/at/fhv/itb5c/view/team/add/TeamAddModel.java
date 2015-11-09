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
	private StringProperty _name;
	private ObjectProperty<IUserRMI> _coach;
	private ObjectProperty<ILeagueRMI> _league;
	private ObservableList<IUserRMI> _searchResult;
	
	private IDepartmentRMI _department;
	
	public TeamAddModel(IDepartmentRMI department) {
		_department = department;
		_name = new SimpleStringProperty();
		_coach = new SimpleObjectProperty<>();
		_league = new SimpleObjectProperty<>();
		_searchResult = FXCollections.observableArrayList();
	}
	
	public IDepartmentRMI getDepartment() {
		return _department;
	}
	
	public StringProperty getName() {
		return _name;
	}
	
	public ObjectProperty<IUserRMI> getCoach() {
		return _coach;
	}
	
	public ObjectProperty<ILeagueRMI> getLeague() {
		return _league;
	}
	
	public ObservableList<IUserRMI> getSearchResult() {
		return _searchResult;
	}
}
