package at.fhv.itb5c.rmi.client;

import java.rmi.RemoteException;

import at.fhv.itb5c.commons.property.PropertyManager;
import at.fhv.itb5c.logging.ILogger;

public class RMIClient implements ILogger {
	private static RMIClient _client;

	private static final String _host = PropertyManager.getInstance().getProperty("at.fhv.itb5c.rmi.host");
	private static final String _port = PropertyManager.getInstance().getProperty("at.fhv.itb5c.rmi.port");

	private ApplicationFacadeRMIStub _applicationFacadeRMIStub;

	private RMIClient() {

	}

	public void startUp() {
		try {
			_applicationFacadeRMIStub = new ApplicationFacadeRMIStub();
			_applicationFacadeRMIStub.init(_host, _port);
		} catch (RemoteException e) {
			log.error(e);
		}
	}

	public void close() {
		throw new UnsupportedOperationException();
	}

	public static RMIClient getRMIClient() {
		if (_client == null) {
			_client = new RMIClient();
		}
		return _client;
	}

	public ApplicationFacadeRMIStub getApplicationFacade() {
		return _applicationFacadeRMIStub;
	}
}