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
	public void init(String host, String port) {
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
		log.debug("Befor save -> " + userToSTring(user));
		IUserRMI saveUser = _userFactory.save((IUserRMI) user);
		log.debug("After save -> " + userToSTring(saveUser));
		return saveUser;
	}

	@Override
	public List<IUserRMI> findUsers(String firstName, String lastName, Long departmentId, Boolean membershipFeePaid)
			throws RemoteException {
		return _userFactory.findUsers(firstName, lastName, departmentId, membershipFeePaid);
	}
	
	private String userToSTring(IUser user) {
		try {
			return "UserDTO [_id=" + user.getId() + ", _version=" + user.getVersion() + ", _firstName=" + user.getFirstName() + ", _lastName="
					+ user.getLastName() + ", _email=" + user.getEmail() + ", _telephoneNumber=" + user.getTelephoneNumber() + ", _gender=" + user.getGender()
					+ ", _address=" + user.getAddress() + ", _dateOfBirth=" + user.getDateOfBirth() + ", _membershipFee=" + user.getMembershipFee()
					+ ", _roles=" + user.getRoles() + ", _typeOfSports=" + user.getTypeOfSports() + "]";
		} catch (Exception e) {
			return e.getStackTrace().toString();
		}
	}

	@Override
	public IUserRMI login(String username, String password) throws RemoteException {
		return _userFactory.login(username, password);
	}
}