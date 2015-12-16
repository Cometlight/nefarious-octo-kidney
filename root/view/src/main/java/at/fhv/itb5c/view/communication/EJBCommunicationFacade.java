package at.fhv.itb5c.view.communication;

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
import at.fhv.itb5c.ejb.client.EJBClient;
import at.fhv.itb5c.ejb.interfaces.*;

public class EJBCommunicationFacade implements ICommunicationFacade {

	@Override
	public void startUp() throws CommunicationErrorException {
		EJBClient.getInstance().startUp();
	}

	@Override
	public void close() throws CommunicationErrorException {
		EJBClient.getInstance().close();
	}

	@Override
	public UserDTO createUser(String sessionId) throws CommunicationErrorException {
		CreateUserRemote ejb = EJBClient.getInstance().getEJBRemote(CreateUserRemote.class);
		return ejb.createUser(sessionId);
	}

	@Override
	public UserDTO getUserById(String sessionId, Long id) throws CommunicationErrorException {
		GetByIdUserRemote ejb = EJBClient.getInstance().getEJBRemote(GetByIdUserRemote.class);
		return ejb.getUserById(sessionId, id);
	}

	@Override
	public Collection<UserDTO> findUsers(String sessionId, String firstName, String lastName, Long departmentId,
			Boolean membershipFeePaid) throws CommunicationErrorException {
		FindUsersRemote ejb = EJBClient.getInstance().getEJBRemote(FindUsersRemote.class);
		return ejb.findUsers(sessionId, firstName, lastName, departmentId, membershipFeePaid);
	}

	@Override
	public Collection<UserDTO> findUsersSimple(String sessionId, String name) throws CommunicationErrorException {
		FindUsersRemote ejb = EJBClient.getInstance().getEJBRemote(FindUsersRemote.class);
		return ejb.findUsersSimple(sessionId, name);
	}

	@Override
	public UserDTO saveUser(String sessionId, UserDTO user) throws CommunicationErrorException {
		SaveUserRemote ejb = EJBClient.getInstance().getEJBRemote(SaveUserRemote.class);
		return ejb.saveUser(sessionId, user);
	}

	@Override
	public DepartmentDTO getDepartmentById(String sessionId, Long id) throws CommunicationErrorException {
		GetByIdDepartmentRemote ejb = EJBClient.getInstance().getEJBRemote(GetByIdDepartmentRemote.class);
		return ejb.getDepartmentById(sessionId, id);
	}

	@Override
	public Collection<DepartmentDTO> getAllDepartments(String sessionId) throws CommunicationErrorException {
		GetAllDepartmentsRemote ejb = EJBClient.getInstance().getEJBRemote(GetAllDepartmentsRemote.class);
		return ejb.getAllDepartments(sessionId);
	}

	@Override
	public TeamDTO getTeamById(String sessionId, Long id) throws CommunicationErrorException {
		GetByIdTeamRemote ejb = EJBClient.getInstance().getEJBRemote(GetByIdTeamRemote.class);
		return ejb.getTeamById(sessionId, id);
	}

	@Override
	public Collection<TeamDTO> findTeams(String sessionId, String name, TypeOfSport typeOfSport, Long departmentId,
			Long leagueId, Long coachId) throws CommunicationErrorException {
		FindTeamsRemote ejb = EJBClient.getInstance().getEJBRemote(FindTeamsRemote.class);
		return ejb.findTeams(sessionId, name, typeOfSport, departmentId, leagueId, coachId);
	}

	@Override
	public TeamDTO saveTeam(String sessionId, TeamDTO team) throws CommunicationErrorException {
		SaveTeamRemote ejb = EJBClient.getInstance().getEJBRemote(SaveTeamRemote.class);
		return ejb.saveTeam(sessionId, team);
	}

	@Override
	public LeagueDTO getLeagueById(String sessionId, Long id) throws CommunicationErrorException {
		GetByIdLeagueRemote ejb = EJBClient.getInstance().getEJBRemote(GetByIdLeagueRemote.class);
		return ejb.getLeagueById(sessionId, id);
	}

	@Override
	public Collection<LeagueDTO> getAllLeagues(String sessionId) throws CommunicationErrorException {
		GetAllLeaguesRemote ejb = EJBClient.getInstance().getEJBRemote(GetAllLeaguesRemote.class);
		return ejb.getAllLeagues(sessionId);
	}

	@Override
	public TeamDTO createTeam(String sessionId) throws CommunicationErrorException {
		CreateTeamRemote ejb = EJBClient.getInstance().getEJBRemote(CreateTeamRemote.class);
		return ejb.createTeam(sessionId);
	}

	@Override
	public String loginLDAP(String username, String password) throws CommunicationErrorException {
		LoginRemote ejb = EJBClient.getInstance().getEJBRemote(LoginRemote.class);
		return ejb.loginLDAP(username, password);
	}

