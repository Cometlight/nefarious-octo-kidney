package at.fhv.itb5c.rmi.client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import at.fhv.itb5c.commons.dto.IUser;
import at.fhv.itb5c.commons.dto.rmi.IUserFactoryRMI;
import at.fhv.itb5c.commons.dto.rmi.IUserRMI;
import at.fhv.itb5c.logging.ILogger;

public class UserFactoryStub extends UnicastRemoteObject implements IUserFactoryRMI, RMIStub, ILogger {
	private static final long serialVersionUID = 1L;

	private IUserFactoryRMI _userFactory;

	protected UserFactoryStub() throws RemoteException {
		super();
	}
	
	@Override
	public void init(String host, int port) {
		Object obj;
		try {
			obj = Naming.lookup("rmi://" + host + ":" + port + "/UserFactory");
			_userFactory = (IUserFactoryRMI) obj;
		} catch (MalformedURLException | NotBoundException | RemoteException e) {
			log.error(e.getMessage());
		}
	}

	@Override
	public IUserRMI createUser() throws RemoteException {
		return _userFactory.createUser();
	}

	@Override
	public IUserRMI save(IUser user) throws RemoteException {
		IUserRMI saveUser = _userFactory.save((IUserRMI) user);
		log.debug("saved user: " + saveUser);
		return saveUser;
	}

	@Override
	public List<IUserRMI> findUsers(String firstName, String lastName, Long departmentId, Boolean membershipFeePaid)
			throws RemoteException {
		return _userFactory.findUsers(firstName, lastName, departmentId, membershipFeePaid);
	}
}