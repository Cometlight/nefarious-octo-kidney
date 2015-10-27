package at.fhv.itb5c.rmi.client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import at.fhv.itb5c.commons.dto.IUser;
import at.fhv.itb5c.commons.dto.IUserFactory;

public class UserFactoryStub extends UnicastRemoteObject implements IUserFactory {
	private static final long serialVersionUID = 1L;

	private IUserFactory _userFactory;

	protected UserFactoryStub() throws RemoteException {
		super();
	}

	@Override
	public IUser createUser() {
		return _userFactory.createUser();
	}

	@Override
	public void save(IUser user) {
		_userFactory.save(user);
	}
	
	public void init(String host, int port){
		Object obj;
		try {
			obj = Naming.lookup("rmi://" + host + ":" + port + "/UserFactory");
			_userFactory = (IUserFactory) obj;
		} catch (MalformedURLException | NotBoundException | RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
