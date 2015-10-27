package at.fhv.itb5c.rmi.client;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import at.fhv.itb5c.rmi.interfaces.IUser;
import at.fhv.itb5c.rmi.interfaces.IUserFactory;

public class UserFactoryStub extends UnicastRemoteObject implements IUserFactory {
	private static final long serialVersionUID = 1L;
	
	protected UserFactoryStub() throws RemoteException {
		super();
	}

	@Override
	public IUser createUser(String firstName) {
		return null;
	}
}
