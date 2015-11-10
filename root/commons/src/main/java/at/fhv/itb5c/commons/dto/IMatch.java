package at.fhv.itb5c.commons.dto;

import java.time.LocalDate;

public interface IMatch {
	void setId(Long id) throws Exception;

	Long getId() throws Exception;

	void setVersion(Long version) throws Exception;

	Long getVersion() throws Exception;

	Object getTeamOne() throws Exception;

	void setTeamOne(Object teamOne) throws Exception;

	Object getTeamTwo() throws Exception;

	void setTeamTwo(Object teamTwo) throws Exception;

	LocalDate getStartDate() throws Exception;

	void setStartDate(LocalDate startDate) throws Exception;

	Integer getResultOne() throws Exception;

	void setResultOne(Integer result) throws Exception;

	Integer getResultTwo() throws Exception;

	void setResultTwo(Integer result) throws Exception;
}
