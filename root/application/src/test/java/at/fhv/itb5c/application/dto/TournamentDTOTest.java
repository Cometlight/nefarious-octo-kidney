package at.fhv.itb5c.application.dto;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class TournamentDTOTest {

	@Test
	public void testDefaultName() {
		TournamentDTO tournament = new TournamentDTO();
		assertNull(tournament.getName());
	}

	@Test
	public void testDefaultFee() {
		TournamentDTO tournament = new TournamentDTO();
		assertNull(tournament.getFee());
	}

	@Test
	public void testDefaultHomeTeamIds() {
		TournamentDTO tournament = new TournamentDTO();
		assertNull(tournament.getHomeTeamsIds());
	}

	@Test
	public void testDefaultGuestTeamIds() {
		TournamentDTO tournament = new TournamentDTO();
		assertNull(tournament.getGuestTeams());
	}

	@Test
	public void testDefaultMatchesIds() {
		TournamentDTO tournament = new TournamentDTO();
		assertNull(tournament.getMatchesIds());
	}

	@Test
	public void testDefaultDate() {
		TournamentDTO tournament = new TournamentDTO();
		assertNull(tournament.getDate());
	}

	@Test
	public void testDefaultDepartmentId() {
		TournamentDTO tournament = new TournamentDTO();
		assertNull(tournament.getDepartmentId());
	}

	@Test
	public void testName() {
		final String name = "Tournament";
		TournamentDTO tournament = new TournamentDTO();
		tournament.setName(name);
		assertNotNull(tournament.getName());
		assertTrue(tournament.getName().equals(name));
	}

	@Test
	public void testFee() {
		final Double fee = 23.99;
		TournamentDTO tournament = new TournamentDTO();
		tournament.setFee(fee);
		assertEquals(fee, tournament.getFee());
	}

	@Test
	public void testHomeTeamIds() {
		Long[] homeTeamIds = new Long[]{42l, 87l};
		final Set<Long> homeTeams = new HashSet<>(Arrays.asList(homeTeamIds));
		TournamentDTO tournament = new TournamentDTO();
		tournament.setHomeTeamsIds(homeTeams);
		assertNotNull(tournament.getHomeTeamsIds());
		for (int i = 0; i < homeTeamIds.length; i++) {
			assertTrue(tournament.getHomeTeamsIds().contains(homeTeamIds[i]));
		}
	}

	@Test
	public void testGuestTeamIds() {
		String[] guestTeamStrings = new String[]{"Team A", "Team B"};
		final Set<String> guestTeams = new HashSet<>(Arrays.asList(guestTeamStrings));
		TournamentDTO tournament = new TournamentDTO();
		tournament.setGuestTeams(guestTeams);
		assertNotNull(tournament.getGuestTeams());
		for (int i = 0; i < guestTeamStrings.length; i++) {
			assertTrue(tournament.getGuestTeams().contains(guestTeamStrings[i]));
		}
	}

	@Test
	public void testMatchIds() {
		Long[] matchIds = new Long[]{4711l, 8765l, 1234l};
		final Set<Long> matches = new HashSet<>(Arrays.asList(matchIds));
		TournamentDTO tournament = new TournamentDTO();
		tournament.setMatchesIds(matches);
		assertNotNull(tournament.getMatchesIds());
		for (int i = 0; i < matchIds.length; i++) {
			assertTrue(tournament.getMatchesIds().contains(matchIds[i]));
		}
	}

	@Test
	public void testDate() {
		LocalDate date = LocalDate.now();
		TournamentDTO tournament = new TournamentDTO();
		tournament.setDate(date);
		assertNotNull(tournament.getDate());
		assertTrue(tournament.getDate().equals(date));
	}

	@Test
	public void testDepartmentId() {
		Long id = 42l;
		TournamentDTO tournament = new TournamentDTO();
		tournament.setDepartmentId(id);
		assertEquals(id, tournament.getDepartmentId());
	}

}
