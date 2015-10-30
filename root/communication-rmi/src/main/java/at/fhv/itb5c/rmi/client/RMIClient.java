package at.fhv.itb5c.rmi.client;

import java.rmi.RemoteException;

import at.fhv.itb5c.commons.dto.rmi.IUserFactoryRMI;
import at.fhv.itb5c.logging.ILogger;

public class RMIClient implements ILogger {
	private static RMIClient _client;
	
	private String _host = "ec2-52-10-208-136.us-west-2.compute.amazonaws.com";
	private int _port = 1337;
	
	private UserFactoryStub _userFactoryStub;
	
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
			}
		}
		return _userFactoryStub;
	}
}