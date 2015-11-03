package at.fhv.itb5c.application.dto;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import at.fhv.itb5c.commons.dto.IDepartment;
import at.fhv.itb5c.commons.dto.IUser;
import at.fhv.itb5c.commons.enums.Gender;
import at.fhv.itb5c.commons.enums.TypeOfSport;
import at.fhv.itb5c.commons.enums.UserRole;

public class UserDTO implements IUser {
	private Long _id;
	private Long _version;
	private String _firstName;
	private String _lastName;
	private String _email;
	private String _telephoneNumber;
	private Gender _gender;
	private String _address;
	private LocalDate _dateOfBirth;
	private double _membershipFee;
	private Set<UserRole> _roles;
	private Set<TypeOfSport> _typeOfSports;
	private boolean _membershipFeePaid;
	private DepartmentDTO _department;
	
	public UserDTO() {
		_roles = new HashSet<UserRole>();
		_typeOfSports = new HashSet<TypeOfSport>();
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

	public Long getId() {
		return _id;
	}

	public void setId(Long id) {
		_id = id;
	}

	public Long getVersion() {
		return _version;
	}

	public void setVersion(Long version) {
		_version = version;
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

	public Gender getGender() {
		return _gender;
	}

	public void setGender(Gender gender) {
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
		_typeOfSports = typeOfSports;
	}

	public boolean getMembershipFeePaid() {
		return _membershipFeePaid;
	}

	public void setMembershipFeePaid(boolean membershipFeePaid) {
		_membershipFeePaid = membershipFeePaid;
	}

	public IDepartment getDepartment() {
		return _department;
	}

	public void setDepartment(IDepartment department) {
		_department = (DepartmentDTO) department;
	}

	@Override
	public String toString() {
		return "UserDTO [_id=" + _id + ", _version=" + _version + ", _firstName=" + _firstName + ", _lastName="
				+ _lastName + ", _email=" + _email + ", _telephoneNumber=" + _telephoneNumber + ", _gender=" + _gender
				+ ", _address=" + _address + ", _dateOfBirth=" + _dateOfBirth + ", _membershipFee=" + _membershipFee
				+ ", _roles=" + _roles + ", _typeOfSports=" + _typeOfSports + "]";
	}
}