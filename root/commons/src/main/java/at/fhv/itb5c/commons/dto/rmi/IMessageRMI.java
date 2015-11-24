package at.fhv.itb5c.commons.dto.rmi;

import java.rmi.RemoteException;
import java.util.Map;

public interface IMessageRMI extends IBaseRMI {
	String getKind() throws RemoteException;

	Object get(String prop) throws RemoteException;

	Map<String, Object> getData() throws RemoteException;
}
