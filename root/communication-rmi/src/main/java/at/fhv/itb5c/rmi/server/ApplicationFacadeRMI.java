package at.fhv.itb5c.rmi.server;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Collection;

import at.fhv.itb5c.application.ApplicationFacade;
import at.fhv.itb5c.application.dto.DepartmentDTO;
import at.fhv.itb5c.application.dto.MatchDTO;
import at.fhv.itb5c.application.dto.TeamDTO;
import at.fhv.itb5c.application.dto.TournamentDTO;
import at.fhv.itb5c.application.dto.UserDTO;
import at.fhv.itb5c.commons.dto.rmi.IApplicationFacadeRMI;
import at.fhv.itb5c.commons.dto.rmi.IDepartmentRMI;
import at.fhv.itb5c.commons.dto.rmi.ILeagueRMI;
import at.fhv.itb5c.commons.dto.rmi.IMatchRMI;
import at.fhv.itb5c.commons.dto.rmi.ITeamRMI;
import at.fhv.itb5c.commons.dto.rmi.ITournamentRMI;
import at.fhv.itb5c.commons.dto.rmi.IUserRMI;
import at.fhv.itb5c.commons.enums.TypeOfSport;
import at.fhv.itb5c.commons.enums.UserRole;
import at.fhv.itb5c.logging.ILogger;
import at.fhv.itb5c.rmi.server.converter.ConverterDepartmentRMI;
import at.fhv.itb5c.rmi.server.converter.ConverterLeagueRMI;
import at.fhv.itb5c.rmi.server.converter.ConverterMatchRMI;
import at.fhv.itb5c.rmi.server.converter.ConverterTeamRMI;
import at.fhv.itb5c.rmi.server.converter.ConverterTournamentRMI;
import at.fhv.itb5c.rmi.server.converter.ConverterUserRMI;

public class ApplicationFacadeRMI extends UnicastRemoteObject implements IApplicationFacadeRMI, RMIServant, ILogger {
	private static final long serialVersionUID = -6290768314413969216L;
	private ApplicationFacade _applicationFacade;

	public ApplicationFacadeRMI() throws RemoteException {
		super();
		_applicationFacade = new ApplicationFacade();
	}

	@Override
	public void init(String host, String port) throws RemoteException, MalformedURLException {
		Naming.rebind("rmi://" + host + ":" + port + "/ApplicationFacadeRMI", this);
	}

	@Override
	public IUserRMI createUser(String sessionId) throws RemoteException {
		return ConverterUserRMI.toRMI(_applicationFacade.createUser(sessionId));
	}

	@Override
	public IUserRMI getUserById(String sessionId, Long id) throws RemoteException {
		return ConverterUserRMI.toRMI(_applicationFacade.getUserById(sessionId, id));
	}

	@Override
	public Collection<IUserRMI> findUsers(String sessionId, String firstName, String lastName, Long departmentId,
			Boolean membershipFeePaid) throws RemoteException {
		return ConverterUserRMI
				.toRMI(_applicationFacade.findUsers(sessionId, firstName, lastName, departmentId, membershipFeePaid));
	}

	@Override
	public Collection<IUserRMI> findUsersSimple(String sessionId, String name) throws RemoteException {
		return ConverterUserRMI.toRMI(_applicationFacade.findUsersSimple(sessionId, name));
	}

	@Override
	public IUserRMI saveUser(String sessionId, IUserRMI user) throws RemoteException {
		UserDTO userDTO = ConverterUserRMI.toDTO(user);
		return ConverterUserRMI.toRMI(_applicationFacade.saveUser(sessionId, userDTO));
	}

	@Override
	public IDepartmentRMI getDepartmentById(String sessionId, Long id) throws RemoteException {
		return ConverterDepartmentRMI.toRMI(_applicationFacade.getDepartmentById(sessionId, id));
	}

	@Override
	public Collection<IDepartmentRMI> getAllDepartments(String sessionId) throws RemoteException {
		return ConverterDepartmentRMI.toRMI(_applicationFacade.getAllDepartments(sessionId));
	}

	@Override
	public ITeamRMI createTeam(String sessionId) throws RemoteException {
		return ConverterTeamRMI.toRMI(_applicationFacade.createTeam(sessionId));
	}

	@Override
	public ITeamRMI getTeamById(String sessionId, Long id) throws RemoteException {
		return ConverterTeamRMI.toRMI(_applicationFacade.getTeamById(sessionId, id));
	}

	@Override
	public Collection<ITeamRMI> findTeams(String sessionId, String name, TypeOfSport typeOfSport, Long departmentId, Long leagueId)
			throws RemoteException {
		return ConverterTeamRMI.toRMI(_applicationFacade.findTeams(sessionId, name, typeOfSport, departmentId, leagueId));
	}

