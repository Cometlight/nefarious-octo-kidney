package at.fhv.itb5c.commons.dto.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

import at.fhv.itb5c.commons.dto.IDepartment;
import at.fhv.itb5c.commons.dto.IUser;
import at.fhv.itb5c.commons.enums.TypeOfSport;

public interface IDepartmentRMI extends IDepartment, Remote {
	void setId(Long id) throws RemoteException;

	Long getId() throws RemoteException;

	void setVersion(Long version) throws RemoteException;

	Long getVersion() throws RemoteException;

	void setName(String name) throws RemoteException;

	String getName() throws RemoteException;
	
	void setTypeOfSport(TypeOfSport typeOfSport) throws RemoteException;
	
	TypeOfSport getTypeOfSport() throws RemoteException;
	
	void setHead(IUser head) throws RemoteException;
	
	IUserRMI getHead() throws RemoteException;
}
