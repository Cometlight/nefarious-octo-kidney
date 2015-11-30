package at.fhv.itb5c.model;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import at.fhv.itb5c.model.entity.Department;
import at.fhv.itb5c.model.entity.User;

public class FindUsersTest {
	private static final String DBFILE = "testdb.odb";

	@Before
	public void beforeEach() throws Exception {
		PersistenceFacade.shutdown();
		DatabaseTestUtility.deleteDatabaseFile(DBFILE);
		PersistenceFacade.setPersistenceUnitName(DBFILE);
		Department tennis = this.addDepartment("Tennis", 5l);
		Department soccer = this.addDepartment("Soccer", 8l);
		this.addUser("Max", "Mustermann", tennis.getId(), true);
		this.addUser("Maximilian", "Müller", soccer.getId(), true);
		this.addUser("MAXIMA", "~~Üli", null, true);
		this.addUser("Markus", "Susmann", tennis.getId(), false);
		this.addUser("MaXimo", "Maxer", tennis.getId(), false);
	}

	private User addUser(String firstName, String lastName, Long departmentId, boolean membershipFeePaid)
			throws Exception {
		User user = new User();
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setDepartmentId(departmentId);
		user.setMembershipFeePaid(membershipFeePaid);
		return PersistenceFacade.getInstance().saveOrUpdate(user);
	}

	private Department addDepartment(String name, Long id) throws Exception {
		Department dept = new Department();
		dept.setName(name);
		dept.setId(id);
		return PersistenceFacade.getInstance().saveOrUpdate(dept);
	}

	@Test
	public void testNameCaseInsensitivity() {
		List<User> result = PersistenceFacade.getInstance().findUsers("Max", null, null, null);
		assertEquals(4, result.size());
	}

	@Test
	public void testNameUnicode() {
		List<User> result = PersistenceFacade.getInstance().findUsers(null, "ül", null, null);
		assertEquals(2, result.size());
	}

	@Test
	public void testFirstNameLastName() {
		List<User> result = PersistenceFacade.getInstance().findUsers("Max", "ül", null, null);
		assertEquals(2, result.size());
	}

	@Test
	public void testDepartment() {
		List<User> result = PersistenceFacade.getInstance().findUsers(null, null, 5l, null);
		assertEquals(3, result.size());
	}

	@Test
	public void testDepartmentFirstName() {
		List<User> result = PersistenceFacade.getInstance().findUsers("Max", null, 5l, null);
		assertEquals(2, result.size());
	}

	@Test
	public void testDepartmentLastName() {
		List<User> result = PersistenceFacade.getInstance().findUsers(null, "üller", 8l, null);
		assertEquals(1, result.size());
	}

	@Test
	public void testDepartmentFirstNameLastName() {
		List<User> result = PersistenceFacade.getInstance().findUsers("Max", "er", 5l, null);
		assertEquals(2, result.size());
	}

	@Test
	public void testDepartmentFirstNameLastNameMembershipFeePaid() {
		List<User> result = PersistenceFacade.getInstance().findUsers("Max", "er", 5l, true);
		assertEquals(1, result.size());
	}

	@Test
	public void testMemebershipFeePaid() {
		List<User> result = PersistenceFacade.getInstance().findUsers(null, null, null, true);
		assertEquals(3, result.size());
	}

	@Test
	public void testMemebershipFeeNotPaid() {
		List<User> result = PersistenceFacade.getInstance().findUsers(null, null, null, false);
		assertEquals(2, result.size());
	}

}
