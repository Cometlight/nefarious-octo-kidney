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
	@Column(name = "date", nullable = false)
	private Date _persistDate;
	@Column(name = "fee", nullable = true)
	private double _fee;
	@Column(name = "homeTeam", nullable = false)
	private Team _homeTeam;
	@Column(name = "guestTeams", nullable = false)
	private Set<String> _guestTeams;
	@Column(name = "matches", nullable = false)
	private Set<Long> _matches;
	@Transient
	private LocalDate _date;

	public Tournament() {
	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	public double getFee() {
		return _fee;
	}

	public void setFee(double fee) {
		_fee = fee;
	}

	public Team getHomeTeam() {
		return _homeTeam;
	}

	public void setHomeTeam(Team homeTeam) {
		_homeTeam = homeTeam;
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

	public Set<Long> getMatches() {
		return _matches;
	}

	public void setMatches(Set<Long> matches) {
		_matches = matches;
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
