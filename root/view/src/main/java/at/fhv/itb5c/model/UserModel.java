package at.fhv.itb5c.model;

import java.time.LocalDate;
import java.util.HashSet;

import at.fhv.itb5c.commons.dto.rmi.IUserRMI;
import at.fhv.itb5c.commons.enums.Gender;
import at.fhv.itb5c.commons.enums.TypeOfSport;
import at.fhv.itb5c.commons.enums.UserRole;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/*
 * TODO: 
 * 	set the values of a User if its set in the constructor
 * 	use setters to set values in the User
 */

public class UserModel {
	private StringProperty _firstName;
	private StringProperty _lastName;
	private StringProperty _address;
	private StringProperty _eMail;
	private StringProperty _telephonenumber;
	private Gender _gender;
	private ObjectProperty<LocalDate> _birthDate;
	private ObservableList<TypeOfSport> _typeOfSports;
	private DoubleProperty _membershipFee;
	private UserRole _userRole;
	
	private IUserRMI _rmiUser;
	
	@SuppressWarnings("unchecked")
	public UserModel(IUserRMI user) {
		_firstName = new SimpleStringProperty();
		_lastName = new SimpleStringProperty();
		_address = new SimpleStringProperty();
		_telephonenumber = new SimpleStringProperty();
		_eMail = new SimpleStringProperty();
		_birthDate = new SimpleObjectProperty<>();
		_membershipFee = new SimpleDoubleProperty();
		
		_firstName.setValue(user.getFirstName());
		_lastName.setValue(user.getLastName());
		_address.setValue(user.getAddress());
		_eMail.setValue(user.getEmail());
		_telephonenumber.setValue(user.getTelephoneNumber());
		_gender = user.getGender();
		_birthDate.setValue(user.getDateOfBirth());
		_typeOfSports = (ObservableList<TypeOfSport>) FXCollections.observableSet(user.getTypeOfSports());
		_membershipFee.setValue(user.getMembershipFee());
		
		//TODO: user rework to set
		//_userRole = user.getRoles();
	}
	
	public void setFirstName(StringProperty firstName) {
		_rmiUser.setFirstName(firstName.getValue());
		_firstName = firstName;
	}
	
	public StringProperty getFirstName() {
		return _firstName;
	}
	
	public StringProperty getLastName() {
		return _lastName;
	}

	public void setLastName(StringProperty lastName) {
		_rmiUser.setLastName(lastName.getValue());
		_lastName = lastName;
	}

	public StringProperty getAdress() {
		return _address;
	}

	public void setAdress(StringProperty adress) {
		_rmiUser.setAddress(adress.getValue());
		_address = adress;
	}

	public StringProperty getTelephonenumber() {
		return _telephonenumber;
	}

	public void setTelephonenumber(StringProperty telephonenumber) {
		_rmiUser.setTelephoneNumber(telephonenumber.getValue());
		_telephonenumber = telephonenumber;
	}

	public StringProperty getEMail() {
		return _eMail;
	}

	public void setEMail(StringProperty eMail) {
		_rmiUser.setEmail(eMail.getValue());
		_eMail = eMail;
	}

	public void setGender(Gender gender) {
		_rmiUser.setGender(gender);
		_gender = gender;
	}
	
	public Gender getGender() {
		return _gender;
	}
	
	public void setBirthDate(ObjectProperty<LocalDate> birthDate) {
		_rmiUser.setDateOfBirth(birthDate.getValue());
		_birthDate = birthDate;
	}
	
	public ObjectProperty<LocalDate> getBirthDate() {
		return _birthDate;
	}
	
	public ObservableList<TypeOfSport> getTypeOfSports() {
		return _typeOfSports;
	}
	
	public void setTypeOfSports(ObservableList<TypeOfSport> typeOfSports) {
		_rmiUser.setTypeOfSports(new HashSet<>(typeOfSports));
		_typeOfSports = typeOfSports;
	}
	
	public DoubleProperty getMemberShipFee() {
		return _membershipFee;
	}
	
	public void setMemberShipfee(DoubleProperty membershipFee) {
		_rmiUser.setMembershipFee(membershipFee.doubleValue());
		_membershipFee = membershipFee;
	}
	
	public UserRole getUserRole() {
		return _userRole;
	}
	
	public void setUserRole(UserRole userRole) {
		//TODO: when reworked gui to support list of roles
		_userRole = userRole;
	}
	
	public IUserRMI getRMIUser() {
		return _rmiUser;
	}
}
