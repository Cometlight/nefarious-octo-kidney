package at.fhv.itb5c.rmi.server.rmiclasses;

import java.rmi.RemoteException;
import java.util.Map;
import java.util.Set;

import at.fhv.itb5c.commons.dto.rmi.ITeamRMI;
import at.fhv.itb5c.commons.enums.TypeOfSport;

public class TeamRMI extends BaseRMI implements ITeamRMI {
	private static final long serialVersionUID = 1L;

	private String _name;
	private TypeOfSport _typeOfSport;
	private Long _departmentId;
	private Long _coachId;
	private Long _leagueId;
	private Set<Long> _members;
	private Map<Long, Boolean> _memberStatus;
	
	public TeamRMI() throws RemoteException {
		super();
	}
	
	@Override
	public String getName() throws RemoteException {
		return _name;
	}

	
	@Override
	public void setName(String name) throws RemoteException {
		_name = name;
	}
	
	@Override
	public TypeOfSport getTypeOfSport() throws RemoteException {
		return _typeOfSport;
	}
	
	@Override
	public void setTypeOfSport(TypeOfSport typeOfSport) throws RemoteException {
		_typeOfSport = typeOfSport;
	}
	
	@Override
	public Long getDepartmentId() throws RemoteException {
		return _departmentId;
	}
	
	@Override
	public void setDepartmentId(Long departmentId) throws RemoteException {
		_departmentId = departmentId;
	}
	
	@Override
	public Long getCoachId() throws RemoteException {
		return _coachId;
	}
	
	@Override
	public void setCoachId(Long coachId) throws RemoteException {
		_coachId = coachId;
	}
	
	@Override
	public Long getLeagueId() throws RemoteException {
		return _leagueId;
	}
	
	@Override
	public void setLeagueId(Long leagueId) throws RemoteException {
		_leagueId = leagueId;
	}
	
	@Override
	public Set<Long> getMemberIds() throws RemoteException {
		return _members;
	}
	
	@Override
	public void setMemberIds(Set<Long> memberIds) throws RemoteException {
		_members = memberIds;
	}
	
	@Override
	public String toString(){
		return _name;
	}
	
	@Override
	public Map<Long, Boolean> getMemberStatus() {
		return _memberStatus;
	}

	@Override
	public void setMemberStatus(Map<Long, Boolean> memberStatus) {
		_memberStatus = memberStatus;
	}
	
	/**
	 * @param userId must be contained in {@link #_members}
	 * @param status null = undefined, true = accepted, false = declined
	 */
	@Override
	public void setMemberStatus(Long userID, Boolean status) {
		if(_members.contains(userID)) {
			_memberStatus.put(userID, status);
		}
	}
}
