package at.fhv.itb5c.rmi.server;

import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.LinkedList;
import java.util.List;

import at.fhv.itb5c.commons.property.PropertyManager;
import at.fhv.itb5c.logging.ILogger;

public class RMIServer implements ILogger{
	private static List<RMIServant> _servants;
	private static final String _host = PropertyManager.getInstance().getProperty("at.fhv.itb5c.rmi.host");
	private static final String _port = PropertyManager.getInstance().getProperty("at.fhv.itb5c.rmi.port");

	public static void main(String args[]) {
		log.info("Starting RMI server ...");
		
		_servants = new LinkedList<RMIServant>();
		try {
			// add servants to server
			_servants.add(new UserFactoryRMI());
			_servants.add(new DepartmentFactoryRMI());
			_servants.add(new TeamFactoryRMI());

			// startup server by initializing all servants
			log.info("... initializing RMI servants ...");
			for (RMIServant servant : _servants) {
				servant.init(_host, _port);
			}
		} catch (RemoteException | MalformedURLException e) {
			log.error(e.getMessage());
		}
		
		log.info("RMI server started!");
	}
}