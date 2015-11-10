package at.fhv.itb5c.model.entity;

import static org.junit.Assert.*;

import java.io.File;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import at.fhv.itb5c.commons.enums.Gender;
import at.fhv.itb5c.commons.enums.TypeOfSport;
import at.fhv.itb5c.commons.enums.UserRole;
import at.fhv.itb5c.model.PersistenceFacade;

public class UserTest {
	private static final String DBFILE = "testdb.odb";

	@Before
	public void setUp() throws Exception {
		PersistenceFacade.setPersistenceUnitName(DBFILE);
	}

	@After
	public void tearDown() throws Exception {
		PersistenceFacade.shutdown();
		File file = new File(DBFILE);
		if(file.exists()) {
			file.delete();
		}
	}

	@Test
	public void testFirstName() {
		final String firstName = "Robert";
		User user = new User();
		user.setFirstName(firstName);
		assertEquals(firstName, user.getFirstName());
	}

	@Test
	public void testLastName() {
		final String lastName = "Codd";
		User user = new User();
		user.setLastName(lastName);
		assertEquals(lastName, user.getLastName());
	}

	@Test
	public void testEmail() {
		final String emailValid1 = "robert.codd@hotmail.com";
		final String emailValid2 = "rd@students.fhv.at";
		final String emailValid3 = "r-d.421@1-2.de";
		
		final String emailValid4 = "äöüßÄÖÜ@äöüßÄÜß.at";

		final String emailInvalid1 = "robert";
		final String emailInvalid2 = "robert@.com";
		final String emailInvalid3 = "robert@gmail.a"; // last tld must contain
														// at least 2 characters
		final String emailInvalid4 = ".robert@gmail.com";
		final String emailInvalid5 = "robert()*@gamil.com"; // no ()*
		final String emailInvalid6 = "robert..codd@aol.com"; // no double dots
		final String emailInvalid7 = "robert.@aol.com"; // first part may not
														// end with dot
		final String emailInvalid8 = "robert@codd@gmail.com";
		final String emailInvalid9 = "robert@aol.1a"; // no numbers in tld
		
		User user = new User();

		assertTrue(user.setEmail(emailValid1));
		assertEquals(emailValid1, user.getEmail());

		assertTrue(user.setEmail(emailValid2));
		assertEquals(emailValid2, user.getEmail());

		assertTrue(user.setEmail(emailValid3));
		assertEquals(emailValid3, user.getEmail());
		
		assertTrue(user.setEmail(emailValid4));
		assertEquals(emailValid4, user.getEmail());

		assertFalse(user.setEmail(emailInvalid1));
		assertNotEquals(emailInvalid1, user.getEmail());

		assertFalse(user.setEmail(emailInvalid2));
		assertNotEquals(emailInvalid2, user.getEmail());

		assertFalse(user.setEmail(emailInvalid3));
		assertNotEquals(emailInvalid3, user.getEmail());

		assertFalse(user.setEmail(emailInvalid4));
		assertNotEquals(emailInvalid4, user.getEmail());

		assertFalse(user.setEmail(emailInvalid5));
		assertNotEquals(emailInvalid5, user.getEmail());

		assertFalse(user.setEmail(emailInvalid6));
		assertNotEquals(emailInvalid6, user.getEmail());

		assertFalse(user.setEmail(emailInvalid7));
		assertNotEquals(emailInvalid7, user.getEmail());

		assertFalse(user.setEmail(emailInvalid8));
		assertNotEquals(emailInvalid8, user.getEmail());

		assertFalse(user.setEmail(emailInvalid9));
		assertNotEquals(emailInvalid9, user.getEmail());

		// delete the email address from the user
		assertTrue(user.setEmail(null));
		assertEquals(null, user.getEmail());
	}

	@Test
	public void testTelephoneNumber() {
		final String telephoneNumber = "123456";
		User user = new User();
		user.setTelephoneNumber(telephoneNumber);
		assertEquals(telephoneNumber, user.getTelephoneNumber());
	}

	@Test
	public void testGender() {
		final Gender genderMale = Gender.Male;
		final Gender genderFemale = Gender.Female;

		User user = new User();
		user.setGender(genderMale);
		assertEquals(genderMale, user.getGender());

		user.setGender(genderFemale);
		assertEquals(genderFemale, user.getGender());
	}

	@Test
	public void testAddress() {
		final String address = "Road 17";
		User user = new User();
		user.setAddress(address);
		assertEquals(address, user.getAddress());
	}

	@Test
	public void testDateOfBirth() {
		final LocalDate dateOfBirth = LocalDate.of(1980, 5, 3);
		User user = new User();
		user.setDateOfBirth(dateOfBirth);
		assertEquals(dateOfBirth, user.getDateOfBirth());
	}

	@Test
	public void testMembershipFee() {
		final double membershipFee = 42;
		User user = new User();
		user.setMembershipFee(membershipFee);
		assertEquals(membershipFee, user.getMembershipFee(), 0.01d);
	}

	@Test
	public void testMembershipFeePaid() {
		final boolean membershipFeePaid = true;
		User user = new User();
		user.setMembershipFeePaid(membershipFeePaid);
		assertEquals(membershipFeePaid, user.getMembershipFeePaid());
	}

