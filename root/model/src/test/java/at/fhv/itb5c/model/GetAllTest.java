package at.fhv.itb5c.model;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import at.fhv.itb5c.model.entity.Department;
import at.fhv.itb5c.model.entity.PersistableObject;
import at.fhv.itb5c.model.entity.Team;
import at.fhv.itb5c.model.entity.User;

public class GetAllTest {
	private static final String DBFILE = "testdb.odb";

	@Before
	public void beforeEach() throws Exception {
		PersistenceFacade.shutdown();
		DatabaseTestUtility.deleteDatabaseFile(DBFILE);
		PersistenceFacade.setPersistenceUnitName(DBFILE);

		User user1 = new User();
		user1.setFirstName("Fabian");
		user1.setLastName("Salzgeber");
		PersistenceFacade.getInstance().saveOrUpdate(user1);

		User user2 = new User();
		user2.setFirstName("Fritz");
		user2.setLastName("Huber");
		PersistenceFacade.getInstance().saveOrUpdate(user2);

		Department department1 = new Department();
		department1.setName("Polo");
		PersistenceFacade.getInstance().saveOrUpdate(department1);

		Department department2 = new Department();
		department2.setName("Soccer");
		PersistenceFacade.getInstance().saveOrUpdate(department2);

		Department department3 = new Department();
		department3.setName("Golf");
		PersistenceFacade.getInstance().saveOrUpdate(department3);

		Team team1 = new Team();
		team1.setName("Good Team");
		team1.setDepartmentId(145l);
		PersistenceFacade.getInstance().saveOrUpdate(team1);

		Team team2 = new Team();
		team2.setName("Better Team");
		team2.setDepartmentId(146l);
		PersistenceFacade.getInstance().saveOrUpdate(team2);
	}

	@Test
	public void getAllUsers() {
		List<User> allUsers = PersistenceFacade.getInstance().getAll(User.class);
		assertEquals(allUsers.size(), 2);
	}

	@Test
	public void getAllDepartments() {
		List<Department> allDepartments = PersistenceFacade.getInstance().getAll(Department.class);
		assertEquals(allDepartments.size(), 3);
	}

	@Test
	public void getAllTeams() {
		List<Team> allTeams = PersistenceFacade.getInstance().getAll(Team.class);
		assertEquals(allTeams.size(), 2);
	}

	@Test
	public void getAllPersistableObjects() {
		List<PersistableObject> allPersistableObjects = PersistenceFacade.getInstance().getAll(PersistableObject.class);
		assertEquals(allPersistableObjects.size(), 7);
	}

	@Test
	public void getAllObjects() {
		List<Object> allObjects = PersistenceFacade.getInstance().getAll(Object.class);
		assertEquals(allObjects.size(), 7);
	}
}
