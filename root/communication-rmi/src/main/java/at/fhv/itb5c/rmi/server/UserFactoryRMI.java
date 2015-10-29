package at.fhv.itb5c.rmi.server;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import at.fhv.itb5c.application.controller.UserFactoryImpl;
import at.fhv.itb5c.commons.dto.IUser;
import at.fhv.itb5c.commons.dto.rmi.IUserFactoryRMI;
import at.fhv.itb5c.commons.dto.rmi.IUserRMI;

public class UserFactoryRMI extends UnicastRemoteObject implements IUserFactoryRMI, RMIServant {
	private static final long serialVersionUID = 1L;
	private UserFactoryImpl _factory;

	protected UserFactoryRMI() throws RemoteException {
		super();
		_factory = new UserFactoryImpl();
	}

	@Override
	public IUserRMI createUser() {
		IUser user = _factory.createUser();
		return UserConverterRMI.toRMI(user);
	}

	@Override
	public void save(IUser user) {
		_factory.save(UserConverterRMI.toRMI(user));
	}
	
	@Override
	public void init(String host, int port) throws RemoteException, MalformedURLException {
		Naming.rebind("rmi://" + host + ":" + port + "/UserFactory", this);
	}
}