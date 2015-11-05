package at.fhv.itb5c.model.entity;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.Transient;

@Entity
public class Match extends PersistableObject {
	@Column(name = "teamOne", nullable = false)
	private Team _teamOne;
	@Column(name = "teamTwo", nullable = false)
	private Team _teamTwo;
	@Column(name = "startDate", nullable = false)
	private Date _persistStartDate;
	@Transient
	private LocalDate _startDate;

	public Match() {

	}

	public Team getTeamOne() {
		return _teamOne;
	}

	public void setTeamOne(Team teamOne) {
		_teamOne = teamOne;
	}

	public Team getTeamTwo() {
		return _teamTwo;
	}

	public void setTeamTwo(Team teamTwo) {
		_teamTwo = teamTwo;
	}

	public LocalDate getStartDate() {
		return _startDate;
	}

	public void setStartDate(LocalDate startDate) {
		_startDate = startDate;
	}

	@PrePersist
	private void persist() {
		if (_startDate != null) {
			_persistStartDate = Date.from(_startDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		}
	}

	@PostLoad
	private void load() {
		if (_persistStartDate != null) {
			_startDate = _persistStartDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		}
	}
}
