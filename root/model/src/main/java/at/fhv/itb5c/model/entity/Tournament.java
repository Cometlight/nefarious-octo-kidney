package at.fhv.itb5c.model.entity;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
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

	@Column(name = "startDate", nullable = false)
	private Date _persistStartDate;

	@Transient
	private LocalDate _startDate;

	@Column(name = "fee", nullable = true)
	private double _fee;

	@Column(name = "attendees", nullable = true)
	private Set<Team> _attendees;

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

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	public LocalDate getStartDate() {
		return _startDate;
	}

	public void setStartDate(LocalDate startDate) {
		_startDate = startDate;
	}

	public double getFee() {
		return _fee;
	}

	public void setFee(double fee) {
		_fee = fee;
	}

	public Set<Team> getAttendees() {
		return _attendees;
	}

	public void setAttendees(Set<Team> attendees) {
		_attendees = attendees;
	}

}
