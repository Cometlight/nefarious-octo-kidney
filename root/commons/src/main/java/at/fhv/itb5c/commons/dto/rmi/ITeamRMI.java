package at.fhv.itb5c.commons.dto.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Set;

import at.fhv.itb5c.commons.dto.IDepartment;
import at.fhv.itb5c.commons.dto.ILeague;
import at.fhv.itb5c.commons.dto.ITeam;
import at.fhv.itb5c.commons.dto.IUser;
import at.fhv.itb5c.commons.enums.TypeOfSport;

public interface ITeamRMI extends Remote, ITeam {
	void setId(Long id) throws RemoteException;
	Long getId() throws RemoteException;

	void setVersion(Long version) throws RemoteException;
	Long getVersion() throws RemoteException;
	
	String getName() throws RemoteException;
	void setName(String name) throws RemoteException;

	TypeOfSport getTypeOfSport() throws RemoteException;
	void setTypeOfSport(TypeOfSport typeOfSport) throws RemoteException;
	
	IDepartment getDepartment() throws RemoteException;
	void setDepartment(IDepartment department) throws RemoteException;
	
	IUser getCoach() throws RemoteException;
	void setCoach(IUser coach) throws RemoteException;

	ILeagueRMI getLeague() throws RemoteException;
	void setLeague(ILeague league) throws RemoteException;

	Set<IUserRMI> getMembers() throws RemoteException;
	void setMembers(Set<IUser> members) throws RemoteException;
}
