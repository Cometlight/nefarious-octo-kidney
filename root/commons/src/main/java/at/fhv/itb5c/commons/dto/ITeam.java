package at.fhv.itb5c.commons.dto;

import java.rmi.Remote;
import java.util.Set;

import at.fhv.itb5c.commons.enums.TypeOfSport;

public interface ITeam extends Remote {
	void setId(Long id) throws Exception;
	Long getId() throws Exception;

	void setVersion(Long version) throws Exception;
	Long getVersion() throws Exception;
	
	String getName() throws Exception;
	void setName(String name) throws Exception;

	TypeOfSport getTypeOfSport() throws Exception;
	void setTypeOfSport(TypeOfSport typeOfSport) throws Exception;
	
	IDepartment getDepartment() throws Exception;
	void setDepartment(IDepartment department) throws Exception;
	
	IUser getCoach() throws Exception;
	void setCoach(IUser coach) throws Exception;

	int getLeague() throws Exception;
	void setLeague(int league) throws Exception;

	Set<IUser> getMembers() throws Exception;
	void setMembers(Set<IUser> members) throws Exception;
}