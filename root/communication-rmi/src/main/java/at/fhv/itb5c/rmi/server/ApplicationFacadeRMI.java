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
import at.fhv.itb5c.commons.dto.rmi.ITeamRMI;
import at.fhv.itb5c.commons.dto.rmi.IUserRMI;
import at.fhv.itb5c.commons.enums.TypeOfSport;
import at.fhv.itb5c.logging.ILogger;
import at.fhv.itb5c.rmi.server.converter.ConverterDepartmentRMI;
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
	public IUserRMI createUser() throws RemoteException {
		return ConverterUserRMI.toRMI(_applicationFacade.createUser());
	}
	
	@Override
	public IUserRMI getUserById(Long id) throws RemoteException {
		return ConverterUserRMI.toRMI(_applicationFacade.getUserById(id));
	}

	@Override
	public Collection<IUserRMI> findUsers(String firstName, String lastName, Long departmentId,
			Boolean membershipFeePaid) throws RemoteException {
		return ConverterUserRMI.toRMI(_applicationFacade.findUsers(firstName, lastName, departmentId, membershipFeePaid));
	}

	@Override
	public Collection<IUserRMI> findUsersSimple(String name) throws RemoteException {
		return ConverterUserRMI.toRMI(_applicationFacade.findUsersSimple(name));
	}

	@Override
	public IUserRMI saveUser(IUserRMI user) throws RemoteException {
		UserDTO userDTO = ConverterUserRMI.toDTO(user);
		return ConverterUserRMI.toRMI(_applicationFacade.saveUser(userDTO));
	}

	@Override
	public IDepartmentRMI getDepartmentById(Long id) throws RemoteException {
		return ConverterDepartmentRMI.toRMI(_applicationFacade.getDepartmentById(id));
	}

	@Override
	public Collection<IDepartmentRMI> getAllDepartments() throws RemoteException {
		return ConverterDepartmentRMI.toRMI(_applicationFacade.getAllDepartments());
	}

	@Override
	public ITeamRMI getTeamById(Long id) throws RemoteException {
		return ConverterTeamRMI.toRMI(_applicationFacade.getTeamById(id));
	}

	@Override
	public Collection<ITeamRMI> findTeams(String name, TypeOfSport typeOfSport, Long departmentId, Long leagueId)
			throws RemoteException {
		return ConverterTeamRMI.toRMI(_applicationFacade.findTeams(name, typeOfSport, departmentId, leagueId));
	}

	@Override
	public ITeamRMI saveTeam(ITeamRMI team) {
		TeamDTO teamDTO = ConverterTeamRMI.toDTO(team);
		return ConverterTeamRMI.toRMI(_applicationFacade.saveTeam(teamDTO));
	}
}
