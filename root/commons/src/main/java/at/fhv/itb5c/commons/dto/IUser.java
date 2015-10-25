package at.fhv.itb5c.commons.dto;

import java.rmi.Remote;
import java.time.LocalDate;
import java.util.Set;

<<<<<<< HEAD
import at.fhv.itb5c.commons.enums.Gender;
import at.fhv.itb5c.commons.enums.TypeOfSport;
import at.fhv.itb5c.commons.enums.UserRole;

=======
>>>>>>> 922f5f09da4959efe6d5538cf5360b5f5f0a790e
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

<<<<<<< HEAD
	public Gender getGender();

	public void setGender(Gender gender);
=======
	public IGender getGender();

	public void setGender(IGender gender);
>>>>>>> 922f5f09da4959efe6d5538cf5360b5f5f0a790e

	public String getAddress();

	public void setAddress(String address);

	public LocalDate getDateOfBirth();

	public void setDateOfBirth(LocalDate dateOfBirth);

	public double getMembershipFee();

	public void setMembershipFee(double membershipFee);

<<<<<<< HEAD
	public Set<UserRole> getRoles();

	public void setRoles(Set<UserRole> roles);

	public Set<TypeOfSport> getTypeOfSports();

	public void setTypeOfSports(Set<TypeOfSport> typeOfSports);

	public long getId();

	public long getVersion();
=======
	public Set<IUserRole> getRoles();

	public void setRoles(Set<IUserRole> roles);

	public Set<ITypeOfSport> getTypeOfSports();

	public void setTypeOfSports(Set<ITypeOfSport> typeOfSports);
>>>>>>> 922f5f09da4959efe6d5538cf5360b5f5f0a790e
}