package at.fhv.itb5c.application.dto;

import java.util.Set;
import java.util.stream.Collectors;

import at.fhv.itb5c.commons.dto.IDepartment;
import at.fhv.itb5c.commons.dto.ILeague;
import at.fhv.itb5c.commons.dto.ITeam;
import at.fhv.itb5c.commons.dto.IUser;
import at.fhv.itb5c.commons.enums.TypeOfSport;

public class TeamDTO implements ITeam {
	private Long _id;
	private Long _version;
	private String _name;
	private TypeOfSport _typeOfSport;
	private IDepartment _department;
	private IUser _coach;
	private ILeague _league;
	private Set<IUser> _members;

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
	public IDepartment getDepartment() {
		return _department;
	}

	@Override
	public void setDepartment(IDepartment department) {
		_department = department;
	}

	@Override
	public IUser getCoach() {
		return _coach;
	}

	@Override
	public void setCoach(IUser coach) {
		_coach = coach;
	}

	@Override
	public ILeague getLeague() {
		return _league;
	}

	@Override
	public void setLeague(ILeague league) {
		this._league = league;
	}

	@Override
	public Set<IUser> getMembers() {
		return _members;
	}

	@Override
	public void setMembers(Set<IUser> members) {
		_members = members;
	}

	@Override
	public String toString() {
		String memberIDs = _members.stream().map(member -> member.toString()).collect(Collectors.joining(","));
		return "DepartmentDTO [_id=" + _id + ", _version=" + _version + ", _name=" + _name + ", _typeOfSport=" + _typeOfSport + ", _department=" + _department + ", _coach=" + _coach + ", _league=" + _league + ", _members(ID)=" + memberIDs;
	}

}
