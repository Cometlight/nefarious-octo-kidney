package at.fhv.itb5c.ejb;

import java.util.Collection;

import javax.ejb.Singleton;
import javax.ejb.Startup;

import at.fhv.itb5c.application.ApplicationFacade;
import at.fhv.itb5c.application.dto.DepartmentDTO;
import at.fhv.itb5c.application.dto.LeagueDTO;
import at.fhv.itb5c.application.dto.MatchDTO;
import at.fhv.itb5c.application.dto.MessageDTO;
import at.fhv.itb5c.application.dto.TeamDTO;
import at.fhv.itb5c.application.dto.TournamentDTO;
import at.fhv.itb5c.application.dto.UserDTO;
import at.fhv.itb5c.commons.enums.TeamInvitationStatus;
import at.fhv.itb5c.commons.enums.TypeOfSport;
import at.fhv.itb5c.commons.enums.UserRole;

@Startup
@Singleton
public class ApplicationFacadeEJB implements ApplicationFacadeEJBLocal {
	private ApplicationFacade applicationFacade;
	
	public ApplicationFacadeEJB() {
		applicationFacade = new ApplicationFacade();
	}
	
	@Override
	public UserDTO createUser(String sessionId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDTO getUserById(String sessionId, Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<UserDTO> findUsers(String sessionId, String firstName, String lastName, Long departmentId,
			Boolean membershipFeePaid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<UserDTO> findUsersSimple(String sessionId, String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDTO saveUser(String sessionId, UserDTO user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DepartmentDTO getDepartmentById(String sessionId, Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<DepartmentDTO> getAllDepartments(String sessionId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DepartmentDTO saveDepartment(String sessionId, DepartmentDTO department) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TeamDTO createTeam(String sessionId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TeamDTO getTeamById(String sessionId, Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<TeamDTO> findTeams(String sessionId, String name, TypeOfSport typeOfSport, Long departmentId,
			Long leagueId, Long coachId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TeamDTO saveTeam(String sessionId, TeamDTO team) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TeamDTO addPlayerToTeam(String sessionId, TeamDTO team, UserDTO player) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LeagueDTO getLeagueById(String sessionId, Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<LeagueDTO> getAllLeagues(String sessionId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<TournamentDTO> findTournaments(String sessionId, String name, Long departmentId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MatchDTO getMatchById(String sessionId, Long matchId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MatchDTO createMatch(String sessionId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TournamentDTO addMatchToTournament(String sessionId, TournamentDTO tournament, MatchDTO match) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String loginLDAP(String username, String password) {
		return applicationFacade.loginLDAP(username, password);
	}

	@Override
	public UserDTO getCurrentUser(String sessionId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean hasRole(String sessionId, UserRole... roles) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TournamentDTO createTournament(String sessionId, DepartmentDTO dept) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TournamentDTO saveTournament(String sessionId, TournamentDTO tournament, DepartmentDTO dept) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TournamentDTO getTournamentById(String sessionId, Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean isDepartmentHead(String sessionId, DepartmentDTO dept) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean isCoach(String sessionId, TeamDTO team) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MatchDTO saveMatch(String sessionId, MatchDTO matchDTO, DepartmentDTO dept) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean rsvp(String sessionId, TeamDTO team, TeamInvitationStatus answer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MessageDTO getMessage(String sessionId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void invitePlayer(String sessionId, UserDTO player, TeamDTO team, TournamentDTO tournament) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isCoach(String sessionId, DepartmentDTO dept) {
		// TODO Auto-generated method stub
		return false;
	}

}