	@Override
	public ITeamRMI saveTeam(String sessionId, ITeamRMI team) {
		TeamDTO teamDTO = ConverterTeamRMI.toDTO(team);
		return ConverterTeamRMI.toRMI(_applicationFacade.saveTeam(sessionId, teamDTO));
	}

	@Override
	public ILeagueRMI getLeagueById(String sessionId, Long id) throws RemoteException {
		return ConverterLeagueRMI.toRMI(_applicationFacade.getLeagueById(sessionId, id));
	}

	@Override
	public Collection<ILeagueRMI> getAllLeagues(String sessionId) throws RemoteException {
		return ConverterLeagueRMI.toRMI(_applicationFacade.getAllLeagues(sessionId));
	}

	@Override
	public String loginLDAP(String username, String password) throws RemoteException{
		return _applicationFacade.loginLDAP(username, password);
	}

	@Override
	public IUserRMI getCurrentUser(String sessionId) throws RemoteException {
		return ConverterUserRMI.toRMI(_applicationFacade.getCurrentUser(sessionId));
	}
	
	@Override
	public ITournamentRMI createTournament(String sessionId, IDepartmentRMI dept) throws RemoteException {
		return ConverterTournamentRMI.toRMI(_applicationFacade.createTournament(sessionId, ConverterDepartmentRMI.toDTO(dept)));
	}
	
	@Override
	public ITournamentRMI saveTournament(String sessionId, ITournamentRMI tournament, IDepartmentRMI dept) throws RemoteException {
		TournamentDTO tournamentDTO = ConverterTournamentRMI.toDTO(tournament);
		return ConverterTournamentRMI.toRMI(_applicationFacade.saveTournament(sessionId, tournamentDTO, ConverterDepartmentRMI.toDTO(dept)));
	}
	
	@Override
	public Collection<ITournamentRMI> findTournaments(String sessionId, String name, Long departmentId) throws RemoteException {
		return ConverterTournamentRMI.toRMI(_applicationFacade.findTournaments(sessionId, name, departmentId));
	}
	
	@Override
	public ITournamentRMI getTournamentById(String sessionId, Long id) throws RemoteException {
		return ConverterTournamentRMI.toRMI(_applicationFacade.getTournamentById(sessionId, id));
	}
	
	@Override
	public ITeamRMI addPlayerToTeam(String sessionId, ITeamRMI team, IUserRMI player) throws RemoteException {
		TeamDTO teamDTO = ConverterTeamRMI.toDTO(team);
		UserDTO playerDTO = ConverterUserRMI.toDTO(player);
		return ConverterTeamRMI.toRMI(_applicationFacade.addPlayerToTeam(sessionId, teamDTO, playerDTO));
	}

	@Override
	public ITournamentRMI addMatchToTournament(String sessionId, ITournamentRMI tournament, IMatchRMI match) throws RemoteException {
		TournamentDTO tournamentDTO = ConverterTournamentRMI.toDTO(tournament);
		MatchDTO matchDTO = ConverterMatchRMI.toDTO(match);
		return ConverterTournamentRMI.toRMI(_applicationFacade.addMatchToTournament(sessionId, tournamentDTO, matchDTO));
	}

	@Override
	public IMatchRMI createMatch(String sessionId) throws RemoteException {
		return ConverterMatchRMI.toRMI(_applicationFacade.createMatch(sessionId));
	}

	@Override
	public IMatchRMI getMatchById(String sessionId, Long matchId) throws RemoteException {
		return ConverterMatchRMI.toRMI(_applicationFacade.getMatchById(sessionId, matchId));
	}
	
	@Override
	public IMatchRMI saveMatch(String sessionId, IMatchRMI match, IDepartmentRMI dept) throws RemoteException {
		MatchDTO matchDTO = ConverterMatchRMI.toDTO(match);
		DepartmentDTO deptDTO = ConverterDepartmentRMI.toDTO(dept);
		return ConverterMatchRMI.toRMI(_applicationFacade.saveMatch(sessionId, matchDTO, deptDTO));
	}

	@Override
	public Boolean isDepartmentHead(String sessionId, IDepartmentRMI dept) throws RemoteException {
		DepartmentDTO departmentDTO = ConverterDepartmentRMI.toDTO(dept);
		return _applicationFacade.isDepartmentHead(sessionId, departmentDTO);
	}

	@Override
	public Boolean isCoach(String sessionId, ITeamRMI team) throws RemoteException {
		TeamDTO teamDTO = ConverterTeamRMI.toDTO(team);
		return _applicationFacade.isCoach(sessionId, teamDTO);
	}

	@Override
	public Boolean hasRole(String sessionId, UserRole... roles) throws RemoteException {
		return _applicationFacade.hasRole(sessionId, roles);
	}
}
