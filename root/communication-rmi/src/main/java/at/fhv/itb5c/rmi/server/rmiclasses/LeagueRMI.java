package at.fhv.itb5c.rmi.server.rmiclasses;

import java.rmi.RemoteException;

import at.fhv.itb5c.commons.dto.rmi.ILeagueRMI;
import at.fhv.itb5c.commons.enums.TypeOfSport;

public class LeagueRMI extends BaseRMI implements ILeagueRMI {
	private static final long serialVersionUID = 1L;
	
	private String _name;
	private TypeOfSport _typeOfSport;
	
	public LeagueRMI() throws RemoteException {
		super();
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

}
