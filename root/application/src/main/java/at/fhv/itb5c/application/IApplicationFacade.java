package at.fhv.itb5c.application;

import java.util.Collection;

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

public interface IApplicationFacade {

	UserDTO createUser(String sessionId);

	UserDTO getUserById(String sessionId, Long id);

	/**
	 * If a parameter is null, it is ignored.
	 */
	Collection<UserDTO> findUsers(String sessionId, String firstName, String lastName, Long departmentId,
			Boolean membershipFeePaid);

	/**
	 * If a parameter is null, it is ignored.
	 */
	Collection<UserDTO> findUsersSimple(String sessionId, String name);

	UserDTO saveUser(String sessionId, UserDTO user);

	DepartmentDTO getDepartmentById(String sessionId, Long id);

	Collection<DepartmentDTO> getAllDepartments(String sessionId);

	DepartmentDTO saveDepartment(String sessionId, DepartmentDTO department);

	TeamDTO createTeam(String sessionId);

	TeamDTO getTeamById(String sessionId, Long id);

	/**
	 * If a parameter is null, it is ignored.
	 */
	Collection<TeamDTO> findTeams(String sessionId, String name, TypeOfSport typeOfSport, Long departmentId,
			Long leagueId, Long coachId);

	TeamDTO saveTeam(String sessionId, TeamDTO team);

	/**
	 * Adds the specified player to the specified team.
	 *
	 * This method is currently untested!
	 *
	 * @param sessionId
	 *            a session id
	 * @param team
	 *            the team the player should be added to
	 * @param player
	 *            the user that should be added to the team
	 * @return the updated team if the session can add players; null, otherwise.
	 */
	TeamDTO addPlayerToTeam(String sessionId, TeamDTO team, UserDTO player);

	LeagueDTO getLeagueById(String sessionId, Long id);

	Collection<LeagueDTO> getAllLeagues(String sessionId);

	Collection<TournamentDTO> findTournaments(String sessionId, String name, Long departmentId);

	MatchDTO getMatchById(String sessionId, Long matchId);

	MatchDTO createMatch(String sessionId);

	/**
	 * Adds a Match to a Tournament. Only administrators and the head of the
	 * hosting department have permissions to do this.
	 *
	 * @param sessionId
	 *            the session id of the currently logged in user
	 * @param tournament
	 *            the tournament, the match should be added to
	 * @param match
	 *            the match that should be added to the tournament
	 * @return the updated tournament; or null, if the match could not be added
	 */
	TournamentDTO addMatchToTournament(String sessionId, TournamentDTO tournament, MatchDTO match);

	/**
	 * checks the login credentials on the ldap server and returns the new
	 * created session id
	 *
	 * @param username
	 *            ldap username
	 * @param password
	 *            ldap password
	 * @return session id
	 */
	String loginLDAP(String username, String password);

	UserDTO getCurrentUser(String sessionId);

	Boolean hasRole(String sessionId, UserRole... roles);

	TournamentDTO createTournament(String sessionId, DepartmentDTO dept);

	TournamentDTO saveTournament(String sessionId, TournamentDTO tournament, DepartmentDTO dept);

	TournamentDTO getTournamentById(String sessionId, Long id);

	Boolean isDepartmentHead(String sessionId, DepartmentDTO dept);

	Boolean isCoach(String sessionId, TeamDTO team);

	MatchDTO saveMatch(String sessionId, MatchDTO matchDTO, DepartmentDTO dept);

	Boolean rsvp(String sessionId, TeamDTO team, TeamInvitationStatus answer);

	MessageDTO getMessage(String sessionId);

	/**
	 * adds a player to a team from the specified tournament and creates a
	 * notification for the player
	 * 
	 * @param sessionId
	 *            session id
	 * @param player
	 *            player to be added
	 * @param team
	 *            team of the player
	 * @param tournament
	 *            tournament
	 */
	void invitePlayer(String sessionId, UserDTO player, TeamDTO team, TournamentDTO tournament);

	boolean isCoach(String sessionId, DepartmentDTO dept);

}