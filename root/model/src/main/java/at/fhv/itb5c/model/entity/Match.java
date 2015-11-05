package at.fhv.itb5c.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Match extends PersistableObject {
	@Column(name = "teamA", nullable = false)
	private Team _teamA;

	@Column(name = "teamB", nullable = false)
	private Team _teamB;

	@Column(name = "result", nullable = true)
	private String _result;

	public Team getTeamA() {
		return _teamA;
	}

	public void setTeamA(Team teamA) {
		_teamA = teamA;
	}

	public Team getTeamB() {
		return _teamB;
	}

	public void setTeamB(Team teamB) {
		_teamB = teamB;
	}

	public String getResult() {
		return _result;
	}

	public void setResult(String result) {
		_result = result;
	}
}
