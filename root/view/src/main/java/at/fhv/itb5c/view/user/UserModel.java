package at.fhv.itb5c.view.user;

import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.HashSet;

import at.fhv.itb5c.commons.dto.rmi.IUserRMI;
import at.fhv.itb5c.commons.enums.Gender;
import at.fhv.itb5c.commons.enums.TypeOfSport;
import at.fhv.itb5c.commons.enums.UserRole;
import at.fhv.itb5c.view.util.AlertUtil;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;

public class UserModel {
	private StringProperty _firstName;
	private StringProperty _lastName;
	private StringProperty _address;
	private StringProperty _eMail;
	private StringProperty _telephonenumber;
	private Gender _gender;
	private ObjectProperty<LocalDate> _birthDate;
	private ObservableSet<TypeOfSport> _typeOfSports;
	private DoubleProperty _membershipFee;
	private ObservableSet<UserRole> _userRoles;

	private IUserRMI _rmiUser;

	private UserModel(IUserRMI user) throws RemoteException {
		_firstName = new SimpleStringProperty();
		_lastName = new SimpleStringProperty();
		_address = new SimpleStringProperty();
		_telephonenumber = new SimpleStringProperty();
		_eMail = new SimpleStringProperty();
		_birthDate = new SimpleObjectProperty<>();
		_membershipFee = new SimpleDoubleProperty();

		setIUserRMI(user);
	}

	public static UserModel createUserModel(IUserRMI user) throws RemoteException {
		if (user != null) {
			return new UserModel(user);
		} else {
			return null;
		}
	}

	public void setIUserRMI(IUserRMI user) throws RemoteException {
		_rmiUser = user;
		_firstName.setValue(user.getFirstName());
		_lastName.setValue(user.getLastName());
		_address.setValue(user.getAddress());
		_eMail.setValue(user.getEmail());
		_telephonenumber.setValue(user.getTelephoneNumber());
		_gender = user.getGender();
		_birthDate.setValue(user.getDateOfBirth());
		_typeOfSports = (ObservableSet<TypeOfSport>) FXCollections.observableSet(user.getTypeOfSports());
		_membershipFee.setValue(user.getMembershipFee());
		_userRoles = (ObservableSet<UserRole>) FXCollections.observableSet(user.getRoles());
	}

	public IUserRMI getRMIUser() {
		try {
			_rmiUser.setFirstName(_firstName.getValue());
			_rmiUser.setLastName(_lastName.getValue());
			_rmiUser.setAddress(_address.getValue());
			_rmiUser.setTelephoneNumber(_telephonenumber.getValue());
			_rmiUser.setEmail(_eMail.getValue());
			_rmiUser.setDateOfBirth(_birthDate.getValue());
			_rmiUser.setMembershipFee(_membershipFee.getValue());
			_rmiUser.setGender(_gender);
			
			if (_userRoles != null) {
				_rmiUser.setRoles(new HashSet<>(_userRoles));
			}

			if (_typeOfSports != null) {
				_rmiUser.setTypeOfSports(new HashSet<>(_typeOfSports));
			}
		} catch (RemoteException e) {
			AlertUtil.ConnectionAlert();
		}

		return _rmiUser;
	}

	public StringProperty getFirstName() {
		return _firstName;
	}

	public StringProperty getLastName() {
		return _lastName;
	}

	public StringProperty getAddress() {
		return _address;
	}

	public StringProperty getTelephonenumber() {
		return _telephonenumber;
	}

	public StringProperty getEMail() {
		return _eMail;
	}

	public void setGender(Gender gender) {
		_gender = gender;
	}

	public Gender getGender() {
		return _gender;
	}

	public ObjectProperty<LocalDate> getBirthDate() {
		return _birthDate;
	}

	public ObservableSet<TypeOfSport> getTypeOfSports() {
		return _typeOfSports;
	}

	public void setTypeOfSports(ObservableSet<TypeOfSport> typeOfSports) {
		_typeOfSports = typeOfSports;
	}

	public DoubleProperty getMemberShipFee() {
		return _membershipFee;
	}

	public ObservableSet<UserRole> getUserRoles() {
		return _userRoles;
	}

	public void setUserRoles(ObservableSet<UserRole> userRoles) {
		_userRoles = userRoles;
	}
}
