package at.fhv.itb5c.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class UserModel {
	
	private StringProperty _firstName;
	/*private final StringProperty _lastName;
	private final StringProperty _adress;
	private final IntegerProperty _telephonenumber;
	private final StringProperty _eMail;
	private final StringProperty _gender;
	private final ObjectProperty<LocalDate>*/

	public UserModel() {
		_firstName = new SimpleStringProperty();
	}
	
	public void setFirstName(String firstName) {
		//set into actual class
		_firstName.set(firstName);
	}
	
	public StringProperty getFirstName() {
		return _firstName;
	}
}
