package at.fhv.itb5c.commons.dto;

import java.util.Map;

public class MessageDTO extends BaseDTO {
	private String _kind;
	private Map<String, Object> _data;
	
	public MessageDTO(String kind, Map<String, Object> data) {
		this._kind = kind;
		this._data = data;
	}

	public String getKind() {
		return _kind;
	}

	public Object get(String prop) {
		return this._data.get(prop);
	}

	public Map<String, Object> getData() {
		return _data;
	}
}
