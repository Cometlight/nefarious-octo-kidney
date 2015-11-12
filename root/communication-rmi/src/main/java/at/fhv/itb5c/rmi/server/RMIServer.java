package at.fhv.itb5c.rmi.server;

import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.LinkedList;
import java.util.List;

import at.fhv.itb5c.commons.property.PropertyManager;
import at.fhv.itb5c.logging.ILogger;
import at.fhv.itb5c.model.testdata.CreateTestData;

public class RMIServer implements ILogger{
	private static List<RMIServant> _servants;
	private static final String _host = PropertyManager.getInstance().getProperty("at.fhv.itb5c.rmi.host");
	private static final String _port = PropertyManager.getInstance().getProperty("at.fhv.itb5c.rmi.port");

	public static void main(String args[]) throws NumberFormatException, RemoteException {
		if(args.length>0){
			if(args[0].equals("test")){
				CreateTestData.run();
			}
		}
		CreateTestData.run();
		
		log.info("Starting RMI server ...");
		LocateRegistry.createRegistry(Integer.parseInt(_port));
		log.info("RMI server started!");
		
		_servants = new LinkedList<RMIServant>();
		try {
			// add servants to server
			_servants.add(new ApplicationFacadeRMI());

			// startup server by initializing all servants
			log.info("... initializing RMI servants...");
			for (RMIServant servant : _servants) {
				servant.init(_host, _port);
			}
		} catch (RemoteException | MalformedURLException e) {
			log.error(e.getMessage());
		}
	}
}