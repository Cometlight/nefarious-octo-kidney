package at.fhv.itb5c.rmi.application.dto;

import java.time.LocalDate;
import java.util.Set;

import at.fhv.itb5c.commons.dto.IGender;
import at.fhv.itb5c.commons.dto.ITypeOfSport;
import at.fhv.itb5c.commons.dto.IUser;
import at.fhv.itb5c.commons.dto.IUserRole;

public class UserDTO implements IUser {
	//TODO replace interfaces with enums when merged with model branch
	private String _firstName;
	private String _lastName;
	private String _email;
	private String _telephoneNumber;
	private IGender _gender;
	private String _address;
	private LocalDate _dateOfBirth;
	private double _membershipFee;
	private Set<IUserRole> _roles;
	private Set<ITypeOfSport> _typeOfSports;
	
	//TODO: implement when merged with model branch
	public UserDTO(IUser user) {
		_firstName = user.getFirstName();
		_lastName = user.getLastName();
		_email = user.getEmail();
		_telephoneNumber = user.getTelephoneNumber();
		_gender = user.getGender();
		_address = user.getAddress();
		_dateOfBirth = user.getDateOfBirth();
		_membershipFee = user.getMembershipFee();
		_roles = user.getRoles();
		_typeOfSports = user.getTypeOfSports();
	}

	public String getFirstName() {
		return _firstName;
	}

	public void setFirstName(String firstName) {
		_firstName = firstName;
	}

	public String getLastName() {
		return _lastName;
	}

	public void setLastName(String lastName) {
		_lastName = lastName;
	}

	public String getEmail() {
		return _email;
	}

	public void setEmail(String email) {
		_email = email;
	}

	public String getTelephoneNumber() {
		return _telephoneNumber;
	}

	public void setTelephoneNumber(String telephoneNumber) {
		_telephoneNumber = telephoneNumber;
	}

	public IGender getGender() {
		return _gender;
	}

	public void setGender(IGender gender) {
		_gender = gender;
	}

	public String getAddress() {
		return _address;
	}

	public void setAddress(String address) {
		_address = address;
	}

	public LocalDate getDateOfBirth() {
		return _dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		_dateOfBirth = dateOfBirth;
	}

	public double getMembershipFee() {
		return _membershipFee;
	}

	public void setMembershipFee(double membershipFee) {
		_membershipFee = membershipFee;
	}

	public Set<IUserRole> getRoles() {
		return _roles;
	}

	public void setRoles(Set<IUserRole> roles) {
		_roles = roles;
	}

	public Set<ITypeOfSport> getTypeOfSports() {
		return _typeOfSports;
	}

	public void setTypeOfSports(Set<ITypeOfSport> typeOfSports) {
		_typeOfSports = typeOfSports;
	}
}