package at.fhv.itb5c.application.dto;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import at.fhv.itb5c.commons.enums.TypeOfSport;

public class TeamDTOTest {

	@Test
	public void testDefaultName() {
		TeamDTO team = new TeamDTO();
		assertNull(team.getName());
	}

	@Test
	public void testDefaultTypeOfSport() {
		TeamDTO team = new TeamDTO();
		assertNull(team.getTypeOfSport());
	}

	@Test
	public void testDefaultDepartmentId() {
		TeamDTO team = new TeamDTO();
		assertNull(team.getDepartmentId());
	}

	@Test
	public void testDefaultCoachId() {
		TeamDTO team = new TeamDTO();
		assertNull(team.getCoachId());
	}

	@Test
	public void testDefaultLeagueId() {
		TeamDTO team = new TeamDTO();
		assertNull(team.getLeagueId());
	}

	@Test
	public void testDefaultMemberIds() {
		TeamDTO team = new TeamDTO();
		assertNull(team.getMemberIds());
	}

	@Test
	public void testName() {
		final String name = "Team Name";
		TeamDTO team = new TeamDTO();
		team.setName(name);
		assertNotNull(team.getName());
		assertTrue(team.getName().equals(name));
	}

	@Test
	public void testTypeOfSport() {
		final TypeOfSport typeOfSport = TypeOfSport.Tennis;
		TeamDTO team = new TeamDTO();
		team.setTypeOfSport(typeOfSport);
		assertEquals(typeOfSport, team.getTypeOfSport());
	}

	@Test
	public void testDepartmentId() {
		final Long departmentId = 42l;
		TeamDTO team = new TeamDTO();
		team.setDepartmentId(departmentId);
		assertEquals(departmentId, team.getDepartmentId());
	}

	@Test
	public void testCoachId() {
		final Long coachId = 98l;
		TeamDTO team = new TeamDTO();
		team.setCoachId(coachId);
		assertEquals(coachId, team.getCoachId());
	}

	@Test
	public void testLeagueId() {
		final Long leagueId = 23l;
		TeamDTO team = new TeamDTO();
		team.setLeagueId(leagueId);
		assertEquals(leagueId, team.getLeagueId());
	}

}
