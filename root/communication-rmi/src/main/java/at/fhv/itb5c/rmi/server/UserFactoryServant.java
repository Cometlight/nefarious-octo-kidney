package at.fhv.itb5c.rmi.server;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import at.fhv.itb5c.commons.dto.IUser;
import at.fhv.itb5c.commons.dto.IUserFactory;
import at.fhv.itb5c.commons.dto.rmi.IUserFactoryRMI;

public class UserFactoryServant extends UnicastRemoteObject implements IUserFactory, RMIServant {
	private static final long serialVersionUID = 1L;
	private IUserFactoryRMI _userFactory;

	protected UserFactoryServant() throws RemoteException {
		super();
		_userFactory = (IUserFactoryRMI) new UserFactoryImplRMI();
	}

	@Override
	public IUser createUser() {
		return _userFactory.createUser();
	}

	@Override
	public void save(IUser user) {
		_userFactory.save(user);
	}

	@Override
	public void init(String host, int port) throws RemoteException, MalformedURLException {
		Naming.rebind("rmi://" + host + ":" + port + "/UserFactory", _userFactory);
	}
}