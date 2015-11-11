package at.fhv.itb5c.model.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;

import at.fhv.itb5c.commons.enums.TypeOfSport;

@Entity
public class Team extends PersistableObject {
	@Column(name = "name", nullable = false)
	private String _name;

	@Column(name = "typeOfSport", nullable = false)
	private TypeOfSport _typeOfSport;

	@Column(name = "departmentId", nullable = true)
	private Long _departmentId;

	@Column(name = "coachId", nullable = true)
	private Long _coachId;

	@Column(name = "leagueId", nullable = true)
	private Long _leagueId;

	@Column(name = "members", nullable = true)
	private Set<Long> _members;

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

	public Long getDepartmentId() {
		return _departmentId;
	}

	public void setDepartmentId(Long departmentId) {
		_departmentId = departmentId;
	}

	public Long getCoachId() {
		return _coachId;
	}

	public void setCoachId(Long coachId) {
		_coachId = coachId;
	}

	public Long getLeagueId() {
		return _leagueId;
	}

	public void setLeagueId(Long leagueId) {
		_leagueId = leagueId;
	}

	public Set<Long> getMembers() {
		return _members;
	}

	public void setMembers(Set<Long> members) {
		_members = members;
	}
}
