package at.fhv.itb5c.commons.dto.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

import at.fhv.itb5c.commons.dto.IUser;
import at.fhv.itb5c.commons.dto.IUserFactory;

public interface IUserFactoryRMI extends Remote, IUserFactory {
	@Override
	public IUserRMI createUser() throws RemoteException;
	@Override
	public void save(IUser user) throws RemoteException;
}