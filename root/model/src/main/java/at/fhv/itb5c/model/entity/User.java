package at.fhv.itb5c.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {
	@Id
	@Column(name = "USERID", nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long _id;
	@Column(name = "NAME", nullable = false)
	private String _name;

	public User() {

	}

	public long getId() {
		return _id;
	}
	
	public void setId(long id) {
		this._id = id;
	}

	public String getName() {
		return _name;
	}
	
	public void setName(String name) {
		_name = name;
	}
}