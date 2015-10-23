package at.fhv.itb5c.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

@Entity
public abstract class PersistableObject {
	@Id
	@Column(name = "ID", nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long _id;

	@Version
	private long _version;

	public long getId() {
		return _id;
	}

	public void setId(long id) {
		_id = id;
	}

	public long getVersion() {
		return _version;
	}

	protected void setVersion(long version) {
		_version = version;
	}
}
