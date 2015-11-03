package at.fhv.itb5c.rmi.client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import at.fhv.itb5c.commons.dto.rmi.IDepartmentFactoryRMI;
import at.fhv.itb5c.commons.dto.rmi.IDepartmentRMI;
import at.fhv.itb5c.logging.ILogger;

public class DepartmentFactoryStub extends UnicastRemoteObject implements IDepartmentFactoryRMI, RMIStub, ILogger {
	private static final long serialVersionUID = 1L;
	private IDepartmentFactoryRMI _departmentFactory;
	
	protected DepartmentFactoryStub() throws RemoteException {
		super();
	}

	@Override
	public void init(String host, String port) {
		Object obj;
		try {
			obj = Naming.lookup("rmi://" + host + ":" + port + "/DepartmentFactory");
			_departmentFactory = (IDepartmentFactoryRMI) obj;
		} catch (MalformedURLException | NotBoundException | RemoteException e) {
			log.error(e.getMessage());
		}
	}

	@Override
	public List<IDepartmentRMI> getAllDepartments() throws RemoteException {
		return _departmentFactory.getAllDepartments();
	}

}
