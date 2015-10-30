package at.fhv.itb5c.application.controller;

import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import at.fhv.itb5c.application.dto.UserDTO;
import at.fhv.itb5c.commons.dto.IUser;
import at.fhv.itb5c.commons.enums.Gender;
import at.fhv.itb5c.commons.enums.TypeOfSport;
import at.fhv.itb5c.commons.enums.UserRole;

public class UserFactoryTest {
	private UserFactory _factory;
	private IUser _newUser;

	@Before
	public void setUp() throws Exception {
		// instantiate factory
		_factory = new UserFactory();

		// create user
		_newUser = new UserDTO();

		// set attributes
		_newUser.setFirstName("Daniel");
		_newUser.setLastName("Unit");
		_newUser.setAddress("Teststraße 7a, 6800 Feldkirch");
		_newUser.setDateOfBirth(LocalDate.now());
		_newUser.setEmail("test@case.com");
		_newUser.setGender(Gender.Male);
		_newUser.setMembershipFee(15.9);
		Set<UserRole> roles = new HashSet<UserRole>();
		roles.add(UserRole.Admin);
		_newUser.setRoles(roles);
		_newUser.setTelephoneNumber("+43 664 874379");
		Set<TypeOfSport> sports = new HashSet<TypeOfSport>();
		sports.add(TypeOfSport.Soccer);
		_newUser.setTypeOfSports(sports);

		// save user
		_newUser = _factory.save(_newUser);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		// ID needs to be set after save
		// TODO no ID set ...
		/*try {
			assertNotEquals(0, _newUser.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}*/

		// search with firstname
		assertTrue("firstname search", !_factory.findUsers("Daniel", null, null, null).isEmpty());

		// search with lastname
		assertTrue("firstname search", !_factory.findUsers(null, "Unit", null, null).isEmpty());

		// search with department
		// TODO dgr
		//assertTrue("firstname search", !_factory.findUsers(null, null, 1l, null).isEmpty());

		// search with membership fee paid
		// TODO dgr
		//assertTrue("firstname search", !_factory.findUsers(null, null, null, true).isEmpty());
	}

}
