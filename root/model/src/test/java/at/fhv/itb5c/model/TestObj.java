package at.fhv.itb5c.model;

import javax.persistence.Column;
import javax.persistence.Entity;

import at.fhv.itb5c.model.entity.PersistableObject;

@Entity
public class TestObj extends PersistableObject {
	@Column(name = "value", nullable = true)
	private int _value;

	public TestObj() {
	}

	public TestObj(int value) {
		_value = value;
	}

	public int getValue() {
		return _value;
	}

	public void setValue(int value) {
		_value = value;
	}
}
