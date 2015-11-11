package at.fhv.itb5c.rmi.server.rmiclasses;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import at.fhv.itb5c.commons.dto.rmi.IBaseRMI;

public class BaseRMI extends UnicastRemoteObject implements IBaseRMI {
	private static final long serialVersionUID = 1L;

	private Long _id;
	private Long _version;
	
	protected BaseRMI() throws RemoteException {
		super();
	}
	
	@Override
	public Long getId() throws RemoteException {
		return _id;
	}

	@Override
	public Long getVersion() throws RemoteException {
		return _version;
	}

	@Override
	public void setId(Long id) throws RemoteException {
		_id = id;
	}

	@Override
	public void setVersion(Long version) throws RemoteException {
		_version = version;
	}
}
