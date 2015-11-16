package at.fhv.itb5c.rmi.server.rmiclasses;

import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.Set;

import at.fhv.itb5c.commons.dto.rmi.ITournamentRMI;

public class TournamentRMI extends BaseRMI implements ITournamentRMI{
	private static final long serialVersionUID = 1L;

	private String _name;
	private Double _fee;
	private Set<Long> _homeTeams;
	private Set<String> _guestTeams;
	private Set<Long> _matches;
	private LocalDate _date;
	private Long _departmentId;

	public TournamentRMI() throws RemoteException {
		super();
	}

	public String getName() throws RemoteException {
		return _name;
	}

	public void setName(String name) throws RemoteException {
		_name = name;
	}

	public Double getFee() throws RemoteException {
		return _fee;
	}

	public void setFee(Double fee) throws RemoteException {
		_fee = fee;
	}

	public Set<Long> getHomeTeamsIds() throws RemoteException {
		return _homeTeams;
	}

	public void setHomeTeamsIds(Set<Long> homeTeams) throws RemoteException {
		_homeTeams = homeTeams;
	}

	public Set<String> getGuestTeams() throws RemoteException {
		return _guestTeams;
	}

	public void setGuestTeams(Set<String> guestTeams) throws RemoteException {
		_guestTeams = guestTeams;
	}

	public LocalDate getDate() throws RemoteException {
		return _date;
	}

	public void setDate(LocalDate date) throws RemoteException {
		_date = date;
	}

	public Long getDepartmentId() throws RemoteException {
		return _departmentId;
	}

	public void setDepartmentId(Long departmentId) throws RemoteException {
		_departmentId = departmentId;
	}

	@Override
	public Set<Long> getMatchesIds() throws RemoteException {
		return _matches;
	}

	@Override
	public void setMatchesIds(Set<Long> matches) throws RemoteException {
		_matches = matches;
	}

}
