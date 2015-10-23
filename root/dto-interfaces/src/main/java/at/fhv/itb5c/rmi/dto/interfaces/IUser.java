package at.fhv.itb5c.rmi.dto.interfaces;

import java.rmi.Remote;
import java.time.LocalDate;
import java.util.Set;

// Remote interface for RMI
public interface IUser extends Remote {
	public String getFirstName();

	public void setFirstName(String firstName);

	public String getLastName();

	public void setLastName(String lastName);

	public String getEmail();

	public void setEmail(String email);

	public String getTelephoneNumber();

	public void setTelephoneNumber(String telephoneNumber);

	public IGender getGender();

	public void setGender(IGender gender);

	public String getAddress();

	public void setAddress(String address);

	public LocalDate getDateOfBirth();

	public void setDateOfBirth(LocalDate dateOfBirth);

	public double getMembershipFee();

	public void setMembershipFee(double membershipFee);

	public Set<IUserRole> getRoles();

	public void setRoles(Set<IUserRole> roles);

	public Set<ITypeOfSport> getTypeOfSports();

	public void setTypeOfSports(Set<ITypeOfSport> typeOfSports);
}