package at.fhv.itb5c.rmi.server;

import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.LinkedList;
import java.util.List;

public class RMIServer {
	private static List<RMIServant> _servants;
	private static final String _host = "localhost";
	private static final int _port = 1337;

	public static void main(String args[]) {
		_servants = new LinkedList<RMIServant>();
		try {
			// add servants to server
			_servants.add(new UserFactoryServant());

			// startup server by initializing all servants
			for (RMIServant servant : _servants) {
				servant.init(_host, _port);
			}
		} catch (RemoteException | MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}