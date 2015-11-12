package at.fhv.itb5c.application.dto;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import at.fhv.itb5c.commons.enums.Gender;
import at.fhv.itb5c.commons.enums.TypeOfSport;
import at.fhv.itb5c.commons.enums.UserRole;

public class UserDTOTest {

	@Test
	public void testDefaultFirstName() {
		UserDTO user = new UserDTO();
		assertNull(user.getFirstName());
	}

	@Test
	public void testDefaultLastName() {
		UserDTO user = new UserDTO();
		assertNull(user.getLastName());
	}

	@Test
	public void testDefaultEmail() {
		UserDTO user = new UserDTO();
		assertNull(user.getEmail());
	}

	@Test
	public void testDefaultTelephoneNumber() {
		UserDTO user = new UserDTO();
		assertNull(user.getTelephoneNumber());
	}

	@Test
	public void testDefaultGender() {
		UserDTO user = new UserDTO();
		assertNull(user.getGender());
	}

	@Test
	public void testDefaultAddress() {
		UserDTO user = new UserDTO();
		assertNull(user.getAddress());
	}

	@Test
	public void testDefaultDateOfBirth() {
		UserDTO user = new UserDTO();
		assertNull(user.getDateOfBirth());
	}

	@Test
	public void testDefaultMembershipFee() {
		UserDTO user = new UserDTO();
		assertNull(user.getMembershipFee());
	}

	@Test
	public void testDefaultRoles() {
		UserDTO user = new UserDTO();
		assertNull(user.getRoles());
	}

	@Test
	public void testDefaultTypeOfSports() {
		UserDTO user = new UserDTO();
		assertNull(user.getTypeOfSports());
	}

	@Test
	public void testDefaultMembershipFeePaid() {
		UserDTO user = new UserDTO();
		assertNull(user.getMembershipFeePaid());
	}

	@Test
	public void testDefaultDepartmentId() {
		UserDTO user = new UserDTO();
		assertNull(user.getDepartmentId());
	}

	@Test
	public void testDefaultLdapUID() {
		UserDTO user = new UserDTO();
		assertNull(user.getLdapUID());
	}

	@Test
	public void testFirstName() {
		final String firstName = "Lars";
		UserDTO user = new UserDTO();
		user.setFirstName(firstName);
		assertNotNull(user.getFirstName());
		assertTrue(user.getFirstName().equals(firstName));
	}

	@Test
	public void testLastName() {
		final String lastName = "Stobaki";
		UserDTO user = new UserDTO();
		user.setLastName(lastName);
		assertNotNull(user.getLastName());
		assertTrue(user.getLastName().equals(lastName));
	}

	@Test
	public void testEmail() {
		final String email = "lars@stobaki.se";
		UserDTO user = new UserDTO();
		user.setEmail(email);
		assertNotNull(user.getEmail());
		assertTrue(user.getEmail().equals(email));
	}

	@Test
	public void testTelephoneNumber() {
		final String telephoneNumber = "01234875";
		UserDTO user = new UserDTO();
		user.setTelephoneNumber(telephoneNumber);
		assertNotNull(user.getTelephoneNumber());
		assertTrue(user.getTelephoneNumber().equals(telephoneNumber));
	}

	@Test
	public void testGender() {
		final Gender gender = Gender.Female;
		UserDTO user = new UserDTO();
		user.setGender(gender);
		assertEquals(gender, user.getGender());
	}

	@Test
	public void testAddress() {
		final String address = "Nikolausweg 87";
		UserDTO user = new UserDTO();
		user.setAddress(address);
		assertNotNull(user.getAddress());
		assertTrue(user.getAddress().equals(address));
	}

	@Test
	public void testDateOfBirth() {
		final LocalDate dateOfBirth = LocalDate.now().minusYears(3);
		UserDTO user = new UserDTO();
		user.setDateOfBirth(dateOfBirth);
		assertNotNull(user.getDateOfBirth());
		assertTrue(user.getDateOfBirth().equals(dateOfBirth));
	}

	@Test
	public void testMembershipFee() {
		final Double membershipFee = 23.99;
		UserDTO user = new UserDTO();
		user.setMembershipFee(membershipFee);
		assertEquals(membershipFee, user.getMembershipFee());
	}

	@Test
	public void testRoles() {
		Set<UserRole> userRoles = new HashSet<>(Arrays.asList(UserRole.StandardUser));
		UserDTO user = new UserDTO();
		user.setRoles(userRoles);
		assertNotNull(user.getRoles());
		assertTrue(user.getRoles().contains(UserRole.StandardUser));
	}

	@Test
	public void testTypeOfSports() {
		Set<TypeOfSport> typeOfSports = new HashSet<>(Arrays.asList(TypeOfSport.Tennis));
		UserDTO user = new UserDTO();
		user.setTypeOfSports(typeOfSports);
		assertNotNull(user.getTypeOfSports());
		assertTrue(user.getTypeOfSports().contains(TypeOfSport.Tennis));
	}

	@Test
	public void testMembershipFeePaid() {
		final Boolean membershipFeePaid = false;
		UserDTO user = new UserDTO();
		user.setMembershipFeePaid(membershipFeePaid);
		assertFalse(user.getMembershipFeePaid());
	}

	@Test
	public void testDepartmentId() {
		final Long departmentId = 5l;
		UserDTO user = new UserDTO();
		user.setDepartmentId(departmentId);
		assertEquals(departmentId, user.getDepartmentId());
	}

	@Test
	public void testLdapUID() {
		final String ldapUID = "tf-test";
		UserDTO user = new UserDTO();
		user.setLdapUID(ldapUID);
		assertNotNull(user.getLdapUID());
		assertTrue(user.getLdapUID().equals(ldapUID));
	}

}
