package at.fhv.itb5c.commons.dto.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Collection;

import at.fhv.itb5c.commons.enums.TypeOfSport;

public interface IApplicationFacadeRMI extends Remote {
	IUserRMI createUser() throws RemoteException;

	IUserRMI getUserById(Long id) throws RemoteException;

	Collection<IUserRMI> findUsers(String firstName, String lastName, Long departmentId, Boolean membershipFeePaid)
			throws RemoteException;

	Collection<IUserRMI> findUsersSimple(String name) throws RemoteException;

	IUserRMI saveUser(IUserRMI user) throws RemoteException;

	IDepartmentRMI getDepartmentById(Long id) throws RemoteException;

	Collection<IDepartmentRMI> getAllDepartments() throws RemoteException;

	ITeamRMI getTeamById(Long id) throws RemoteException;

	Collection<ITeamRMI> findTeams(String name, TypeOfSport typeOfSport, Long departmentId, Long leagueId)
			throws RemoteException;

	ITeamRMI saveTeam(ITeamRMI team) throws RemoteException;

	ILeagueRMI getLeagueById(Long id) throws RemoteException;

	Collection<ILeagueRMI> getAllLeagues() throws RemoteException;

	ITeamRMI createTeam() throws RemoteException;
	
	String loginLDAP(String username, String password)  throws RemoteException;

	IUserRMI getUserBySessionId(String sessionId) throws RemoteException;
}
