package at.fhv.itb5c.rmi.server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import at.fhv.itb5c.commons.dto.rmi.ILeagueRMI;
import at.fhv.itb5c.commons.enums.TypeOfSport;

public class LeagueRMI extends UnicastRemoteObject implements ILeagueRMI {
	private static final long serialVersionUID = 1L;
	private Long _id;
	private Long _version;
	private String _name;
	private TypeOfSport _typeOfSport;
	
	protected LeagueRMI() throws RemoteException {
		super();
	}

	@Override
	public void setId(Long id) throws RemoteException {
		_id = id;
	}

	@Override
	public Long getId() throws RemoteException {
		return _id;
	}

	@Override
	public void setVersion(Long version) throws RemoteException {
		_version = version;
	}

	@Override
	public Long getVersion() throws RemoteException {
		return _version;
	}

	@Override
	public void setName(String name) throws RemoteException {
		_name = name;
	}

	@Override
	public String getName() throws RemoteException {
		return _name;
	}

	@Override
	public void setTypeOfSport(TypeOfSport typeOfSport) throws RemoteException {
		_typeOfSport = typeOfSport;
	}

	@Override
	public TypeOfSport getTypeOfSport() throws RemoteException {
		return _typeOfSport;
	}
}
