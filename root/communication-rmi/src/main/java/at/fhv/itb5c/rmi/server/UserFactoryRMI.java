package at.fhv.itb5c.rmi.server;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.LinkedList;
import java.util.List;

import at.fhv.itb5c.application.controller.UserFactory;
import at.fhv.itb5c.commons.dto.IUser;
import at.fhv.itb5c.commons.dto.rmi.IUserFactoryRMI;
import at.fhv.itb5c.commons.dto.rmi.IUserRMI;
import at.fhv.itb5c.logging.ILogger;

public class UserFactoryRMI extends UnicastRemoteObject implements IUserFactoryRMI, RMIServant, ILogger {
	private static final long serialVersionUID = 1L;
	private UserFactory _factory;

	protected UserFactoryRMI() throws RemoteException {
		super();
		_factory = new UserFactory();
	}
	
	@Override
	public void init(String host, String port) throws RemoteException, MalformedURLException {
		log.info("... initializing UserFactoryRMI");
		Naming.rebind("rmi://" + host + ":" + port + "/UserFactory", this);
	}

	@Override
	public IUserRMI createUser() {
		log.debug("new user requested");
		IUser user = _factory.createUser();
		return UserConverterRMI.toRMI(user);
	}

	@Override
	public IUserRMI save(IUser user) {
		log.debug("saving user");
		IUserRMI userrmi = (IUserRMI) user;
		IUserRMI saveUser = UserConverterRMI.toRMI(_factory.save(UserConverterRMI.toDTO(userrmi)));
		log.debug("saved user: " + saveUser);

		return saveUser;	
	}

	@Override
	public List<IUserRMI> findUsers(String firstName, String lastName, Long departmentId, Boolean membershipFeePaid)
			throws RemoteException {
		// search results
		List<IUser> userDTO = _factory.findUsers(firstName, lastName, departmentId, membershipFeePaid);
		
		// convert to rmi
		List<IUserRMI> userRMI = new LinkedList<IUserRMI>();
		for(IUser user : userDTO){
			userRMI.add(UserConverterRMI.toRMI(user));
		}
		
		return userRMI;
	}

	@Override
	public IUserRMI login(String username, String password) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}
}