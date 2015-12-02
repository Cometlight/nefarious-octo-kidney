package at.fhv.itb5c.communication;

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

public interface ICommunicationFacade {
	
	void startUp() throws CommunicationErrorException;
	
	void close() throws CommunicationErrorException;
	
	UserDTO createUser(String sessionId) throws CommunicationErrorException;

	UserDTO getUserById(String sessionId, Long id) throws CommunicationErrorException;

	Collection<UserDTO> findUsers(String sessionId, String firstName, String lastName, Long departmentId,
			Boolean membershipFeePaid) throws CommunicationErrorException;

	Collection<UserDTO> findUsersSimple(String sessionId, String name) throws CommunicationErrorException;

	UserDTO saveUser(String sessionId, UserDTO user) throws CommunicationErrorException;

	DepartmentDTO getDepartmentById(String sessionId, Long id) throws CommunicationErrorException;
	
	Collection<DepartmentDTO> getAllDepartments(String sessionId) throws CommunicationErrorException;

	TeamDTO getTeamById(String sessionId, Long id) throws CommunicationErrorException;

	Collection<TeamDTO> findTeams(String sessionId, String name, TypeOfSport typeOfSport, Long departmentId,
			Long leagueId, Long coachId) throws CommunicationErrorException;

	TeamDTO saveTeam(String sessionId, TeamDTO team) throws CommunicationErrorException;

	LeagueDTO getLeagueById(String sessionId, Long id) throws CommunicationErrorException;

	Collection<LeagueDTO> getAllLeagues(String sessionId) throws CommunicationErrorException;

	TeamDTO createTeam(String sessionId) throws CommunicationErrorException;

	String loginLDAP(String username, String password) throws CommunicationErrorException;

	UserDTO getCurrentUser(String sessionId) throws CommunicationErrorException;

	Boolean hasRole(String sessionId, UserRole... roles) throws CommunicationErrorException;

	TournamentDTO createTournament(String sessionId, DepartmentDTO dept) throws CommunicationErrorException;

	TournamentDTO saveTournament(String sessionId, TournamentDTO tournament, DepartmentDTO dept) throws CommunicationErrorException;

	TournamentDTO getTournamentById(String sessionId, Long id) throws CommunicationErrorException;

	TeamDTO addPlayerToTeam(String sessionId, TeamDTO team, UserDTO player) throws CommunicationErrorException;

	MatchDTO createMatch(String sessionId) throws CommunicationErrorException;

	TournamentDTO addMatchToTournament(String sessionId, TournamentDTO tournament, MatchDTO match) throws CommunicationErrorException;

	Collection<TournamentDTO> findTournaments(String sessionId, String name, Long departmentId) throws CommunicationErrorException;

	MatchDTO getMatchById(String sessionId, Long matchId) throws CommunicationErrorException;

	MatchDTO saveMatch(String sessionId, MatchDTO match, DepartmentDTO dept) throws CommunicationErrorException;

	Boolean isDepartmentHead(String sessionId, DepartmentDTO dept) throws CommunicationErrorException;

	Boolean isCoach(String sessionId, TeamDTO team) throws CommunicationErrorException;

	Boolean rsvp(String sessionId, TeamDTO team, TeamInvitationStatus answer) throws CommunicationErrorException;

	MessageDTO getMessage(String sessionId) throws CommunicationErrorException;

	void invitePlayer(String sessionId, UserDTO player, TeamDTO team, TournamentDTO tournament) throws CommunicationErrorException;
}
