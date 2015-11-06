package at.fhv.itb5c.commons.dto.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import at.fhv.itb5c.commons.dto.IUser;
import at.fhv.itb5c.commons.dto.IUserFactory;

public interface IUserFactoryRMI extends Remote, IUserFactory {
	@Override
	public IUserRMI createUser() throws RemoteException;
	@Override
	public IUserRMI save(IUser user) throws RemoteException;
	@Override
	public List<IUserRMI> findUsers(String firstName, String lastName, Long departmentId, Boolean membershipFeePaid) throws RemoteException;
	@Override
	public IUserRMI login(String username, String password) throws RemoteException;
}