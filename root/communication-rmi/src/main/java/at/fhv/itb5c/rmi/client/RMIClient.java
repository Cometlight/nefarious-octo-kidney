package at.fhv.itb5c.rmi.client;

import java.rmi.RemoteException;

import at.fhv.itb5c.commons.dto.IUserFactory;

public class RMIClient {
	private static RMIClient _client;
	
	private String _host = "localhost";
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
	
	public IUserFactory getUserFactory() {
		if (_userFactoryStub == null) {
			try {
				_userFactoryStub = new UserFactoryStub();
				_userFactoryStub.init(_host, _port);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return _userFactoryStub;
	}
}