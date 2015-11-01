package at.fhv.itb5c.view.usersearch;

import at.fhv.itb5c.commons.dto.IUser;
import at.fhv.itb5c.commons.dto.rmi.IUserRMI;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/*
 * TODO:
 * 	add property for selected department
 */

public class SearchUserModel {

	private StringProperty _firstName;
	private StringProperty _lastName;
	private BooleanProperty _isPaid;
	private ObservableList<IUserRMI> _searchResult;
	
	public SearchUserModel() 
	{
		_firstName = new SimpleStringProperty();
		_lastName = new SimpleStringProperty();
		_isPaid = new SimpleBooleanProperty();
		_searchResult = FXCollections.observableArrayList();
	}
	
	public StringProperty getFirstName() {
		return _firstName;
	}
	
	public StringProperty getLastName() {
		return _lastName;
	}
	
	public BooleanProperty getIsPaid() {
		return _isPaid;
	}
	
	public ObservableList<IUserRMI> getSearchResult() {
		return _searchResult;
	}
}
