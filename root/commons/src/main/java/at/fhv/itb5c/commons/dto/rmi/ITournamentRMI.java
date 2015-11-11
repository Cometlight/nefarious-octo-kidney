package at.fhv.itb5c.commons.dto.rmi;

import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.Set;

public interface ITournamentRMI extends IBaseRMI {
	String getName() throws RemoteException;

	void setName(String name) throws RemoteException;

	Double getFee() throws RemoteException;

	void setFee(Double fee) throws RemoteException;

	Set<Long> getHomeTeamsIds() throws RemoteException;

	void setHomeTeamsIds(Set<Long> homeTeam) throws RemoteException;

	Set<String> getGuestTeams() throws RemoteException;

	void setGuestTeams(Set<String> guestTeams) throws RemoteException;

	LocalDate getDate() throws RemoteException;

	void setDate(LocalDate date) throws RemoteException;

	Set<Long> getMatchesIds() throws RemoteException;

	void setMatchesIds(Set<Long> matches) throws RemoteException;

	Long getDepartmentId() throws RemoteException;

	void setDepartmentId(Long departmentId) throws RemoteException;
}
