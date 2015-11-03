package at.fhv.itb5c.rmi.client;

import java.rmi.RemoteException;

import at.fhv.itb5c.commons.dto.rmi.IDepartmentFactoryRMI;
import at.fhv.itb5c.commons.dto.rmi.IUserFactoryRMI;
import at.fhv.itb5c.commons.property.PropertyManager;
import at.fhv.itb5c.logging.ILogger;

public class RMIClient implements ILogger {
	private static RMIClient _client;
	
	private static final String _host = PropertyManager.getInstance().getProperty("at.fhv.itb5c.rmi.host");
	private static final String _port = PropertyManager.getInstance().getProperty("at.fhv.itb5c.rmi.port");
	
	private UserFactoryStub _userFactoryStub;
	private DepartmentFactoryStub _departmentFactoryStub;
	
	private RMIClient(){
		
	}

	public static RMIClient getRMIClient(){
		if(_client == null){
			_client = new RMIClient();
		}
		return _client;
	}
	
	public IUserFactoryRMI getUserFactory() {
		if (_userFactoryStub == null) {
			try {
				_userFactoryStub = new UserFactoryStub();
				_userFactoryStub.init(_host, _port);
			} catch (RemoteException e) {
				log.error(e.getMessage());
				return null;
			}
		}
		return _userFactoryStub;
	}
	
	public IDepartmentFactoryRMI getDepartmentFactory() {
		if (_departmentFactoryStub == null) {
			try {
				_departmentFactoryStub = new DepartmentFactoryStub();
				_departmentFactoryStub.init(_host, _port);
			} catch (RemoteException e) {
				log.error(e.getMessage());
				return null;
			}
		}
		return _departmentFactoryStub;
	}
}