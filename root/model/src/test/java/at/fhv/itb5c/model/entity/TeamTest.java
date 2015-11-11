package at.fhv.itb5c.model.entity;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import at.fhv.itb5c.commons.enums.TypeOfSport;
import at.fhv.itb5c.model.DatabaseTestUtility;
import at.fhv.itb5c.model.PersistenceFacade;

public class TeamTest {
	private static final String DBFILE = "testdb.odb";

	@Before
	public void beforeEach() throws Exception {
		DatabaseTestUtility.deleteDatabaseFile(DBFILE);
		PersistenceFacade.setPersistenceUnitName(DBFILE);
	}

	@Test
	public void testMembers() throws Exception {
		User user1 = new User();
		user1.setFirstName("TeamTest - User1");
		user1 = PersistenceFacade.getInstance().saveOrUpdate(user1);
		
		User user2 = new User();
		user2.setFirstName("TeamTest - User2");
		user2 = PersistenceFacade.getInstance().saveOrUpdate(user2);
		
		Set<Long> members = new HashSet<>(Arrays.asList(user1.getId(), user2.getId()));

		Team team = new Team();
		team.setMemberIds(members);
		team = PersistenceFacade.getInstance().saveOrUpdate(team);

		assertEquals(members, team.getMemberIds());
	}

	@Test
	public void testName() throws Exception {
		final String name = "Clearly Awesome Team";
		
		Team team = new Team();
		team.setName(name);
		team = PersistenceFacade.getInstance().saveOrUpdate(team);

		assertEquals(name, team.getName());
	}

	@Test
	public void testTypeOfSport() throws Exception {
		final TypeOfSport typeOfSport = TypeOfSport.Soccer;

		Team team = new Team();
		team.setTypeOfSport(typeOfSport);
		team = PersistenceFacade.getInstance().saveOrUpdate(team);

		assertEquals(typeOfSport, team.getTypeOfSport());
	}

	@Test
	public void testDepartment() throws Exception {
		Department department = new Department();
		department.setName("Test Department");
		PersistenceFacade.getInstance().saveOrUpdate(department);

		Team team = new Team();
		team.setDepartmentId(department.getId());
		team = PersistenceFacade.getInstance().saveOrUpdate(team);

		assertEquals(department.getId(), team.getDepartmentId());
	}

	@Test
	public void testCoach() throws Exception {
		User coach = new User();
		coach = PersistenceFacade.getInstance().saveOrUpdate(coach);

		Team team = new Team();
		team.setCoachId(coach.getId());
		team = PersistenceFacade.getInstance().saveOrUpdate(team);

		assertEquals(coach.getId(), team.getCoachId());
	}

	@Test
	public void testLeague() throws Exception {
		League league = new League();
		league.setName("Test League");
		league = PersistenceFacade.getInstance().saveOrUpdate(league);

		Team team = new Team();
		team.setLeagueId(league.getId());
		team = PersistenceFacade.getInstance().saveOrUpdate(team);

		assertEquals(league.getId(), team.getLeagueId());
	}

}
