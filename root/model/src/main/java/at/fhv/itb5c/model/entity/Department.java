package at.fhv.itb5c.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Department extends PersistableObject {
	@Column(name = "name", nullable = false)
	private String _name;
	
	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}
}
