package at.fhv.itb5c.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

import at.fhv.itb5c.commons.enums.TypeOfSport;

@Entity
public class Department extends PersistableObject {
	@Column(name = "name", nullable = false)
	private String _name;
	
	@Column(name = "head", nullable = false)
	private Long _headId;
	
	@Column(name = "typeOfSport", nullable = false)
	private TypeOfSport _typeOfSport;
	
	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	public Long getHeadId() {
		return _headId;
	}

	public void setHeadId(Long head) {
		_headId = head;
	}

	public TypeOfSport getTypeOfSport() {
		return _typeOfSport;
	}

	public void setTypeOfSport(TypeOfSport typeOfSport) {
		_typeOfSport = typeOfSport;
	}
}
