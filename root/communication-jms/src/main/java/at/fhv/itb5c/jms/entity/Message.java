package at.fhv.itb5c.jms.entity;

import java.io.Serializable;
import java.util.Map;

/**
 * Represents a message received from a messaging service.
 */
public class Message implements Serializable {
	private static final long serialVersionUID = 1L;
	private String _kind;
	private Map<String, Object> _data;

	public Message(String kind, Map<String, Object> data) {
		this._kind = kind;
		this._data = data;
	}

	public String getKind() {
		return this._kind;
	}

	public Object get(String prop) {
		return this._data.get(prop);
	}

	@Override
	public String toString() {
		return "Message [_kind=" + _kind + ", _data=" + _data + "]";
	}
}
