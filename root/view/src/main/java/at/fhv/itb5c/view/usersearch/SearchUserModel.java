package at.fhv.itb5c.view.usersearch;

import java.time.LocalDate;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class SearchUserModel {

	private StringProperty _firstName;
	private StringProperty _lastName;
	private ObjectProperty<LocalDate> _dateOfBirth;
	private BooleanProperty _isPaid;
	
	public SearchUserModel() 
	{
		_firstName = new SimpleStringProperty();
		_lastName = new SimpleStringProperty();
		_dateOfBirth = new SimpleObjectProperty<>();
		_isPaid = new SimpleBooleanProperty();
	}
	
	public StringProperty getFirstName() {
		return _firstName;
	}
	
	public StringProperty getLastName() {
		return _lastName;
	}
	
	public ObjectProperty<LocalDate> getDateOfBirth() {
		return _dateOfBirth;
	}
	
	public BooleanProperty getIsPaid() {
		return _isPaid;
	}
}
