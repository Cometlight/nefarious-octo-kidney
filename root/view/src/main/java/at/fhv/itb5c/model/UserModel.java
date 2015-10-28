package at.fhv.itb5c.model;

import java.time.LocalDate;

import at.fhv.itb5c.commons.enums.Gender;
import at.fhv.itb5c.commons.enums.TypeOfSport;
import at.fhv.itb5c.commons.enums.UserRole;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;

/*
 * TODO: 
 * 	set the values of a User if its set in the constructor
 * 	use setters to set values in the User
 */

public class UserModel {
	private StringProperty _firstName;
	private StringProperty _lastName;
	private StringProperty _adress;
	private StringProperty _eMail;
	private StringProperty _telephonenumber;
	private Gender _gender;
	private ObjectProperty<LocalDate> _birthDate;
	private ObservableList<TypeOfSport> _typeOfSports;
	private DoubleProperty _membershipFee;
	private UserRole _userRole;

	public UserModel() {
		_firstName = new SimpleStringProperty();
		_lastName = new SimpleStringProperty();
		_adress = new SimpleStringProperty();
		_telephonenumber = new SimpleStringProperty();
		_eMail = new SimpleStringProperty();
		_birthDate = new SimpleObjectProperty<>();
		_membershipFee = new SimpleDoubleProperty();
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

	public void setGender(Gender gender) {
		_gender = gender;
	}
	
	public Gender getGender() {
		return _gender;
	}
	
	public void setBirthDate(ObjectProperty<LocalDate> birthDate) {
		_birthDate = birthDate;
	}
	
	public ObjectProperty<LocalDate> getBirthDate() {
		return _birthDate;
	}
	
	public ObservableList<TypeOfSport> getTypeOfSports() {
		return _typeOfSports;
	}
	
	public void setTypeOfSports(ObservableList<TypeOfSport> typeOfSports) {
		_typeOfSports = typeOfSports;
	}
	
	public DoubleProperty getMemberShipFee() {
		return _membershipFee;
	}
	
	public void setMemberShipfee(DoubleProperty membershipFee) {
		_membershipFee = membershipFee;
	}
	
	public UserRole getUserRole() {
		return _userRole;
	}
	
	public void setUserRole(UserRole userRole) {
		_userRole = userRole;
	}
}

