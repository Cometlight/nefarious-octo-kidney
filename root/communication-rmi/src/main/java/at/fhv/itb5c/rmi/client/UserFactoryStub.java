package at.fhv.itb5c.rmi.client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import at.fhv.itb5c.commons.dto.IUser;
import at.fhv.itb5c.commons.dto.rmi.IUserFactoryRMI;
import at.fhv.itb5c.commons.dto.rmi.IUserRMI;

public class UserFactoryStub extends UnicastRemoteObject implements IUserFactoryRMI, RMIStub {
	private static final long serialVersionUID = 1L;

	private IUserFactoryRMI _userFactory;

	protected UserFactoryStub() throws RemoteException {
		super();
	}

	@Override
	public IUserRMI createUser() throws RemoteException {
		return _userFactory.createUser();
	}

	@Override
	public void save(IUser user) throws RemoteException {
		_userFactory.save((IUserRMI) user);
	}

	public void init(String host, int port) {
		Object obj;
		try {
			obj = Naming.lookup("rmi://" + host + ":" + port + "/UserFactory");
			_userFactory = (IUserFactoryRMI) obj;
		} catch (MalformedURLException | NotBoundException | RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}