package at.fhv.itb5c.commons.dto.rmi;

import java.rmi.RemoteException;

import at.fhv.itb5c.commons.enums.TypeOfSport;

public interface ILeagueRMI extends IBaseRMI {
	String getName() throws RemoteException;
	void setName(String name) throws RemoteException;
	
	public TypeOfSport getTypeOfSport() throws RemoteException;
	public void setTypeOfSport(TypeOfSport typeOfSport) throws RemoteException;
}
