package at.fhv.itb5c.commons.dto;

import java.time.LocalDate;

public interface IMatch {
	void setId(Long id) throws Exception;

	Long getId() throws Exception;

	void setVersion(Long version) throws Exception;

	Long getVersion() throws Exception;

	ITeam getTeamOne() throws Exception;

	void setTeamOne(ITeam teamOne) throws Exception;

	ITeam getTeamTwo() throws Exception;

	void setTeamTwo(ITeam teamTwo) throws Exception;

	LocalDate getStartDate() throws Exception;

	void setStartDate(LocalDate startDate) throws Exception;

	String getResult() throws Exception;

	void setResult(String result) throws Exception;

}
