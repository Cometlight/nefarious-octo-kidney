package at.fhv.itb5c.commons.dto.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.time.LocalDate;

import at.fhv.itb5c.commons.dto.IMatch;
import at.fhv.itb5c.commons.dto.ITeam;

public interface IMatchRMI extends Remote, IMatch {
	@Override
	void setId(Long id) throws RemoteException;

	@Override
	Long getId() throws RemoteException;

	@Override
	void setVersion(Long version) throws RemoteException;

	@Override
	Long getVersion() throws RemoteException;

	@Override
	ITeamRMI getTeamOne() throws RemoteException;

	@Override
	void setTeamOne(ITeam teamOne) throws RemoteException;

	@Override
	ITeamRMI getTeamTwo() throws RemoteException;

	@Override
	void setTeamTwo(ITeam teamTwo) throws RemoteException;

	@Override
	LocalDate getStartDate() throws RemoteException;

	@Override
	void setStartDate(LocalDate startDate) throws RemoteException;

	@Override
	String getResult() throws RemoteException;

	@Override
	void setResult(String result) throws RemoteException;
}
