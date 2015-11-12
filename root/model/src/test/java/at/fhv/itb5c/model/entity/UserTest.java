package at.fhv.itb5c.model.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import at.fhv.itb5c.commons.enums.Gender;
import at.fhv.itb5c.commons.enums.TypeOfSport;
import at.fhv.itb5c.commons.enums.UserRole;
import at.fhv.itb5c.model.DatabaseTestUtility;
import at.fhv.itb5c.model.PersistenceFacade;

public class UserTest {
	private static final String DBFILE = "testdb.odb";

	@Before
	public void beforeEach() throws Exception {
		DatabaseTestUtility.deleteDatabaseFile(DBFILE);
		PersistenceFacade.setPersistenceUnitName(DBFILE);
	}

	@Test
	public void testFirstName() throws Exception {
		final String firstName = "Michael";

		User user = new User();
		user.setFirstName(firstName);
		user = PersistenceFacade.getInstance().saveOrUpdate(user);

		assertEquals(firstName, user.getFirstName());
	}

	@Test
	public void testLastName() throws Exception {
		final String lastName = "Moore";

		User user = new User();
		user.setLastName(lastName);
		user = PersistenceFacade.getInstance().saveOrUpdate(user);

		assertEquals(lastName, user.getLastName());
	}

	@Test
	public void testEmail() throws Exception {
		final String emailValid = "michael.moore@nine-eleven.com";
		final String emailInvalid = "michael.moore";

		User user = new User();

		// set a valid email address
		user.setEmail(emailValid);
		user = PersistenceFacade.getInstance().saveOrUpdate(user);

		assertEquals(emailValid, user.getEmail());

		// try to set an invalid email address - does not delete the valid
		user.setEmail(emailInvalid);
		user = PersistenceFacade.getInstance().saveOrUpdate(user);

		assertEquals(emailValid, user.getEmail());

		// delete the email address
		user.setEmail(null);
		user = PersistenceFacade.getInstance().saveOrUpdate(user);

		assertEquals(null, user.getEmail());
	}

	@Test
	public void testTelephoneNumber() throws Exception {
		final String telephoneNumber = "123456";

		User user = new User();
		user.setTelephoneNumber(telephoneNumber);
		user = PersistenceFacade.getInstance().saveOrUpdate(user);

		assertEquals(telephoneNumber, user.getTelephoneNumber());
	}

	@Test
	public void testGender() throws Exception {
		User user = new User();
		user.setGender(Gender.Male);
		user = PersistenceFacade.getInstance().saveOrUpdate(user);

		assertEquals(Gender.Male, user.getGender());

		user.setGender(Gender.Female);
		user = PersistenceFacade.getInstance().saveOrUpdate(user);

		assertEquals(Gender.Female, user.getGender());
	}

	@Test
	public void testAddress() throws Exception {
		final String address = "Road 17";

		User user = new User();
		user.setAddress(address);
		user = PersistenceFacade.getInstance().saveOrUpdate(user);

		assertEquals(address, user.getAddress());
	}

	/**
	 * Tests whether the default date of birth is null.
	 */
	@Test
	public void testDateOfBirthDefault() throws Exception {
		User user = new User();
		user = PersistenceFacade.getInstance().saveOrUpdate(user);

		assertEquals(null, user.getDateOfBirth());
	}

	@Test
	public void testDateOfBirth() throws Exception {
		final LocalDate dateOfBirth = LocalDate.of(1980, 5, 3);

		User user = new User();
		user.setDateOfBirth(dateOfBirth);
		user = PersistenceFacade.getInstance().saveOrUpdate(user);

		assertEquals(dateOfBirth, user.getDateOfBirth());
	}

	/**
	 * Tests whether a date in the future as date of birth is accepted.
	 */
	@Test
	public void testDateOfBirthSetter() throws Exception {
		final LocalDate dateOfBirth = LocalDate.now().plusYears(3l);

		User user = new User();

		user.setDateOfBirth(dateOfBirth);
		user = PersistenceFacade.getInstance().saveOrUpdate(user);

		assertEquals(null, user.getDateOfBirth());
	}

	/**
	 * Tests whether the default membership fee is $0.00.
	 */
	@Test
	public void testMembershipFeeDefault() throws Exception {
		User user = new User();
		user = PersistenceFacade.getInstance().saveOrUpdate(user);

		assertEquals(0.0, user.getMembershipFee(), 0.01d);
	}

	@Test
	public void testMembershipFee() throws Exception {
		final double membershipFee = 42;

		User user = new User();
		user.setMembershipFee(membershipFee);
		user = PersistenceFacade.getInstance().saveOrUpdate(user);
		assertEquals(membershipFee, user.getMembershipFee(), 0.01d);
	}

