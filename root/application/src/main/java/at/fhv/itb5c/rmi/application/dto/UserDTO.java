package at.fhv.itb5c.rmi.application.dto;

import java.time.LocalDate;
import java.util.Set;

<<<<<<< HEAD
import at.fhv.itb5c.commons.dto.IUser;
import at.fhv.itb5c.commons.enums.Gender;
import at.fhv.itb5c.commons.enums.TypeOfSport;
import at.fhv.itb5c.commons.enums.UserRole;

public class UserDTO implements IUser {
	private long _id;
	private long _version;
=======
import at.fhv.itb5c.commons.dto.IGender;
import at.fhv.itb5c.commons.dto.ITypeOfSport;
import at.fhv.itb5c.commons.dto.IUser;
import at.fhv.itb5c.commons.dto.IUserRole;

public class UserDTO implements IUser {
	//TODO replace interfaces with enums when merged with model branch
>>>>>>> 922f5f09da4959efe6d5538cf5360b5f5f0a790e
	private String _firstName;
	private String _lastName;
	private String _email;
	private String _telephoneNumber;
<<<<<<< HEAD
	private Gender _gender;
	private String _address;
	private LocalDate _dateOfBirth;
	private double _membershipFee;
	private Set<UserRole> _roles;
	private Set<TypeOfSport> _typeOfSports;
	
	public UserDTO() {
	}

	public UserDTO(String firstName, String lastName, String email, String telephoneNumber, Gender gender,
			String address, LocalDate dateOfBirth, double membershipFee, Set<UserRole> roles,
			Set<TypeOfSport> typeOfSports) {
		_firstName = firstName;
		_lastName = lastName;
		_email = email;
		_telephoneNumber = telephoneNumber;
		_gender = gender;
		_address = address;
		_dateOfBirth = dateOfBirth;
		_membershipFee = membershipFee;
		_roles = roles;
		_typeOfSports = typeOfSports;
	}

	public long getId() {
		return _id;
	}

	public void setId(long id) {
		_id = id;
	}

	public long getVersion() {
		return _version;
	}

	public void setVersion(long version) {
		_version = version;
=======
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
>>>>>>> 922f5f09da4959efe6d5538cf5360b5f5f0a790e
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

<<<<<<< HEAD
	public Gender getGender() {
		return _gender;
	}

	public void setGender(Gender gender) {
=======
	public IGender getGender() {
		return _gender;
	}

	public void setGender(IGender gender) {
>>>>>>> 922f5f09da4959efe6d5538cf5360b5f5f0a790e
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

<<<<<<< HEAD
	public Set<UserRole> getRoles() {
		return _roles;
	}

	public void setRoles(Set<UserRole> roles) {
		_roles = roles;
	}

	public Set<TypeOfSport> getTypeOfSports() {
		return _typeOfSports;
	}

	public void setTypeOfSports(Set<TypeOfSport> typeOfSports) {
=======
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
>>>>>>> 922f5f09da4959efe6d5538cf5360b5f5f0a790e
		_typeOfSports = typeOfSports;
	}
}