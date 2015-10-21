package at.fhv.itb5c.model.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {
	@Id
	@Column(name = "ID", nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long _id;
	
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
	
	@Column(name = "dateOfBirth", nullable = false)
	private LocalDate _dateOfBirth;
	
	@Column(name = "membershipFee", nullable = true)
	private double _membershipFee;

	public User() {

	}

	public long getId() {
		return _id;
	}
	
	public void setId(long id) {
		this._id = id;
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

	public void setEmail(String email) {
		this._email = email;
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
	
	@Override
	public String toString() {
		return _firstName + " " + _lastName + "\n    " + _address + " -- " + _dateOfBirth.toString();
	}
}