	/**
	 * Tests whether the membership fee is NOT paid by default.
	 */
	@Test
	public void testMemberShipFeePaidDefault() throws Exception {
		User user = new User();
		user = PersistenceFacade.getInstance().saveOrUpdate(user);
		assertEquals(false, user.getMembershipFeePaid());
	}

	@Test
	public void testMembershipFeePaid() throws Exception {
		User user = new User();

		// the user has paid the membership fee
		user.setMembershipFeePaid(true);
		user = PersistenceFacade.getInstance().saveOrUpdate(user);

		assertEquals(true, user.getMembershipFeePaid());

		// the user has to pay the membership fee
		user.setMembershipFeePaid(false);
		user = PersistenceFacade.getInstance().saveOrUpdate(user);

		assertEquals(false, user.getMembershipFeePaid());
	}

	/**
	 * Tests whether user roles is an empty set by default.
	 */
	@Test
	public void testRolesDefault() throws Exception {
		User user = new User();
		user = PersistenceFacade.getInstance().saveOrUpdate(user);
		assertEquals(0, user.getRoles().size());
	}

	@Test
	public void testRoles() throws Exception {
		final Set<UserRole> roles = new HashSet<>();
		roles.add(UserRole.Admin);

		User user = new User();
		user.setRoles(roles);
		user = PersistenceFacade.getInstance().saveOrUpdate(user);

		assertEquals(1, user.getRoles().size());
		assertTrue(user.getRoles().contains(UserRole.Admin));
	}

	/**
	 * Tests whether the setter for user roles sets an empty set when the
	 * specified value is null.
	 */
	@Test
	public void testRolesSetter() throws Exception {
		final Set<UserRole> roles = new HashSet<>();
		roles.add(UserRole.Admin);
		roles.add(UserRole.StandardUser);

		User user = new User();
		user.setRoles(roles);
		user = PersistenceFacade.getInstance().saveOrUpdate(user);

		assertEquals(2, user.getRoles().size());
		assertTrue(user.getRoles().contains(UserRole.Admin));
		assertTrue(user.getRoles().contains(UserRole.StandardUser));

		// try to set roles to null
		user.setRoles(null);
		user = PersistenceFacade.getInstance().saveOrUpdate(user);
		assertEquals(0, user.getRoles().size());
	}

	/**
	 * Tests whether the default set of type of sports is the empty set.
	 */
	@Test
	public void testTypeOfSportsDefault() throws Exception {
		User user = new User();
		user = PersistenceFacade.getInstance().saveOrUpdate(user);

		assertEquals(0, user.getTypeOfSports().size());
	}

	@Test
	public void testTypeOfSports() throws Exception {
		final Set<TypeOfSport> typeOfSports = new HashSet<>();
		typeOfSports.add(TypeOfSport.Soccer);
		typeOfSports.add(TypeOfSport.Tennis);

		User user = new User();
		user.setTypeOfSports(typeOfSports);
		user = PersistenceFacade.getInstance().saveOrUpdate(user);

		assertEquals(2, user.getTypeOfSports().size());
		assertTrue(user.getTypeOfSports().contains(TypeOfSport.Soccer));
		assertTrue(user.getTypeOfSports().contains(TypeOfSport.Tennis));
	}

	/**
	 * Tests whether the default department id is null.
	 */
	@Test
	public void testDepartmentIdDefault() throws Exception {
		User user = new User();
		user = PersistenceFacade.getInstance().saveOrUpdate(user);

		assertEquals(null, user.getDepartmentId());
	}

	@Test
	public void testDepartmentId() throws Exception {
		Department department = new Department();
		department.setName("Test Department");
		department = PersistenceFacade.getInstance().saveOrUpdate(department);

		User user = new User();
		user.setDepartmentId(department.getId());
		user = PersistenceFacade.getInstance().saveOrUpdate(user);

		assertEquals(department.getId(), user.getDepartmentId());

		// delete the department id
		user.setDepartmentId(null);
		user = PersistenceFacade.getInstance().saveOrUpdate(user);

		assertEquals(null, user.getDepartmentId());
	}

	/**
	 * Tests whether the default LDAP UserID is null.
	 */
	@Test
	public void testLdapUIDDefault() throws Exception {
		User user = new User();
		user = PersistenceFacade.getInstance().saveOrUpdate(user);

		assertEquals(null, user.getLdapUID());
	}

	@Test
	public void testLdapUID() throws Exception {
		final String ldapUID = "tf-test";

		User user = new User();
		user.setLdapUID(ldapUID);
		user = PersistenceFacade.getInstance().saveOrUpdate(user);

		assertEquals(ldapUID, user.getLdapUID());

		user.setLdapUID(null);
		user = PersistenceFacade.getInstance().saveOrUpdate(user);

		assertEquals(null, user.getLdapUID());
	}
}
