package at.fhv.itb5c.ejb;

import java.util.Collection;

import javax.ejb.Singleton;
import javax.ejb.Startup;

import at.fhv.itb5c.application.ApplicationFacade;
import at.fhv.itb5c.commons.dto.DepartmentDTO;
import at.fhv.itb5c.commons.dto.LeagueDTO;
import at.fhv.itb5c.commons.dto.MatchDTO;
import at.fhv.itb5c.commons.dto.MessageDTO;
import at.fhv.itb5c.commons.dto.TeamDTO;
import at.fhv.itb5c.commons.dto.TournamentDTO;
import at.fhv.itb5c.commons.dto.UserDTO;
import at.fhv.itb5c.commons.enums.TeamInvitationStatus;
import at.fhv.itb5c.commons.enums.TypeOfSport;
import at.fhv.itb5c.commons.enums.UserRole;
import at.fhv.itb5c.logging.ILogger;

@Startup
@Singleton
public class ApplicationFacadeEJB implements ApplicationFacadeEJBLocal, ILogger {
	private ApplicationFacade applicationFacade;
	
	public ApplicationFacadeEJB() {
		applicationFacade = new ApplicationFacade();
	}
	
	@Override
	public UserDTO createUser(String sessionId) {
		return applicationFacade.createUser(sessionId);
	}

	@Override
	public UserDTO getUserById(String sessionId, Long id) {
		return applicationFacade.getUserById(sessionId, id);
	}

	@Override
	public Collection<UserDTO> findUsers(String sessionId, String firstName, String lastName, Long departmentId,
			Boolean membershipFeePaid) {
		return applicationFacade.findUsers(sessionId, firstName, lastName, departmentId, membershipFeePaid);
	}

	@Override
	public Collection<UserDTO> findUsersSimple(String sessionId, String name) {
		return applicationFacade.findUsersSimple(sessionId, name);
	}

	@Override
	public UserDTO saveUser(String sessionId, UserDTO user) {
		return applicationFacade.saveUser(sessionId, user);
	}

	@Override
	public DepartmentDTO getDepartmentById(String sessionId, Long id) {
		return applicationFacade.getDepartmentById(sessionId, id);
	}

	@Override
	public Collection<DepartmentDTO> getAllDepartments(String sessionId) {
		return applicationFacade.getAllDepartments(sessionId);
	}

	@Override
	public DepartmentDTO saveDepartment(String sessionId, DepartmentDTO department) {
		return applicationFacade.saveDepartment(sessionId, department);
	}

	@Override
	public TeamDTO createTeam(String sessionId) {
		return applicationFacade.createTeam(sessionId);
	}

	@Override
	public TeamDTO getTeamById(String sessionId, Long id) {
		return applicationFacade.getTeamById(sessionId, id);
	}

	@Override
	public Collection<TeamDTO> findTeams(String sessionId, String name, TypeOfSport typeOfSport, Long departmentId,
			Long leagueId, Long coachId) {
		return applicationFacade.findTeams(sessionId, name, typeOfSport, departmentId, leagueId, coachId);
	}

	@Override
	public TeamDTO saveTeam(String sessionId, TeamDTO team) {
		return applicationFacade.saveTeam(sessionId, team);
	}

	@Override
	public TeamDTO addPlayerToTeam(String sessionId, TeamDTO team, UserDTO player) {
		return applicationFacade.addPlayerToTeam(sessionId, team, player);
	}

	@Override
	public LeagueDTO getLeagueById(String sessionId, Long id) {
		return applicationFacade.getLeagueById(sessionId, id);
	}

	@Override
	public Collection<LeagueDTO> getAllLeagues(String sessionId) {
		return applicationFacade.getAllLeagues(sessionId);
	}

	@Override
	public Collection<TournamentDTO> findTournaments(String sessionId, String name, Long departmentId) {
		return applicationFacade.findTournaments(sessionId, name, departmentId);
	}

	@Override
	public MatchDTO getMatchById(String sessionId, Long matchId) {
		return applicationFacade.getMatchById(sessionId, matchId);
	}

	@Override
	public MatchDTO createMatch(String sessionId) {
		return applicationFacade.createMatch(sessionId);
	}

	@Override
	public TournamentDTO addMatchToTournament(String sessionId, TournamentDTO tournament, MatchDTO match) {
		return applicationFacade.addMatchToTournament(sessionId, tournament, match);
	}

	@Override
	public String loginLDAP(String username, String password) {
		log.debug("trying to login user " + username);
		return applicationFacade.loginLDAP(username, password);
	}

	@Override
	public UserDTO getCurrentUser(String sessionId) {
		return applicationFacade.getCurrentUser(sessionId);
	}

	@Override
	public Boolean hasRole(String sessionId, UserRole... roles) {
		return applicationFacade.hasRole(sessionId, roles);
	}

	@Override
	public TournamentDTO createTournament(String sessionId, DepartmentDTO dept) {
		return applicationFacade.createTournament(sessionId, dept);
	}

	@Override
	public TournamentDTO saveTournament(String sessionId, TournamentDTO tournament, DepartmentDTO dept) {
		return applicationFacade.saveTournament(sessionId, tournament, dept);
	}

	@Override
	public TournamentDTO getTournamentById(String sessionId, Long id) {
		return applicationFacade.getTournamentById(sessionId, id);
	}

	@Override
	public Boolean isDepartmentHead(String sessionId, DepartmentDTO dept) {
		return applicationFacade.isDepartmentHead(sessionId, dept);
	}

	@Override
	public Boolean isCoach(String sessionId, TeamDTO team) {
		return applicationFacade.isCoach(sessionId, team);
	}

	@Override
	public MatchDTO saveMatch(String sessionId, MatchDTO matchDTO, DepartmentDTO dept) {
		return applicationFacade.saveMatch(sessionId, matchDTO, dept);
	}

	@Override
	public Boolean rsvp(String sessionId, TeamDTO team, TeamInvitationStatus answer) {
		return applicationFacade.rsvp(sessionId, team, answer);
	}

	@Override
	public MessageDTO getMessage(String sessionId) {
		return applicationFacade.getMessage(sessionId);
	}

	@Override
	public void invitePlayer(String sessionId, UserDTO player, TeamDTO team, TournamentDTO tournament) {
		applicationFacade.invitePlayer(sessionId, player, team, tournament);
	}

	@Override
	public boolean isCoach(String sessionId, DepartmentDTO dept) {
		return applicationFacade.isCoach(sessionId, dept);
	}

}
