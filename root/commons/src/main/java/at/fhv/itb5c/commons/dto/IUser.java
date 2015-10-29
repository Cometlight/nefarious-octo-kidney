package at.fhv.itb5c.commons.dto;

import java.time.LocalDate;
import java.util.Set;

import at.fhv.itb5c.commons.enums.Gender;
import at.fhv.itb5c.commons.enums.TypeOfSport;
import at.fhv.itb5c.commons.enums.UserRole;

// Remote interface for RMI
public interface IUser {
	public String getFirstName() throws Exception;

	public void setFirstName(String firstName) throws Exception;

	public String getLastName() throws Exception;

	public void setLastName(String lastName) throws Exception;

	public String getEmail() throws Exception;

	public void setEmail(String email) throws Exception;

	public String getTelephoneNumber() throws Exception;

	public void setTelephoneNumber(String telephoneNumber) throws Exception;

	public Gender getGender() throws Exception;

	public void setGender(Gender gender) throws Exception;

	public String getAddress() throws Exception;

	public void setAddress(String address) throws Exception;

	public LocalDate getDateOfBirth() throws Exception;

	public void setDateOfBirth(LocalDate dateOfBirth) throws Exception;

	public double getMembershipFee() throws Exception;

	public void setMembershipFee(double membershipFee) throws Exception;

	public Set<UserRole> getRoles() throws Exception;

	public void setRoles(Set<UserRole> roles) throws Exception;

	public Set<TypeOfSport> getTypeOfSports() throws Exception;

	public void setTypeOfSports(Set<TypeOfSport> typeOfSports) throws Exception;

	public long getId() throws Exception;

	public long getVersion() throws Exception;
}