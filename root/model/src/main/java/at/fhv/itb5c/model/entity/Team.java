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

	@Column(name = "department", nullable = true)
	private Department _department;

	@Column(name = "departmentId", nullable = true)
	private Long _departmentId; // needed for search; may be refactored if the
								// need arises

	@Column(name = "coach", nullable = true)
	private User _coach;

	@Column(name = "league", nullable = true)
	private League _league;

	@Column(name = "leagueId", nullable = true)
	private Long _leagueId; // needed for search; may be refactored if the need
							// arises

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

	public Department getDepartment() {
		return _department;
	}

	public void setDepartment(Department department) {
		_department = department;
		_departmentId = department == null ? null : department.getId();
	}

	public User getCoach() {
		return _coach;
	}

	public void setCoach(User coach) {
		_coach = coach;
	}

	public League getLeague() {
		return _league;
	}

	public void setLeague(League league) {
		_league = league;
		_leagueId = league == null ? null : league.getId();
	}

	public Set<Long> getMembers() {
		return _members;
	}

	public void setMembers(Set<Long> members) {
		_members = members;
	}

}
