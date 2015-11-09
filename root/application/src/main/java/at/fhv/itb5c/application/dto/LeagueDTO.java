package at.fhv.itb5c.application.dto;

import at.fhv.itb5c.commons.dto.ILeague;
import at.fhv.itb5c.commons.enums.TypeOfSport;

public class LeagueDTO implements ILeague {
	private Long _id;
	private Long _version;
	private String _name;
	private TypeOfSport _typeOfSport;

	@Override
	public Long getId() {
		return _id;
	}

	@Override
	public void setId(Long id) {
		_id = id;
	}

	@Override
	public Long getVersion() {
		return _version;
	}

	@Override
	public void setVersion(Long version) {
		_version = version;
	}

	@Override
	public String getName() {
		return _name;
	}

	@Override
	public void setName(String name) {
		_name = name;
	}

	@Override
	public TypeOfSport getTypeOfSport() {
		return _typeOfSport;
	}

	@Override
	public void setTypeOfSport(TypeOfSport typeOfSport) {
		_typeOfSport = typeOfSport;
	}

	@Override
	public String toString() {
		return "LeagueDTO [_id=" + _id + ", _version=" + _version + ", _name=" + _name + ", _typeOfSport="
				+ _typeOfSport + "]";
	}
}
