package at.fhv.itb5c.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/*
 * TODO: 
 * 	set the values of a User if its set in the constructor
 * 	use setters to set values in the User
 */

public class UserModel {
	
	private StringProperty _firstName;
	private StringProperty _lastName;
	private StringProperty _adress;
	private StringProperty _telephonenumber;
	private StringProperty _eMail;
	/*private final StringProperty _gender;
	private final ObjectProperty<LocalDate>*/

	public UserModel() {
		_firstName = new SimpleStringProperty();
		_lastName = new SimpleStringProperty();
		_adress = new SimpleStringProperty();
		_telephonenumber = new SimpleStringProperty();
		_eMail = new SimpleStringProperty();
	}
	
	public void setFirstName(StringProperty firstName) {
		//set into actual class
		_firstName = firstName;
	}
	
	public StringProperty getFirstName() {
		return _firstName;
	}
	
	public StringProperty getLastName() {
		return _lastName;
	}

	public void setLastName(StringProperty lastName) {
		_lastName = lastName;
	}

	public StringProperty getAdress() {
		return _adress;
	}

	public void setAdress(StringProperty adress) {
		_adress = adress;
	}

	public StringProperty getTelephonenumber() {
		return _telephonenumber;
	}

	public void setTelephonenumber(StringProperty telephonenumber) {
		_telephonenumber = telephonenumber;
	}

	public StringProperty getEMail() {
		return _eMail;
	}

	public void setEMail(StringProperty eMail) {
		_eMail = eMail;
	}

	

}
