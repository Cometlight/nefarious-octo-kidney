package at.fhv.itb5c.rmi.server;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Collection;

import at.fhv.itb5c.application.ApplicationFacade;
import at.fhv.itb5c.application.dto.TeamDTO;
import at.fhv.itb5c.application.dto.UserDTO;
import at.fhv.itb5c.commons.dto.rmi.IApplicationFacadeRMI;
import at.fhv.itb5c.commons.dto.rmi.IDepartmentRMI;
import at.fhv.itb5c.commons.dto.rmi.ILeagueRMI;
import at.fhv.itb5c.commons.dto.rmi.ITeamRMI;
import at.fhv.itb5c.commons.dto.rmi.IUserRMI;
import at.fhv.itb5c.commons.enums.TypeOfSport;
import at.fhv.itb5c.logging.ILogger;
import at.fhv.itb5c.rmi.server.converter.ConverterDepartmentRMI;
import at.fhv.itb5c.rmi.server.converter.ConverterLeagueRMI;
import at.fhv.itb5c.rmi.server.converter.ConverterTeamRMI;
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
	public String loginLDAP(String sessionId, String username, String password) throws RemoteException{
		return _applicationFacade.loginLDAP(sessionId, username, password);
	}

	@Override
	public IUserRMI getCurrentUser(String sessionId) throws RemoteException {
		return ConverterUserRMI.toRMI(_applicationFacade.getCurrentUser(sessionId));
	}
	
	
}
