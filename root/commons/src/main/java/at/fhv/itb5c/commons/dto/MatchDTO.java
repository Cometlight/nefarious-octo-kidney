package at.fhv.itb5c.commons.dto;

import java.time.LocalDateTime;

public class MatchDTO extends BaseDTO {
	private Object _teamOne;
	private Object _teamTwo;
	private Integer _resultTeamOne;
	private Integer _resultTeamTwo;
	private LocalDateTime _startDate;

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

	public LocalDateTime getStartDate() {
		return _startDate;
	}

	public void setStartDate(LocalDateTime startDate) {
		_startDate = startDate;
	}

}
