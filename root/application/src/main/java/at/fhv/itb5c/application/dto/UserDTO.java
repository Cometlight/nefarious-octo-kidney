package at.fhv.itb5c.application.dto;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import at.fhv.itb5c.commons.enums.Gender;
import at.fhv.itb5c.commons.enums.TypeOfSport;
import at.fhv.itb5c.commons.enums.UserRole;

public class UserDTO extends BaseDTO {
	private String _firstName;
	private String _lastName;
	private String _email;
	private String _telephoneNumber;
	private Gender _gender;
	private String _address;
	private LocalDate _dateOfBirth;
	private Double _membershipFee;
	private Set<UserRole> _roles;
	private Set<TypeOfSport> _typeOfSports;
	private Boolean _membershipFeePaid;
	private Long _departmentId;
	private String _ldapUID;
	
	public UserDTO() {
		_roles = new HashSet<>();
		_typeOfSports = new HashSet<>();
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

	public Double getMembershipFee() {
		return _membershipFee;
	}

	public void setMembershipFee(Double membershipFee) {
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

	public Boolean getMembershipFeePaid() {
		return _membershipFeePaid;
	}

	public void setMembershipFeePaid(Boolean membershipFeePaid) {
		_membershipFeePaid = membershipFeePaid;
	}

	public Long getDepartmentId() {
		return _departmentId;
	}

	public void setDepartmentId(Long departmentId) {
		_departmentId = departmentId;
	}

	public String getLdapUID() {
		return _ldapUID;
	}

	public void setLdapUID(String ldapUID) {
		_ldapUID = ldapUID;
	}

	@Override
	public String toString() {
		return "UserDTO [_id=" + _id + ", _version=" + _version + ", _firstName=" + _firstName + ", _lastName="
				+ _lastName + ", _email=" + _email + ", _telephoneNumber=" + _telephoneNumber + ", _gender=" + _gender
				+ ", _address=" + _address + ", _dateOfBirth=" + _dateOfBirth + ", _membershipFee=" + _membershipFee
				+ ", _roles=" + _roles + ", _typeOfSports=" + _typeOfSports + ", _membershipFeePaid="
				+ _membershipFeePaid + ", _departmentId=" + _departmentId + ", _ldapUID=" + _ldapUID + "]";
	}
}
