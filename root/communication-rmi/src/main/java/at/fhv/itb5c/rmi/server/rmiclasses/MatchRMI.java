package at.fhv.itb5c.rmi.server.rmiclasses;

import java.rmi.RemoteException;
import java.time.LocalDateTime;

import at.fhv.itb5c.commons.dto.rmi.IMatchRMI;

public class MatchRMI extends BaseRMI implements IMatchRMI {
	private static final long serialVersionUID = 1L;
	
	private Object _teamOne;
	private Object _teamTwo;
	private Integer _resultTeamOne;
	private Integer _resultTeamTwo;
	private LocalDateTime _startDate;

	public MatchRMI() throws RemoteException {
		super();
	}

	public Object getTeamOne() throws RemoteException {
		return _teamOne;
	}

	public void setTeamOne(Object teamOne) throws RemoteException {
		_teamOne = teamOne;
	}

	public Object getTeamTwo() throws RemoteException {
		return _teamTwo;
	}

	public void setTeamTwo(Object teamTwo) throws RemoteException {
		_teamTwo = teamTwo;
	}

	public Integer getResultTeamOne() throws RemoteException {
		return _resultTeamOne;
	}

	public void setResultTeamOne(Integer resultTeamOne) throws RemoteException {
		_resultTeamOne = resultTeamOne;
	}

	public Integer getResultTeamTwo() throws RemoteException {
		return _resultTeamTwo;
	}

	public void setResultTeamTwo(Integer resultTeamTwo) throws RemoteException {
		_resultTeamTwo = resultTeamTwo;
	}

	public LocalDateTime getStartDate() throws RemoteException {
		return _startDate;
	}

	public void setStartDate(LocalDateTime startDate) throws RemoteException {
		_startDate = startDate;
	}

}
