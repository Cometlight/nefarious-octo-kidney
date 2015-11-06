package at.fhv.itb5c.commons.dto.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.Set;

import at.fhv.itb5c.commons.dto.IMatch;
import at.fhv.itb5c.commons.dto.ITeam;
import at.fhv.itb5c.commons.dto.ITournament;

public interface ITournamentRMI extends Remote, ITournament {
	@Override
	void setId(Long id) throws RemoteException;

	@Override
	Long getId() throws RemoteException;

	@Override
	void setVersion(Long version) throws RemoteException;

	@Override
	Long getVersion() throws RemoteException;

	@Override
	String getName() throws RemoteException;

	@Override
	void setName(String name) throws RemoteException;

	@Override
	double getFee() throws RemoteException;

	@Override
	void setFee(double fee) throws RemoteException;

	@Override
	ITeamRMI getHomeTeam() throws RemoteException;

	@Override
	void setHomeTeam(ITeam homeTeam) throws RemoteException;

	@Override
	Set<String> getGuestTeams() throws RemoteException;

	@Override
	void setGuestTeams(Set<String> guestTeams) throws RemoteException;

	@Override
	LocalDate getDate() throws RemoteException;

	@Override
	void setDate(LocalDate date) throws RemoteException;

	@Override
	Set<IMatchRMI> getMatches() throws RemoteException;

	@Override
	void setMatches(Set<IMatch> matches) throws RemoteException;
}
