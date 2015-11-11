package at.fhv.itb5c.model.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import at.fhv.itb5c.commons.enums.TypeOfSport;
import at.fhv.itb5c.model.PersistenceFacade;

public class TeamPersistenceTest {
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

	@Test
	public void testMembers() throws Exception {
		User user1 = new User();
		user1.setFirstName("TeamPersistenceTest - User1");
		user1 = PersistenceFacade.getInstance().saveOrUpdate(user1);
		User user2 = new User();
		user2.setFirstName("TeamPersistenceTest - User2");
		user2 = PersistenceFacade.getInstance().saveOrUpdate(user2);
		user1 = PersistenceFacade.getInstance().saveOrUpdate(user1);
		user2 = PersistenceFacade.getInstance().saveOrUpdate(user2);
		final Set<Long> members = new HashSet<>(Arrays.asList(user1.getId(), user2.getId()));
		Team team = new Team();
		team.setMembers(members);
		team = PersistenceFacade.getInstance().saveOrUpdate(team);

		assertEquals(members.size(), team.getMembers().size());
		assertTrue(team.getMembers().contains(user1.getId()));
		assertTrue(team.getMembers().contains(user2.getId()));

		Team team2 = PersistenceFacade.getInstance().getById(Team.class, team.getId());
		assertEquals(members.size(), team2.getMembers().size());
		assertTrue(team2.getMembers().contains(user1.getId()));
		assertTrue(team2.getMembers().contains(user2.getId()));

		final Set<Long> membersUpdated = new HashSet<>(Arrays.asList(user2.getId()));
		team.setMembers(membersUpdated);
		team2 = PersistenceFacade.getInstance().saveOrUpdate(team);
		assertEquals(membersUpdated.size(), team2.getMembers().size());
		assertFalse(team2.getMembers().contains(user1.getId()));
		assertTrue(team2.getMembers().contains(user2.getId()));
	}

	@Test
	public void testName() throws Exception {
		final String name = "Team C";
		Team team = new Team();
		team.setName(name);
		team = PersistenceFacade.getInstance().saveOrUpdate(team);

		assertEquals(name, team.getName());

		Team team2 = PersistenceFacade.getInstance().getById(Team.class, team.getId());
		assertEquals(name, team2.getName());

		final String nameUpdated = "Team No Name";
		team.setName(nameUpdated);
		team2 = PersistenceFacade.getInstance().saveOrUpdate(team);
		assertEquals(nameUpdated, team2.getName());
	}

	@Test
	public void testTypeOfSport() throws Exception {
		final TypeOfSport typeOfSport = TypeOfSport.Tennis;
		Team team = new Team();
		team.setTypeOfSport(typeOfSport);
		team = PersistenceFacade.getInstance().saveOrUpdate(team);

		assertEquals(typeOfSport, team.getTypeOfSport());

		Team team2 = PersistenceFacade.getInstance().getById(Team.class, team.getId());
		assertEquals(typeOfSport, team2.getTypeOfSport());

		final TypeOfSport typeOfSportUpdated = TypeOfSport.Soccer;
		team.setTypeOfSport(typeOfSportUpdated);
		team2 = PersistenceFacade.getInstance().saveOrUpdate(team);
		assertEquals(typeOfSportUpdated, team2.getTypeOfSport());
	}

}
