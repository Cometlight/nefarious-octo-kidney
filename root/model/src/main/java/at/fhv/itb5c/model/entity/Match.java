package at.fhv.itb5c.model.entity;

import java.time.LocalDateTime;
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
	private Object _teamOne;
	@Column(name = "teamTwo", nullable = false)
	private Object _teamTwo;
	@Column(name = "startDate", nullable = false)
	private Date _persistStartDate;
	@Column(name = "resultTeamOne", nullable = false)
	private Integer _resultTeamOne;
	@Column(name = "resultTeamTwo", nullable = false)
	private Integer _resultTeamTwo;
	@Transient
	private LocalDateTime _startDate;

	public Match() {
		
	}

	public Object getTeamOne() {
		return _teamOne;
	}

	public void setTeamOne(Object teamOne) {
		_teamOne = teamOne;
	}

	public Object getTeamTwo() {
		return _teamTwo;
	}

	public void setTeamTwo(Object teamTwo) {
		_teamTwo = teamTwo;
	}

	public LocalDateTime getStartDate() {
		return _startDate;
	}

	public void setStartDate(LocalDateTime startDate) {
		_startDate = startDate;
	}

	public Integer getResultTeamOne() {
		return _resultTeamOne;
	}

	public void setResultTeamOne(Integer resultTeamOne) {
		_resultTeamOne = resultTeamOne;
	}

	public Integer getResultTeamTwo() {
		return _resultTeamTwo;
	}

	public void setResultTeamTwo(Integer resultTeamTwo) {
		_resultTeamTwo = resultTeamTwo;
	}

	@PrePersist
	private void persist() {
		if (_startDate != null) {
			_persistStartDate = Date.from(_startDate.atZone(ZoneId.systemDefault()).toInstant());
		}
	}

	@PostLoad
	private void load() {
		if (_persistStartDate != null) {
			_startDate = _persistStartDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
		}
	}

	@Override
	public String toString() {
		return "Match [_teamOne=" + _teamOne + ", _teamTwo=" + _teamTwo + ", _persistStartDate=" + _persistStartDate
				+ ", _resultTeamOne=" + _resultTeamOne + ", _resultTeamTwo=" + _resultTeamTwo + ", _startDate="
				+ _startDate + "]";
	}
}
