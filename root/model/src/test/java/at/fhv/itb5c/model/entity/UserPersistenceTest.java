package at.fhv.itb5c.model.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import at.fhv.itb5c.commons.enums.Gender;
import at.fhv.itb5c.commons.enums.TypeOfSport;
import at.fhv.itb5c.model.PersistenceFacade;

public class UserPersistenceTest {
	private static final String DBFILE = "testdb.odb";

	@Before
	public void setUp() throws Exception {
		PersistenceFacade.setPersistenceUnitName(DBFILE);
	}

	@After
	public void tearDown() throws Exception {
		PersistenceFacade.shutdown();
		File file = new File(DBFILE);
		if (file.exists()) {
			file.delete();
		}
	}

	/**
	 * Tests dateOfBirth when saving a new user, retrieving, and updating an
	 * existing user.
	 * 
	 * @throws Exception
	 *             should not be thrown
	 */
	@Test
	public void testDateOfBirth() throws Exception {
		final LocalDate dateOfBirth = LocalDate.of(1990, 4, 2);
		User user = new User();
		user.setDateOfBirth(dateOfBirth);
		user = PersistenceFacade.getInstance().saveOrUpdate(user);

		assertEquals(dateOfBirth, user.getDateOfBirth());

		User user2 = PersistenceFacade.getInstance().getById(User.class, user.getId());
		assertEquals(dateOfBirth, user2.getDateOfBirth());

		final LocalDate dateOfBirthUpdated = LocalDate.of(2000, 5, 5);
		user.setDateOfBirth(dateOfBirthUpdated);
		user2 = PersistenceFacade.getInstance().saveOrUpdate(user);
		assertEquals(dateOfBirthUpdated, user2.getDateOfBirth());
	}

	/**
	 * Tests gender when saving a new user, retrieving, and updating an existing
	 * user.
	 * 
	 * @throws Exception
	 *             should not be thrown
	 */
	@Test
	public void testGender() throws Exception {
		final Gender gender = Gender.Male;
		User user = new User();
		user.setGender(gender);
		user = PersistenceFacade.getInstance().saveOrUpdate(user);

		assertEquals(gender, user.getGender());

		User user2 = PersistenceFacade.getInstance().getById(User.class, user.getId());
		assertEquals(gender, user2.getGender());

		final Gender genderUpdated = Gender.Female;
		user.setGender(genderUpdated);
		user2 = PersistenceFacade.getInstance().saveOrUpdate(user);
		assertEquals(genderUpdated, user2.getGender());
	}

	/**
	 * Tests typeOfSports when saving a new user, retrieving, and updating an
	 * existing user.
	 * 
	 * @throws Exception
	 *             should not be thrown
	 */
	@Test
	public void testTypeOfSports() throws Exception {
		Set<TypeOfSport> typeOfSports = new HashSet<>(Arrays.asList(TypeOfSport.Soccer, TypeOfSport.Tennis));
		User user = new User();
		user.setTypeOfSports(typeOfSports);
		user = PersistenceFacade.getInstance().saveOrUpdate(user);

		assertTrue(user.getTypeOfSports().size() == 2);
		assertTrue(user.getTypeOfSports().contains(TypeOfSport.Soccer));
		assertTrue(user.getTypeOfSports().contains(TypeOfSport.Tennis));

		User user2 = PersistenceFacade.getInstance().getById(User.class, user.getId());
		assertEquals(typeOfSports, user2.getTypeOfSports());

		Set<TypeOfSport> typeOfSports2 = new HashSet<>(Arrays.asList(TypeOfSport.Tennis));
		user.setTypeOfSports(typeOfSports2);
		user2 = PersistenceFacade.getInstance().saveOrUpdate(user);
		assertTrue(user.getTypeOfSports().size() == 1);
		assertTrue(user.getTypeOfSports().contains(TypeOfSport.Tennis));
	}

}
