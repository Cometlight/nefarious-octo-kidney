package at.fhv.itb5c.commons.dto.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

import at.fhv.itb5c.commons.enums.TypeOfSport;

public interface IDepartmentRMI extends Remote, IBaseRMI {
	String getName() throws RemoteException;
	void setName(String name) throws RemoteException;
	
	Long getHeadId() throws RemoteException;
	void setHeadId(Long headId) throws RemoteException;
	
	TypeOfSport getTypeOfSport() throws RemoteException;
	void setTypeOfSport(TypeOfSport typeOfSport) throws RemoteException;
}
