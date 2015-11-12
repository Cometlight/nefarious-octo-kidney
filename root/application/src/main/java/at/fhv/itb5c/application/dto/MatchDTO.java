package at.fhv.itb5c.application.dto;

import java.time.LocalDate;
import java.util.Date;

public class MatchDTO extends BaseDTO {
	private Object _teamOne;
	private Object _teamTwo;
	private Date _persistStartDate;
	private Integer _resultTeamOne;
	private Integer _resultTeamTwo;
	private LocalDate _startDate;

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

	public Date getPersistStartDate() {
		return _persistStartDate;
	}

	public void setPersistStartDate(Date persistStartDate) {
		_persistStartDate = persistStartDate;
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

	public LocalDate getStartDate() {
		return _startDate;
	}

	public void setStartDate(LocalDate startDate) {
		_startDate = startDate;
	}

}
