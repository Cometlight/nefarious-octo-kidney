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
	private Long _id;

	@Version
	private Long _version;

	public Long getId() {
		return _id;
	}

	public void setId(Long id) {
		_id = id;
	}

	public Long getVersion() {
		return _version;
	}

	public void setVersion(Long version) {
		_version = version;
	}
}
