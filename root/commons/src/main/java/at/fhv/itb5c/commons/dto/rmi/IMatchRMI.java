package at.fhv.itb5c.commons.dto.rmi;

import java.rmi.RemoteException;
import java.time.LocalDateTime;

public interface IMatchRMI extends IBaseRMI {
	Object getTeamOne() throws RemoteException;

	void setTeamOne(Object teamOne) throws RemoteException;

	Object getTeamTwo() throws RemoteException;

	void setTeamTwo(Object teamTwo) throws RemoteException;

	LocalDateTime getStartDate() throws RemoteException;

	void setStartDate(LocalDateTime startDate) throws RemoteException;

	Integer getResultTeamOne() throws RemoteException;

	void setResultTeamOne(Integer resultTeamOne) throws RemoteException;

	Integer getResultTeamTwo() throws RemoteException;

	void setResultTeamTwo(Integer resultTeamTwo) throws RemoteException;
}
