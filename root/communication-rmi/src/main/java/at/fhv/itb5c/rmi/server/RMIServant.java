package at.fhv.itb5c.rmi.server;

import java.net.MalformedURLException;
import java.rmi.RemoteException;

public interface RMIServant {
	public void init(String host, String port) throws RemoteException, MalformedURLException;
}