	@Override
	public UserDTO getCurrentUser(String sessionId) throws CommunicationErrorException {
		SessionHelperRemote ejb = EJBClient.getInstance().getEJBRemote(SessionHelperRemote.class);
		return ejb.getCurrentUser(sessionId);
	}

	@Override
	public Boolean hasRole(String sessionId, UserRole... roles) throws CommunicationErrorException {
		SessionHelperRemote ejb = EJBClient.getInstance().getEJBRemote(SessionHelperRemote.class);
		return ejb.hasRole(sessionId, roles);
	}

	@Override
	public TournamentDTO createTournament(String sessionId, DepartmentDTO dept) throws CommunicationErrorException {
		CreateTournamentRemote ejb = EJBClient.getInstance().getEJBRemote(CreateTournamentRemote.class);
		return ejb.createTournament(sessionId, dept);
	}

	@Override
	public TournamentDTO saveTournament(String sessionId, TournamentDTO tournament, DepartmentDTO dept)
			throws CommunicationErrorException {
		SaveTournamentRemote ejb = EJBClient.getInstance().getEJBRemote(SaveTournamentRemote.class);
		return ejb.saveTournament(sessionId, tournament, dept);
	}

	@Override
	public TournamentDTO getTournamentById(String sessionId, Long id) throws CommunicationErrorException {
		GetByIdTournamentRemote ejb = EJBClient.getInstance().getEJBRemote(GetByIdTournamentRemote.class);
		return ejb.getTournamentById(sessionId, id);
	}

	@Override
	public TeamDTO addPlayerToTeam(String sessionId, TeamDTO team, UserDTO player) throws CommunicationErrorException {
		AddPlayerToTeamRemote ejb = EJBClient.getInstance().getEJBRemote(AddPlayerToTeamRemote.class);
		return ejb.addPlayerToTeam(sessionId, team, player);
	}

	@Override
	public MatchDTO createMatch(String sessionId) throws CommunicationErrorException {
		CreateMatchRemote ejb = EJBClient.getInstance().getEJBRemote(CreateMatchRemote.class);
		return ejb.createMatch(sessionId);
	}

	@Override
	public TournamentDTO addMatchToTournament(String sessionId, TournamentDTO tournament, MatchDTO match)
			throws CommunicationErrorException {
		AddMatchToTournamentRemote ejb = EJBClient.getInstance().getEJBRemote(AddMatchToTournamentRemote.class);
		return ejb.addMatchToTournament(sessionId, tournament, match);
	}

	@Override
	public Collection<TournamentDTO> findTournaments(String sessionId, String name, Long departmentId)
			throws CommunicationErrorException {
		FindTournamentsRemote ejb = EJBClient.getInstance().getEJBRemote(FindTournamentsRemote.class);
		return ejb.findTournaments(sessionId, name, departmentId);
	}

	@Override
	public MatchDTO getMatchById(String sessionId, Long matchId) throws CommunicationErrorException {
		GetByIdMatchRemote ejb = EJBClient.getInstance().getEJBRemote(GetByIdMatchRemote.class);
		return ejb.getMatchById(sessionId, matchId);
	}

	@Override
	public MatchDTO saveMatch(String sessionId, MatchDTO match, DepartmentDTO dept) throws CommunicationErrorException {
		SaveMatchRemote ejb = EJBClient.getInstance().getEJBRemote(SaveMatchRemote.class);
		return ejb.saveMatch(sessionId, match, dept);
	}

	@Override
	public Boolean isDepartmentHead(String sessionId, DepartmentDTO dept) throws CommunicationErrorException {
		SessionHelperRemote ejb = EJBClient.getInstance().getEJBRemote(SessionHelperRemote.class);
		return ejb.isDepartmentHead(sessionId, dept);
	}

	@Override
	public Boolean isCoach(String sessionId, TeamDTO team) throws CommunicationErrorException {
		SessionHelperRemote ejb = EJBClient.getInstance().getEJBRemote(SessionHelperRemote.class);
		return ejb.isCoach(sessionId, team);
	}

	@Override
	public Boolean rsvp(String sessionId, TeamDTO team, TeamInvitationStatus answer)
			throws CommunicationErrorException {
		RSVPRemote ejb = EJBClient.getInstance().getEJBRemote(RSVPRemote.class);
		return ejb.rsvp(sessionId, team, answer);
	}

	@Override
	public MessageDTO getMessage(String sessionId) throws CommunicationErrorException {
		SessionHelperRemote ejb = EJBClient.getInstance().getEJBRemote(SessionHelperRemote.class);
		return ejb.getMessage(sessionId);
	}

	@Override
	public void invitePlayer(String sessionId, UserDTO player, TeamDTO team, TournamentDTO tournament)
			throws CommunicationErrorException {
		InvitePlayerRemote ejb = EJBClient.getInstance().getEJBRemote(InvitePlayerRemote.class);
		ejb.invitePlayer(sessionId, player, team, tournament);
	}

}
