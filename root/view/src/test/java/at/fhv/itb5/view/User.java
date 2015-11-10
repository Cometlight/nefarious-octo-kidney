package at.fhv.itb5.view;

import static org.junit.Assert.assertEquals;

import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import at.fhv.itb5c.commons.dto.rmi.IUserRMI;
import at.fhv.itb5c.commons.enums.Gender;
import at.fhv.itb5c.commons.enums.TypeOfSport;
import at.fhv.itb5c.commons.enums.UserRole;

public class User {
	
	@Test
	public void saveUser() {
		/*try {
			IUserRMI user = RMIClient.getRMIClient().getUserFactory().createUser();
			user.setFirstName("Daniel");
			user.setLastName("Unit");
			user.setAddress("Teststraße 7a, 6800 Feldkirch");
			user.setDateOfBirth(LocalDate.now());
			user.setEmail("test@case.com");
			user.setGender(Gender.Male);
			user.setMembershipFee(15.9);
			Set<UserRole> roles = new HashSet<UserRole>();
			roles.add(UserRole.Admin);
			user.setRoles(roles);
			user.setTelephoneNumber("+43 664 874379");
			Set<TypeOfSport> sports = new HashSet<TypeOfSport>();
			sports.add(TypeOfSport.Soccer);
			user.setTypeOfSports(sports);
			user.setMembershipFeePaid(true);

			// save user
			IUserRMI savedUser = RMIClient.getRMIClient().getUserFactory().save(user);
			
			assertEquals(savedUser.getAddress(), user.getAddress());
			assertEquals(savedUser.getFirstName(), user.getFirstName());
			assertEquals(savedUser.getLastName(), user.getLastName());
			assertEquals(savedUser.getDateOfBirth(), user.getDateOfBirth());
			assertEquals(savedUser.getEmail(), user.getEmail());
			assertEquals(savedUser.getGender(), user.getGender());
			assertEquals(Double.doubleToLongBits(savedUser.getMembershipFee()),
					Double.doubleToLongBits(user.getMembershipFee()));
			assertEquals(savedUser.getRoles(), user.getRoles());
			assertEquals(savedUser.getTypeOfSports(), user.getTypeOfSports());
			assertEquals(savedUser.getMembershipFeePaid(), user.getMembershipFeePaid());
			assertEquals(savedUser.getDepartment(), user.getDepartment());
			
		} catch (RemoteException e) {
			Assert.assertEquals(true, false);
		}*/
	}

}
