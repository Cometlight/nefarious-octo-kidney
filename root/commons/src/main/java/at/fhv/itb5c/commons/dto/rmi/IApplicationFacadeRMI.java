package at.fhv.itb5c.commons.dto.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Collection;

import at.fhv.itb5c.commons.enums.TypeOfSport;
import at.fhv.itb5c.commons.enums.UserRole;

public interface IApplicationFacadeRMI extends Remote {
	IUserRMI createUser(String sessionId) throws RemoteException;

	IUserRMI getUserById(String sessionId, Long id) throws RemoteException;

	Collection<IUserRMI> findUsers(String sessionId, String firstName, String lastName, Long departmentId,
			Boolean membershipFeePaid) throws RemoteException;

	Collection<IUserRMI> findUsersSimple(String sessionId, String name) throws RemoteException;

	IUserRMI saveUser(String sessionId, IUserRMI user) throws RemoteException;

	IDepartmentRMI getDepartmentById(String sessionId, Long id) throws RemoteException;

	Collection<IDepartmentRMI> getAllDepartments(String sessionId) throws RemoteException;

	ITeamRMI getTeamById(String sessionId, Long id) throws RemoteException;

	Collection<ITeamRMI> findTeams(String sessionId, String name, TypeOfSport typeOfSport, Long departmentId,
			Long leagueId, Long coachId) throws RemoteException;

	ITeamRMI saveTeam(String sessionId, ITeamRMI team) throws RemoteException;

	ILeagueRMI getLeagueById(String sessionId, Long id) throws RemoteException;

	Collection<ILeagueRMI> getAllLeagues(String sessionId) throws RemoteException;

	ITeamRMI createTeam(String sessionId) throws RemoteException;

	String loginLDAP(String username, String password) throws RemoteException;

	IUserRMI getCurrentUser(String sessionId) throws RemoteException;

	Boolean hasRole(String sessionId, UserRole... roles) throws RemoteException;

	ITournamentRMI createTournament(String sessionId, IDepartmentRMI dept) throws RemoteException;

	ITournamentRMI saveTournament(String sessionId, ITournamentRMI tournament, IDepartmentRMI dept)
			throws RemoteException;

	ITournamentRMI getTournamentById(String sessionId, Long id) throws RemoteException;

	ITeamRMI addPlayerToTeam(String sessionId, ITeamRMI team, IUserRMI player) throws RemoteException;

	IMatchRMI createMatch(String sessionId) throws RemoteException;

	ITournamentRMI addMatchToTournament(String sessionId, ITournamentRMI tournament, IMatchRMI match)
			throws RemoteException;

	Collection<ITournamentRMI> findTournaments(String sessionId, String name, Long departmentId) throws RemoteException;

	IMatchRMI getMatchById(String sessionId, Long matchId) throws RemoteException;

	IMatchRMI saveMatch(String sessionId, IMatchRMI match, IDepartmentRMI dept) throws RemoteException;

	Boolean isDepartmentHead(String sessionId, IDepartmentRMI dept) throws RemoteException;

	Boolean isCoach(String sessionId, ITeamRMI team) throws RemoteException;

	Boolean rsvp(String sessionId, ITeamRMI team, Boolean answer) throws RemoteException;

	IMessageRMI getMessage(String sessionId) throws RemoteException;

	void invitePlayer(String sessionId, IUserRMI player, ITeamRMI team, ITournamentRMI tournament)
			throws RemoteException;
}
