package at.fhv.itb5c.model;

import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.HashSet;

import at.fhv.itb5c.commons.dto.rmi.IUserRMI;
import at.fhv.itb5c.commons.enums.Gender;
import at.fhv.itb5c.commons.enums.TypeOfSport;
import at.fhv.itb5c.commons.enums.UserRole;
import at.fhv.itb5c.util.AlertUtil;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


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
	private ObservableList<UserRole> _userRoles;
	
	private IUserRMI _rmiUser;
	
	private UserModel(IUserRMI user) throws RemoteException {
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
		//_typeOfSports = (ObservableList<TypeOfSport>) FXCollections.observableSet(user.getTypeOfSports());
		_membershipFee.setValue(user.getMembershipFee());	
		//_userRoles = (ObservableList<UserRole>) FXCollections.observableSet(user.getRoles());
		
		_rmiUser = user;
	}
	
	public static UserModel createUserModel(IUserRMI user) throws RemoteException {
		return new UserModel(user);
	}
	
	public IUserRMI getRMIUser() {
		return _rmiUser;
	}
	
	public void setFirstName(StringProperty firstName) {
		try {
			_rmiUser.setFirstName(firstName.getValue());
		} catch (RemoteException e) {
			AlertUtil.ConnectionAlert();
		}
		_firstName = firstName;
	}
	
	public StringProperty getFirstName() {
		return _firstName;
	}
	
	public StringProperty getLastName() {
		return _lastName;
	}

	public void setLastName(StringProperty lastName) {
		try {
			_rmiUser.setLastName(lastName.getValue());
		} catch (RemoteException e) {
			AlertUtil.ConnectionAlert();
		}
		_lastName = lastName;
	}

	public StringProperty getAdress() {
		return _address;
	}

	public void setAdress(StringProperty adress) {
		try {
			_rmiUser.setAddress(adress.getValue());
		} catch (RemoteException e) {
			AlertUtil.ConnectionAlert();
		}
		_address = adress;
	}

	public StringProperty getTelephonenumber() {
		return _telephonenumber;
	}

	public void setTelephonenumber(StringProperty telephonenumber) {
		try {
			_rmiUser.setTelephoneNumber(telephonenumber.getValue());
		} catch (RemoteException e) {
			AlertUtil.ConnectionAlert();
		}
		_telephonenumber = telephonenumber;
	}

	public StringProperty getEMail() {
		return _eMail;
	}

	public void setEMail(StringProperty eMail) {
		try {
			_rmiUser.setEmail(eMail.getValue());
		} catch (RemoteException e) {
			AlertUtil.ConnectionAlert();
		}
		_eMail = eMail;
	}

	public void setGender(Gender gender) {
		try {
			_rmiUser.setGender(gender);
		} catch (RemoteException e) {
			AlertUtil.ConnectionAlert();
		}
		_gender = gender;
	}
	
	public Gender getGender() {
		return _gender;
	}
	
	public void setBirthDate(ObjectProperty<LocalDate> birthDate) {
		try {
			_rmiUser.setDateOfBirth(birthDate.getValue());
		} catch (RemoteException e) {
			AlertUtil.ConnectionAlert();
		}
		_birthDate = birthDate;
	}
	
	public ObjectProperty<LocalDate> getBirthDate() {
		return _birthDate;
	}
	
	public ObservableList<TypeOfSport> getTypeOfSports() {
		return _typeOfSports;
	}
	
	public void setTypeOfSports(ObservableList<TypeOfSport> typeOfSports) {
		try {
			_rmiUser.setTypeOfSports(new HashSet<>(typeOfSports));
		} catch (RemoteException e) {
			AlertUtil.ConnectionAlert();
		}
		_typeOfSports = typeOfSports;
	}
	
	public DoubleProperty getMemberShipFee() {
		return _membershipFee;
	}
	
	public void setMemberShipfee(DoubleProperty membershipFee) {
		try {
			_rmiUser.setMembershipFee(membershipFee.doubleValue());
		} catch (RemoteException e) {
			AlertUtil.ConnectionAlert();
		}
		_membershipFee = membershipFee;
	}
	
	public ObservableList<UserRole> getUserRoles() {
		return _userRoles;
	}
	
	public void setUserRoles(ObservableList<UserRole> userRoles) {
		_userRoles = userRoles;
	}
}
