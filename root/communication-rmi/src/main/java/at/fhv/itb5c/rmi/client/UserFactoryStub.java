package at.fhv.itb5c.rmi.client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import at.fhv.itb5c.commons.dto.IUser;
import at.fhv.itb5c.commons.dto.rmi.IUserFactoryRMI;
import at.fhv.itb5c.commons.dto.rmi.IUserFactoryRMI;

public class UserFactoryStub extends UnicastRemoteObject implements IUserFactoryRMI, RMIStub {
	private static final long serialVersionUID = 1L;

	private IUserFactoryRMI _userFactory;

	protected UserFactoryStub() throws RemoteException {
		super();
	}

	@Override
	public IUser createUser() {
		try {
			return _userFactory.createUser();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void save(IUser user) {
		try {
			_userFactory.save(user);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void init(String host, int port){
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