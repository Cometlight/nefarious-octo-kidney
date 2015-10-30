package at.fhv.itb5c.model.entity;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.Transient;

import at.fhv.itb5c.commons.enums.Gender;
import at.fhv.itb5c.commons.enums.TypeOfSport;
import at.fhv.itb5c.commons.enums.UserRole;
import at.fhv.itb5c.model.validator.EmailValidator;

@Entity
public class User extends PersistableObject {
	@Column(name = "firstName", nullable = false)
	private String _firstName;

	@Column(name = "lastName", nullable = false)
	private String _lastName;

	@Column(name = "email", nullable = true)
	private String _email;

	@Column(name = "telephoneNumber", nullable = true)
	private String _telephoneNumber;

	@Column(name = "gender", nullable = false)
	@Enumerated(EnumType.STRING)
	private Gender _gender;

	@Column(name = "address", nullable = false)
	private String _address;

	@Transient
	private LocalDate _dateOfBirth;

	@Column(name = "dateOfBirth", nullable = false)
	private Date _persistDateOfBirth;
	
	@Column(name = "membershipFee", nullable = true)
	private double _membershipFee;

	@Column(name = "membershipFeePaid", nullable = true)
	private boolean _membershipFeePaid;

	@Column(name = "roles", nullable = false)
	private Set<UserRole> _roles;

	@Column(name = "typeOfSports", nullable = true)
	private Set<TypeOfSport> _typeOfSports;

	@Column(name = "department", nullable = true)
	private Department _department;

	@Column(name = "departmentId", nullable = true)
	private Long _departmentId; // needed for search; may be refactored if the
								// need arises

	public User() {

	}

	@PrePersist
	private void persist() {
		if (_dateOfBirth != null) {
			_persistDateOfBirth = Date.from(_dateOfBirth.atStartOfDay(ZoneId.systemDefault()).toInstant());
		}
	}

	@PostLoad
	private void load() {
		if (_persistDateOfBirth != null) {
			_dateOfBirth = _persistDateOfBirth.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		}
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
		this._lastName = lastName;
	}

	public String getEmail() {
		return _email;
	}

	public boolean setEmail(String email) {
		if (email == null) {
			_email = null;
			return true;
		} else {
			boolean validMail = EmailValidator.validate(email);
			if (validMail) {
				_email = email;
			}
			return validMail;
		}
	}

	public String getTelephoneNumber() {
		return _telephoneNumber;
	}

	public void setTelephoneNumber(String telephoneNumber) {
		this._telephoneNumber = telephoneNumber;
	}

	public Gender getGender() {
		return _gender;
	}

	public void setGender(Gender gender) {
		this._gender = gender;
	}

	public String getAddress() {
		return _address;
	}

	public void setAddress(String address) {
		this._address = address;
	}

	public LocalDate getDateOfBirth() {
		return _dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this._dateOfBirth = dateOfBirth;
	}

	public double getMembershipFee() {
		return _membershipFee;
	}

	public void setMembershipFee(double membershipFee) {
		this._membershipFee = membershipFee;
	}
	
	public boolean getMembershipFeePaid() {
		return _membershipFeePaid;
	}

	public void setMembershipFeePaid(boolean membershipFeePaid) {
		this._membershipFeePaid = membershipFeePaid;
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

	public Department getDepartment() {
		return _department;
	}

	public void setDepartment(Department department) {
		_department = department;
		_departmentId = department == null ? null : department.getId();
	}

	@Override
	public String toString() {
		return _firstName + " " + _lastName + "\n    " + _address;
	}
}