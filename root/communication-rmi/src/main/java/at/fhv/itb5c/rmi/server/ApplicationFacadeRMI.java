package at.fhv.itb5c.rmi.server;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Collection;

import at.fhv.itb5c.application.ApplicationFacade;
import at.fhv.itb5c.application.dto.UserDTO;
import at.fhv.itb5c.commons.dto.rmi.IApplicationFacadeRMI;
import at.fhv.itb5c.commons.dto.rmi.IUserRMI;
import at.fhv.itb5c.logging.ILogger;
import at.fhv.itb5c.rmi.server.converter.ConverterUserRMI;

public class ApplicationFacadeRMI extends UnicastRemoteObject implements IApplicationFacadeRMI, RMIServant, ILogger {
	private static final long serialVersionUID = -6290768314413969216L;
	private ApplicationFacade _applicationFacade;
	
	protected ApplicationFacadeRMI() throws RemoteException {
		super();
		_applicationFacade = new ApplicationFacade();
	}

	@Override
	public void init(String host, String port) throws RemoteException, MalformedURLException {
		Naming.rebind("rmi://" + host + ":" + port + "/ApplicationFacadeRMI", this);
	}

	@Override
	public IUserRMI createUser() throws RemoteException {
		return ConverterUserRMI.toRMI(_applicationFacade.createUser());
	}

	@Override
	public Collection<IUserRMI> findUsers(String firstName, String lastName, Long departmentId,
			Boolean membershipFeePaid) throws RemoteException {
		return ConverterUserRMI.toRMI(_applicationFacade.findUsers(firstName, lastName, departmentId, membershipFeePaid));
	}

	@Override
	public Collection<IUserRMI> findUsersSimple(String name) throws RemoteException {
		return ConverterUserRMI.toRMI(_applicationFacade.findUsersSimple(name));
	}

	@Override
	public IUserRMI saveUser(IUserRMI user) throws RemoteException {
		UserDTO userDTO = ConverterUserRMI.toDTO(user);
		return ConverterUserRMI.toRMI(_applicationFacade.saveUser(userDTO));
	}

}
