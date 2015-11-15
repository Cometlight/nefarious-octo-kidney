package at.fhv.itb5c.application.dto;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;

import org.junit.Test;

public class MatchDTOTest {

	@Test
	public void testDefaultTeamOne() {
		MatchDTO match = new MatchDTO();
		assertNull(match.getTeamOne());
	}

	@Test
	public void testDefaultTeamTwo() {
		MatchDTO match = new MatchDTO();
		assertNull(match.getTeamTwo());
	}

	@Test
	public void testDefaultStartDate() {
		MatchDTO match = new MatchDTO();
		assertNull(match.getStartDate());
	}

	@Test
	public void testDefaultResultTeamOne() {
		MatchDTO match = new MatchDTO();
		assertNull(match.getResultTeamOne());
	}

	@Test
	public void testDefaultResultTeamTwo() {
		MatchDTO match = new MatchDTO();
		assertNull(match.getResultTeamTwo());
	}

	@Test
	public void testTeamOneString() {
		final String teamName = "External Team 1";
		MatchDTO match = new MatchDTO();
		match.setTeamOne(teamName);
		assertEquals(teamName, match.getTeamOne());
	}

	@Test
	public void testTeamOneId() {
		final Long teamId = 4711l;
		MatchDTO match = new MatchDTO();
		match.setTeamOne(teamId);
		assertEquals(teamId, match.getTeamOne());
	}

	@Test
	public void testTeamTwoString() {
		final String teamName = "External Team 2";
		MatchDTO match = new MatchDTO();
		match.setTeamTwo(teamName);
		assertEquals(teamName, match.getTeamTwo());
	}

	@Test
	public void testTeamTwoId() {
		final Long teamId = 4711l;
		MatchDTO match = new MatchDTO();
		match.setTeamTwo(teamId);
		assertEquals(teamId, match.getTeamTwo());
	}

	@Test
	public void testStartDate() {
		final LocalDateTime startDate = LocalDateTime.now();
		MatchDTO match = new MatchDTO();
		match.setStartDate(startDate);
		assertTrue(startDate.equals(match.getStartDate()));
	}

	@Test
	public void testResultTeamOne() {
		final Integer result = 12;
		MatchDTO match = new MatchDTO();
		match.setResultTeamOne(result);
		assertEquals(result, match.getResultTeamOne());
	}

	@Test
	public void testResultTeamTwo() {
		final Integer result = 9;
		MatchDTO match = new MatchDTO();
		match.setResultTeamTwo(result);
		assertEquals(result, match.getResultTeamTwo());
	}

}
