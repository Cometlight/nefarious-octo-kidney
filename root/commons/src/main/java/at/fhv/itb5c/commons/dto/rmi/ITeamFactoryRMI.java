package at.fhv.itb5c.commons.dto.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import at.fhv.itb5c.commons.dto.ITeam;
import at.fhv.itb5c.commons.dto.ITeamFactory;
import at.fhv.itb5c.commons.enums.TypeOfSport;

public interface ITeamFactoryRMI extends ITeamFactory, Remote {
	@Override
	ITeamRMI createTeam() throws RemoteException;
	
	@Override
	ITeamRMI saveOrUpdate(ITeam team) throws RemoteException;
	
	@Override
	List<ITeamRMI> findTeams(String name, TypeOfSport typeOfSport, Long departmentId, Long leagueId) throws RemoteException;
}
