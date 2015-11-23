package at.fhv.itb5c.model.entity;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
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
	private Set<Long> _memberIds = new HashSet<>();
	
	@Column(name = "memberStatus", nullable = true)
	/**
	 * Long ... userID
	 * Boolean ... null = undefined, true = accepted, false = declined
	 */
	private Map<Long /*userID*/, Boolean> _memberStatus = new HashMap<>();

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

	public Set<Long> getMemberIds() {
		return _memberIds;
	}

	public void setMemberIds(Set<Long> memberIds) {
		_memberIds = memberIds;
	}

	public Map<Long, Boolean> getMemberStatus() {
		return _memberStatus;
	}

	public void setMemberStatus(Map<Long, Boolean> memberStatus) {
		_memberStatus = memberStatus;
	}
	
	/**
	 * @param userId must be contained in {@link #_memberIds}
	 * @param status null = undefined, true = accepted, false = declined
	 */
	public void setMemberStatus(Long userID, Boolean status) {
		if(_memberIds.contains(userID)) {
			_memberStatus.put(userID, status);
		}
	}
}
