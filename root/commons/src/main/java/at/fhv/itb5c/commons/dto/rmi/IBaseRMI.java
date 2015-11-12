package at.fhv.itb5c.commons.dto.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * This is the base RMI interface for all entities.
 */
public interface IBaseRMI extends Remote {
	public Long getId() throws RemoteException;
	public void setId(Long id) throws RemoteException;

	public Long getVersion() throws RemoteException;
	public void setVersion(Long version) throws RemoteException;
}
