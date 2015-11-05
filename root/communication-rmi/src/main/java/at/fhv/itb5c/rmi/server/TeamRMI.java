package at.fhv.itb5c.rmi.server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Set;
import java.util.stream.Collectors;

import at.fhv.itb5c.commons.dto.IDepartment;
import at.fhv.itb5c.commons.dto.ILeague;
import at.fhv.itb5c.commons.dto.IUser;
import at.fhv.itb5c.commons.dto.rmi.IDepartmentRMI;
import at.fhv.itb5c.commons.dto.rmi.ILeagueRMI;
import at.fhv.itb5c.commons.dto.rmi.ITeamRMI;
import at.fhv.itb5c.commons.dto.rmi.IUserRMI;
import at.fhv.itb5c.commons.enums.TypeOfSport;

public class TeamRMI extends UnicastRemoteObject implements ITeamRMI {
	private static final long serialVersionUID = 1l;

	private Long _id;
	private Long _version;
	private String _name;
	private TypeOfSport _typeOfSport;
	private IDepartmentRMI _department;
	private IUserRMI _coach;
	private ILeagueRMI _league;
	private Set<IUserRMI> _members;
	
	protected TeamRMI() throws RemoteException {
		super();
	}

	@Override
	public Long getId() throws RemoteException {
		return _id;
	}

	@Override
	public void setId(Long id) throws RemoteException {
		_id = id;
	}

	@Override
	public Long getVersion() throws RemoteException {
		return _version;
	}

	@Override
	public void setVersion(Long version) throws RemoteException {
		_version = version;
	}

	@Override
	public String getName() throws RemoteException {
		return _name;
	}

	@Override
	public void setName(String name) throws RemoteException {
		_name = name;
	}

	@Override
	public TypeOfSport getTypeOfSport() throws RemoteException {
		return _typeOfSport;
	}

	@Override
	public void setTypeOfSport(TypeOfSport typeOfSport) throws RemoteException {
		_typeOfSport = typeOfSport;
	}

	@Override
	public IDepartment getDepartment() throws RemoteException {
		return _department;
	}

	@Override
	public void setDepartment(IDepartment department) throws RemoteException {
		_department = (IDepartmentRMI) department;
	}

	@Override
	public IUser getCoach() throws RemoteException {
		return _coach;
	}

	@Override
	public void setCoach(IUser coach) throws RemoteException {
		_coach = (IUserRMI) coach;
	}

	@Override
	public ILeagueRMI getLeague() throws RemoteException {
		return _league;
	}

	@Override
	public void setLeague(ILeague league) throws RemoteException {
		_league = (ILeagueRMI) league;
	}

	@Override
	public Set<IUserRMI> getMembers() throws RemoteException {
		return _members;
	}

	@Override
	public void setMembers(Set<IUser> members) throws RemoteException {
		_members = members.stream().map(UserConverterRMI::toRMI).collect(Collectors.toSet());
	}
}
