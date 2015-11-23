package at.fhv.itb5c.commons.messaging;

import java.util.Map;

/**
 * Represents a message received from a messaging service.
 */
public class Message {
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
}
