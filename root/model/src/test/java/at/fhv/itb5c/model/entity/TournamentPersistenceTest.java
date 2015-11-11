package at.fhv.itb5c.model.entity;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import at.fhv.itb5c.commons.enums.TypeOfSport;
import at.fhv.itb5c.model.PersistenceFacade;

public class TournamentPersistenceTest {
	private Tournament _tournament;
	private Tournament _tournamentPersist;

	@Before
	public void setUp() throws Exception {
		_tournament = new Tournament();
		_tournament.setName("Tournament");
		_tournament.setDate(LocalDate.now().plusMonths(2));
		_tournament.setFee(200.5d);
		Team homeTeam = new Team();
		homeTeam.setName("Team One");
		homeTeam.setTypeOfSport(TypeOfSport.Soccer);
		PersistenceFacade.getInstance().saveOrUpdate(homeTeam);
		_tournament.setHomeTeamsIds(new HashSet<>(Arrays.asList(homeTeam.getId())));
		Set<String> guestTeams = new LinkedHashSet<String>();
		String guestTeam = "Team Two";
		guestTeams.add(guestTeam);
		_tournament.setGuestTeams(guestTeams );
		Set<Long> matches = new LinkedHashSet<Long>();
		Match match = new Match();
		match.setTeamOne(homeTeam);
		match.setTeamTwo(guestTeam);
		match.setStartDate(LocalDate.now().plusMonths(2));
		PersistenceFacade.getInstance().saveOrUpdate(match);
		matches.add(match.getId());
		_tournament.setMatchesIds(matches);
		PersistenceFacade.getInstance().saveOrUpdate(homeTeam);
		_tournamentPersist = PersistenceFacade.getInstance().saveOrUpdate(_tournament);
	}

	@After
	public void tearDown() throws Exception {
		PersistenceFacade.getInstance().delete(_tournamentPersist);
	}

	@Test
	public void test() {
		assertNotNull(_tournamentPersist.getId());
		assertEquals(_tournament.getName(), _tournamentPersist.getName());
		assertEquals(_tournament.getDate(), _tournamentPersist.getDate());
		assertEquals(_tournament.getFee(), _tournamentPersist.getFee(), 0.001d);
		assertEquals(_tournament.getHomeTeamsIds(), _tournamentPersist.getHomeTeamsIds());
		assertEquals(_tournament.getGuestTeams(), _tournamentPersist.getGuestTeams());
		assertEquals(_tournament.getMatchesIds(), _tournamentPersist.getMatchesIds());
	}

}
