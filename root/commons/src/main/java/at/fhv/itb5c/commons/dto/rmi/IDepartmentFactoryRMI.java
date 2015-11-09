package at.fhv.itb5c.commons.dto.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import at.fhv.itb5c.commons.dto.IDepartmentFactory;

public interface IDepartmentFactoryRMI extends IDepartmentFactory, Remote {
	@Override
	List<IDepartmentRMI> getAllDepartments() throws RemoteException;
}
