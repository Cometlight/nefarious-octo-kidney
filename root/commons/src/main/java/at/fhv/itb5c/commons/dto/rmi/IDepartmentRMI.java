package at.fhv.itb5c.commons.dto.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

import at.fhv.itb5c.commons.dto.IDepartment;

public interface IDepartmentRMI extends IDepartment, Remote {
	public void setId(Long id) throws RemoteException;

	public Long getId() throws RemoteException;

	public void setVersion(Long version) throws RemoteException;

	public Long getVersion() throws RemoteException;

	public void setName(String name) throws RemoteException;

	public String getName() throws RemoteException;
}
