package at.fhv.itb5c.ws;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashSet;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import at.fhv.itb5c.commons.enums.Gender;
import at.fhv.itb5c.commons.enums.TypeOfSport;
import at.fhv.itb5c.commons.enums.UserRole;
import at.fhv.itb5c.model.PersistenceFacade;
import at.fhv.itb5c.model.entity.Department;
import at.fhv.itb5c.model.entity.League;
import at.fhv.itb5c.model.entity.Match;
import at.fhv.itb5c.model.entity.Team;
import at.fhv.itb5c.model.entity.Tournament;
import at.fhv.itb5c.model.entity.User;

public class TournamentWSTest {

	private Tournament _tournament1;
	private Department _deptSoccer;
	private Team _teamSoccer1;
	private Team _teamSoccer2;
	private User _userDS;
	private User _userDG;
	private User _userSA;
	private Department _deptTennis;
	private League _leagueSoccer;

	@Before
	public void setUp() throws Exception {
		_deptSoccer = new Department();
		_deptSoccer.setName("Soccer");
		_deptSoccer.setTypeOfSport(TypeOfSport.Soccer);
		_deptSoccer = PersistenceFacade.getInstance().saveOrUpdate(_deptSoccer);

		_deptTennis = new Department();
		_deptTennis.setName("Tennis");
		_deptTennis.setTypeOfSport(TypeOfSport.Tennis);
		_deptTennis = PersistenceFacade.getInstance().saveOrUpdate(_deptTennis);

		_leagueSoccer = new League();
		_leagueSoccer.setName("Soccer League");
		_leagueSoccer.setTypeOfSport(TypeOfSport.Soccer);
		_leagueSoccer = PersistenceFacade.getInstance().saveOrUpdate(_leagueSoccer);

		_userDS = new User();
		_userDS.setFirstName("Daniel");
		_userDS.setLastName("Scheffknecht");
		_userDS.setAddress("Hasenfeldstraße 7a, A - 6890 Lustenau");
		_userDS.setDateOfBirth(LocalDate.now().minusYears(22));
		_userDS.setDepartmentId(_deptSoccer.getId());
		_userDS.setEmail("daniel.scheffknecht@gmx.at");
		_userDS.setGender(Gender.Male);
		_userDS.setMembershipFee(20.7d);
		_userDS.setMembershipFeePaid(false);
		_userDS.setTelephoneNumber("0043 664 8268464");
		_userDS.setTypeOfSports(new HashSet<>(Arrays.asList(TypeOfSport.Soccer)));
		_userDS.setRoles(new HashSet<>(Arrays.asList(UserRole.Admin)));
		_userDS.setLdapUID("dsc2253");
		_userDS = PersistenceFacade.getInstance().saveOrUpdate(_userDS);

		_userDG = new User();
		_userDG.setFirstName("Daniel");
		_userDG.setLastName("Grießer");
		_userDG.setLdapUID("dgr7348");
		_userDG.setAddress("Idiot's Avenue");
		_userDG.setDateOfBirth(LocalDate.now().minusYears(27));
		_userDS.setDepartmentId(_deptSoccer.getId());
		_userDG.setEmail("daniel.griesser@dumm.du");
		_userDG.setGender(Gender.Male);
		_userDG.setMembershipFee(6.9d);
		_userDG.setMembershipFeePaid(true);
		_userDG.setTelephoneNumber("+43 (0) I DONT KNOW");
		_userDG.setTypeOfSports(new HashSet<>(Arrays.asList(TypeOfSport.Soccer)));
		_userDG.setRoles(new HashSet<>(Arrays.asList(UserRole.Admin)));
		_userDG = PersistenceFacade.getInstance().saveOrUpdate(_userDG);

		_userSA = new User();
		_userSA.setFirstName("Simon");
		_userSA.setLastName("Angerer");
		_userSA.setLdapUID("san7985");
		_userSA.setAddress("----");
		_userSA.setDateOfBirth(LocalDate.now().minusYears(25));
		_userDS.setDepartmentId(_deptTennis.getId());
		_userSA.setEmail("simone.a@gmx.net");
		_userSA.setGender(Gender.Male);
		_userSA.setMembershipFeePaid(true);
		_userSA.setTelephoneNumber("06648268464");
		_userSA.setTypeOfSports(new HashSet<>(Arrays.asList(TypeOfSport.Tennis)));
		_userSA.setRoles(new HashSet<>(Arrays.asList(UserRole.Admin)));
		_userSA = PersistenceFacade.getInstance().saveOrUpdate(_userSA);

		_deptSoccer.setHeadId(_userDS.getId());
		_deptTennis.setHeadId(_userSA.getId());
		PersistenceFacade.getInstance().saveOrUpdate(_deptSoccer);
		PersistenceFacade.getInstance().saveOrUpdate(_deptTennis);

		_teamSoccer1 = new Team();
		_teamSoccer1.setName("Team One Soccer");
		_teamSoccer1.setTypeOfSport(TypeOfSport.Soccer);
		_teamSoccer1.setDepartmentId(_deptSoccer.getId());
		_teamSoccer1.setCoachId(_userDS.getId());
		_teamSoccer1.setMemberIds(new HashSet<>(Arrays.asList(_userDS.getId(), _userDG.getId(), _userSA.getId())));
		_teamSoccer1.setLeagueId(_leagueSoccer.getId());
		_teamSoccer1 = PersistenceFacade.getInstance().saveOrUpdate(_teamSoccer1);

		_teamSoccer2 = new Team();
		_teamSoccer2.setName("Team Two Soccer");
		_teamSoccer2.setTypeOfSport(TypeOfSport.Soccer);
		_teamSoccer2.setDepartmentId(_deptSoccer.getId());
		_teamSoccer2.setCoachId(_userDS.getId());
		_teamSoccer2.setMemberIds(new HashSet<>(Arrays.asList(_userDS.getId(), _userDG.getId(), _userSA.getId())));
		_teamSoccer2.setLeagueId(_leagueSoccer.getId());
		_teamSoccer2 = PersistenceFacade.getInstance().saveOrUpdate(_teamSoccer2);

		// two external teams in the past with a result
		Match match1Tournament1 = new Match();
		match1Tournament1.setTeamOne("A3 Bregenz");
		match1Tournament1.setTeamTwo("A2 Dornbirn");
		match1Tournament1.setStartDate(LocalDateTime.now().minusDays(2));
		match1Tournament1.setResultTeamOne(4);
		match1Tournament1.setResultTeamTwo(2);
		match1Tournament1 = PersistenceFacade.getInstance().saveOrUpdate(match1Tournament1);

		// two local team
		Match match2Tournament1 = new Match();
		match2Tournament1.setTeamOne(_teamSoccer1.getId());
		match2Tournament1.setTeamTwo(_teamSoccer2.getId());
		match2Tournament1.setStartDate(LocalDateTime.now().plusDays(10));
		match2Tournament1 = PersistenceFacade.getInstance().saveOrUpdate(match2Tournament1);

		// one local one external
		Match match3Tournament1 = new Match();
		match3Tournament1.setTeamOne(_teamSoccer1.getId());
		match3Tournament1.setTeamTwo("A3 Bregenz");
		match3Tournament1.setStartDate(LocalDateTime.now().plusDays(11));
		match3Tournament1 = PersistenceFacade.getInstance().saveOrUpdate(match3Tournament1);

		Tournament _tournament1 = new Tournament();
		_tournament1.setName("Local Tournament");
		_tournament1.setDepartmentId(_deptSoccer.getId());
		_tournament1.setDate(LocalDate.now());
		_tournament1.setFee(new Double(10.50));
		_tournament1.setMatchesIds(new HashSet<>(
				Arrays.asList(match1Tournament1.getId(), match2Tournament1.getId(), match3Tournament1.getId())));
		_tournament1.setHomeTeamsIds(new HashSet<>(Arrays.asList(_teamSoccer1.getId(), _teamSoccer2.getId())));
		_tournament1.setGuestTeams(new HashSet<>(Arrays.asList("A3 Bregenz", "A2 Dornbirn")));
		_tournament1.setDone(true);
		_tournament1 = PersistenceFacade.getInstance().saveOrUpdate(_tournament1);

	}

	@Test
	public void test() {
		TournamentWS ws = new TournamentWS();
		assertTrue(ws.hasResults("Soccer", "Soccer League", LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))));
		assertNotNull(ws.getResults("Soccer", "Soccer League", LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))));
	}

	@After
	public void tearDown() throws Exception {
		PersistenceFacade.getInstance().delete(_tournament1);
	}
}
