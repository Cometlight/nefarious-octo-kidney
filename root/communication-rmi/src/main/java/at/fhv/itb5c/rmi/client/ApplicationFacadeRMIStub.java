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
import at.fhv.itb5c.commons.dto.rmi.ITeamRMI;
import at.fhv.itb5c.commons.dto.rmi.IUserRMI;
import at.fhv.itb5c.commons.enums.TypeOfSport;
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
	public IUserRMI createUser() throws RemoteException {
		return _applicationFacadeRMI.createUser();
	}

	@Override
	public IUserRMI getUserById(Long id) throws RemoteException {
		return _applicationFacadeRMI.getUserById(id);
	}

	@Override
	public Collection<IUserRMI> findUsers(String firstName, String lastName, Long departmentId,
			Boolean membershipFeePaid) throws RemoteException {
		return _applicationFacadeRMI.findUsers(firstName, lastName, departmentId, membershipFeePaid);
	}

	@Override
	public Collection<IUserRMI> findUsersSimple(String name) throws RemoteException {
		return _applicationFacadeRMI.findUsersSimple(name);
	}

	@Override
	public IUserRMI saveUser(IUserRMI user) throws RemoteException {
		return _applicationFacadeRMI.saveUser(user);
	}

	@Override
	public IDepartmentRMI getDepartmentById(Long id) throws RemoteException {
		return _applicationFacadeRMI.getDepartmentById(id);
	}

	@Override
	public Collection<IDepartmentRMI> getAllDepartments() throws RemoteException {
		return _applicationFacadeRMI.getAllDepartments();
	}

	public ITeamRMI createTeam() throws RemoteException{
		return _applicationFacadeRMI.createTeam();
	}
	
	@Override
	public ITeamRMI getTeamById(Long id) throws RemoteException {
		return _applicationFacadeRMI.getTeamById(id);
	}

	@Override
	public Collection<ITeamRMI> findTeams(String name, TypeOfSport typeOfSport, Long departmentId, Long leagueId)
			throws RemoteException {
		return _applicationFacadeRMI.findTeams(name, typeOfSport, departmentId, leagueId);
	}

	@Override
	public ITeamRMI saveTeam(ITeamRMI team) throws RemoteException {
		return _applicationFacadeRMI.saveTeam(team);
	}

	@Override
	public ILeagueRMI getLeagueById(Long id) throws RemoteException {
		return _applicationFacadeRMI.getLeagueById(id);
	}

	@Override
	public Collection<ILeagueRMI> getAllLeagues() throws RemoteException {
		return _applicationFacadeRMI.getAllLeagues();
	}

	@Override
	public String loginLDAP(String username, String password) {
		return _applicationFacadeRMI.loginLDAP(username, password);
	}
}
