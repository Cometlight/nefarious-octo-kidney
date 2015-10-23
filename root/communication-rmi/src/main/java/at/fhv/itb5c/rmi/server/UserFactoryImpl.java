package at.fhv.itb5c.rmi.server;

import java.rmi.RemoteException;

import at.fhv.itb5c.rmi.interfaces.IUser;
import at.fhv.itb5c.rmi.interfaces.IUserFactory;

public class UserFactoryImpl implements IUserFactory {
	public UserFactoryImpl() throws RemoteException {
		super();
	}

	@Override
	public IUser createUser(String firstName) {
		return null;
	}
}