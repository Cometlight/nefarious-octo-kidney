package at.fhv.itb5c.rmi.server;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Collection;

import at.fhv.itb5c.commons.dto.rmi.IUserRMI;

public interface IApplicationFacadeRMI extends Remote {
	IUserRMI createUser() throws RemoteException;
	Collection<IUserRMI> findUsers(String firstName, String lastName, Long departmentId, Boolean membershipFeePaid) throws RemoteException;
	Collection<IUserRMI> findUsersSimple(String name) throws RemoteException;
	IUserRMI saveUser(IUserRMI user) throws RemoteException;
}
