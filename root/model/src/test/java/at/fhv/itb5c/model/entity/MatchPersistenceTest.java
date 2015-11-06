package at.fhv.itb5c.model.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.time.LocalDate;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import at.fhv.itb5c.commons.enums.TypeOfSport;
import at.fhv.itb5c.model.PersistenceFacade;

public class MatchPersistenceTest {
	private Match _match;
	private Match _matchPersist;

	@Before
	public void setUp() throws Exception {
		_match = new Match();
		Team teamOne = new Team();
		teamOne.setName("Team One");
		// coach is not mandatory
		/*User coach = new User();
		coach.setFirstName("Coach");
		coach.setLastName("Boss");
		coach.setGender(Gender.Male);
		coach.setEmail("coach@boss.in");
		coach.setAddress("Address Address 45, 5679jf Address");
		coach.setTelephoneNumber("+43 7684 4638959");
		coach.setDateOfBirth(LocalDate.now().minusYears(46));
		LinkedHashSet<UserRole> roles = new LinkedHashSet<UserRole>();
		roles.add(UserRole.StandardUser);
		coach.setRoles(roles );
		teamOne.setCoach(coach );*/
		teamOne.setTypeOfSport(TypeOfSport.Soccer);
		_match.setTeamOne(teamOne);
		Team teamTwo = new Team();
		teamTwo.setName("Team Two");
		teamTwo.setTypeOfSport(TypeOfSport.Soccer);
		_match.setTeamTwo(teamTwo);
		_match.setStartDate(LocalDate.now().plusMonths(2));
		_match.setResultTeamOne(5);
		_match.setResultTeamTwo(7);
		PersistenceFacade.getInstance().saveOrUpdate(teamOne);
		PersistenceFacade.getInstance().saveOrUpdate(teamTwo);
		_matchPersist = PersistenceFacade.getInstance().saveOrUpdate(_match);
	}

	@After
	public void tearDown() throws Exception {
		PersistenceFacade.getInstance().delete(_match);
	}

	@Test
	public void test() {
		assertNotNull(_matchPersist.getId());
		assertEquals(_matchPersist.getStartDate(), _match.getStartDate());
		assertEquals(_matchPersist.getTeamOne(), _match.getTeamOne());
		assertEquals(_matchPersist.getTeamTwo(), _match.getTeamTwo());
		assertEquals(_matchPersist.getResultTeamOne(), _match.getResultTeamOne());
		assertEquals(_matchPersist.getResultTeamTwo(), _match.getResultTeamTwo());
	}

}
