package at.fhv.itb5c.commons.dto.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.time.LocalDate;

import at.fhv.itb5c.commons.dto.IMatch;

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
	Object getTeamOne() throws RemoteException;

	@Override
	void setTeamOne(Object teamOne) throws RemoteException;

	@Override
	Object getTeamTwo() throws RemoteException;

	@Override
	void setTeamTwo(Object teamTwo) throws RemoteException;

	@Override
	LocalDate getStartDate() throws RemoteException;

	@Override
	void setStartDate(LocalDate startDate) throws RemoteException;

	@Override
	Integer getResultOne() throws Exception;

	@Override
	void setResultOne(Integer result) throws Exception;

	@Override
	Integer getResultTwo() throws Exception;

	@Override
	void setResultTwo(Integer result) throws Exception;
}
