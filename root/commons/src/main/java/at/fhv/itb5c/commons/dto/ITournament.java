package at.fhv.itb5c.commons.dto;

import java.time.LocalDate;
import java.util.Set;

public interface ITournament {
	void setId(Long id) throws Exception;

	Long getId() throws Exception;

	void setVersion(Long version) throws Exception;

	Long getVersion() throws Exception;

	String getName() throws Exception;

	void setName(String name) throws Exception;

	double getFee() throws Exception;

	void setFee(double fee) throws Exception;

	ITeam getHomeTeam() throws Exception;

	void setHomeTeam(ITeam homeTeam) throws Exception;

	Set<String> getGuestTeams() throws Exception;

	void setGuestTeams(Set<String> guestTeams) throws Exception;

	LocalDate getDate() throws Exception;

	void setDate(LocalDate date) throws Exception;

	Set<? extends IMatch> getMatches() throws Exception;

	void setMatches(Set<IMatch> matches) throws Exception;

}
