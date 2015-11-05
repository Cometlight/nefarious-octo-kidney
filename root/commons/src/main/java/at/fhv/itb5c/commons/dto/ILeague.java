package at.fhv.itb5c.commons.dto;

import at.fhv.itb5c.commons.enums.TypeOfSport;

public interface ILeague {
	void setId(Long id) throws Exception;

	Long getId() throws Exception;

	void setVersion(Long version) throws Exception;

	Long getVersion() throws Exception;

	void setName(String name) throws Exception;

	String getName() throws Exception;

	TypeOfSport getTypeOfSport() throws Exception;

	void setTypeOfSport(TypeOfSport typeOfSport) throws Exception;
}
