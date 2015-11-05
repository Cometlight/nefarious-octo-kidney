package at.fhv.itb5c.rmi.server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Set;

import at.fhv.itb5c.commons.dto.IDepartment;
import at.fhv.itb5c.commons.dto.IUser;
import at.fhv.itb5c.commons.dto.rmi.ITeamRMI;
import at.fhv.itb5c.commons.enums.TypeOfSport;

public class TeamRMI extends UnicastRemoteObject implements ITeamRMI {
	private static final long serialVersionUID = 1l;

	private Long _id;
	private Long _version;
	private String _name;
	private TypeOfSport _typeOfSport;
	private IDepartment _department;
	private IUser _coach;
	private int _league;
	private Set<IUser> _members;
	
	protected TeamRMI() throws RemoteException {
		super();
	}

	@Override
	public Long getId() {
		return _id;
	}

	@Override
	public void setId(Long id) {
		_id = id;
	}

	@Override
	public Long getVersion() {
		return _version;
	}

	@Override
	public void setVersion(Long version) {
		_version = version;
	}

	@Override
	public String getName() {
		return _name;
	}

	@Override
	public void setName(String name) {
		_name = name;
	}

	@Override
	public TypeOfSport getTypeOfSport() {
		return _typeOfSport;
	}

	@Override
	public void setTypeOfSport(TypeOfSport typeOfSport) {
		_typeOfSport = typeOfSport;
	}

	@Override
	public IDepartment getDepartment() {
		return _department;
	}

	@Override
	public void setDepartment(IDepartment department) {
		_department = department;
	}

	@Override
	public IUser getCoach() {
		return _coach;
	}

	@Override
	public void setCoach(IUser coach) {
		_coach = coach;
	}

	@Override
	public int getLeague() {
		return _league;
	}

	@Override
	public void setLeague(int league) {
		_league = league;
	}

	@Override
	public Set<IUser> getMembers() {
		return _members;
	}

	@Override
	public void setMembers(Set<IUser> members) {
		_members = members;
	}
}
