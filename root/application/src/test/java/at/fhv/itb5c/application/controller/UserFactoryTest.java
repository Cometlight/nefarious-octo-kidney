package at.fhv.itb5c.application.controller;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

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
	private IUser _user;

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
		_newUser.setMembershipFeePaid(true);

		// save user
		_user = _factory.save(_newUser);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void findUsers() {
		assertNotNull(_user);
		// ID needs to be set after save
		try {
			assertNotEquals(0, _user.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}

		// search with firstname
		assertTrue("firstname search", !_factory.findUsers("Daniel", null, null, null).isEmpty());

		// search with lastname
		assertTrue("lastname search", !_factory.findUsers(null, "Unit", null, null).isEmpty());

		// search with department
		// TODO dgr
		//assertTrue("firstname search", !_factory.findUsers(null, null, 1l, null).isEmpty());

		// search with membership fee paid
		assertTrue("fee paid search", !_factory.findUsers(null, null, null, true).isEmpty());
	}

	@Test
	public void compare(){
		try {
			assertEquals(_newUser.getAddress(), _user.getAddress());
			assertEquals(_newUser.getFirstName(), _user.getFirstName());
			assertEquals(_newUser.getLastName(), _user.getLastName());
			assertEquals(_newUser.getDateOfBirth(), _user.getDateOfBirth());
			assertEquals(_newUser.getEmail(), _user.getEmail());
			assertEquals(_newUser.getGender(), _user.getGender());
			assertEquals(Double.doubleToLongBits(_newUser.getMembershipFee()),
					Double.doubleToLongBits(_user.getMembershipFee()));
			assertEquals(_newUser.getRoles(), _user.getRoles());
			assertEquals(_newUser.getTypeOfSports(), _user.getTypeOfSports());
			assertEquals(_newUser.getMembershipFeePaid(), _user.getMembershipFeePaid());
			assertEquals(_newUser.getDepartment(), _user.getDepartment());
		} catch (Exception e) {
			assertTrue(false);
		}
	}
}
