package at.fhv.itb5c.application.dto;

import at.fhv.itb5c.commons.enums.TypeOfSport;

public class LeagueDTO extends BaseDTO {
	private String _name;
	private TypeOfSport _typeOfSport;
	
	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	public TypeOfSport getTypeOfSport() {
		return _typeOfSport;
	}

	public void setTypeOfSport(TypeOfSport typeOfSport) {
		_typeOfSport = typeOfSport;
	}
}
