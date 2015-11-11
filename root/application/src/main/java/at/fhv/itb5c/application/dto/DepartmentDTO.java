package at.fhv.itb5c.application.dto;

import at.fhv.itb5c.commons.enums.TypeOfSport;

/**
 * Data Transfer Object for Departments.
 */
public class DepartmentDTO extends BaseDTO {
	private Long _headId;
	private String _name;
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

	public void setHeadId(Long headId) {
		_headId = headId;
	}
	
	public TypeOfSport getTypeOfSport() {
		return _typeOfSport;
	}
	
	public void setTypeOfSport(TypeOfSport typeOfSport) {
		_typeOfSport = typeOfSport;
	}
	
	@Override
	public String toString() {
		return 
				"DepartmentDTO\n" +
				"    -> Sport: " + _typeOfSport + "\n" + 
				"    -> Name:  " + _name + "\n" +
				"    -> ID:    " + _id + "\n" +
				"    -> Head:  " + _headId + "\n";
	}
}
