package at.fhv.itb5c.rmi.client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Collection;

import at.fhv.itb5c.commons.dto.rmi.IApplicationFacadeRMI;
import at.fhv.itb5c.commons.dto.rmi.IDepartmentRMI;
import at.fhv.itb5c.commons.dto.rmi.ILeagueRMI;
import at.fhv.itb5c.commons.dto.rmi.IMatchRMI;
import at.fhv.itb5c.commons.dto.rmi.IMessageRMI;
import at.fhv.itb5c.commons.dto.rmi.ITeamRMI;
import at.fhv.itb5c.commons.dto.rmi.ITournamentRMI;
import at.fhv.itb5c.commons.dto.rmi.IUserRMI;
import at.fhv.itb5c.commons.enums.TeamInvitationStatus;
import at.fhv.itb5c.commons.enums.TypeOfSport;
import at.fhv.itb5c.commons.enums.UserRole;
import at.fhv.itb5c.logging.ILogger;
import at.fhv.itb5c.rmi.server.RMIServant;

public class ApplicationFacadeRMIStub extends UnicastRemoteObject
		implements IApplicationFacadeRMI, RMIServant, RMIStub, ILogger {
	private static final long serialVersionUID = 5936681743587706578L;
	private IApplicationFacadeRMI _applicationFacadeRMI;

	protected ApplicationFacadeRMIStub() throws RemoteException {
		super();
	}

	@Override
	public void init(String host, String port) {
		Object obj;
		try {
			obj = Naming.lookup("rmi://" + host + ":" + port + "/ApplicationFacadeRMI");
			_applicationFacadeRMI = (IApplicationFacadeRMI) obj;
		} catch (MalformedURLException | NotBoundException | RemoteException e) {
			log.error(e.getMessage());
		}
	}

	@Override
	public IUserRMI createUser(String sessionId) throws RemoteException {
		return _applicationFacadeRMI.createUser(sessionId);
	}

	@Override
	public IUserRMI getUserById(String sessionId, Long id) throws RemoteException {
		return _applicationFacadeRMI.getUserById(sessionId, id);
	}

	@Override
	public Collection<IUserRMI> findUsers(String sessionId, String firstName, String lastName, Long departmentId,
			Boolean membershipFeePaid) throws RemoteException {
		return _applicationFacadeRMI.findUsers(sessionId, firstName, lastName, departmentId, membershipFeePaid);
	}

	@Override
	public Collection<IUserRMI> findUsersSimple(String sessionId, String name) throws RemoteException {
		return _applicationFacadeRMI.findUsersSimple(sessionId, name);
	}

	@Override
	public IUserRMI saveUser(String sessionId, IUserRMI user) throws RemoteException {
		return _applicationFacadeRMI.saveUser(sessionId, user);
	}

	@Override
	public IDepartmentRMI getDepartmentById(String sessionId, Long id) throws RemoteException {
		return _applicationFacadeRMI.getDepartmentById(sessionId, id);
	}

	@Override
	public Collection<IDepartmentRMI> getAllDepartments(String sessionId) throws RemoteException {
		return _applicationFacadeRMI.getAllDepartments(sessionId);
	}

	@Override
	public ITeamRMI createTeam(String sessionId) throws RemoteException {
		return _applicationFacadeRMI.createTeam(sessionId);
	}

	@Override
	public ITeamRMI getTeamById(String sessionId, Long id) throws RemoteException {
		return _applicationFacadeRMI.getTeamById(sessionId, id);
	}

	@Override
	public Collection<ITeamRMI> findTeams(String sessionId, String name, TypeOfSport typeOfSport, Long departmentId,
			Long leagueId, Long coachId) throws RemoteException {
		return _applicationFacadeRMI.findTeams(sessionId, name, typeOfSport, departmentId, leagueId, coachId);
	}

	@Override
	public ITeamRMI saveTeam(String sessionId, ITeamRMI team) throws RemoteException {
		return _applicationFacadeRMI.saveTeam(sessionId, team);
	}

	@Override
	public ILeagueRMI getLeagueById(String sessionId, Long id) throws RemoteException {
		return _applicationFacadeRMI.getLeagueById(sessionId, id);
	}

	@Override
	public Collection<ILeagueRMI> getAllLeagues(String sessionId) throws RemoteException {
		return _applicationFacadeRMI.getAllLeagues(sessionId);
	}

	@Override
	public String loginLDAP(String username, String password) throws RemoteException {
		return _applicationFacadeRMI.loginLDAP(username, password);
	}

	@Override
	public IUserRMI getCurrentUser(String sessionId) throws RemoteException {
		return _applicationFacadeRMI.getCurrentUser(sessionId);
	}

	@Override
	public ITournamentRMI createTournament(String sessionId, IDepartmentRMI dept) throws RemoteException {
		return _applicationFacadeRMI.createTournament(sessionId, dept);
	}

	@Override
	public ITournamentRMI saveTournament(String sessionId, ITournamentRMI tournament, IDepartmentRMI dept)
			throws RemoteException {
		return _applicationFacadeRMI.saveTournament(sessionId, tournament, dept);
	}

	@Override
	public Collection<ITournamentRMI> findTournaments(String sessionId, String name, Long departmentId)
			throws RemoteException {
		return _applicationFacadeRMI.findTournaments(sessionId, name, departmentId);
	}

	@Override
	public ITournamentRMI getTournamentById(String sessionId, Long id) throws RemoteException {
		return _applicationFacadeRMI.getTournamentById(sessionId, id);
	}

	public ITeamRMI addPlayerToTeam(String sessionId, ITeamRMI team, IUserRMI player) throws RemoteException {
		return _applicationFacadeRMI.addPlayerToTeam(sessionId, team, player);
	}

	@Override
	public ITournamentRMI addMatchToTournament(String sessionId, ITournamentRMI tournament, IMatchRMI match)
			throws RemoteException {
		return _applicationFacadeRMI.addMatchToTournament(sessionId, tournament, match);
	}

	@Override
	public IMatchRMI createMatch(String sessionId) throws RemoteException {
		return _applicationFacadeRMI.createMatch(sessionId);
	}

	@Override
	public IMatchRMI getMatchById(String sessionId, Long matchId) throws RemoteException {
		return _applicationFacadeRMI.getMatchById(sessionId, matchId);
	}

	public IMatchRMI saveMatch(String sessionId, IMatchRMI match, IDepartmentRMI dept) throws RemoteException {
		return _applicationFacadeRMI.saveMatch(sessionId, match, dept);
	}

	@Override
	public Boolean isDepartmentHead(String sessionId, IDepartmentRMI dept) throws RemoteException {
		return _applicationFacadeRMI.isDepartmentHead(sessionId, dept);
	}

	@Override
	public Boolean isCoach(String sessionId, ITeamRMI team) throws RemoteException {
		return _applicationFacadeRMI.isCoach(sessionId, team);
	}

	@Override
	public Boolean hasRole(String sessionId, UserRole... roles) throws RemoteException {
		return _applicationFacadeRMI.hasRole(sessionId, roles);
	}

	@Override
	public Boolean rsvp(String sessionId, ITeamRMI team, TeamInvitationStatus answer) throws RemoteException {
		return _applicationFacadeRMI.rsvp(sessionId, team, answer);
	}

	@Override
	public IMessageRMI getMessage(String sessionId) throws RemoteException {
		return _applicationFacadeRMI.getMessage(sessionId);
	}

	@Override
	public void invitePlayer(String sessionId, IUserRMI player, ITeamRMI team, ITournamentRMI tournament)
			throws RemoteException {
		_applicationFacadeRMI.invitePlayer(sessionId, player, team, tournament);
	}
}
