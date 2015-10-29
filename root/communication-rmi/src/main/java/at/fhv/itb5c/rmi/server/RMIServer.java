package at.fhv.itb5c.rmi.server;

import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.LinkedList;
import java.util.List;

import at.fhv.itb5c.logging.ILogger;

public class RMIServer implements ILogger{
	private static List<RMIServant> _servants;
	private static final String _host = "localhost";
	private static final int _port = 1337;

	public static void main(String args[]) {
		log.info("Starting RMI server ...");
		
		_servants = new LinkedList<RMIServant>();
		try {
			// add servants to server
			_servants.add(new UserFactoryRMI());

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