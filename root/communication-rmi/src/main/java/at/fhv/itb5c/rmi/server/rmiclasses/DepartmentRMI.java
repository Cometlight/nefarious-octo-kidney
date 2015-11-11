package at.fhv.itb5c.rmi.server.rmiclasses;

import java.rmi.RemoteException;

import at.fhv.itb5c.commons.dto.rmi.IDepartmentRMI;
import at.fhv.itb5c.commons.enums.TypeOfSport;

public class DepartmentRMI extends BaseRMI implements IDepartmentRMI {
	private static final long serialVersionUID = 1L;

	private Long _headId;
	private String _name;
	private TypeOfSport _typeOfSport;
	
	public DepartmentRMI() throws RemoteException {
		super();
	}
	
	@Override
	public String getName() throws RemoteException {
		return _name;
	}

	@Override
	public void setName(String name) {
		_name = name;
	}

	@Override
	public Long getHeadId() throws RemoteException {
		return _headId;
	}

	@Override
	public void setHeadId(Long headId) {
		_headId = headId;
	}

	@Override
	public TypeOfSport getTypeOfSport() throws RemoteException {
		return _typeOfSport;
	}

	@Override
	public void setTypeOfSport(TypeOfSport typeOfSport) {
		_typeOfSport = typeOfSport;
	}

}
