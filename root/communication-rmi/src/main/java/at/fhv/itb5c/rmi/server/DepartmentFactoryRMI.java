package at.fhv.itb5c.rmi.server;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.LinkedList;
import java.util.List;

import at.fhv.itb5c.application.controller.DepartmentFactory;
import at.fhv.itb5c.commons.dto.IDepartment;
import at.fhv.itb5c.commons.dto.rmi.IDepartmentFactoryRMI;
import at.fhv.itb5c.commons.dto.rmi.IDepartmentRMI;
import at.fhv.itb5c.logging.ILogger;

public class DepartmentFactoryRMI extends UnicastRemoteObject implements IDepartmentFactoryRMI, RMIServant, ILogger {
	private static final long serialVersionUID = 1L;
	private DepartmentFactory _factory;
	
	protected DepartmentFactoryRMI() throws RemoteException {
		super();
		_factory = new DepartmentFactory();
	}

	@Override
	public void init(String host, int port) throws RemoteException, MalformedURLException {
		log.info("... initializing UserFactoryRMI");
		Naming.rebind("rmi://" + host + ":" + port + "/DepartmentFactory", this);
	}

	@Override
	public List<IDepartmentRMI> getAllDepartments() throws RemoteException {
		List<IDepartment> depts = null;
		try {
			depts = _factory.getAllDepartments();
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		List<IDepartmentRMI> deptsNew = new LinkedList<IDepartmentRMI>();
		for(IDepartment dept : depts){
			deptsNew.add(DepartmentConverterRMI.toRMI(dept));
		}
		return deptsNew;
	}

}