	@Test
	public void testRoles() {
		final Set<UserRole> roles = new HashSet<>();
		roles.add(UserRole.Admin);
		User user = new User();
		user.setRoles(roles);
		assertEquals(1, user.getRoles().size());
		assertTrue(user.getRoles().contains(UserRole.Admin));
	}

	@Test
	public void testTypeOfSports() {
		final Set<TypeOfSport> typeOfSports = new HashSet<>();
		typeOfSports.add(TypeOfSport.Soccer);
		typeOfSports.add(TypeOfSport.Tennis);
		User user = new User();
		user.setTypeOfSports(typeOfSports);
		assertEquals(2, user.getTypeOfSports().size());
		assertTrue(user.getTypeOfSports().contains(TypeOfSport.Soccer));
		assertTrue(user.getTypeOfSports().contains(TypeOfSport.Tennis));
	}

//	@Test
//	public void testDepartment() {
//		final Department department = new Department();
//		department.setName("Soccer 01");
//		User user = new User();
//		user.setDepartment(department);
//		assertEquals(department, user.getDepartment());
//	}

	@Test
	public void testSaveOrUpdate() {
		// save a new user
		User newUser = new User();
		newUser.setFirstName("Daniel");
		newUser.setLastName("Integration");
		newUser.setAddress("Teststraße 7a, 6800 Feldkirch");
		newUser.setDateOfBirth(LocalDate.now());
		newUser.setEmail("test@case.com");
		newUser.setGender(Gender.Male);
		newUser.setMembershipFee(15.9);
		Set<UserRole> roles = new HashSet<UserRole>();
		roles.add(UserRole.Admin);
		newUser.setRoles(roles);
		newUser.setTelephoneNumber("+43 664 874379");
		Set<TypeOfSport> sports = new HashSet<TypeOfSport>();
		sports.add(TypeOfSport.Soccer);
		newUser.setTypeOfSports(sports);
		newUser.setMembershipFeePaid(true);
		// Department dept = new Department();
		// dept.setName("Daniel's Dept");
		// newUser.setDepartment(dept);
		User returningUser = null;
		try {
			returningUser = PersistenceFacade.getInstance().saveOrUpdate(newUser);
		} catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}

		// check returned user
		assertNotNull(returningUser);
		assertEquals(newUser.getAddress(), returningUser.getAddress());
		assertEquals(newUser.getFirstName(), returningUser.getFirstName());
		assertEquals(newUser.getLastName(), returningUser.getLastName());
		assertEquals(newUser.getDateOfBirth(), returningUser.getDateOfBirth());
		assertEquals(newUser.getEmail(), returningUser.getEmail());
		assertEquals(newUser.getGender(), returningUser.getGender());
		assertEquals(Double.doubleToLongBits(newUser.getMembershipFee()),
				Double.doubleToLongBits(returningUser.getMembershipFee()));
		assertEquals(newUser.getRoles(), returningUser.getRoles());
		assertEquals(newUser.getTypeOfSports(), returningUser.getTypeOfSports());
		assertEquals(newUser.getMembershipFeePaid(), returningUser.getMembershipFeePaid());
//		assertEquals(newUser.getDepartment(), returningUser.getDepartment());

		// update the user
		newUser.setFirstName("Daniel2");
		newUser.setLastName("Integration2");
		newUser.setAddress("Teststraße 7a, 6800 Feldkirch2");
		newUser.setDateOfBirth(LocalDate.now());
		newUser.setEmail("test2@case.com");
		newUser.setGender(Gender.Male);
		newUser.setMembershipFee(15.9);
		roles.clear();
		newUser.setRoles(roles);
		newUser.setTelephoneNumber("+43 664 8743792");
		sports.clear();
		sports.add(TypeOfSport.Tennis);
		newUser.setTypeOfSports(sports);
		newUser.setMembershipFeePaid(false);
		// Department dept = new Department();
		// dept.setName("Daniel's Dept");
		// newUser.setDepartment(dept);
		try {
			returningUser = PersistenceFacade.getInstance().saveOrUpdate(newUser);
		} catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}

		// check returned user
		assertNotNull(returningUser);
		assertEquals(newUser.getAddress(), returningUser.getAddress());
		assertEquals(newUser.getFirstName(), returningUser.getFirstName());
		assertEquals(newUser.getLastName(), returningUser.getLastName());
		assertEquals(newUser.getDateOfBirth(), returningUser.getDateOfBirth());
		assertEquals(newUser.getEmail(), returningUser.getEmail());
		assertEquals(newUser.getGender(), returningUser.getGender());
		assertEquals(Double.doubleToLongBits(newUser.getMembershipFee()),
				Double.doubleToLongBits(returningUser.getMembershipFee()));
		assertEquals(newUser.getRoles(), returningUser.getRoles());
		assertEquals(newUser.getTypeOfSports(), returningUser.getTypeOfSports());
		assertEquals(newUser.getMembershipFeePaid(), returningUser.getMembershipFeePaid());
//		assertEquals(newUser.getDepartment(), returningUser.getDepartment());
	}
}
