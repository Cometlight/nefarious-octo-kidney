package at.fhv.itb5c.model.entity;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import at.fhv.itb5c.commons.enums.TypeOfSport;

public class TeamTest {

	@Test
	public void testMembers() {
		Team team = new Team();
		User user1 = new User();
		user1.setFirstName("TeamTest - User1");
		User user2 = new User();
		user2.setFirstName("TeamTest - User2");
		Set<User> members = new HashSet<>(Arrays.asList(user1, user2));
		
		team.setMembers(members);
		
		assertEquals(members, team.getMembers());
	}

	@Test
	public void testName() {
		Team team = new Team();
		final String name = "Clearly Awesome Team";
		
		team.setName(name);
		
		assertEquals(name, team.getName());
	}

	@Test
	public void testTypeOfSport() {
		Team team = new Team();
		final TypeOfSport typeOfSport = TypeOfSport.Soccer;
		
		team.setTypeOfSport(typeOfSport);
		
		assertEquals(typeOfSport, team.getTypeOfSport());
	}

	@Test
	public void testDepartment() {
		Team team = new Team();
		final Department department = new Department();
		department.setName("TeamTest - Department1");
		
		team.setDepartment(department);
		
		assertEquals(department, team.getDepartment());
	}

	@Test
	public void testCoach() {
		Team team = new Team();
		final User coach = new User();
		coach.setFirstName("TeamTest - Coach1");
		
		team.setCoach(coach);
		
		assertEquals(coach, team.getCoach());
	}

	@Test
	public void testLeague() {
		Team team = new Team();
		final int league = 42;
		
		team.setLeague(league);
		
		assertEquals(league, team.getLeague());
	}

}
