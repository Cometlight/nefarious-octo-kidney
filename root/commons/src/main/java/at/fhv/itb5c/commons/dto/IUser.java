package at.fhv.itb5c.commons.dto;

import java.time.LocalDate;
import java.util.Set;

import at.fhv.itb5c.commons.enums.Gender;
import at.fhv.itb5c.commons.enums.TypeOfSport;
import at.fhv.itb5c.commons.enums.UserRole;

// Remote interface for RMI
public interface IUser {
	public String getFirstName();

	public void setFirstName(String firstName);

	public String getLastName();

	public void setLastName(String lastName);

	public String getEmail();

	public void setEmail(String email);

	public String getTelephoneNumber();

	public void setTelephoneNumber(String telephoneNumber);

	public Gender getGender();

	public void setGender(Gender gender);

	public String getAddress();

	public void setAddress(String address);

	public LocalDate getDateOfBirth();

	public void setDateOfBirth(LocalDate dateOfBirth);

	public double getMembershipFee();

	public void setMembershipFee(double membershipFee);

	public Set<UserRole> getRoles();

	public void setRoles(Set<UserRole> roles);

	public Set<TypeOfSport> getTypeOfSports();

	public void setTypeOfSports(Set<TypeOfSport> typeOfSports);

	public long getId();

	public long getVersion();
}