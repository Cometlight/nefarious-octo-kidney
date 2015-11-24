package at.fhv.itb5c.rmi.server.rmiclasses;

import java.rmi.RemoteException;
import java.util.Map;

import at.fhv.itb5c.commons.dto.rmi.IMessageRMI;

public class MessageRMI extends BaseRMI implements IMessageRMI {
	private static final long serialVersionUID = 1L;
	private String _kind;
	private Map<String, Object> _data;

	protected MessageRMI() throws RemoteException {
		super();
	}
	
	public MessageRMI(String kind, Map<String, Object> data) throws RemoteException {
		this._kind = kind;
		this._data = data;
	}

	@Override
	public String getKind() throws RemoteException {
		return _kind;
	}

	@Override
	public Object get(String prop) throws RemoteException {
		return this._data.get(prop);
	}

	@Override
	public Map<String, Object> getData() throws RemoteException {
		return _data;
	}

}
