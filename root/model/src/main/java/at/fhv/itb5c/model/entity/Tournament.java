package at.fhv.itb5c.model.entity;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.Transient;

@Entity
public class Tournament extends PersistableObject {
	@Column(name = "name", nullable = false)
	private String _name;
	@Column(name = "date", nullable = false)
	private Date _persistDate;
	@Column(name = "fee", nullable = true)
	private Double _fee;
	@Column(name = "homeTeam", nullable = false)
	private Set<Long> _homeTeams = new HashSet<>();
	@Column(name = "guestTeams", nullable = false)
	private Set<String> _guestTeams = new HashSet<>();
	@Column(name = "matches", nullable = false)
	private Set<Long> _matches = new HashSet<>();
	@Column(name = "departmentId", nullable = false)
	private Long _departmentId;
	@Transient
	private LocalDate _date;

	public Tournament() {
		_homeTeams = new HashSet<>();
		_guestTeams = new HashSet<>();
		_matches = new HashSet<>();
	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	public Double getFee() {
		return _fee;
	}

	public void setFee(Double fee) {
		_fee = fee;
	}

	public Set<Long> getHomeTeamsIds() {
		return _homeTeams;
	}

	public void setHomeTeamsIds(Set<Long> homeTeam) {
		_homeTeams = homeTeam;
	}

	public Set<String> getGuestTeams() {
		return _guestTeams;
	}

	public void setGuestTeams(Set<String> guestTeams) {
		_guestTeams = guestTeams;
	}

	public LocalDate getDate() {
		return _date;
	}

	public void setDate(LocalDate date) {
		_date = date;
	}

	public Set<Long> getMatchesIds() {
		return _matches;
	}

	public void setMatchesIds(Set<Long> matches) {
		_matches = matches;
	}

	public Long getDepartmentId() {
		return _departmentId;
	}

	public void setDepartmentId(Long departmentId) {
		_departmentId = departmentId;
	}

	@PrePersist
	private void persist() {
		if (_date != null) {
			_persistDate = Date.from(_date.atStartOfDay(ZoneId.systemDefault()).toInstant());
		}
	}

	@PostLoad
	private void load() {
		if (_persistDate != null) {
			_date = _persistDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		}
	}
}
