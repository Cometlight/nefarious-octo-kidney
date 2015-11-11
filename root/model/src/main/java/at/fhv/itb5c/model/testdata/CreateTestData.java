package at.fhv.itb5c.model.testdata;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;

import at.fhv.itb5c.commons.enums.Gender;
import at.fhv.itb5c.commons.enums.TypeOfSport;
import at.fhv.itb5c.commons.enums.UserRole;
import at.fhv.itb5c.logging.ILogger;
import at.fhv.itb5c.model.PersistenceFacade;
import at.fhv.itb5c.model.entity.Department;
//import at.fhv.itb5c.model.entity.League;
//import at.fhv.itb5c.model.entity.Team;
//import at.fhv.itb5c.model.entity.League;
//import at.fhv.itb5c.model.entity.Team;
import at.fhv.itb5c.model.entity.User;

public class CreateTestData implements ILogger {
	private static User _userDS;
	private static User _userDG;
	private static User _userSA;
	private static Department _deptSoccer;
	private static Department _deptTennis;
	//private static Team _teamSoccer;
	//private static League _leagueSoccer;

	public static void run() {
		try {
			createDepartments();
			createUsers();
			//createLeagues();
			//createTeams();
			updateDepartments();
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}

	private static void updateDepartments() throws Exception {
		_deptSoccer.setHeadId(_userDS.getId());
		_deptTennis.setHeadId(_userSA.getId());
		PersistenceFacade.getInstance().saveOrUpdate(_deptSoccer);
		PersistenceFacade.getInstance().saveOrUpdate(_deptTennis);
	}

	private static void createDepartments() throws Exception {
		_deptSoccer = new Department();
		_deptSoccer.setName("Soccer");
		_deptSoccer.setTypeOfSport(TypeOfSport.Soccer);
		_deptSoccer = PersistenceFacade.getInstance().saveOrUpdate(_deptSoccer);

		_deptTennis = new Department();
		_deptTennis.setName("Tennis");
		_deptTennis.setTypeOfSport(TypeOfSport.Tennis);
		_deptTennis = PersistenceFacade.getInstance().saveOrUpdate(_deptTennis);
	}

	private static void createUsers() throws Exception {
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
		_userDS.setRoles(new HashSet<>(Arrays.asList(UserRole.StandardUser)));
		_userDS = PersistenceFacade.getInstance().saveOrUpdate(_userDS);

		_userDG = new User();
		_userDG.setFirstName("Daniel");
		_userDG.setLastName("Grießer");
		_userDG.setAddress("Rickenbacherstr. 15a, A - 6922 Wolfurt");
		_userDG.setDateOfBirth(LocalDate.now().minusYears(27));
		_userDS.setDepartmentId(_deptSoccer.getId());
		_userDG.setEmail("daniel.griesser@gmail.com");
		_userDG.setGender(Gender.Male);
		_userDG.setMembershipFee(6.9d);
		_userDG.setMembershipFeePaid(true);
		_userDG.setTelephoneNumber("+43 (0) 6648268464");
		_userDG.setTypeOfSports(new HashSet<>(Arrays.asList(TypeOfSport.Soccer)));
		_userDG.setRoles(new HashSet<>(Arrays.asList(UserRole.Admin)));
		_userDG = PersistenceFacade.getInstance().saveOrUpdate(_userDG);

		_userSA = new User();
		_userSA.setFirstName("Simone");
		_userSA.setLastName("Angerer");
		_userSA.setAddress("Prinzessinnenweg 7b, 6480 Götzis");
		_userSA.setDateOfBirth(LocalDate.now().minusYears(25));
		_userDS.setDepartmentId(_deptTennis.getId());
		_userSA.setEmail("simone.a@gmx.net");
		_userSA.setGender(Gender.Female);
		_userSA.setMembershipFeePaid(true);
		_userSA.setTelephoneNumber("06648268464");
		_userSA.setTypeOfSports(new HashSet<>(Arrays.asList(TypeOfSport.Tennis)));
		_userSA.setRoles(new HashSet<>(Arrays.asList(UserRole.StandardUser)));
		_userSA = PersistenceFacade.getInstance().saveOrUpdate(_userSA);
	}

	private static void createTeams() throws Exception {
		/*_teamSoccer = new Team();
		_teamSoccer.setName("Team One");
		_teamSoccer.setTypeOfSport(TypeOfSport.Soccer);
		_teamSoccer.setDepartmentId();
		_teamSoccer.setDepartment(_deptSoccer); FIXME
		_teamSoccer.setCoachId(_userDS.getId());
		_teamSoccer.setMembers(new HashSet<>(Arrays.asList(_userDS.getId(), _userDG.getId(), _userSA.getId())));
		_teamSoccer.setLeague(_leagueSoccer); FIXME
		_teamSoccer = PersistenceFacade.getInstance().saveOrUpdate(_teamSoccer);*/
	}

	private static void createLeagues() throws Exception {
		/*_leagueSoccer = new League();
		_leagueSoccer.setName("Soccer League");
		_leagueSoccer.setTypeOfSport(TypeOfSport.Soccer);
		_leagueSoccer = PersistenceFacade.getInstance().saveOrUpdate(_leagueSoccer);*/
	}

}
